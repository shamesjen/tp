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
 * Marks the tutorial participation of all filtered students
 */

public class MarkAllParticipationCommand extends Command {

    public static final String COMMAND_WORD = "markallp";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the participation of all filtered students \n"
            + "Parameters: WEEK (must be a positive integer between 3 and 13)\n"
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_ARGUMENTS = "Week Number: %2$d";
    public static final String MESSAGE_MARK_ALL_PARTICIPATION_SUCCESS = "Marked all participation for week %1$d";
    private static final int FIRST_WEEK = 3;
    private static final int LAST_WEEK = 13;
    private final Index weekNumber;

    /**
     * @param weekNumber week number to mark the person with
     */
    public MarkAllParticipationCommand(Index weekNumber) {
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
            List<Integer> newParticipationScores = getNewParticipationScores(personToMark);
            Person updatedPerson = createMarkedPerson(personToMark, newParticipationScores);
            model.setPerson(personToMark, updatedPerson);
        }
        if (model.shouldPurgeAddressBook()) {
            model.purgeAddressBook();
        }
        CommandResult markAllParticipationCommandResult =
                new CommandResult(String.format(MESSAGE_MARK_ALL_PARTICIPATION_SUCCESS, weekNumber.getZeroBased()));
        model.commitAddressBook(markAllParticipationCommandResult);
        return markAllParticipationCommandResult;
    }

    /**
     * Returns a list of updated participation scores for {@code personToMark}.
     *
     * @param personToMark the person to mark participation for
     * @return a list of updated participation scores
     */
    private List<Integer> getNewParticipationScores(Person personToMark) {
        List<Integer> oldParticipationScores = personToMark.getParticipationScores();
        List<Integer> newParticipationScores = new ArrayList<>();
        int weekIndex = weekNumber.getZeroBased() - 3;

        for (int j = 0; j < oldParticipationScores.size(); j++) {
            if (j == weekIndex) {
                newParticipationScores.add(oldParticipationScores.get(j) + 1);
            } else {
                newParticipationScores.add(oldParticipationScores.get(j));
            }
        }
        return newParticipationScores;
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToMark}
     * with the updated participation scores. Utilizes the overload Person constructor.
     */
    private static Person createMarkedPerson(Person personToMark, List<Integer> updatedParticipationScores) {
        assert personToMark != null;

        Name name = personToMark.getName();
        MatricNumber matricNumber = personToMark.getMatricNumber();
        Email email = personToMark.getEmail();
        TelegramHandle telegramHandle = personToMark.getTelegramHandle();
        Set<Tag> tags = personToMark.getTags();
        Set<Assignment> assignments = personToMark.getAssignments();
        List<Integer> attendanceScores = personToMark.getAttendanceScores();

        return new Person(name, matricNumber, email, telegramHandle,
                tags, assignments, updatedParticipationScores, attendanceScores);
    }
    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MarkAllParticipationCommand)) {
            return false;
        }

        // state check
        MarkAllParticipationCommand e = (MarkAllParticipationCommand) other;
        return weekNumber.equals(e.weekNumber);
    }

}
