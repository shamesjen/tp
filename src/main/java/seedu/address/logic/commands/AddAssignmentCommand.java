package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Assignment;
import seedu.address.model.person.Person;

/**
 * Adds an assignment to all students in the address book.
 */
public class AddAssignmentCommand extends Command {

    public static final String COMMAND_WORD = "adda";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Creates assignment(s) on all students.\n"
        + "Parameters: ASSIGNMENT (must be a positive integer)\n"
        + "[" + PREFIX_ASSIGNMENT + "ASSIGNMENT]...\n"
        + "Example: " + COMMAND_WORD + " " + PREFIX_ASSIGNMENT + "Homework1";

    public static final String MESSAGE_SUCCESS = "New assignment added: %1$s";

    public static final String MESSAGE_ASSIGNMENT_EXISTS_FAILURE_STRING = "No new assignments added. "
        + "All students already have the assignment(s) specified.";

    public static final String MESSAGE_DUPLICATE_ASSIGNMENT = "Duplicate assignments detected.";

    private final Set<Assignment> assignments;

    private boolean hasAssignment = true;

    public AddAssignmentCommand(Set<Assignment> assignments) {
        this.assignments = assignments;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        for (Person person : lastShownList) {
            Set<Assignment> newAssignments = generateAssignmentList(person, assignments);
            if (newAssignments.size() > person.getAssignments().size()) {
                hasAssignment = false;
            }
            Person newPerson = new Person(person.getName(), person.getMatricNumber(),
                person.getEmail(), person.getTelegramHandle(), person.getTags(),
                    newAssignments, person.getParticipationScores(), person.getAttendanceScores());
            model.setPerson(person, newPerson);
        }
        if (hasAssignment) {
            throw new CommandException(
                MESSAGE_ASSIGNMENT_EXISTS_FAILURE_STRING);
        }
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        if (model.shouldPurgeAddressBook()) {
            model.purgeAddressBook();
        }

        CommandResult addAssignmentCommandResult = new CommandResult(
            String.format(MESSAGE_SUCCESS, assignments.toString()));
        model.commitAddressBook(addAssignmentCommandResult);
        return addAssignmentCommandResult;
    }

    /**
     * Generates a new list of assignments for a person.
     * @param person Person to add assignments to.
     * @param assignments Assignments to add.
     * @return List of assignments for the person.
     */
    public static Set<Assignment> generateAssignmentList(Person person, Set<Assignment> assignments) {
        Set<Assignment> oldAssignments = person.getAssignments();
        Set<Assignment> newAssignments = new HashSet<>(oldAssignments);
        for (Assignment assignment : assignments) {
            if (!person.hasAssignment(assignment)) {
                newAssignments.add(assignment);
            } else {
                continue;
            }
        }
        return newAssignments;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddAssignmentCommand // instanceof handles nulls
            && assignments.equals(((AddAssignmentCommand) other).assignments));
    }

    @Override
    public String toString() {
        StringBuilder assignmentsString = new StringBuilder();
        for (Assignment assignment : this.assignments) {
            assignmentsString.append(assignment.toString());
            assignmentsString.append(", ");
        }
        return assignmentsString.toString();
    }
}
