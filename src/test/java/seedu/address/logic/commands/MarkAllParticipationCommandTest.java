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

public class MarkAllParticipationCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_allFilteredPersons_markedSuccessfully() {
        Index weekNumber = Index.fromOneBased(4);
        MarkAllParticipationCommand markAllParticipationCommand = new MarkAllParticipationCommand(weekNumber);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        List<Person> updatedPersons = model.getFilteredPersonList().stream()
                .map(person -> {
                    List<Integer> oldParticipationScores = person.getParticipationScores();
                    List<Integer> newParticipationScores = IntStream.range(0, oldParticipationScores.size())
                            .mapToObj(index -> index == weekNumber.getZeroBased() - 3
                                ? oldParticipationScores.get(index) + 1 : oldParticipationScores.get(index))
                            .collect(Collectors.toList());
                    return new PersonBuilder(person)
                            .withParticipationScores(newParticipationScores)
                            .build();
                })
                .collect(Collectors.toList());

        updatedPersons.forEach(person -> expectedModel.setPerson(person, person));

        String expectedMessage = MarkAllParticipationCommand.MESSAGE_MARK_ALL_SUCCESS;
        assertCommandSuccess(markAllParticipationCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidWeekNumber_throwsCommandException() {
        Index invalidWeekNumber = Index.fromZeroBased(15);
        MarkAllParticipationCommand markAllParticipationCommand = new MarkAllParticipationCommand(invalidWeekNumber);

        assertCommandFailure(markAllParticipationCommand, model, Messages.MESSAGE_INVALID_WEEK);
    }

}
