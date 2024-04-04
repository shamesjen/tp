package seedu.address.ui;

import java.util.Comparator;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

/**
 * A UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label matricNumber;
    @FXML
    private Label telegramHandle;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane assignments;
    @FXML
    private FlowPane attendance;
    @FXML
    private FlowPane participation;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        matricNumber.setText(person.getMatricNumber().value);
        telegramHandle.setText("@" + person.getTelegramHandle().value);
        email.setText(person.getEmail().value);
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        List<Integer> participationScores = person.getParticipationScores();
        List<Integer> attendanceScores = person.getAttendanceScores();
        for (int i = 0; i < participationScores.size(); i++) {
            Label participationLabel = new Label("Week " + (i + 3) + ": " + participationScores.get(i) + " ");
            Label attendanceLabel = new Label("Week " + (i + 3));
            if (participationScores.get(i) == 0) {
                participationLabel.setStyle("-fx-background-color: red");
            } else {
                participationLabel.setStyle("-fx-background-color: green");
            }

            if (attendanceScores.get(i) == 0) {
                attendanceLabel.setStyle("-fx-background-color: red");
            } else {
                attendanceLabel.setStyle("-fx-background-color: green");
            }
            participation.getChildren().add(participationLabel);
            attendance.getChildren().add(attendanceLabel);
        }

        // diplay red if score is zero, green otherwise
        if (person.getAssignments().isEmpty()) {
            Label label = new Label("No assignments");
            label.setStyle("-fx-background-color: red");
            assignments.getChildren().add(label);
        } else {
            // space out assignments
            person.getAssignments().stream()
                .sorted(Comparator.comparing(assignment -> assignment.assignmentName))
                .forEach(assignment -> {
                    Label label = new Label(assignment.assignmentName + ": " + assignment.getScore() + ", ");
                    if (assignment.getScore() == 0) {
                        label.setStyle("-fx-background-color: orange");
                    } else {
                        label.setStyle("-fx-background-color: green");
                    }
                    assignments.getChildren().add(label);
                });
        }

    }
}
