package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Assignment;
import seedu.address.model.person.Person;

/**
 * Marks an assignment as done for one student.
 */
public class GradeAssignmentCommand extends Command {

    public static final String COMMAND_WORD = "grade";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Grades an assignment as done for one student.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "SCORE (must be postivie integer)\n"
            + "Example: " + COMMAND_WORD + " 1" + " " + "10 " + PREFIX_ASSIGNMENT + "Assignment1";


    public static final String MESSAGE_SUCCESS =
        "Assignment %1$s marked as done with score: %2$d from this person : %3$s.";

    public static final String MESSAGE_ASSIGNMENT_NOT_FOUND = "Assignment not found.";

    private final Index targetIndex;
    private final String assignmentName;
    private final int assignmentScore;

    /**
     * @param targetIndex of the person in the filtered person list to mark assignment
     * @param assignmentName of the assignment to be marked
     * @param assignmentScore of the assignment to be marked
     */
    public GradeAssignmentCommand(Index targetIndex, String assignmentName, int assignmentScore) {
        this.targetIndex = targetIndex;
        this.assignmentName = assignmentName;
        this.assignmentScore = assignmentScore;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToMark = lastShownList.get(targetIndex.getZeroBased());
        Set<Assignment> assignments = personToMark.getAssignments();
        Set<Assignment> newAssignments = new HashSet<>(assignments);
        boolean assignmentFound = false;

        for (Assignment assignment : assignments) {
            if (assignment.getName().equals(assignmentName)) {
                assignmentFound = true;
                Assignment newAssignment = new Assignment(assignment.getName(), assignmentScore);
                newAssignments.remove(assignment);
                newAssignments.add(newAssignment);

            }
        }

        if (!assignmentFound) {
            throw new CommandException(MESSAGE_ASSIGNMENT_NOT_FOUND);
        }
        Person newPerson = new Person(personToMark.getName(), personToMark.getMatricNumber(),
                personToMark.getEmail(), personToMark.getTelegramHandle(), personToMark.getTags(),
                    newAssignments, personToMark.getParticipationScores(), personToMark.getAttendanceScores());
        model.setPerson(personToMark, newPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        if (model.shouldPurgeAddressBook()) {
            model.purgeAddressBook();
        }
        CommandResult gradeCommandResult = new CommandResult(String.format(MESSAGE_SUCCESS, assignmentName,
            assignmentScore, personToMark.getName()));
        model.commitAddressBook(gradeCommandResult);
        return gradeCommandResult;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof GradeAssignmentCommand
                && targetIndex.equals(((GradeAssignmentCommand) other).targetIndex)
                && assignmentName.equals(((GradeAssignmentCommand) other).assignmentName)
                && assignmentScore == ((GradeAssignmentCommand) other).assignmentScore);
    }

    @Override
    public String toString() {
        return "MarkAssignmentCommand{" + "targetIndex=" + targetIndex + ", assignmentName='" + assignmentName + '\''
                + ", assignmentScore=" + assignmentScore + '}';
    }

}
