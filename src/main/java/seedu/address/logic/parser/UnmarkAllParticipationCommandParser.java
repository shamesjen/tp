package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UnmarkAllParticipationCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UnmarkCommand object
 */
public class UnmarkAllParticipationCommandParser implements Parser<UnmarkAllParticipationCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of UnmarkAllParticipationCommand
     * and returns an UnmarkAllParticipationCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnmarkAllParticipationCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseZeroIndex(args);
            return new UnmarkAllParticipationCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnmarkAllParticipationCommand.MESSAGE_USAGE), pe);
        }
    }

}
