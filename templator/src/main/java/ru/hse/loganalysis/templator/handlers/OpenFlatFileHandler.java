package ru.hse.loganalysis.templator.handlers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;

public class OpenFlatFileHandler implements EventHandler<ActionEvent> {
	
	private String stackTrace(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		return sw.toString();
	}
	
	private Alert constructExceptionDialog(Exception e) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Exception");
		alert.setHeaderText(e.getMessage());
		Label label = new Label("The exception stacktrace was:");
		TextArea textArea = new TextArea(stackTrace(e));
		textArea.setEditable(false);
		textArea.setWrapText(false);
		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);
		GridPane expContent = new GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.add(label, 0, 0);
		expContent.add(textArea, 0, 1);
		alert.getDialogPane().setExpandableContent(expContent);
		return alert;
	}
	
	@Override
	public void handle(ActionEvent event) {
		// TODO необоснованное предположение.
		MenuItem source = (MenuItem) event.getSource();
		Window window = source.getParentPopup().getOwnerWindow();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open flat logs file");
		fileChooser.getExtensionFilters().addAll(
				new ExtensionFilter("Log files", "*.log"),
				new ExtensionFilter("All files", "*.*"));
		File selectedFile = fileChooser.showOpenDialog(window);
		if (selectedFile != null) {
			try {			
				List<String> messages = Files.readAllLines(selectedFile.toPath());			
				ListView<String> view = new ListView<String>(FXCollections.observableList(messages));
				// TODO необоснованное предположение.
				BorderPane pane = (BorderPane) window.getScene().getRoot();
				pane.setCenter(view);
			} catch (IOException e) {
				Alert alert = constructExceptionDialog(e);
				alert.showAndWait();
			}
		}
	}

}
