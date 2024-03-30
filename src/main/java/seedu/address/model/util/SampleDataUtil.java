package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Assignment;
import seedu.address.model.person.Email;
import seedu.address.model.person.MatricNumber;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.TelegramHandle;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new MatricNumber("A1234567Z"), new Email("alexyeoh@u.nus.edu"),
                new TelegramHandle("yeohyeoh01"), getAssignmentList("Assignment1"),
                getTagSet("G19Group1")),
            new Person(new Name("Bernice Yu"), new MatricNumber("A2020202Y"), new Email("berniceyu@u.nus.edu"),
                new TelegramHandle("yub3rn"), getAssignmentList("Assignment1"),
                getTagSet("G19Group1")),
            new Person(new Name("Charlotte Oliveiro"), new MatricNumber("A3344556X"), new Email("charlotte@u.nus.edu"),
                new TelegramHandle("charlottsweb99"), getAssignmentList("Assignment1"),
                getTagSet("G19Group1")),
            new Person(new Name("David Li"), new MatricNumber("A2342345Y"), new Email("lidavid@u.nus.edu"),
                new TelegramHandle("G19Group2"), getAssignmentList("Assignment1"),
                getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new MatricNumber("A0000111Z"), new Email("irfan@u.nus.edu"),
                new TelegramHandle("iiirfan"), getAssignmentList("Assignment1"),
                getTagSet("G19Group2")),
            new Person(new Name("Roy Balakrishnan"), new MatricNumber("A7654321Y"), new Email("royb@u.nus.edu"),
                new TelegramHandle("ohboyroy"), getAssignmentList("Assignment1"),
                getTagSet("G19Group2"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a list of assignments containing the list of strings given.
     */
    public static List<Assignment> getAssignmentList(String... strings) {
        return Arrays.stream(strings)
                .map(Assignment::new)
                .collect(Collectors.toList());
    }

}
