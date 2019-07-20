package ru.hse.loganalysis.templator.panes;

import java.util.List;

import javafx.stage.Stage;

public class SourceDataPaneWithMessages implements Pane{
	private final List<String> messages;
	
	public SourceDataPaneWithMessages(List<String> messages) {
		this.messages = messages;
	}



	@Override
	public void showOn(Stage stage) {
		// TODO Auto-generated method stub
		
	}

}
