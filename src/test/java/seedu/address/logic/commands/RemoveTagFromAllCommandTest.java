package seedu.address.logic.commands;


import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashSet;
import javafx.util.Pair;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class RemoveTagFromAllCommandTest {

    private static final String TAG_STUB_1 = "Sometag1";

    private static final String TAG_STUB_2 = "Sometag2";

    private static final String TAG_STUB_3 = "Sometag3";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    private ArrayList<Pair<Person, List<Tag>>> removedTagList = new ArrayList<Pair<Person, List<Tag>>>();

    @Test
    public void execute_removeTagFilteredList_success() {
        //remove from all persons in the list
        for (Person person : model.getFilteredPersonList()) {
            Person originalPerson = new PersonBuilder(person).withTags(TAG_STUB_1, TAG_STUB_2, TAG_STUB_3).build();
            model.setPerson(person, originalPerson);
            removedTagList.add(new Pair<Person, List<Tag>>(originalPerson,
                new ArrayList<Tag>(Arrays.asList(new Tag(TAG_STUB_2), new Tag(TAG_STUB_3)))));
        }
        Set<Tag> tags = new HashSet<Tag>();
        tags.add(new Tag(TAG_STUB_2));
        tags.add(new Tag(TAG_STUB_3));
        RemoveTagFromAllCommand removeTagCommand = new RemoveTagFromAllCommand(tags);

        String expectedMessage = String.format(RemoveTagFromAllCommand.MESSAGE_REMOVE_TAG_SUCCESS,
        RemoveTagFromAllCommand.formatRemovedTags(removedTagList));
        
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        for (Person person : model.getFilteredPersonList()) {
            Person editedPerson = new PersonBuilder(person).withTags(TAG_STUB_1).build();
            expectedModel.setPerson(person, editedPerson);
        }

        assertCommandSuccess(removeTagCommand, model, expectedMessage, expectedModel);

    }

    
}
