package pl.projektyjava.listToDoJavaFX;


import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import pl.projektyjava.listToDoJavaFX.appinitialization.StarterClass;


@SpringBootApplication
public class ListToDoJavaFxApplication  {

	public static void main(String[] args) {
		Application.launch(StarterClass.class, args);

	}
}
