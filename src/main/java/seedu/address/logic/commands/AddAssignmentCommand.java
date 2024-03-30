package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Assignment;
import seedu.address.model.person.Person;

/**
 * Adds an assignment to all students in the address book.
 */
public class AddAssignmentCommand extends Command {
    
    public static final String COMMAND_WORD = "addAssignment";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Creates assignment(s) on all students.\n"
        + "Parameters: ASSIGNMENT (must be a positive integer)\n"
        + "[" + PREFIX_ASSIGNMENT + "ASSIGNMENT]...\n"
        + "Example: " + COMMAND_WORD + " " + PREFIX_ASSIGNMENT + "Homework 1";

    public static final String MESSAGE_SUCCESS = "New assignment added: %1$s";

    private final List<Assignment> assignments;

    public AddAssignmentCommand(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        boolean hasAssignment = true;
        for (Person person : lastShownList) {
            List<Assignment> oldAssignments = person.getAssignments();
            List<Assignment> newAssignments = new ArrayList<>(oldAssignments);
            for (Assignment assignment : this.assignments) {
                if (!person.hasAssignment(assignment)) {
                    System.out.println("assignment: " + assignment + " person: " + person);
                    newAssignments.add(assignment);
                    hasAssignment = false;
                } else {
                    continue;
                }
            }
            
            Person newPerson = new Person(person.getName(), person.getMatricNumber(), person.getEmail(), person.getTelegramHandle(),
                    newAssignments, person.getTags());
                model.setPerson(person, newPerson);
            System.out.println("newPerson: " + newPerson);
        }
        if (hasAssignment) {
            throw new CommandException("No new assignments added. All students already have the assignment(s) specified.");
        }
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        if (model.shouldPurgeAddressBook()) {
            model.purgeAddressBook();
        }

        CommandResult addAssignmenCommandResult = new CommandResult(String.format(MESSAGE_SUCCESS, assignments.toString()));
        model.commitAddressBook(addAssignmenCommandResult);
        return addAssignmenCommandResult;
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
