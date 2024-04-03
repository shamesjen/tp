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

public class UnmarkAllAttendanceCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_allFilteredPersons_markedSuccessfully() {
        Index weekNumber = Index.fromOneBased(4); // Week 4
        UnmarkAllAttendanceCommand unmarkAllAttendanceCommand = new UnmarkAllAttendanceCommand(weekNumber);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        List<Person> updatedPersons = model.getFilteredPersonList().stream()
                .map(person -> {
                    List<Integer> newAttendanceScores = IntStream.rangeClosed(1, 13) // Assuming weeks 1-13
                            .mapToObj(week -> week == weekNumber.getOneBased() ? 1 : 0)
                            .collect(Collectors.toList());
                    return new PersonBuilder(person)
                            .withAttendanceScores(newAttendanceScores)
                            .build();
                })
                .collect(Collectors.toList());

        updatedPersons.forEach(person -> expectedModel.setPerson(person, person));

        String expectedMessage = String.format(UnmarkAllAttendanceCommand.MESSAGE_UNMARK_ALL_ATTENDANCE_SUCCESS,
                weekNumber.getZeroBased());
        assertCommandSuccess(unmarkAllAttendanceCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidWeekNumber_throwsCommandException() {
        Index invalidWeekNumber = Index.fromZeroBased(14);
        UnmarkAllAttendanceCommand unmarkAllAttendanceCommand = new UnmarkAllAttendanceCommand(invalidWeekNumber);

        assertCommandFailure(unmarkAllAttendanceCommand, model, Messages.MESSAGE_INVALID_WEEK);
    }

}
