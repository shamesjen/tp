package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.GradeAssignmentCommand;


public class GradeAssignmentCommandParserTest {

    private GradeAssignmentCommandParser parser = new GradeAssignmentCommandParser();

    @Test
    public void parse_preambleEmpty_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, GradeAssignmentCommand.MESSAGE_USAGE);
        // preamble is empty
        assertParseFailure(parser, "grade  ", expectedMessage);
    }

}
