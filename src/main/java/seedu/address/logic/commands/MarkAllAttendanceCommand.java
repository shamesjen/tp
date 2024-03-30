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
import seedu.address.model.person.Email;
import seedu.address.model.person.MatricNumber;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.TelegramHandle;
import seedu.address.model.tag.Tag;

/**
 * Marks the tutorial attendance of all filtered student
 */

public class MarkAllAttendanceCommand extends Command {

    public static final String COMMAND_WORD = "markall";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the attendance of the person identified \n"
            + "Parameters: INDEX (must be a positive integer), WEEK (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_ARGUMENTS = "Week Number: %2$d";
    public static final String MESSAGE_MARK_PERSON_SUCCESS = "Marked all";
    private final Index weekNumber;

    /**
     * @param index of the person in the filtered person list to edit
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

        if (weekNumber.getZeroBased() > 13) {
            throw new CommandException(Messages.MESSAGE_INVALID_WEEK);
        }
        for (int i = 0; i < lastShownList.size(); i++) {
            Person personToMark = lastShownList.get(i);
            List<Integer> oldParticipationScores = personToMark.getParticipationScores();
            List<Integer> newParticipationScores = new ArrayList<>();
            int weekIndex = weekNumber.getZeroBased();

            for (int j = 0; j < oldParticipationScores.size(); j++) {
                if (j == weekIndex) {
                    newParticipationScores.add(1);
                } else {
                    newParticipationScores.add(oldParticipationScores.get(j));
                }
            }
            Person updatedPerson = createMarkedPerson(personToMark, newParticipationScores);
            model.setPerson(personToMark, updatedPerson);
        }
        if (model.shouldPurgeAddressBook()) {
            model.purgeAddressBook();
        }
        CommandResult markCommandResult = new CommandResult(MESSAGE_MARK_PERSON_SUCCESS);
        model.commitAddressBook(markCommandResult);
        return markCommandResult;
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
        List<Integer> attendanceScores = personToMark.getAttendanceScores();

        return new Person(name, matricNumber, email, telegramHandle,
                tags, updatedParticipationScores, attendanceScores);
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
