package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkAllParticipationCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;


public class MarkAllParticipationCommandParserTest {

    private MarkAllParticipationCommandParser parser = new MarkAllParticipationCommandParser();

    @Test
    public void parse_validArgs_returnsMarkAllParticipationCommand() {
        // Assume week 1 is a valid argument
        assertParseSuccess(parser, "1",
                new MarkAllParticipationCommand(Index.fromOneBased(1)));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // Invalid because it's a non-integer input
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkAllParticipationCommand.MESSAGE_USAGE));

        // Invalid because it's an out of range input (negative number)
        assertParseFailure(parser, "-1", String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkAllParticipationCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        // Fails because no arguments were provided
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkAllParticipationCommand.MESSAGE_USAGE));
    }
}
