package ru.hse.loganalysis.templator.handlers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
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
			try {
				// TODO implement displaying of messeges
				List<String> messages = Files.readAllLines(selectedFile.toPath());
			} catch (IOException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Exception Dialog");
				alert.setHeaderText("Look, an Exception Dialog");
				alert.setContentText("Could not find file blabla.txt!");
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				e.printStackTrace(pw);
				String exceptionText = sw.toString();
				Label label = new Label("The exception stacktrace was:");
				TextArea textArea = new TextArea(exceptionText);
				textArea.setEditable(false);
				textArea.setWrapText(true);
				textArea.setMaxWidth(Double.MAX_VALUE);
				textArea.setMaxHeight(Double.MAX_VALUE);
				GridPane.setVgrow(textArea, Priority.ALWAYS);
				GridPane.setHgrow(textArea, Priority.ALWAYS);
				GridPane expContent = new GridPane();
				expContent.setMaxWidth(Double.MAX_VALUE);
				expContent.add(label, 0, 0);
				expContent.add(textArea, 0, 1);
				// Set expandable Exception into the dialog pane.
				alert.getDialogPane().setExpandableContent(expContent);
				alert.showAndWait();
			}
		} else {
			System.out.println("Nothing selected");
		}
	}

}
