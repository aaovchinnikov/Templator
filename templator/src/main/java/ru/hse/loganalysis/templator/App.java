package ru.hse.loganalysis.templator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;
import ru.hse.loganalysis.templator.panes.EmptySimilarMessagesPane;
import ru.hse.loganalysis.templator.panes.EmptySourceDataPane;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
 //   	new EmptySourceDataPane(640,480).showOn(stage);
		try {
	    	List<String> messages = Files.readAllLines(Paths.get("C:\\Users\\aaovchinnikov\\Documents\\system.log"));
	       	new EmptySimilarMessagesPane(640, 480, messages).showOn(stage);	        
		} catch (IOException e) {
			e.printStackTrace();
		}
    	stage.setTitle("Templator with JavaFX");        
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}