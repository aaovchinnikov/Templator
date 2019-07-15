package ru.hse.loganalysis.templator.handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ExitHandler implements EventHandler<ActionEvent> {
	@Override
	public void handle(ActionEvent event) {
		System.exit(0);
	}
}
