package ru.hse.loganalysis.templator.panes;

import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
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

	private void addNextButtonToBottomOfPane(BorderPane root) {
		Button next = new Button("Next");
		AnchorPane.setTopAnchor(next, 10.0);
		AnchorPane.setRightAnchor(next, 10.0);
		AnchorPane.setBottomAnchor(next, 10.0);
		AnchorPane anchor = new AnchorPane(next);	
		root.setBottom(anchor);		
	}
	
	@Override
	public void showOn(Stage stage) {
		BorderPane root = new BorderPane();
		Label label = new Label("Press \"Next group of similar messages\" to generate next template based on loaded messages");
		VBox.setVgrow(label, Priority.ALWAYS);
		Button similar = new Button("Next group of similar messages");
		Label generatedLabel = new Label("Generated template:");
		TextField generatedText = new TextField();
		generatedText.setDisable(true);
		Label userLabel = new Label("User template:");		
		TextField userText = new TextField();
		userText.setDisable(true);
		VBox central = new VBox(label,similar,generatedLabel,generatedText,userLabel,userText);
		central.setAlignment(Pos.BOTTOM_LEFT);
		addNextButtonToBottomOfPane(root);
		root.setCenter(central);
		stage.setScene(new Scene(root, this.width, this.height));
	}
}
