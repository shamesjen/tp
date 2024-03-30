package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Assignment {

    public static final String MESSAGE_CONSTRAINTS = "Assignment names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String assignmentName;
    
    private int assignmentScore;

    /**
     * Constructs a {@code Assignment}.
     *
     * @param assignmentName A valid assignment name.
     */
    public Assignment(String assignmentName) {
        requireNonNull(assignmentName);
        checkArgument(isValidAssignmentName(assignmentName), MESSAGE_CONSTRAINTS);
        this.assignmentName = assignmentName;
        this.assignmentScore = 0;
    }

    /**
     * Returns true if a given string is a valid assignment name.
     */
    public static boolean isValidAssignmentName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public void setAssignmentScore(int score) {
        this.assignmentScore = score;
    }

    public int getAssignmentScore() {
        return this.assignmentScore;
    }

    @Override
    public String toString() {
        return assignmentName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Assignment // instanceof handles nulls
                && assignmentName.equals(((Assignment) other).assignmentName)); // state check
    }
}
