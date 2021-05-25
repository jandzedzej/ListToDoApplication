package pl.projektyjava.listToDoJavaFX.fxmlcontrollers;

import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.projektyjava.listToDoJavaFX.body.Task;
import pl.projektyjava.listToDoJavaFX.body.TaskRepository;

import java.io.IOException;
import java.util.*;

@Component
@Scope("prototype")
public class ListWindowController {

    //CONNECT WITH DATABASE
    @Autowired
    TaskRepository taskRepository;


    AddingWindowController addingWindowController;

    public void setAddingWindowController(AddingWindowController addingWindowController) {
        this.addingWindowController = addingWindowController;
    }

    //REFERENCE TO MAIN SCREEN
    MainScreenController mainScreenController;

    public void setMainScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }



    @FXML
    private TableView<Task> tableView;


    @FXML
    private TableColumn<Task, String> titleColumn;

    @FXML
    private TableColumn<Task, String> descriptionColumn;

    @FXML
    private TableColumn<Task, Double> priorityColumn;

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
        removeButton.setOnAction(this::removeTsk);
        addingButton.setOnAction(t -> {
            mainScreenController.loadAddingWindow();
        });
        modifyButton.setOnAction(this::modifyFocusedCell);

    }

    private void modifyFocusedCell(ActionEvent event) {

    }

    //REMOVE FOCUSED TASK FROM LIST
    private void removeTsk(ActionEvent event) {
        Task focusedItem = tableView.getFocusModel().getFocusedItem();
        taskRepository.delete(focusedItem);
        addingWindowController.openListOfQuest();
    }

    //SHOWING SORTED TASKS (BY PRIORITY)
    private void showTasks() {
        List<Task> listOfTasks = new ArrayList<>();
        titleColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("description"));
        priorityColumn.setCellValueFactory(new PropertyValueFactory<Task, Double>("priority"));
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        Iterable<Task> all = taskRepository.findAll();
        all.forEach(t -> listOfTasks.add(t));
        listOfTasks.sort(new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return -(int) (o1.getPriority() - o2.getPriority());
            }
        });
        listOfTasks.stream().forEach(t -> tableView.getItems().add(t));

    }


}



