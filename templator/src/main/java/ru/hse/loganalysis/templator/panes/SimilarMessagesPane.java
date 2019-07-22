package ru.hse.loganalysis.templator.panes;

import java.util.List;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SimilarMessagesPane implements Pane {
	private static final double RIGHT_PERCENTAGE_WIDTH = 25.0;
	private final int width;
	private final int height;
	private final List<String> messages;	
	
	/**
	 * @param width
	 * @param height
	 * @param messages
	 */
	public SimilarMessagesPane(int width, int height, List<String> messages) {
		this.width = width;
		this.height = height;
		this.messages = messages;
	}

	@Override
	public void showOn(Stage stage) {
		// TODO Delete title setter line
		stage.setTitle("SimilarMessagesPane");
		GridPane root = new GridPane();
		
		stage.setScene(new Scene(root, this.width, this.height));
	}

}
