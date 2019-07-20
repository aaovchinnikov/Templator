package ru.hse.loganalysis.templator;

import javafx.application.Application;
import javafx.stage.Stage;
import ru.hse.loganalysis.templator.panes.EmptySourceDataPane;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
    	new EmptySourceDataPane(640,480).showOn(stage);
        stage.setTitle("Templator with JavaFX");        
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}