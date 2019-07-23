package ru.hse.loganalysis.templator.panes;

import java.util.Iterator;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Represents UI screen with group of similar log messages and offered template for this messages
 * @author aaovchinnikov
 *
 */
public class SimilarMessagesPane implements Pane {
	private static final double RIGHT_PERCENTAGE_WIDTH = 25.0;
	private final int width;
	private final int height;
	private final List<String> messages;
	private final List<String> similarMessages;
	private final String template;
	private final Iterator<String> iterator;
	
	/**
	 * @param width
	 * @param height
	 * @param messages
	 */
	public SimilarMessagesPane(int width, int height, List<String> messages, List<String> similarMessages, String template, Iterator<String> iterator) {
		this.width = width;
		this.height = height;
		this.messages = messages;
		this.similarMessages = similarMessages;
		this.template = template;
		this.iterator = iterator;
	}

	@Override
	public void showOn(Stage stage) {
		// TODO Delete title setter line
		stage.setTitle("SimilarMessagesPane");
		GridPane root = new GridPane();
		
		stage.setScene(new Scene(root, this.width, this.height));
	}

}
