package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    private static final int NUMBER_OF_WEEKS = 11;

    // Identity fields
    private final Name name;
    private final MatricNumber matricNumber;
    private final Email email;
    private final TelegramHandle telegramHandle;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();
    private final List<Assignment> assignments;
    private final List<Integer> participationScores;
    private final List<Integer> attendanceScores;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, MatricNumber matricNumber, Email email, TelegramHandle telegramHandle, List<Assignment> assignments, Set<Tag> tags) {
        requireAllNonNull(name, matricNumber, email, telegramHandle, tags);
        this.name = name;
        this.matricNumber = matricNumber;
        this.email = email;
        this.telegramHandle = telegramHandle;
        this.tags.addAll(tags);
        this.participationScores = new ArrayList<>();
        this.assignments = assignments;
        this.attendanceScores = new ArrayList<>();

        for (int i = 0; i < NUMBER_OF_WEEKS; i++) {
            participationScores.add(0);
            attendanceScores.add(0);
        }
    }

    /**
     * The overloaded Person constructor. Every field must be present and not null.
     */
    public Person(Name name, MatricNumber matricNumber, Email email, TelegramHandle telegramHandle,
            Set<Tag> tags,  List<Assignment> assignments, List<Integer> participationScores, List<Integer> attendanceScores) {
        requireAllNonNull(name, matricNumber, email, telegramHandle, tags, participationScores, attendanceScores);
        this.name = name;
        this.matricNumber = matricNumber;
        this.email = email;
        this.telegramHandle = telegramHandle;
        this.tags.addAll(tags);
        this.participationScores = participationScores;
        this.assignments = assignments;
        this.attendanceScores = attendanceScores;
    }

    public Name getName() {
        return name;
    }

    public MatricNumber getMatricNumber() {
        return matricNumber;
    }

    public Email getEmail() {
        return email;
    }

    public TelegramHandle getTelegramHandle() {
        return telegramHandle;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void addAssignment(Assignment assignment) {
        assignments.add(assignment);
    }

    // public void removeAssignment(Assignment assignment) {
    //     assignments.remove(assignment);
    // }

    public boolean hasAssignment(Assignment assignment) {
        return assignments.contains(assignment);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable participation score list, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public List<Integer> getParticipationScores() {
        return Collections.unmodifiableList(participationScores);
    }

    /**
     * Returns an immutable attendance score list, which throws (@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public List<Integer> getAttendanceScores() {
        return Collections.unmodifiableList(attendanceScores);
    }

    /**
     * Returns true if tag exists on the person.
     */
    public boolean hasTag(Tag tag) {
        return tags.contains(tag);
    }

    public void removeTag(Tag tag) {
        tags.remove(tag);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && matricNumber.equals(otherPerson.matricNumber)
                && email.equals(otherPerson.email)
                && telegramHandle.equals(otherPerson.telegramHandle)
                && tags.equals(otherPerson.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, matricNumber, email, telegramHandle, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", matricNumber)
                .add("email", email)
                .add("address", telegramHandle)
                .add("participationScores", participationScores)
                .add("assignments", assignments)
                .add("tags", tags)
                .toString();
    }

}
