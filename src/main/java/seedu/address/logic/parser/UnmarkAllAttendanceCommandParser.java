package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UnmarkAllAttendanceCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UnmarkAllAttendanceCommand object
 */
public class UnmarkAllAttendanceCommandParser implements Parser<UnmarkAllAttendanceCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of UnmarkAllAttendanceCommand
     * and returns a UnmarkAllAttendanceCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnmarkAllAttendanceCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseZeroIndex(args);
            return new UnmarkAllAttendanceCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnmarkAllAttendanceCommand.MESSAGE_USAGE), pe);
        }
    }

}
