package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Assignment;

/**
 * Jackson-friendly version of {@link Assignment}.
 */
class JsonAdaptedAssignment {

    private final String assignmentName;
    private final int score;

    /**
     * Constructs a {@code JsonAdaptedAssignment} with the given {@code assignmentName}.
     */
    @JsonCreator
    public JsonAdaptedAssignment(String assignmentName, int score) {
        this.assignmentName = assignmentName;
        this.score = score;
    }

    /**
     * Converts a given {@code Assignment} into this class for Jackson use.
     */
    @JsonCreator
    public JsonAdaptedAssignment(Assignment source, int score) {
        assignmentName = source.assignmentName;
        this.score = score;
    }

    @JsonValue
    public String getassignmentName() {
        return assignmentName;
    }

    @JsonValue
    public int getScore() {
        return score;
    }


    /**
     * Converts this Jackson-friendly adapted assignment object into the model's {@code Assignment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted assignment.
     */
    public Assignment toModelType() throws IllegalValueException {
        if (!Assignment.isValidAssignmentName(assignmentName)) {
            throw new IllegalValueException(Assignment.MESSAGE_CONSTRAINTS);
        }
        return new Assignment(assignmentName);
    }

}
