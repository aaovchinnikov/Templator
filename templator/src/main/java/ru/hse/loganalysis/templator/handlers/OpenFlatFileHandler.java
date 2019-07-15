package ru.hse.loganalysis.templator.handlers;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;

public class OpenFlatFileHandler implements EventHandler<ActionEvent> {
	@Override
	public void handle(ActionEvent event) {
		MenuItem source = (MenuItem) event.getSource();
		Window window = source.getParentPopup().getOwnerWindow();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open flat logs file");
		fileChooser.getExtensionFilters().addAll(
				new ExtensionFilter("Log files", "*.log"),
				new ExtensionFilter("All files", "*.*"));
		File selectedFile = fileChooser.showOpenDialog(window);
		if (selectedFile != null) {
			System.out.println(selectedFile);
//			mainStage.display(selectedFile);
		} else {
			System.out.println("Nothing selected");
		}
	}

}
