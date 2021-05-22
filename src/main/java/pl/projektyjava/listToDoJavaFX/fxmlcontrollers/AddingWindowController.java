package pl.projektyjava.listToDoJavaFX.fxmlcontrollers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.projektyjava.listToDoJavaFX.body.Task;
import pl.projektyjava.listToDoJavaFX.body.TaskRepository;

@Component
@Scope("prototype")
public class AddingWindowController {

    @Autowired
    TaskRepository taskRepository;

    private boolean incorrect;

    @FXML
    private TextField title;

    @FXML
    private TextArea description;

    @FXML
    private Button listOfQuest;

    @FXML
    private Button addQuest;

    @FXML
    private Slider priority;

    public void initialize() {
        addQuest.setOnAction(this::saveButtonAction);

        title.addEventFilter(MouseEvent.MOUSE_CLICKED, this::emptyTitleCorrect);

        description.addEventFilter(KeyEvent.KEY_RELEASED, this::saveKeyAction);
    }

    private void saveKeyAction(KeyEvent event) {

        String descriptionText = description.getText();
        if (descriptionText.endsWith("\n")) {
            String titleText = title.getText();
            descriptionText.trim();
            double priorityVal = priority.getValue();
            saveTask();
        }

    }


    private void emptyTitleCorrect(MouseEvent event) {
        if (incorrect) {
            title.clear();
            incorrect = false;
        }
    }

    private void saveButtonAction(ActionEvent event) {
        saveTask();
    }

    private void saveTask() {
        String titleText = title.getText();
        String descriptionText = description.getText();
        double priorityVal = priority.getValue();

        if (titleText.equals("")||incorrect) {
            title.setText("Wpisz tytuł zadania!");
            incorrect = true;
            description.clear();
            descriptionText.trim();
            description.setText(descriptionText);
        } else {
            Task task = new Task(titleText, descriptionText, priorityVal);
            taskRepository.save(task);
            title.clear();
            description.clear();
            priority.setValue(0);

        }
    }
}
