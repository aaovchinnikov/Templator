package ru.hse.loganalysis.templator.panes;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ru.hse.loganalysis.templator.handlers.ExitHandler;
import ru.hse.loganalysis.templator.handlers.OpenPlainLogFileHandler;

public class EmptySourceDataPane implements Pane {
	private final int width;
	private final int height;

	/**
	 * @param width
	 * @param height
	 */
	public EmptySourceDataPane(int width, int height) {
		this.width = width;
		this.height = height;
	}

	private void createMenuBarOnTop(BorderPane root) {
		MenuItem plain = new MenuItem("Open _plain log file");
		plain.setOnAction(new OpenPlainLogFileHandler());
		SeparatorMenuItem separator = new SeparatorMenuItem();
		MenuItem exit = new MenuItem("_Exit");
		exit.setOnAction(new ExitHandler());
		Menu menu = new Menu("_File");
		menu.getItems().add(plain);
		menu.getItems().add(separator);
		menu.getItems().add(exit);
		MenuBar bar = new MenuBar(menu);
		root.setTop(bar);
	}

	@Override
	public void showOn(Stage stage) {
		BorderPane root = new BorderPane();
		createMenuBarOnTop(root);
		stage.setScene(new Scene(root, this.width, this.height));
	}
}
