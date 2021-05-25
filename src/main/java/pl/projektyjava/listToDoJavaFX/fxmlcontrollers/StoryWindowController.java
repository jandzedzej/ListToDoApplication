package pl.projektyjava.listToDoJavaFX.fxmlcontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.projektyjava.listToDoJavaFX.body.Task;
import pl.projektyjava.listToDoJavaFX.body.TaskRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
@Scope("prototype")
public class StoryWindowController {

    //CONNECT WITH DATABASE
    @Autowired
    TaskRepository taskRepository;

    //REFERENCE TO ADDING SCREEN
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
    private Button listButton;

    public void initialize(){
//        showTasks();
        addingButton.setOnAction(t->mainScreenController.loadAddingWindow());
        listButton.setOnAction(t->addingWindowController.openListOfQuest());
    }

//    private void showTasks() {
//        List<Task> listOfTasks = new ArrayList<>();
//        titleColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("title"));
//        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("description"));
//        priorityColumn.setCellValueFactory(new PropertyValueFactory<Task, Double>("priority"));
//        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
//        Iterable<Task> all = taskRepository.findAll();
//        all.forEach(t -> listOfTasks.add(t));
//        listOfTasks.sort(new Comparator<Task>() {
//            @Override
//            public int compare(Task o1, Task o2) {
//                return -(int) (o1.getPriority() - o2.getPriority());
//            }
//        });
//        listOfTasks.stream().forEach(t -> tableView.getItems().add(t));
//    }

}
