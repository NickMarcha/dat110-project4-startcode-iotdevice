package no.hvl.dat110.aciotdevice.ui;

import javafx.scene.control.Button;

public class LED extends Button {
	
	private String oncolor,offcolor;
	
	public LED(String label, String oncolor, String offcolor) {

		setStyle("-fx-background-color: " + offcolor + "; ");
		this.oncolor = oncolor;
		this.offcolor = offcolor;
	}
	
	public void on() {
		setStyle("-fx-background-color: " + oncolor + "; ");
	}
	
	public void off () {
		setStyle("-fx-background-color: " + offcolor + "; ");
	}
}
