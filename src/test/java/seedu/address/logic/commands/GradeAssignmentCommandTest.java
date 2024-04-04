package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Assignment;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class GradeAssignmentCommandTest {

    private static final Assignment ASSIGNMENT_STUB1 = new Assignment("SomeAssignment");
    private static final int GRADE_STUB = 100;

    private Model model;
    
    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_GradeAssignment_success() {
        List<Assignment> assignments = new ArrayList<>();
        assignments.add(ASSIGNMENT_STUB1);
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person originalPerson = new PersonBuilder(firstPerson).withAssignments(new ArrayList<>(assignments)).build();
        model.setPerson(firstPerson, originalPerson);
        Person editedPerson = new PersonBuilder(firstPerson).withAssignments(new ArrayList<>()).build();
        GradeAssignmentCommand gradeAssignmentCommand =
            new GradeAssignmentCommand(INDEX_FIRST_PERSON, ASSIGNMENT_STUB1.assignmentName, GRADE_STUB);
        String expectedMessage = String.format(GradeAssignmentCommand.MESSAGE_SUCCESS, ASSIGNMENT_STUB1.assignmentName,
            GRADE_STUB,editedPerson.getName());
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);
        assertCommandSuccess(gradeAssignmentCommand, model, expectedMessage, expectedModel);
    }


    
}
