package ru.hse.loganalysis.templator.panes;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * First screen of the Templator application, displaying the {@link List} of {@link String Strings} provided in constructor.<br />
 * Tries to be immutable, but internal JavaFX objects may be accessed with FX-objects tree traversing.<br />
 * Pressing on "Next" button creates new instance of Pane. 
 * 
 * @author Alexander Ovchinnikov
 *
 */
public class SourceDataPaneWithMessages implements Pane {
	private final int width;
	private final int height;
	private final MenuBar bar;
	private final List<String> messages;

	/**
	 * Constructor
	 * 
	 * @param width
	 * @param height
	 * @param messages
	 */
	public SourceDataPaneWithMessages(int width, int height, MenuBar bar, List<String> messages) {
		this.width = width;
		this.height = height;
		this.bar = bar;
		this.messages = messages;
	}

	@Override
	public void showOn(Stage stage) {
		BorderPane root = new BorderPane();
		ListView<String> view = new ListView<String>(FXCollections.observableList(this.messages));
		Button next = new Button("Next");
		next.setOnAction(event -> {
			Pane pane = new EmptySimilarMessagesPane(this.width, this.height, this.messages);
			pane.showOn(stage);
		});
		AnchorPane.setTopAnchor(next, 10.0);
		AnchorPane.setRightAnchor(next, 10.0);
		AnchorPane.setBottomAnchor(next, 10.0);
		AnchorPane anchor = new AnchorPane(next);
		root.setTop(this.bar);
		root.setCenter(view);
		root.setBottom(anchor);
		stage.setScene(new Scene(root, this.width, this.height));
	}
}
