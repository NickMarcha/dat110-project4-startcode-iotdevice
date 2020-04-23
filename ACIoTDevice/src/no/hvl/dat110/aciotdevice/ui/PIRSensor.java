package no.hvl.dat110.aciotdevice.ui;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import no.hvl.dat110.aciotdevice.pins.Constants;
import no.hvl.dat110.aciotdevice.pins.IOPins;

public class PIRSensor extends Button {

	private IOPins iopins;
	int pin;

	public PIRSensor(String label, IOPins iopins, int pin) {
		
		super(label);
		this.iopins = iopins;
		this.pin = pin;

		Image image = new Image(getClass().getResourceAsStream("pir.png"));
		this.setGraphic(new ImageView(image));
		this.setPrefWidth(100);
		this.setPrefHeight(100);
		
		setOnAction(e -> {
			iopins.write(pin, Constants.HIGH);
			System.out.println("PIR SENSOR pin " + pin);
		});
	}
}
