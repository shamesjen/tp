package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddAssignmentCommand;


public class AddAssignmentCommandParserTest {

    private AddAssignmentCommandParser parser = new AddAssignmentCommandParser();

    @Test
    public void parse_preambleEmpty_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAssignmentCommand.MESSAGE_USAGE);
        // preamble is empty
        assertParseFailure(parser, "AddAssignment  ", expectedMessage);
    }

}
