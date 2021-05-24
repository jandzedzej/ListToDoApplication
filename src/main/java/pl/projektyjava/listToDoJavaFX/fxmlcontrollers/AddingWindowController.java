package pl.projektyjava.listToDoJavaFX.fxmlcontrollers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.projektyjava.listToDoJavaFX.body.Task;
import pl.projektyjava.listToDoJavaFX.body.TaskRepository;

import java.io.IOException;

@Component
@Scope("prototype")
public class AddingWindowController {

    //CONNECT WITH DATABASE
    @Autowired
    TaskRepository taskRepository;

    //ADDING FXML TO SPRING CONTEXT
    @Autowired
    ApplicationContext applicationContext;

    //REFERENCE TO MAIN SCREEN
    MainScreenController mainScreenController;

    public void setMainScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

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

    //ADDING SCREEN ACTIONS
    public void initialize() {
        addQuest.setOnAction(this::saveTask);
        listOfQuest.setOnAction(this::openLisOfQuests);
        title.addEventFilter(MouseEvent.MOUSE_CLICKED, this::emptyTitleCorrect);
        description.addEventFilter(KeyEvent.KEY_RELEASED, this::saveKeyAction);
    }

    private void saveTask(ActionEvent event) {
    }

    //OPENING LIST WINDOW
    private void openLisOfQuests(ActionEvent event) {
        HBox hBox=null;
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/fxml/listWindow.fxml"));
        fxmlLoader.setControllerFactory(applicationContext::getBean);
        try {
            hBox = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ListWindowController listWindowController=fxmlLoader.getController();
        listWindowController.setMainScreenController(mainScreenController);
        mainScreenController.setScreen(hBox);
    }

    //IF ENTER CLICKED SAVE TASK
    private void saveKeyAction(KeyEvent event) {

        String descriptionText = description.getText();
        if (descriptionText.endsWith("\n")) {
            String titleText = title.getText();
            descriptionText.trim();
            double priorityVal = priority.getValue();
            saveTask();
        }

    }

    //CELAR WINDOW IF USER WANT TO WRITE TITLE
    private void emptyTitleCorrect(MouseEvent event) {
        if (incorrect) {
            title.clear();
            incorrect = false;
        }
    }
    //SAVE TASK WHEN BUTTON CLICKED
   private void saveButtonAction(ActionEvent event) {
        saveTask();
    }

    //SAVING TASK ACTION
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
