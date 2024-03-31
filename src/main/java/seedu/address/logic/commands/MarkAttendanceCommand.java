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
 * Marks the tutorial attendance of a student
 */

public class MarkAttendanceCommand extends Command {

    public static final String COMMAND_WORD = "marka";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the attendance of the person identified "
            + "by the index number used in the last person listing.\n"
            + "Parameters: INDEX (must be a positive integer), WEEK (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 " + "5";

    public static final String MESSAGE_MARK_ATTENDANCE_SUCCESS = "Marked Attendance for: %1$s in Week %2$d";
    private static final int FIRST_WEEK = 3;
    private static final int LAST_WEEK = 13;
    private final Index targetIndex;
    private final Index weekNumber;

    /**
     * @param index of the person in the filtered person list to edit
     * @param weekNumber week number to mark the person with
     */
    public MarkAttendanceCommand(Index index, Index weekNumber) {
        requireAllNonNull(index, weekNumber);

        this.targetIndex = index;
        this.weekNumber = weekNumber;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        if (weekNumber.getZeroBased() < FIRST_WEEK | weekNumber.getZeroBased() > LAST_WEEK) {
            throw new CommandException(Messages.MESSAGE_INVALID_WEEK);
        }

        Person personToMark = lastShownList.get(targetIndex.getZeroBased());
        List<Integer> newAttendanceScores = getnewAttendanceScores(personToMark);

        Person updatedPerson = createMarkedPerson(personToMark, newAttendanceScores);

        model.setPerson(personToMark, updatedPerson);
        if (model.shouldPurgeAddressBook()) {
            model.purgeAddressBook();
        }
        CommandResult markAttendanceCommandResult =
                new CommandResult(String.format(MESSAGE_MARK_ATTENDANCE_SUCCESS, updatedPerson.getName(),
                        weekNumber.getZeroBased()));
        model.commitAddressBook(markAttendanceCommandResult);
        return markAttendanceCommandResult;
    }

    /**
     * Returns a list of updated attendance scores for {@code personToMark}.
     *
     * @param personToMark the person to mark attendance for
     * @return a list of updated attendance scores
     */
    private List<Integer> getnewAttendanceScores(Person personToMark) {
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
     * with the updated Attendance scores. Utilizes the overload Person constructor.
     */
    private static Person createMarkedPerson(Person personToMark, List<Integer> updatedAttendanceScores) {
        assert personToMark != null;

        Name name = personToMark.getName();
        MatricNumber matricNumber = personToMark.getMatricNumber();
        Email email = personToMark.getEmail();
        TelegramHandle telegramHandle = personToMark.getTelegramHandle();
        Set<Tag> tags = personToMark.getTags();
        List<Integer> participationScores = personToMark.getParticipationScores();
        List<Assignment> assignments = personToMark.getAssignments();

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
        if (!(other instanceof MarkAttendanceCommand)) {
            return false;
        }

        // state check
        MarkAttendanceCommand e = (MarkAttendanceCommand) other;
        return targetIndex.equals(e.targetIndex)
                && weekNumber.equals(e.weekNumber);
    }

}
