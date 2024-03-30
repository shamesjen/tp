package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_WEEK;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.MarkParticipationCommand;

public class MarkParticipationCommandParserTest {
    private MarkParticipationCommandParser parser = new MarkParticipationCommandParser();

    @Test
    public void parse_allIndexes_success() {
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + "1 " + "3 ",
                new MarkParticipationCommand(INDEX_FIRST_PERSON, INDEX_FIRST_WEEK));
    }

    @Test
    public void parse_missingIndexes_failure() {
        assertParseFailure(parser, PREAMBLE_WHITESPACE + "1 ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkParticipationCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_tooManyIndexes_failure() {
        assertParseFailure(parser, PREAMBLE_WHITESPACE + "1 " + "3 " + "12 ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkParticipationCommand.MESSAGE_USAGE));
    }

}
