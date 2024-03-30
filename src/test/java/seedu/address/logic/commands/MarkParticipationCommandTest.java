package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.MarkParticipationCommand.MESSAGE_MARK_PERSON_SUCCESS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_WEEK;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class MarkParticipationCommandTest {

    private static final List<Integer> participationScoresStub =
            new ArrayList<>(Arrays.asList(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexAndWeekNumber_success() {
        Person personToMark = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person markedPerson = new PersonBuilder(personToMark)
                .withParticipationScores(participationScoresStub).build();
        MarkParticipationCommand markParticipationCommand =
                new MarkParticipationCommand(INDEX_FIRST_PERSON, INDEX_FIRST_WEEK);

        String expectedMessage = String.format(MESSAGE_MARK_PERSON_SUCCESS, markedPerson.getName());

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), markedPerson);

        assertCommandSuccess(markParticipationCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexAndValidWeek_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        MarkParticipationCommand markParticipationCommand =
                new MarkParticipationCommand(outOfBoundIndex, INDEX_FIRST_WEEK);

        assertCommandFailure(markParticipationCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexAndInvalidWeek_failure() {
        Index weekLessThanThree = Index.fromZeroBased(2);
        MarkParticipationCommand markParticipationCommandFirst =
                new MarkParticipationCommand(INDEX_FIRST_PERSON, weekLessThanThree);

        assertCommandFailure(markParticipationCommandFirst, model, Messages.MESSAGE_INVALID_WEEK);

        Index weekGreaterThanThirteen = Index.fromZeroBased(14);
        MarkParticipationCommand markParticipationCommandSecond =
                new MarkParticipationCommand(INDEX_FIRST_PERSON, weekGreaterThanThirteen);

        assertCommandFailure(markParticipationCommandSecond, model, Messages.MESSAGE_INVALID_WEEK);
    }

}
