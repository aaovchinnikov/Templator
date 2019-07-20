package ru.hse.loganalysis.templator.panes;

import javafx.stage.Stage;

public interface Pane {
	/**
	 * Creates and shows new Scene with interface controls.<br /> 
	 * @implNote Assumes {@link Stage#setScene(Scene)} is called as the last line.
	 * 
	 * @param stage - stage, where the scene should to be displayed
	 */
	void showOn(Stage stage);
}
