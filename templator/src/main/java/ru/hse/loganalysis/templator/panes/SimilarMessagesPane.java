package ru.hse.loganalysis.templator.panes;

import java.util.List;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import ru.hse.loganalysis.templator.lcs.SimilarGroup;
import ru.hse.loganalysis.templator.lcs.SubsequenceForGroup;
import ru.hse.loganalysis.templator.templates.TemplateFromGroupAndLCSubsequence;

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
	private final SimilarGroup group;
	
	/**
	 * @param width
	 * @param height
	 * @param messages
	 */
	public SimilarMessagesPane(int width, int height, List<String> messages, SimilarGroup group) {
		this.width = width;
		this.height = height;
		this.messages = messages;
		this.group = group;
	}

	/**
	 * Creates and configures menu bar of the window.
	 * @return configured new MenuBar instance
	 */
	private MenuBar createMenuBar() {
		MenuItem exit = new MenuItem("_Exit");
		exit.setOnAction(event -> {
			Platform.exit();
		});
		Menu file = new Menu("_File");
		file.getItems().add(exit);
		MenuItem load = new MenuItem("_Load placeholders");
		MenuItem save = new MenuItem("_Save placeholders");
		save.setDisable(true);
		Menu placeholders = new Menu("_Placeholders");
		placeholders.getItems().add(load);
		placeholders.getItems().add(save);
		MenuBar bar = new MenuBar(file, placeholders);
		return bar;
	}

	/**
	 * @implNote GridPane uses maxSize property instead of prefferedSize, 
	 * using preferredSize makes no effect or weird behavior 
	 */
	private TitledPane createTemplateView(Stage stage) {
		List<String> similarMessages = this.group.group();
		while (similarMessages.size() == 1) {
			similarMessages = group.group();
		}
		if (similarMessages.isEmpty()) {
			//TODO implement this behavior
			throw new IllegalStateException("similarMessages is empty");
		} 
		String lcSequence = new SubsequenceForGroup(similarMessages).subsequence();
		String template = new TemplateFromGroupAndLCSubsequence(similarMessages, lcSequence).template(); 
		ListView<String> view = new ListView<String>(FXCollections.observableList(similarMessages));
		Label generatedLabel = new Label("Generated template:");
		Button similar = new Button("Next group of similar messages");
		similar.setOnAction(event -> {
			Pane pane = new SimilarMessagesPane(this.width, this.height, this.messages, this.group);
			pane.showOn(stage);
		});
		TextField generatedText = new TextField(template);
		generatedText.setDisable(true);
		Label userLabel = new Label("User template:");
		Button copy = new Button("Copy generated template");
		TextField userText = new TextField();
		GridPane grid = new GridPane();
		ColumnConstraints col0 = new ColumnConstraints();
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setHgrow(Priority.ALWAYS);
		col1.setHalignment(HPos.RIGHT);
		grid.getColumnConstraints().add(col0);		
		grid.getColumnConstraints().add(col1);
		grid.add(view, 0, 0, 2, 1);
		GridPane.setVgrow(view, Priority.ALWAYS);
		grid.add(generatedLabel, 0, 1);
		grid.add(similar, 1, 1);
		final Insets ins = new Insets(2,0,2,2);
		GridPane.setMargin(similar, ins);
		grid.add(generatedText, 0, 2, 2, 1);
		grid.add(userLabel, 0, 3);
		grid.add(copy, 1, 3);
		GridPane.setMargin(copy, ins);
		grid.add(userText, 0, 4, 2, 1);
		TitledPane pane = new TitledPane();
		pane.setText("Groups and templates");
		pane.setCollapsible(false);
		pane.setContent(grid);
		pane.setMaxHeight(Double.MAX_VALUE);
		return pane;
	}
	
	/**
	 * @implNote GridPane uses maxSize property instead of prefferedSize, 
	 * using preferredSize makes no effect or weird behavior
	 * @return configured new TitledPane instance with placeholders tree view inside
	 */
	private TitledPane createPlaceholdersView() {
		final Insets ins5 = new Insets(5);
		final Label label = new Label("Use \"Load placeholders\" in \"Placeholders\" menu to load previously saved placeholders");
		label.setWrapText(true);
		label.setPadding(ins5);
		final TitledPane placeholders = new TitledPane();
		placeholders.setText("Placeholders");
		placeholders.setCollapsible(false);
		placeholders.setContent(label);
		placeholders.setMaxHeight(Double.MAX_VALUE);
		return placeholders;
	}
	
	/**
	 * Creates and configures AnchorPane with a "Next"-button
	 * @return configured new AnchorPane instance with a "Next"-button
	 */
	private AnchorPane createAnchorPaneWithNextButton() {
		Button next = new Button("Next");
		next.setDisable(true);
		AnchorPane.setTopAnchor(next, 10.0);
		AnchorPane.setRightAnchor(next, 10.0);
		AnchorPane.setBottomAnchor(next, 10.0);
		AnchorPane anchor = new AnchorPane(next);
		return anchor;
	}
	
	private GridPane createGirdPane() {
		GridPane root = new GridPane();
		ColumnConstraints col0 = new ColumnConstraints();
		col0.setHgrow(Priority.ALWAYS);
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setPercentWidth(RIGHT_PERCENTAGE_WIDTH);
		root.getColumnConstraints().add(col0);
		root.getColumnConstraints().add(col1);
		RowConstraints row0 = new RowConstraints();
		RowConstraints row1 = new RowConstraints();
		row1.setVgrow(Priority.ALWAYS);
		root.getRowConstraints().add(row0);
		root.getRowConstraints().add(row1);
		return root;
	}
	
	@Override
	public void showOn(Stage stage) {
		GridPane root = createGirdPane();
		MenuBar bar = createMenuBar();
		root.add(bar, 0, 0, 2, 1);
		TitledPane template = createTemplateView(stage);
		root.add(template, 0, 1);
		GridPane.setMargin(template, new Insets(5));
		AnchorPane anchor = createAnchorPaneWithNextButton();
		root.add(anchor, 1, 2);
		TitledPane placeholders = createPlaceholdersView();
		root.add(placeholders, 1, 1);
		final Insets ins5 = new Insets(5);
		GridPane.setMargin(placeholders, ins5);
		stage.setScene(new Scene(root, this.width, this.height));
	}
}
