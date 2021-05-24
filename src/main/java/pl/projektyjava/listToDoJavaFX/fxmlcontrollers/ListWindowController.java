package pl.projektyjava.listToDoJavaFX.fxmlcontrollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Scope("prototype")
public class ListWindowController {

    MainScreenController mainScreenController;

    @FXML
    private ListView<String> listView;

    @FXML
    private Button addingButton;

    @FXML
    private Button removeButton;

    @FXML
    private Button modifyButton;

    public void setMainScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }
    public void initialize(){

        listView.getItems().addAll("asd","dupanek", "zupa");
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        addingButton.setOnAction(t -> {
            try {
                mainScreenController.loadAddingWindow();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }


//
//    private void openAddingWindow(ActionEvent event) {
//
//    }
}
