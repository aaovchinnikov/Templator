package ru.hse.loganalysis.templator.panes;

import java.util.List;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SourceDataPaneWithMessages implements Pane{
	private final int width;
	private final int height;
	private final List<String> messages;
	
	/**
	 * @param width
	 * @param height
	 * @param messages
	 */
	public SourceDataPaneWithMessages(int width, int height, List<String> messages) {
		this.width = width;
		this.height = height;
		this.messages = messages;
	}


	@Override
	public void showOn(Stage stage) {
		Label label = new Label("This is SourceDataPaneWithMessages");
		BorderPane root = new BorderPane(label);
		stage.setScene(new Scene(root, this.width, this.height));		
	}
}
