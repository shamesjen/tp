package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AssignmentTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Assignment(null));
    }

    @Test
    public void constructor_invalidAssignment_throwsIllegalArgumentException() {
        String invalidAssignment = "";
        assertThrows(IllegalArgumentException.class, () -> new Assignment(invalidAssignment));
    }

    @Test
    public void isValidAssignment() {
        // null assignment
        assertThrows(NullPointerException.class, () -> Assignment.isValidAssignmentName(null));

        // invalid assignment
        assertFalse(Assignment.isValidAssignmentName("")); // empty string
        assertFalse(Assignment.isValidAssignmentName(" ")); // spaces only
        assertFalse(Assignment.isValidAssignmentName("-")); // non-alphanumeric characters

        // valid assignment
        assertTrue(Assignment.isValidAssignmentName("Assignment1"));
        assertTrue(Assignment.isValidAssignmentName("Assignment1Assignment2")); // long assignment
    }

}
