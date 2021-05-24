package pl.projektyjava.listToDoJavaFX.fxmlcontrollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ListWindowController {

    //MainScreenController mainScreenController;

    @FXML
    private Button addingButton;

    @FXML
    private Button removeButton;

    @FXML
    private Button modifyButton;

//    public void setMainScreenController(MainScreenController mainScreenController) {
//        this.mainScreenController = mainScreenController;
//    }
//    void initialize(){
//        addingButton.setOnAction(this::openAddingWindow);
//
//    }
//
//    private void openAddingWindow(ActionEvent event) {
//
//    }
}
