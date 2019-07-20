package ru.hse.loganalysis.templator.panes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import ru.hse.loganalysis.templator.controls.ExceptionAlert;

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

	private void createMenuBarOnTop(BorderPane root, Stage stage) {
		MenuItem plain = new MenuItem("Open _plain log file");
		plain.setOnAction(event -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open flat logs file");
			fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Log files", "*.log"),
					new ExtensionFilter("All files", "*.*"));
			File selectedFile = fileChooser.showOpenDialog(stage);
			if (selectedFile != null) {
				try {
					List<String> messages = Files.readAllLines(selectedFile.toPath());
					Pane pane = new SourceDataPaneWithMessages(this.width, this.height, messages);
					pane.showOn(stage);
				} catch (IOException e) {
					ExceptionAlert alert = new ExceptionAlert(e);
					alert.showAndWait();
				}
			}
		});
		SeparatorMenuItem separator = new SeparatorMenuItem();
		MenuItem exit = new MenuItem("_Exit");
		exit.setOnAction(event -> {
			Platform.exit();
		});
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
		createMenuBarOnTop(root, stage);
		Label label = new Label("Open log file with \"Open log file\" menu item from \"File\" menu to proceed");
		root.setCenter(label);
		stage.setScene(new Scene(root, this.width, this.height));
	}
}
