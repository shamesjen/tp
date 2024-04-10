package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.RemoveAssignmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Assignment;

/**
 * Parses input arguments and creates a new RemoveAssignmentCommand object
 */
public class RemoveAssignmentCommandParser implements Parser<RemoveAssignmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RemoveAssignmentCommand
     * and returns a RemoveAssignmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemoveAssignmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ASSIGNMENT);
        if (!arePrefixesPresent(argMultimap, PREFIX_ASSIGNMENT) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RemoveAssignmentCommand.MESSAGE_USAGE));
        }
        List<String> assignments = argMultimap.getAllValues(PREFIX_ASSIGNMENT);
        Set<Assignment> assignmentSet = new HashSet<>();
        for (String assignment : assignments) {
            assignmentSet.add(new Assignment(assignment));
        }
        return new RemoveAssignmentCommand(assignmentSet);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
    */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
