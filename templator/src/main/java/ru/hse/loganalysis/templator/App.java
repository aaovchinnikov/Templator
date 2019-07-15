package ru.hse.loganalysis.templator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ru.hse.loganalysis.templator.controls.ExitMenuItem;
import ru.hse.loganalysis.templator.controls.OpenFlatFileMenuItem;
import ru.hse.loganalysis.templator.handlers.ExitHandler;
import ru.hse.loganalysis.templator.handlers.OpenFlatFileHandler;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
    	BorderPane root = new BorderPane();
    	createContent(root);
        Scene scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.setTitle("Templator with JavaFX");
        stage.show();
    }

    private void createContent(BorderPane root) {
    	OpenFlatFileMenuItem flat = new OpenFlatFileMenuItem(new OpenFlatFileHandler());
    	SeparatorMenuItem separator = new SeparatorMenuItem();
    	ExitMenuItem exit = new ExitMenuItem(new ExitHandler());
		Menu menu = new Menu("_File");
		menu.getItems().add(flat);
		menu.getItems().add(separator);		
		menu.getItems().add(exit);
		MenuBar bar = new MenuBar(menu);
		root.setTop(bar);
	}

	public static void main(String[] args) {
        launch();
    }

}