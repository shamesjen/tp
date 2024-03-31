package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkAllParticipationCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new MarkCommand object
 */
public class MarkAllParticipationCommandParser implements Parser<MarkAllParticipationCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of MarkAllParticipationCommand
     * and returns a MarkAllParticipationCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkAllParticipationCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseZeroIndex(args);
            return new MarkAllParticipationCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkAllParticipationCommand.MESSAGE_USAGE), pe);
        }
    }

}
