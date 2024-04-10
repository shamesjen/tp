package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Assignment;
import seedu.address.model.person.Person;

/**
 * Removes an assignment from all students.
 */
public class RemoveAssignmentCommand extends Command {

    public static final String COMMAND_WORD = "removea";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes assignment(s) on all students.\n"
            + "Parameters: ASSIGNMENT (must be a positive integer)\n"
            + "[" + PREFIX_ASSIGNMENT + "ASSIGNMENT]...\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_ASSIGNMENT + "Homework1";

    public static final String MESSAGE_SUCCESS = "Assignments removed: %1$s";

    public static final String MESSAGE_ASSIGNMENT_DOES_NOT_EXIST_FAILURE_STRING = "No assignments removed. "
        + "No students have the assignment(s) specified.";

    private final Set<Assignment> assignments;

    private boolean hasAssignment = false;

    public RemoveAssignmentCommand(Set<Assignment> assignments) {
        this.assignments = assignments;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        for (Person person : lastShownList) {
            Set<Assignment> newAssignments = generateAssignmentSet(person, assignments);
            if (newAssignments.size() < person.getAssignments().size()) {
                hasAssignment = true;
            }
            Person newPerson = new Person(person.getName(), person.getMatricNumber(),
                person.getEmail(), person.getTelegramHandle(), person.getTags(),
                    newAssignments, person.getParticipationScores(), person.getAttendanceScores());
            model.setPerson(person, newPerson);

            if (!hasAssignment) {
                throw new CommandException(MESSAGE_ASSIGNMENT_DOES_NOT_EXIST_FAILURE_STRING);
            }
        }
        if (model.shouldPurgeAddressBook()) {
            model.purgeAddressBook();
        }
        CommandResult removeAssignmentCommandResult = new CommandResult(String.format(MESSAGE_SUCCESS, assignments));
        model.commitAddressBook(removeAssignmentCommandResult);
        return removeAssignmentCommandResult;
    }

    /**
     * Generates a new list of assignments for the person.
     * @param person Person to generate the new list of assignments for.
     * @param assignments List of assignments to remove.
     * @return List of assignments for the person.
     */
    private Set<Assignment> generateAssignmentSet(Person person, Set<Assignment> assignments) {
        Set<Assignment> newAssignments = new HashSet<>(person.getAssignments());
        newAssignments.removeAll(assignments);
        return newAssignments;
    }

}

