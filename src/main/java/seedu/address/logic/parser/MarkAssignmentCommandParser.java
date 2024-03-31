package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkAssignmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Assignment;




/**
 * Parses input arguments and creates a new MarkAssignmentCommand object
 */
public class MarkAssignmentCommandParser {

    public static final String MESSAGE_INVALID_ASSIGNMENT_INDEX = "Invalid assignment index provided.";

    /**
     * Parses the given {@code String} of arguments in the context of the MarkAssignmentCommand
     * and returns a MarkAssignmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkAssignmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ASSIGNMENT);
        if (!arePrefixesPresent(argMultimap, PREFIX_ASSIGNMENT)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                MarkAssignmentCommand.MESSAGE_USAGE));
        }
        Index[] indexes = ParserUtil.parseIndexes(argMultimap.getPreamble());
        Assignment assignment = ParserUtil.parseAssignment(argMultimap.getValue(PREFIX_ASSIGNMENT).get());
        return new MarkAssignmentCommand(indexes[0], assignment.assignmentName, indexes[1].getZeroBased());
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
