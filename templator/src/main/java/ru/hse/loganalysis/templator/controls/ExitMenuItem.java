package ru.hse.loganalysis.templator.controls;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;

public class ExitMenuItem extends MenuItem {

	public ExitMenuItem(EventHandler<ActionEvent> handler) {
		super("_Exit");
		setOnAction(handler);
	}
}
