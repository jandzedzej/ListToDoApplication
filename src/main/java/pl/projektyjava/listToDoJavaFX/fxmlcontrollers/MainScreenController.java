package pl.projektyjava.listToDoJavaFX.fxmlcontrollers;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.projektyjava.listToDoJavaFX.appinitialization.StarterClass;

import java.io.IOException;

@Component
@Scope("prototype")
public class MainScreenController {

    @Autowired
    private ApplicationContext applicationContext ;

    @FXML
    private StackPane mainScreen;


    public void initialize() throws IOException {


        HBox hBox=null;
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/addingWindow.fxml"));
        loader.setControllerFactory(applicationContext::getBean);
        hBox=loader.load();
        AddingWindowController addingWindowController=loader.getController();
        addingWindowController.setMainScreenController(this);
        setScreen(hBox);

    }

    public void setScreen(HBox hbox) {
        mainScreen.getChildren().clear();
        mainScreen.getChildren().add(hbox);
    }

}
