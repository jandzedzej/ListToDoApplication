package pl.projektyjava.listToDoJavaFX.fxmlcontrollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.projektyjava.listToDoJavaFX.body.Task;
import pl.projektyjava.listToDoJavaFX.body.TaskRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Scope("prototype")
public class ListWindowController {

    //CONNECT WITH DATABASE
    @Autowired
    TaskRepository taskRepository;

    //REFERENCE TO MAIN SCREEN
    MainScreenController mainScreenController;

    public void setMainScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

    @FXML
    private ListView<Task> listView;

    @FXML
    private Button addingButton;

    @FXML
    private Button removeButton;

    @FXML
    private Button modifyButton;

    @FXML
    private Button doneButton;

    @FXML
    private Button storyButton;


    public void initialize() {

        showTasks();

        addingButton.setOnAction(t -> {
            mainScreenController.loadAddingWindow();
        });

    }

    private void showTasks() {
        // List<Task> listOfTasks=new ArrayList<>();

        Iterable<Task> all = taskRepository.findAll();
        all.forEach(t -> listView.getItems().add(t));
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


    }

    private void addTasksToListView(Task task) {

    }

}


//
//    private void openAddingWindow(ActionEvent event) {
//
//    }

