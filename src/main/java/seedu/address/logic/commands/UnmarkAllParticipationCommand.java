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
 * Unmarks the tutorial participation of all filtered students
 */

public class UnmarkAllParticipationCommand extends Command {

    public static final String COMMAND_WORD = "unmarkallp";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unmarks the participation of all filtered students \n"
            + "Parameters: WEEK (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_ARGUMENTS = "Week Number: %2$d";
    public static final String MESSAGE_UNMARK_ALL_PARTICIPATION_SUCCESS = "Unmarked all participation for week %1$d";
    public static final String MESSAGE_PARTICIPATION_ALREADY_ZERO = "Participation for %1$s in Week %2$d is already 0";
    private static final int FIRST_WEEK = 3;
    private static final int LAST_WEEK = 13;
    private final Index weekNumber;

    /**
     * @param weekNumber week number to mark the person with
     */
    public UnmarkAllParticipationCommand(Index weekNumber) {
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
            if (personToUnmark.getParticipationScores().get(weekNumber.getZeroBased() - 3) == 0) {
                throw new CommandException(String.format(MESSAGE_PARTICIPATION_ALREADY_ZERO,
                        personToUnmark.getName(), weekNumber.getZeroBased()));
            }
        }
        for (Person personToUnmark : lastShownList) {
            List<Integer> newParticipationScores = getNewParticipationScores(personToUnmark);
            Person updatedPerson = createMarkedPerson(personToUnmark, newParticipationScores);
            model.setPerson(personToUnmark, updatedPerson);
        }
        if (model.shouldPurgeAddressBook()) {
            model.purgeAddressBook();
        }
        CommandResult unmarkAllParticipationCommandResult =
                new CommandResult(String.format(MESSAGE_UNMARK_ALL_PARTICIPATION_SUCCESS, weekNumber.getZeroBased()));
        model.commitAddressBook(unmarkAllParticipationCommandResult);
        return unmarkAllParticipationCommandResult;
    }

    /**
     * Returns a list of updated participation scores for {@code personToUnmark}.
     *
     * @param personToUnmark the person to unmark participation for
     * @return a list of updated participation scores
     */
    private List<Integer> getNewParticipationScores(Person personToUnmark) {
        List<Integer> oldParticipationScores = personToUnmark.getParticipationScores();
        List<Integer> newParticipationScores = new ArrayList<>();
        int weekIndex = weekNumber.getZeroBased() - 3;

        for (int j = 0; j < oldParticipationScores.size(); j++) {
            if (j == weekIndex) {
                newParticipationScores.add(oldParticipationScores.get(j) - 1);
            } else {
                newParticipationScores.add(oldParticipationScores.get(j));
            }
        }
        return newParticipationScores;
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToUnmark}
     * with the updated participation scores. Utilizes the overload Person constructor.
     */
    private static Person createMarkedPerson(Person personToUnmark, List<Integer> updatedParticipationScores) {
        assert personToUnmark != null;

        Name name = personToUnmark.getName();
        MatricNumber matricNumber = personToUnmark.getMatricNumber();
        Email email = personToUnmark.getEmail();
        TelegramHandle telegramHandle = personToUnmark.getTelegramHandle();
        Set<Tag> tags = personToUnmark.getTags();
        Set<Assignment> assignments = personToUnmark.getAssignments();
        List<Integer> attendanceScores = personToUnmark.getAttendanceScores();

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
        if (!(other instanceof UnmarkAllParticipationCommand)) {
            return false;
        }

        // state check
        UnmarkAllParticipationCommand e = (UnmarkAllParticipationCommand) other;
        return weekNumber.equals(e.weekNumber);
    }

}
