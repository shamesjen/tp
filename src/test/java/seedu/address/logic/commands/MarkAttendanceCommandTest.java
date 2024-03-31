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
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class MarkAttendanceCommandTest {

    private static final List<Integer> attendanceScoresStub =
            new ArrayList<>(Arrays.asList(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexAndWeekNumber_success() {
        Person personToMark = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person markedPerson = new PersonBuilder(personToMark)
                .withAttendanceScores(attendanceScoresStub).build();
        MarkAttendanceCommand markAttendanceCommand = new MarkAttendanceCommand(INDEX_FIRST_PERSON, INDEX_FIRST_WEEK);

        String expectedMessage = String.format(MarkAttendanceCommand.MESSAGE_MARK_ATTENDANCE_SUCCESS,
                markedPerson.getName(), INDEX_FIRST_WEEK.getOneBased());

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), markedPerson);

        assertCommandSuccess(markAttendanceCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexAndValidWeek_failure() {
        Index outOfBoundIndex = Index.fromZeroBased(model.getFilteredPersonList().size());
        MarkAttendanceCommand markAttendanceCommand = new MarkAttendanceCommand(outOfBoundIndex, INDEX_FIRST_WEEK);

        assertCommandFailure(markAttendanceCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexAndInvalidWeek_failure() {
        Index weekLessThanThree = Index.fromZeroBased(2);
        MarkAttendanceCommand markAttendanceCommandFirst =
                new MarkAttendanceCommand(INDEX_FIRST_PERSON, weekLessThanThree);

        assertCommandFailure(markAttendanceCommandFirst, model, Messages.MESSAGE_INVALID_WEEK);

        Index weekGreaterThanThirteen = Index.fromZeroBased(14);
        MarkAttendanceCommand markAttendanceCommandSecond =
                new MarkAttendanceCommand(INDEX_FIRST_PERSON, weekGreaterThanThirteen);

        assertCommandFailure(markAttendanceCommandSecond, model, Messages.MESSAGE_INVALID_WEEK);
    }

}
