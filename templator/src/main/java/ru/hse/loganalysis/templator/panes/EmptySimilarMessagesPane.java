package ru.hse.loganalysis.templator.panes;

import java.util.List;

import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class EmptySimilarMessagesPane implements Pane {
	private static final double RIGHT_PERCENTAGE_WIDTH = 25.0;
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

	private void addMenuBarToTopOfPane(GridPane root) {
		MenuItem exit = new MenuItem("_Exit");
		exit.setOnAction(event -> {
			Platform.exit();
		});
		Menu file = new Menu("_File");
		file.getItems().add(exit);
		MenuItem load = new MenuItem("_Load placeholders");
		MenuItem save = new MenuItem("_Save placeholders");
		Menu placeholders = new Menu("_Placeholders");
		placeholders.getItems().add(load);
		placeholders.getItems().add(save);
		MenuBar bar = new MenuBar(file, placeholders);
		root.add(bar, 0, 0, 2, 1);
	}

	private void addTemplatesToCenterOfPane(GridPane root) {
		Label label = new Label(
				"Press \"Next group of similar messages\" to generate next template based on loaded messages");
		Label generatedLabel = new Label("Generated template:");
		Button similar = new Button("Next group of similar messages");
		TextField generatedText = new TextField();
		generatedText.setDisable(true);
		Label userLabel = new Label("User template:");
		Button copy = new Button("Copy generated template");
		copy.setDisable(true);
		TextField userText = new TextField();
		userText.setDisable(true);
		GridPane grid = new GridPane();
		ColumnConstraints col0 = new ColumnConstraints();
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setHgrow(Priority.ALWAYS);
		col1.setHalignment(HPos.RIGHT);
		grid.getColumnConstraints().add(col0);		
		grid.getColumnConstraints().add(col1);
		grid.add(label, 0, 0, 2, 1);
		GridPane.setVgrow(label, Priority.ALWAYS);
		GridPane.setHalignment(label, HPos.CENTER);
		grid.add(generatedLabel, 0, 1);
		grid.add(similar, 1, 1);
		final Insets ins = new Insets(2,0,2,2);
		GridPane.setMargin(similar, ins);
		grid.add(generatedText, 0, 2, 2, 1);
		grid.add(userLabel, 0, 3);
		grid.add(copy, 1, 3);
		GridPane.setMargin(copy, ins);
		grid.add(userText, 0, 4, 2, 1);
		TitledPane templates = new TitledPane();
		templates.setText("Groups and templates");
		templates.setCollapsible(false);
		templates.setContent(grid);
//		root.add(templates, 0, 1);
		GridPane.setMargin(templates, new Insets(5));
	}

	/**
	 * @param root
	 * @implNote GridPane uses maxSize property instead of prefferedSize, 
	 * using preferredSize makes no effect or weird behavior 
	 */
	private void addPlaceholdersViewToRightOfPane(GridPane root) {
		Label label = new Label("Use \"Load placeholders\" in \"Placeholders\" menu to load previously saved placeholders");
		label.setWrapText(true);
		TitledPane placeholders = new TitledPane();
		placeholders.setText("Placeholders");
		placeholders.setCollapsible(false);
		placeholders.setContent(label);
		placeholders.setMaxHeight(Double.MAX_VALUE);
		root.add(placeholders, 0, 1);
		GridPane.setMargin(placeholders, new Insets(5));
//		GridPane.setHalignment(label, HPos.CENTER);
	}

	private void addNextButtonToBottomOfPane(GridPane root) {
		Button next = new Button("Next");
		next.setDisable(true);
		AnchorPane.setTopAnchor(next, 10.0);
		AnchorPane.setRightAnchor(next, 10.0);
		AnchorPane.setBottomAnchor(next, 10.0);
		AnchorPane anchor = new AnchorPane(next);
		root.add(anchor, 1, 2);
	}

	@Override
	public void showOn(Stage stage) {
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
		addMenuBarToTopOfPane(root);
		addTemplatesToCenterOfPane(root);
		addNextButtonToBottomOfPane(root);
		addPlaceholdersViewToRightOfPane(root);
		stage.setScene(new Scene(root, this.width, this.height));
	}
}
