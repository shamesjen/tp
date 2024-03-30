package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Email;
import seedu.address.model.person.MatricNumber;
import seedu.address.model.person.Name;
import seedu.address.model.person.TelegramHandle;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_MATRIC_NUMBER = "+651234";
    private static final String INVALID_TELEGRAM_HANDLE = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_ASSIGNMENT = " ";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getMatricNumber().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getTelegramHandle().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedAssignment> VALID_ASSIGNMENTS = BENSON.getAssignments().stream()
            .map(JsonAdaptedAssignment::new)
            .collect(Collectors.toList());
    private static final List<Integer> VALID_PARTICIPATION_SCORES = BENSON.getParticipationScores();
    private static final List<Integer> VALID_ATTENDNACE_SCORES = BENSON.getAttendanceScores();

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_TAGS, VALID_ASSIGNMENTS, VALID_PARTICIPATION_SCORES,
                            VALID_ATTENDNACE_SCORES);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_TAGS, VALID_ASSIGNMENTS, VALID_PARTICIPATION_SCORES, VALID_ATTENDNACE_SCORES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, INVALID_MATRIC_NUMBER, VALID_EMAIL,
                        VALID_ADDRESS, VALID_TAGS, VALID_ASSIGNMENTS, VALID_PARTICIPATION_SCORES,
                            VALID_ATTENDNACE_SCORES);
        String expectedMessage = MatricNumber.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, null, VALID_EMAIL,
                VALID_ADDRESS, VALID_TAGS, VALID_ASSIGNMENTS, VALID_PARTICIPATION_SCORES, VALID_ATTENDNACE_SCORES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, MatricNumber.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, INVALID_EMAIL,
                        VALID_ADDRESS, VALID_TAGS, VALID_ASSIGNMENTS, VALID_PARTICIPATION_SCORES,
                            VALID_ATTENDNACE_SCORES);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, null,
                VALID_ADDRESS, VALID_TAGS, VALID_ASSIGNMENTS, VALID_PARTICIPATION_SCORES, VALID_ATTENDNACE_SCORES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        INVALID_TELEGRAM_HANDLE, VALID_TAGS, VALID_ASSIGNMENTS,
                            VALID_PARTICIPATION_SCORES, VALID_ATTENDNACE_SCORES);
        String expectedMessage = TelegramHandle.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                null, VALID_TAGS, VALID_ASSIGNMENTS, VALID_PARTICIPATION_SCORES, VALID_ATTENDNACE_SCORES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TelegramHandle.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, invalidTags, VALID_ASSIGNMENTS,
                            VALID_PARTICIPATION_SCORES, VALID_ATTENDNACE_SCORES);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidAssignments_throwsIllegalValueException() {
        List<JsonAdaptedAssignment> invalidAssignments = new ArrayList<>(VALID_ASSIGNMENTS);
        invalidAssignments.add(new JsonAdaptedAssignment(INVALID_ASSIGNMENT));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_TAGS, invalidAssignments,
                            VALID_PARTICIPATION_SCORES, VALID_ATTENDNACE_SCORES);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
