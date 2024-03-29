package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkAllAttendanceCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new MarkCommand object
 */
public class MarkAllAttendanceCommandParser implements Parser<MarkAllAttendanceCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of MarkAllCommand
     * and returns a MarkAllCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkAllAttendanceCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new MarkAllAttendanceCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkAllAttendanceCommand.MESSAGE_USAGE), pe);
        }
    }

}
