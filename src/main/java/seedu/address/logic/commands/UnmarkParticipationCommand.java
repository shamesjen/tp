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
 * Unmarks the tutorial participation of a student
 */

public class UnmarkParticipationCommand extends Command {

    public static final String COMMAND_WORD = "unmarkp";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unmarks the participation of the person identified "
            + "by the index number used in the last person listing.\n"
            + "Parameters: INDEX (must be a positive integer), WEEK (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 " + "5";

    public static final String MESSAGE_UNMARK_PARTICIPATION_SUCCESS = "Unmarked Participation for: %1$s in Week %2$d "
            + "\nCurrent Participation Score: %3$s";

    public static final String MESSAGE_PARTICIPATION_ALREADY_ZERO = "Participation for %1$s in Week %2$d is already 0";

    private static final int FIRST_WEEK = 3;
    private static final int LAST_WEEK = 13;
    private final Index targetIndex;
    private final Index weekNumber;

    /**
     * @param index of the person in the filtered person list to edit
     * @param weekNumber week number to unmark the person with
     */
    public UnmarkParticipationCommand(Index index, Index weekNumber) {
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

        Person personToUnmark = lastShownList.get(targetIndex.getZeroBased());
        if (personToUnmark.getParticipationScores().get(weekNumber.getZeroBased() - 3) == 0) {
            throw new CommandException(String.format(MESSAGE_PARTICIPATION_ALREADY_ZERO,
                    personToUnmark.getName(), weekNumber.getZeroBased()));
        }
        List<Integer> newParticipationScores = getNewParticipationScores(personToUnmark);

        Person updatedPerson = createMarkedPerson(personToUnmark, newParticipationScores);

        model.setPerson(personToUnmark, updatedPerson);
        if (model.shouldPurgeAddressBook()) {
            model.purgeAddressBook();
        }
        CommandResult unmarkParticipationCommandResult =
                new CommandResult(String.format(MESSAGE_UNMARK_PARTICIPATION_SUCCESS, updatedPerson.getName(),
                        weekNumber.getZeroBased(),
                        updatedPerson.getParticipationScores().get(weekNumber.getZeroBased() - 3)));
        model.commitAddressBook(unmarkParticipationCommandResult);
        return unmarkParticipationCommandResult;
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

        for (int i = 0; i < oldParticipationScores.size(); i++) {
            if (i == weekIndex) {
                newParticipationScores.add(oldParticipationScores.get(i) - 1);
            } else {
                newParticipationScores.add(oldParticipationScores.get(i));
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
        List<Integer> attendanceScores = personToUnmark.getAttendanceScores();
        List<Assignment> assignments = personToUnmark.getAssignments();

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
        if (!(other instanceof UnmarkParticipationCommand)) {
            return false;
        }

        // state check
        UnmarkParticipationCommand e = (UnmarkParticipationCommand) other;
        return targetIndex.equals(e.targetIndex)
                && weekNumber.equals(e.weekNumber);
    }

}
