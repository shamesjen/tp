package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's matriculation (matric) number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidMatricNumber(String)}
 */
public class MatricNumber {

    public static final String MESSAGE_CONSTRAINTS =
            "Matric Numbers should begin and end with capital letters, and comprise 7 digits in between.";
    public static final String VALIDATION_REGEX = "[A-Z]\\d{7}[A-Z]";
    public final String value;

    /**
     * Constructs a {@code MatricNumber}.
     *
     * @param matricNumber A valid matric number.
     */
    public MatricNumber(String matricNumber) {
        requireNonNull(matricNumber);
        checkArgument(isValidMatricNumber(matricNumber), MESSAGE_CONSTRAINTS);
        value = matricNumber;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidMatricNumber(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MatricNumber)) {
            return false;
        }

        MatricNumber otherMatricNumber = (MatricNumber) other;
        return value.equals(otherMatricNumber.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
