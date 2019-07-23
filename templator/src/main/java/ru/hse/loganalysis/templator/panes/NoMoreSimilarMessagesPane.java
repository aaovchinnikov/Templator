package ru.hse.loganalysis.templator.panes;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class NoMoreSimilarMessagesPane implements Pane {
	private final int width;
	private final int height;

	
	
	public NoMoreSimilarMessagesPane(int width, int height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public void showOn(Stage stage) {
		// TODO Delete title setter line
		stage.setTitle("NoMoreSimilarMessagesPane Opened!");
		GridPane root = new GridPane();
		
		stage.setScene(new Scene(root, this.width, this.height));
	}

}
