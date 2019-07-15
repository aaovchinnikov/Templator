package ru.hse.loganalysis.templator.controls;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;

public class OpenFlatFileMenuItem extends MenuItem {
	
	public OpenFlatFileMenuItem(EventHandler<ActionEvent> handler) {
		super("Open _Flat File");
		setOnAction(handler);
	}
}
