package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkParticipationCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new MarkParticipationCommand object
 */
public class MarkParticipationCommandParser implements Parser<MarkParticipationCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of MarkParticipationCommand
     * and returns a MarkParticipationCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkParticipationCommand parse(String args) throws ParseException {
        try {
            Index[] indexes = ParserUtil.parseIndexes(args);
            return new MarkParticipationCommand(indexes[0], indexes[1]);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkParticipationCommand.MESSAGE_USAGE), pe);
        }
    }

}
