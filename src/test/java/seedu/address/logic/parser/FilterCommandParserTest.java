package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_COLLEAGUE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.model.person.PersonContainsTagPredicate;

public class FilterCommandParserTest {

    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingAllOrAnyKeyword_throwsParseException() {
        assertParseFailure(parser, VALID_TAG_FRIEND,
                "The argument must start with 'all' or 'any'.");
    }

    @Test
    public void parse_validArgsAny_returnsFilterCommand() {
        // no leading and trailing whitespaces with 'any' keyword
        FilterCommand expectedFilterCommandAny =
                new FilterCommand(new PersonContainsTagPredicate(Arrays.asList(VALID_TAG_FRIEND, VALID_TAG_COLLEAGUE),
                    false));
        assertParseSuccess(parser, "any " + VALID_TAG_FRIEND + " " + VALID_TAG_COLLEAGUE, expectedFilterCommandAny);

        // multiple whitespaces between keywords with 'any' keyword
        assertParseSuccess(parser, "any \n " + VALID_TAG_FRIEND + " \n \t "
                + VALID_TAG_COLLEAGUE + " \t", expectedFilterCommandAny);
    }
}
