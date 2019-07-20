package ru.hse.loganalysis.templator.panes;

import java.util.List;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class EmptySimilarMessagesPane implements Pane {
	private final int width;
	private final int height;
	private final List<String> messages;
	
	/**
	 * @param width
	 * @param height
	 * @param messages
	 */
	public EmptySimilarMessagesPane(int width, int height, List<String> messages) {
		this.width = width;
		this.height = height;
		this.messages = messages;
	}

	@Override
	public void showOn(Stage stage) {
		BorderPane root = new BorderPane();
		Label label = new Label("This is EmptySimilarMessagesPane");
		root.setCenter(label);
		stage.setScene(new Scene(root, this.width, this.height));
	}

}
