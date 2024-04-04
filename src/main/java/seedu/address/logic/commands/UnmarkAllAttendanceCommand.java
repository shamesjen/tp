package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Assignment;
import seedu.address.model.person.Email;
import seedu.address.model.person.MatricNumber;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.TelegramHandle;
import seedu.address.model.tag.Tag;

/**
 * Unmarks the tutorial attendance of all filtered students
 */

public class UnmarkAllAttendanceCommand extends Command {

    public static final String COMMAND_WORD = "unmarkalla";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unmarks the attendance of all filtered students \n"
            + "Parameters: WEEK (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 3 ";

    public static final String MESSAGE_ARGUMENTS = "Week Number: %2$d";
    public static final String MESSAGE_UNMARK_ALL_ATTENDANCE_SUCCESS = "Unmarked all attendance for week %1$d";

    public static final String MESSAGE_ATTENDANCE_ALREADY_ZERO = "Attendance for %1$s in Week %2$d is already 0";
    private static final int FIRST_WEEK = 3;
    private static final int LAST_WEEK = 13;
    private final Index weekNumber;

    /**
     * @param weekNumber week number to mark the person with
     */
    public UnmarkAllAttendanceCommand(Index weekNumber) {
        requireAllNonNull(weekNumber);
        this.weekNumber = weekNumber;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (weekNumber.getZeroBased() < FIRST_WEEK | weekNumber.getZeroBased() > LAST_WEEK) {
            throw new CommandException(Messages.MESSAGE_INVALID_WEEK);
        }
        for (Person personToUnmark : lastShownList) {
            if (personToUnmark.getAttendanceScores().get(weekNumber.getZeroBased() - 3) == 0) {
                throw new CommandException(String.format(MESSAGE_ATTENDANCE_ALREADY_ZERO,
                        personToUnmark.getName(), weekNumber.getZeroBased()));
            }
        }
        for (Person personToUnmark : lastShownList) {
            List<Integer> newAttendanceScores = getNewAttendanceScores(personToUnmark);
            Person updatedPerson = createUnmarkedPerson(personToUnmark, newAttendanceScores);
            model.setPerson(personToUnmark, updatedPerson);
        }
        if (model.shouldPurgeAddressBook()) {
            model.purgeAddressBook();
        }
        CommandResult unmarkCommandResult = new CommandResult(String.format(MESSAGE_UNMARK_ALL_ATTENDANCE_SUCCESS,
                weekNumber.getZeroBased()));
        model.commitAddressBook(unmarkCommandResult);
        return unmarkCommandResult;
    }

    /**
     * Returns a list of updated attendance scores for {@code personToUnmark}.
     *
     * @param personToUnmark the person to unmark attendance for
     * @return a list of updated attendance scores
     */
    private List<Integer> getNewAttendanceScores(Person personToUnmark) {
        List<Integer> oldAttendanceScores = personToUnmark.getAttendanceScores();
        List<Integer> newAttendanceScores = new ArrayList<>();
        int weekIndex = weekNumber.getZeroBased() - 3;

        for (int i = 0; i < oldAttendanceScores.size(); i++) {
            if (i == weekIndex) {
                newAttendanceScores.add(0);
            } else {
                newAttendanceScores.add(oldAttendanceScores.get(i));
            }
        }
        return newAttendanceScores;
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToMark}
     * with the updated participation scores. Utilizes the overload Person constructor.
     */
    private static Person createUnmarkedPerson(Person personToUnmark, List<Integer> updatedAttendanceScores) {
        assert personToUnmark != null;

        Name name = personToUnmark.getName();
        MatricNumber matricNumber = personToUnmark.getMatricNumber();
        Email email = personToUnmark.getEmail();
        TelegramHandle telegramHandle = personToUnmark.getTelegramHandle();
        Set<Tag> tags = personToUnmark.getTags();
        List<Assignment> assignments = personToUnmark.getAssignments();

        List<Integer> participationScores = personToUnmark.getParticipationScores();

        return new Person(name, matricNumber, email, telegramHandle,
                tags, assignments, participationScores, updatedAttendanceScores);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UnmarkAllAttendanceCommand)) {
            return false;
        }

        // state check
        UnmarkAllAttendanceCommand e = (UnmarkAllAttendanceCommand) other;
        return weekNumber.equals(e.weekNumber);
    }

}
