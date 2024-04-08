package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.MarkParticipationCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Assignment;
import seedu.address.model.person.Email;
import seedu.address.model.person.MatricNumber;
import seedu.address.model.person.Name;
import seedu.address.model.person.TelegramHandle;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_ARGUMENTS_MARKASSIGNMENT =
        "Invalid arguments provided for MarkAssignmentCommand.";
    public static final String MESSAGE_INVALID_ARGUMENTS_GRADEASSIGNMENT =
        "Invalid arguments provided for Grade Command.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    public static Integer parseGrade(String grade) throws ParseException {
        String trimmedGrade = grade.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedGrade)) {
            throw new ParseException(MESSAGE_INVALID_ARGUMENTS_GRADEASSIGNMENT);
        }
        return Integer.parseInt(trimmedGrade);
    }

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it as a zerobased index.
     * Leading and trailing whitespaces will be trimmed. Used mainly for the markall command.
     * @throws ParseException
     */
    public static Index parseZeroIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromZeroBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses {@code oneBasedIndexes} into an {@code Index[]} and returns it. Leading and trailing whitespaces will
     * be trimmed. Used mainly for the mark command.
     * @throws ParseException if any of the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index[] parseIndexes(String oneBasedIndexes) throws ParseException {
        String[] indexArray = oneBasedIndexes.trim().split("\\s+");
        Index[] indexes = new Index[2];
        if (indexArray.length != 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MarkParticipationCommand.MESSAGE_USAGE));
        }

        String personIndex = indexArray[0];
        String weekNumber = indexArray[1];

        if (!StringUtil.isNonZeroUnsignedInteger(personIndex) | !StringUtil.isNonZeroUnsignedInteger(weekNumber)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        indexes[0] = Index.fromOneBased(Integer.parseInt(personIndex));
        indexes[1] = Index.fromZeroBased(Integer.parseInt(weekNumber));

        return indexes;
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String matricNumber} into a {@code MatricNumber}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code matricNumber} is invalid.
     */
    public static MatricNumber parseMatricNumber(String matricNumber) throws ParseException {
        requireNonNull(matricNumber);
        String trimmedMatricNumber = matricNumber.trim();
        if (!MatricNumber.isValidMatricNumber(trimmedMatricNumber)) {
            throw new ParseException(MatricNumber.MESSAGE_CONSTRAINTS);
        }
        return new MatricNumber(trimmedMatricNumber);
    }

    /**
     * Parses a {@code String telegramHandle} into an {@code TelegramHandle}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code telegramHandle} is invalid.
     */
    public static TelegramHandle parseTelegramHandle(String telegramHandle) throws ParseException {
        requireNonNull(telegramHandle);
        String trimmedTelegramHandle = telegramHandle.trim();
        if (!TelegramHandle.isValidTelegramHandle(trimmedTelegramHandle)) {
            throw new ParseException(TelegramHandle.MESSAGE_CONSTRAINTS);
        }
        return new TelegramHandle(trimmedTelegramHandle);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String assignment} into an {@code Assignment}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code assignment} is invalid.
     */
    public static Assignment parseAssignment(String assignment) throws ParseException {
        requireNonNull(assignment);
        String trimmedAssignment = assignment.trim();
        if (!Assignment.isValidAssignmentName(trimmedAssignment)) {
            throw new ParseException(Assignment.MESSAGE_CONSTRAINTS);
        }
        return new Assignment(trimmedAssignment);
    }
}
