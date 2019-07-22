package ru.hse.loganalysis.templator.panes;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ru.hse.loganalysis.templator.lcs.StringComparison;
import ru.hse.loganalysis.templator.metrics.LevenshteinDistance;
import ru.hse.loganalysis.templator.metrics.Metrics;
import ru.hse.loganalysis.templator.metrics.MinOfTwoStringsLength;
import ru.hse.loganalysis.templator.metrics.OverlapCoefficient;
import ru.hse.loganalysis.templator.metrics.checks.LessCheck;
import ru.hse.loganalysis.templator.metrics.checks.MetricCheck;
import ru.hse.loganalysis.templator.metrics.checks.MoreCheck;
import ru.hse.loganalysis.templator.metrics.checks.TwoChecksComposite;
import ru.hse.loganalysis.templator.metrics.checks.TwoMetricComposite;

public class EmptySimilarMessagesPane implements Pane {
	private static final double RIGHT_PERCENTAGE_WIDTH = 25.0;
	private static final int LENGTH_THRESHOLD = 150;
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
		save.setDisable(true);
		Menu placeholders = new Menu("_Placeholders");
		placeholders.getItems().add(load);
		placeholders.getItems().add(save);
		MenuBar bar = new MenuBar(file, placeholders);
		root.add(bar, 0, 0, 2, 1);
	}

	/**
	 * @param root
	 * @implNote GridPane uses maxSize property instead of prefferedSize, 
	 * using preferredSize makes no effect or weird behavior 
	 */
	private void addTemplatesToCenterOfPane(GridPane root, Stage stage) {
		Label label = new Label(
				"Press \"Next group of similar messages\" below to generate next template based on loaded messages");
		label.setWrapText(true);
		label.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, null, null)));
		label.setMaxHeight(Double.MAX_VALUE);
		label.setPadding(new Insets(5));
		Label generatedLabel = new Label("Generated template:");
		Button similar = new Button("Next group of similar messages");
		similar.setOnAction(event -> {
			System.out.println("changeGroupButton pressed");
			userTemplateText.setText("");
			//TODO Быстро переписать это говно!! Из-за него и тормозит!
			List<String> similarMessages = getNextSimilarGroup();
			while (similarMessages.size() == 1) {
				System.out.println("similarMessages.size==1");
				similarMessages = getNextSimilarGroup();
			}
			if (!similarMessages.isEmpty()) {
				setInput(similarMessages);
			} else {
				listViewer.setInput(new String[] { "No more similar groups. Seems you are stuck here. =)" });
			}

			
			// TODO this was method getNextSimilarGroup()
			List<String> similarStrings = new LinkedList<String>();
			for(String currentMessage: this.messages) {
				for (String s : this.messages) {
					MetricCheck check = new TwoChecksComposite(
							new LessCheck(new LevenshteinDistance(currentMessage, s), 20),
							new MoreCheck(new OverlapCoefficient(currentMessage, s), 90),
							new LessCheck(new MinOfTwoStringsLength(currentMessage, s), 150));
					if (check.isTrue()) {
						similarStrings.add(s);
					}
				}
				if(similarStrings.size()==1){
					break;
				}
				String lcSequence = StringComparison.computeLCSubsequenceForStringGroup(similarStrings);
				String unitedTemplate = Templates.getUnitedTemplate(similarStrings, lcSequence);
				offeredTemplateText.setText(unitedTemplate);
			}
			////////////////////////
			
			Pane pane = new SimilarMessagesPane(this.width, this.height, this.messages);
			pane.showOn(stage);
		});
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
		templates.setMaxHeight(Double.MAX_VALUE);
		root.add(templates, 0, 1);
		GridPane.setMargin(templates, new Insets(5));
	}

	/**
	 * @param root
	 * @implNote GridPane uses maxSize property instead of prefferedSize, 
	 * using preferredSize makes no effect or weird behavior 
	 */
	private void addPlaceholdersViewToRightOfPane(GridPane root) {
		final Insets ins5 = new Insets(5);
		Label label = new Label("Use \"Load placeholders\" in \"Placeholders\" menu to load previously saved placeholders");
		label.setWrapText(true);
		label.setPadding(ins5);
		TitledPane placeholders = new TitledPane();
		placeholders.setText("Placeholders");
		placeholders.setCollapsible(false);
		placeholders.setContent(label);
		placeholders.setMaxHeight(Double.MAX_VALUE);
		root.add(placeholders, 1, 1);
		GridPane.setMargin(placeholders, ins5);
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
		addTemplatesToCenterOfPane(root, stage);
		addNextButtonToBottomOfPane(root);
		addPlaceholdersViewToRightOfPane(root);
		stage.setScene(new Scene(root, this.width, this.height));
	}
}
