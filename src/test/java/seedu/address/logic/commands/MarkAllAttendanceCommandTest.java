package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class MarkAllAttendanceCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_allFilteredPersons_markedSuccessfully() {
        Index weekNumber = Index.fromOneBased(1); // Assuming week numbers start from 1
        MarkAllAttendanceCommand markAllAttendanceCommand = new MarkAllAttendanceCommand(weekNumber);

        // Expected model to compare with after marking attendance
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        List<Person> updatedPersons = model.getFilteredPersonList().stream()
                .map(person -> new PersonBuilder(person)
                        .withParticipationScores(IntStream.range(0, 14) // Assuming 14 weeks in total
                                .mapToObj(week -> week == weekNumber.getZeroBased() ? 1 : 0)
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());
        for (int i = 0; i < updatedPersons.size(); i++) {
            expectedModel.setPerson(model.getFilteredPersonList().get(i), updatedPersons.get(i));
        }

        String expectedMessage = MarkAllAttendanceCommand.MESSAGE_MARK_PERSON_SUCCESS;
        assertCommandSuccess(markAllAttendanceCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidWeekNumber_throwsCommandException() {
        Index invalidWeekNumber = Index.fromOneBased(15); // Assuming only 14 weeks
        MarkAllAttendanceCommand markAllAttendanceCommand = new MarkAllAttendanceCommand(invalidWeekNumber);

        assertCommandFailure(markAllAttendanceCommand, model, Messages.MESSAGE_INVALID_WEEK);
    }
}
