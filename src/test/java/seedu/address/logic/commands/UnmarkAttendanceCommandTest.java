package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
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

public class UnmarkAttendanceCommandTest {

    private static final List<Integer> attendanceScoresStub =
            new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexAndWeekNumber_success() throws CommandException {
        Person personToUnmark = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person unmarkedPerson = new PersonBuilder(personToUnmark)
                .withAttendanceScores(attendanceScoresStub).build();
        CommandResult markAttendanceCommandResult =
                new MarkAttendanceCommand(INDEX_FIRST_PERSON, INDEX_FIRST_WEEK).execute(model);
        UnmarkAttendanceCommand unmarkAttendanceCommand = new UnmarkAttendanceCommand(
                INDEX_FIRST_PERSON, INDEX_FIRST_WEEK);

        String expectedMessage = String.format(UnmarkAttendanceCommand.MESSAGE_UNMARK_ATTENDANCE_SUCCESS,
                unmarkedPerson.getName(), INDEX_FIRST_WEEK.getZeroBased());

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), unmarkedPerson);

        assertCommandSuccess(unmarkAttendanceCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexAndValidWeek_failure() {
        Index outOfBoundIndex = Index.fromZeroBased(model.getFilteredPersonList().size());
        UnmarkAttendanceCommand unmarkAttendanceCommand = new UnmarkAttendanceCommand(
                outOfBoundIndex, INDEX_FIRST_WEEK);

        assertCommandFailure(unmarkAttendanceCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexAndInvalidWeek_failure() {
        Index weekLessThanThree = Index.fromZeroBased(2);
        UnmarkAttendanceCommand unmarkAttendanceCommandFirst =
                new UnmarkAttendanceCommand(INDEX_FIRST_PERSON, weekLessThanThree);

        assertCommandFailure(unmarkAttendanceCommandFirst, model, Messages.MESSAGE_INVALID_WEEK);

        Index weekGreaterThanThirteen = Index.fromZeroBased(14);
        UnmarkAttendanceCommand unmarkAttendanceCommandSecond =
                new UnmarkAttendanceCommand(INDEX_FIRST_PERSON, weekGreaterThanThirteen);

        assertCommandFailure(unmarkAttendanceCommandSecond, model, Messages.MESSAGE_INVALID_WEEK);
    }

    @Test
    public void execute_attendanceAlreadyZero_failure() {
        Person personToUnmark = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        UnmarkAttendanceCommand unmarkAttendanceCommand = new UnmarkAttendanceCommand(
                INDEX_FIRST_PERSON, INDEX_FIRST_WEEK);

        assertCommandFailure(unmarkAttendanceCommand, model,
                String.format(UnmarkAttendanceCommand.MESSAGE_ATTENDANCE_ALREADY_ZERO,
                        personToUnmark.getName(), INDEX_FIRST_WEEK.getZeroBased()));
    }
}
