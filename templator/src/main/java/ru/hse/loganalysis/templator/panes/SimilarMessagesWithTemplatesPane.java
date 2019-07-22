package ru.hse.loganalysis.templator.panes;

import java.util.List;

import javafx.stage.Stage;

public class SimilarMessagesWithTemplatesPane implements Pane {
	private static final double RIGHT_PERCENTAGE_WIDTH = 25.0;
	private final int width;
	private final int height;
	private final List<String> messages;

	/**
	 * @param width
	 * @param height
	 * @param messages
	 */
	public SimilarMessagesWithTemplatesPane(int width, int height, List<String> messages) {
		this.width = width;
		this.height = height;
		this.messages = messages;
	}



	@Override
	public void showOn(Stage stage) {
		// TODO Auto-generated method stub

	}

}
