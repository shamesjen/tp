package seedu.address.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.person.Assignment;
import seedu.address.model.person.Email;
import seedu.address.model.person.MatricNumber;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.TelegramHandle;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_MATRIC_NUMBER = "A1234567Z";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_TELEGRAM_HANDLE = "yeohyeoh01";

    private Name name;
    private MatricNumber matricNumber;
    private Email email;
    private TelegramHandle telegramHandle;
    private Set<Tag> tags;
    private List<Integer> attendanceScores = new ArrayList<>();
    private List<Integer> participationScores = new ArrayList<>();
    private List<Assignment> assignments;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        matricNumber = new MatricNumber(DEFAULT_MATRIC_NUMBER);
        email = new Email(DEFAULT_EMAIL);
        telegramHandle = new TelegramHandle(DEFAULT_TELEGRAM_HANDLE);
        tags = new HashSet<>();
        assignments = new ArrayList<>();

        for (int i = 0; i < 11; i++) {
            participationScores.add(0);
            attendanceScores.add(0);
        }
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        matricNumber = personToCopy.getMatricNumber();
        email = personToCopy.getEmail();
        telegramHandle = personToCopy.getTelegramHandle();
        tags = new HashSet<>(personToCopy.getTags());
        participationScores = personToCopy.getParticipationScores();
        attendanceScores = personToCopy.getAttendanceScores();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code TelegramHandle} of the {@code Person} that we are building.
     */
    public PersonBuilder withTelegramHandle(String telegramHandle) {
        this.telegramHandle = new TelegramHandle(telegramHandle);
        return this;
    }

    /**
     * Sets the {@code MatricNumber} of the {@code Person} that we are building.
     */
    public PersonBuilder withMatricNumber(String matricNumber) {
        this.matricNumber = new MatricNumber(matricNumber);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code participationScores} of the {@code Person} that we are building.
     */
    public PersonBuilder withParticipationScores(List<Integer> participationScores) {
        this.participationScores = participationScores;
        return this;
    }

    /**
     * Sets the {@code attendanceScores} of the {@code Person} that we are building.
     */
    public PersonBuilder withAttendanceScores(List<Integer> attendanceScores) {
        this.attendanceScores = attendanceScores;
        return this;
    }

    public Person build() {
        return new Person(name, matricNumber, email, telegramHandle, assignments, tags);
    }

}
