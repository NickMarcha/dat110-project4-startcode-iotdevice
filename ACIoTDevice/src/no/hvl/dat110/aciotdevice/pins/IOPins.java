package no.hvl.dat110.aciotdevice.pins;

import no.hvl.dat110.aciotdevice.ui.LED;

public class IOPins {

	private LED greenled, yellowled, redled, blueled;

	private int[] pins;

	private void setLED(LED led, int newstate) {

		if (newstate == Constants.HIGH) {
			led.on();
		} else {
			led.off();
		}
	}

	public IOPins(LED greenled, LED yellowled, LED redled, LED blueled) {

		pins = new int[14];

		this.redled = redled;
		pins[Wiring.REDLED] = Constants.LOW;

		this.yellowled = yellowled;
		pins[Wiring.YELLOWLED] = Constants.LOW;

		this.greenled = greenled;
		pins[Wiring.GREENLED] = Constants.LOW;

		this.blueled = blueled;
		pins[Wiring.BLUELED] = Constants.LOW;

	}

	public void write(int pin, int newstate) {

		pins[pin] = newstate;

		// actuate on write
		// improved modelling could use pinMode
		switch (pin) {

		case Wiring.REDLED:
			setLED(redled, newstate);
			break;

		case Wiring.YELLOWLED:
			setLED(yellowled, newstate);
			break;

		case Wiring.GREENLED:
			setLED(greenled, newstate);
			break;

		case Wiring.BLUELED:
			setLED(blueled, newstate);
			break;
		default:
			break;
		}
	}

	public int read(int pin) {

		int pinstate = pins[pin];

		// reading currently sets to low - button press are remembered until read
		pins[pin] = Constants.LOW; // FIXME

		return pinstate;
	}

}
