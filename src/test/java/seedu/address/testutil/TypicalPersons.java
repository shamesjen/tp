package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MATRIC_NUMBER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MATRIC_NUMBER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_HANDLE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_HANDLE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final List<Integer> PARTICIPATION_SCORES_DEFAULT =
            new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
    public static final List<Integer> ATTENDANCE_SCORES_DEFAULT =
            new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withTelegramHandle("alicep01").withEmail("alice@example.com")
            .withMatricNumber("A1234567Z")
            .withTags("friends")
            .withParticipationScores(PARTICIPATION_SCORES_DEFAULT)
            .withAttendanceScores(ATTENDANCE_SCORES_DEFAULT).build();

    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withTelegramHandle("bm01")
            .withEmail("johnd@example.com").withMatricNumber("A9999999Y")
            .withTags("owesMoney", "friends")
            .withParticipationScores(PARTICIPATION_SCORES_DEFAULT)
            .withAttendanceScores(ATTENDANCE_SCORES_DEFAULT).build();

    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withMatricNumber("A2222222Y")
            .withEmail("heinz@example.com").withTelegramHandle("kurzzz")
            .withParticipationScores(PARTICIPATION_SCORES_DEFAULT)
            .withAttendanceScores(ATTENDANCE_SCORES_DEFAULT).build();;

    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withMatricNumber("A7654321Z")
            .withEmail("cornelia@example.com").withTelegramHandle("meiermeier").withTags("friends")
            .withParticipationScores(PARTICIPATION_SCORES_DEFAULT)
            .withAttendanceScores(ATTENDANCE_SCORES_DEFAULT).build();;

    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withMatricNumber("A7778887X")
            .withEmail("werner@example.com").withTelegramHandle("ellebelle")
            .withParticipationScores(PARTICIPATION_SCORES_DEFAULT)
            .withAttendanceScores(ATTENDANCE_SCORES_DEFAULT).build();;

    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withMatricNumber("A1231234Z")
            .withEmail("lydia@example.com").withTelegramHandle("ShreksWife")
            .withParticipationScores(PARTICIPATION_SCORES_DEFAULT)
            .withAttendanceScores(ATTENDANCE_SCORES_DEFAULT).build();;

    public static final Person GEORGE = new PersonBuilder().withName("George Best").withMatricNumber("A4455667Y")
            .withEmail("anna@example.com").withTelegramHandle("theBestGeorge")
            .withParticipationScores(PARTICIPATION_SCORES_DEFAULT)
            .withAttendanceScores(ATTENDANCE_SCORES_DEFAULT).build();;

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withMatricNumber("A1357531G")
            .withEmail("stefan@example.com").withTelegramHandle("BeeHoon99")
            .withParticipationScores(PARTICIPATION_SCORES_DEFAULT)
            .withAttendanceScores(ATTENDANCE_SCORES_DEFAULT).build();;

    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withMatricNumber("A7788776H")
            .withEmail("hans@example.com").withTelegramHandle("IdrisIda")
            .withParticipationScores(PARTICIPATION_SCORES_DEFAULT)
            .withAttendanceScores(ATTENDANCE_SCORES_DEFAULT).build();;

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY)
            .withMatricNumber(VALID_MATRIC_NUMBER_AMY).withEmail(VALID_EMAIL_AMY)
            .withTelegramHandle(VALID_TELEGRAM_HANDLE_AMY).withTags(VALID_TAG_FRIEND)
            .withParticipationScores(PARTICIPATION_SCORES_DEFAULT)
            .withAttendanceScores(ATTENDANCE_SCORES_DEFAULT).build();;

    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB)
            .withMatricNumber(VALID_MATRIC_NUMBER_BOB).withEmail(VALID_EMAIL_BOB)
            .withTelegramHandle(VALID_TELEGRAM_HANDLE_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .withParticipationScores(PARTICIPATION_SCORES_DEFAULT)
            .withAttendanceScores(ATTENDANCE_SCORES_DEFAULT).build();;

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
