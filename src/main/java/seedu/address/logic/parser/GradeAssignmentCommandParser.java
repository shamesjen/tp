package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT;
import static seedu.address.logic.parser.ParserUtil.parseGrade;
import static seedu.address.logic.parser.ParserUtil.parseIndex;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.GradeAssignmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Assignment;




/**
 * Parses input arguments and creates a new MarkAssignmentCommand object
 */
public class GradeAssignmentCommandParser implements Parser<GradeAssignmentCommand> {

    public static final String MESSAGE_INVALID_ASSIGNMENT_INDEX = "Invalid assignment index provided.";

    /**
     * Parses the given {@code String} of arguments in the context of the MarkAssignmentCommand
     * and returns a MarkAssignmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public GradeAssignmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ASSIGNMENT);
        if (!arePrefixesPresent(argMultimap, PREFIX_ASSIGNMENT)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    GradeAssignmentCommand.MESSAGE_USAGE));
        }
        String[] indexes = argMultimap.getPreamble().trim().split("\\s+");
        Index index = parseIndex(indexes[0]);
        int grade = parseGrade(indexes[1]);
        Assignment assignment = ParserUtil.parseAssignment(argMultimap.getValue(PREFIX_ASSIGNMENT).get());
        return new GradeAssignmentCommand(index, assignment.assignmentName, grade);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
