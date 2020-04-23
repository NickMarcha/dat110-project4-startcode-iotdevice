package no.hvl.dat110.aciotdevice.controller;

import no.hvl.dat110.aciotdevice.pins.Constants;
import no.hvl.dat110.aciotdevice.pins.IOPins;

public abstract class MicroController extends Thread {

	private IOPins iopins;
	
	static final int INPUT = Constants.INPUT;
	static final int OUTPUT = Constants.OUTPUT;
	
	static final int HIGH = Constants.HIGH;
	static final int LOW = Constants.LOW;
	
	public void connectPins(IOPins iopins) {
		this.iopins = iopins; 
	}

	abstract void setup ();
	
	abstract void loop ();
	
	protected void pinMode(int pin,int pinmode) {
		// not really used in the current implementation
	}
	
	protected void delay (int msecs) {
			
		try {
			Thread.sleep(msecs);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	protected int digitalRead(int pin) {
	
		return iopins.read(pin);
	}
	
	protected void digitalWrite(int pin,int newstate) {
		
		iopins.write(pin,newstate);
	}
	
	public void run () {
		
		Serial.println("Microcontroller starting");
		
		Serial.println("Microcontroller pre-setup()");
		
		setup();
		
		Serial.println("Microcontroller post-setup()");
		
		while (true) {
			
			loop();
			Thread.yield();
		}
	}
}
