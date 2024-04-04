package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UnmarkAllParticipationCommand;

public class UnmarkAllParticipationCommandParserTest {

    private UnmarkAllParticipationCommandParser parser = new UnmarkAllParticipationCommandParser();

    @Test
    public void parse_validArgs_returnsUnmarkAllParticipationCommand() {
        assertParseSuccess(parser, "4",
                new UnmarkAllParticipationCommand(Index.fromZeroBased(4)));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UnmarkAllParticipationCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "-1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UnmarkAllParticipationCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UnmarkAllParticipationCommand.MESSAGE_USAGE));
    }
}
