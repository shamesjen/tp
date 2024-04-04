package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_WEEK;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class UnmarkAllParticipationCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_allFilteredPersons_unmarkedSuccessfully() throws CommandException {
        Index weekNumber = Index.fromOneBased(4);
        CommandResult markAllParticipationCommandResult = new MarkAllParticipationCommand(weekNumber).execute(model);
        UnmarkAllParticipationCommand unmarkAllParticipationCommand = new UnmarkAllParticipationCommand(weekNumber);

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

        String expectedMessage = String.format(UnmarkAllParticipationCommand.MESSAGE_UNMARK_ALL_PARTICIPATION_SUCCESS,
                weekNumber.getZeroBased());
        assertCommandSuccess(unmarkAllParticipationCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidWeekNumber_throwsCommandException() {
        Index invalidWeekNumber = Index.fromZeroBased(15);
        UnmarkAllParticipationCommand unmarkAllParticipationCommand = new UnmarkAllParticipationCommand(
                invalidWeekNumber);

        assertCommandFailure(unmarkAllParticipationCommand, model, Messages.MESSAGE_INVALID_WEEK);
    }

    @Test
    public void execute_participationAlreadyZero_failure() {
        Person personToUnmark = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        UnmarkAllParticipationCommand unmarkAllParticipationCommand = new UnmarkAllParticipationCommand(
                INDEX_FIRST_WEEK);

        assertCommandFailure(unmarkAllParticipationCommand, model,
                String.format(UnmarkAllParticipationCommand.MESSAGE_PARTICIPATION_ALREADY_ZERO,
                        personToUnmark.getName(), INDEX_FIRST_WEEK.getZeroBased()));
    }
}
