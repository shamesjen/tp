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
 * Marks the tutorial attendance of all filtered students
 */

public class MarkAllAttendanceCommand extends Command {

    public static final String COMMAND_WORD = "markalla";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the attendance of all filtered students \n"
            + "Parameters: WEEK (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 3 ";

    public static final String MESSAGE_ARGUMENTS = "Week Number: %2$d";
    public static final String MESSAGE_MARK_ALL_ATTENDANCE_SUCCESS = "Marked all attendance for week %1$d";
    private static final int FIRST_WEEK = 3;
    private static final int LAST_WEEK = 13;
    private final Index weekNumber;

    /**
     * @param weekNumber week number to mark the person with
     */
    public MarkAllAttendanceCommand(Index weekNumber) {
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
        for (Person personToMark : lastShownList) {
            List<Integer> newAttendanceScores = getNewAttendanceScores(personToMark);
            Person updatedPerson = createMarkedPerson(personToMark, newAttendanceScores);
            model.setPerson(personToMark, updatedPerson);
        }
        if (model.shouldPurgeAddressBook()) {
            model.purgeAddressBook();
        }
        CommandResult markAllAttendanceCommandResult =
                new CommandResult(String.format(MESSAGE_MARK_ALL_ATTENDANCE_SUCCESS, weekNumber.getZeroBased()));
        model.commitAddressBook(markAllAttendanceCommandResult);
        return markAllAttendanceCommandResult;
    }

    /**
     * Returns a list of updated attendance scores for {@code personToMark}.
     *
     * @param personToMark the person to mark attendance for
     * @return a list of updated attendance scores
     */
    private List<Integer> getNewAttendanceScores(Person personToMark) {
        List<Integer> oldAttendanceScores = personToMark.getAttendanceScores();
        List<Integer> newAttendanceScores = new ArrayList<>();
        int weekIndex = weekNumber.getZeroBased() - 3;

        for (int i = 0; i < oldAttendanceScores.size(); i++) {
            if (i == weekIndex) {
                newAttendanceScores.add(1);
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
    private static Person createMarkedPerson(Person personToMark, List<Integer> updatedAttendanceScores) {
        assert personToMark != null;

        Name name = personToMark.getName();
        MatricNumber matricNumber = personToMark.getMatricNumber();
        Email email = personToMark.getEmail();
        TelegramHandle telegramHandle = personToMark.getTelegramHandle();
        Set<Tag> tags = personToMark.getTags();
        List<Assignment> assignments = personToMark.getAssignments();

        List<Integer> participationScores = personToMark.getParticipationScores();

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
        if (!(other instanceof MarkAllAttendanceCommand)) {
            return false;
        }

        // state check
        MarkAllAttendanceCommand e = (MarkAllAttendanceCommand) other;
        return weekNumber.equals(e.weekNumber);
    }

}
