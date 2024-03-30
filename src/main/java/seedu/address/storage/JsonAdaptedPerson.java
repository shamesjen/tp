package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Assignment;
import seedu.address.model.person.Email;
import seedu.address.model.person.MatricNumber;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.TelegramHandle;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String matricNumber;
    private final String email;
    private final String telegramHandle;
    private final List<JsonAdaptedAssignment> assignments = new ArrayList<>();
    private final List<JsonAdaptedTag> tags = new ArrayList<>();
    private final List<Integer> participationScores = new ArrayList<>();
    private final List<Integer> attendanceScores = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("matricNumber") String matricNumber,
                             @JsonProperty("email") String email, @JsonProperty("telegramHandle") String telegramHandle,
                             @JsonProperty("tags") List<JsonAdaptedTag> tags,
                             @JsonProperty("assignments") List<JsonAdaptedAssignment> assignments,
                             @JsonProperty("participationScores") List<Integer> participationScores,
                             @JsonProperty("attendanceScores") List<Integer> attendanceScores) {
        this.name = name;
        this.matricNumber = matricNumber;
        this.email = email;
        this.telegramHandle = telegramHandle;
        if (tags != null) {
            this.tags.addAll(tags);
        }
        if (participationScores != null) {
            this.participationScores.addAll(participationScores);
        }
        if (assignments != null) {
            this.assignments.addAll(assignments);
        }
        if (attendanceScores != null) {
            this.attendanceScores.addAll(attendanceScores);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        matricNumber = source.getMatricNumber().value;
        email = source.getEmail().value;
        telegramHandle = source.getTelegramHandle().value;
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        participationScores.addAll(source.getParticipationScores());
        attendanceScores.addAll(source.getAttendanceScores());
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            personTags.add(tag.toModelType());
        }
        final List<Assignment> personAssignments = new ArrayList<>();
        for (JsonAdaptedAssignment assignment : assignments) {
            personAssignments.add(assignment.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (matricNumber == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    MatricNumber.class.getSimpleName()));
        }
        if (!MatricNumber.isValidMatricNumber(matricNumber)) {
            throw new IllegalValueException(MatricNumber.MESSAGE_CONSTRAINTS);
        }
        final MatricNumber modelMatricNumber = new MatricNumber(matricNumber);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (telegramHandle == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TelegramHandle.class.getSimpleName()));
        }
        if (!TelegramHandle.isValidTelegramHandle(telegramHandle)) {
            throw new IllegalValueException(TelegramHandle.MESSAGE_CONSTRAINTS);
        }
        final TelegramHandle modelTelegramHandleHandle = new TelegramHandle(telegramHandle);

        final Set<Tag> modelTags = new HashSet<>(personTags);

        final List<Integer> modelParticipationScores = new ArrayList<>(participationScores);
        final List<Integer> modelAttendanceScores = new ArrayList<>(attendanceScores);

        final List<Assignment> modelAssignments = new ArrayList<>(personAssignments);

        return new Person(modelName, modelMatricNumber, modelEmail, modelTelegramHandleHandle,
         modelTags, modelAssignments, modelParticipationScores, modelAttendanceScores);
    }

}
