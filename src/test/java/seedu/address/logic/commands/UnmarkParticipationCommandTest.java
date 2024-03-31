package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.UnmarkParticipationCommand.MESSAGE_UNMARK_PARTICIPATION_SUCCESS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_WEEK;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;
public class UnmarkParticipationCommandTest {
    private static final List<Integer> PARTICIPATION_SCORES_STUB_ZERO =
            new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
    private static final List<Integer> PARTICIPATION_SCORES_STUB_ONE =
            new ArrayList<>(Arrays.asList(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
    private static final List<Integer> PARTICIPATION_SCORES_STUB_TWO =
            new ArrayList<>(Arrays.asList(2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexAndWeekNumber_success() throws CommandException {
        Person personToUnmark = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person unmarkedPerson = new PersonBuilder(personToUnmark)
                .withParticipationScores(PARTICIPATION_SCORES_STUB_ZERO).build();
        CommandResult markParticipationCommandResult =
                new MarkParticipationCommand(INDEX_FIRST_PERSON, INDEX_FIRST_WEEK).execute(model);
        UnmarkParticipationCommand unmarkParticipationCommand =
                new UnmarkParticipationCommand(INDEX_FIRST_PERSON, INDEX_FIRST_WEEK);

        String expectedMessage = String.format(MESSAGE_UNMARK_PARTICIPATION_SUCCESS, unmarkedPerson.getName(),
                INDEX_FIRST_WEEK.getZeroBased(),
                PARTICIPATION_SCORES_STUB_ZERO.get(INDEX_FIRST_WEEK.getZeroBased() - 3));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), unmarkedPerson);

        assertCommandSuccess(unmarkParticipationCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_scoreGreaterThanOne_success() throws CommandException {
        Person personToUnmark = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person unmarkedPerson = new PersonBuilder(personToUnmark)
                .withParticipationScores(PARTICIPATION_SCORES_STUB_ONE).build();
        CommandResult markParticipationCommandResult =
                new MarkParticipationCommand(INDEX_FIRST_PERSON, INDEX_FIRST_WEEK).execute(model);
        CommandResult markParticipationCommandTwoResult =
                new MarkParticipationCommand(INDEX_FIRST_PERSON, INDEX_FIRST_WEEK).execute(model);
        UnmarkParticipationCommand unmarkParticipationCommand =
                new UnmarkParticipationCommand(INDEX_FIRST_PERSON, INDEX_FIRST_WEEK);

        String expectedMessage = String.format(MESSAGE_UNMARK_PARTICIPATION_SUCCESS, unmarkedPerson.getName(),
                INDEX_FIRST_WEEK.getZeroBased(),
                PARTICIPATION_SCORES_STUB_ONE.get(INDEX_FIRST_WEEK.getZeroBased() - 3));

        ModelManager expectedModelTwo = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModelTwo.setPerson(model.getFilteredPersonList().get(0), unmarkedPerson);

        assertCommandSuccess(unmarkParticipationCommand, model, expectedMessage, expectedModelTwo);
    }

    @Test
    public void execute_invalidIndexAndValidWeek_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        UnmarkParticipationCommand unmarkParticipationCommand =
                new UnmarkParticipationCommand(outOfBoundIndex, INDEX_FIRST_WEEK);

        assertCommandFailure(unmarkParticipationCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexAndInvalidWeek_failure() {
        Index weekLessThanThree = Index.fromZeroBased(2);
        UnmarkParticipationCommand unmarkParticipationCommandFirst =
                new UnmarkParticipationCommand(INDEX_FIRST_PERSON, weekLessThanThree);

        assertCommandFailure(unmarkParticipationCommandFirst, model, Messages.MESSAGE_INVALID_WEEK);

        Index weekGreaterThanThirteen = Index.fromZeroBased(14);
        UnmarkParticipationCommand unmarkParticipationCommandSecond =
                new UnmarkParticipationCommand(INDEX_FIRST_PERSON, weekGreaterThanThirteen);

        assertCommandFailure(unmarkParticipationCommandSecond, model, Messages.MESSAGE_INVALID_WEEK);
    }
}
