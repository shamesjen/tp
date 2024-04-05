package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PersonContainsTagPredicate;

/**
 * Parses input arguments and creates a new FilterCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * and returns a FilterCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {
        boolean isAll = false;
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }



        List<String> tagKeywords = Arrays.asList(trimmedArgs.split("\\s+"));

        if (tagKeywords.contains("all") || tagKeywords.contains("any")) {
            if (tagKeywords.contains("all")) {
                isAll = true;
            }
            tagKeywords = tagKeywords.stream()
                    .filter(arg -> !arg.equals("all") && !arg.equals("any"))
                    .collect(Collectors.toList());
        } else {
            throw new ParseException("The argument must start with 'all' or 'any'.");
        }

        return new FilterCommand(new PersonContainsTagPredicate(tagKeywords, isAll));
    }

}
