package no.hvl.dat110.aciotdevice.main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import no.hvl.dat110.aciotdevice.controller.AccessController;
import no.hvl.dat110.aciotdevice.controller.MicroController;
import no.hvl.dat110.aciotdevice.pins.IOPins;
import no.hvl.dat110.aciotdevice.pins.Wiring;
import no.hvl.dat110.aciotdevice.ui.LED;
import no.hvl.dat110.aciotdevice.ui.PIRSensor;
import no.hvl.dat110.aciotdevice.ui.PushButton;
import javafx.scene.control.Button;

public class Main extends Application {

	@Override
	public void start(Stage stage) {

		stage.setTitle("IoT Access Control Device");

		// actuators
		
		LED greenled = new LED(" ","#7CFC00","#006400");
		LED yellowled = new LED(" ","#FFFF00","#CCCC00");
		LED redled = new LED(" ","#FF0000","#8B0000");
		LED blueled = new LED(" ","#0000FF","#0080FF");
		
		HBox ledhbox = new HBox(greenled,yellowled,redled,blueled);

		// input/output pins
		
		IOPins iopins = new IOPins(greenled,yellowled,redled,blueled);
		
		// sensors
		PushButton pbtn1 = new PushButton("1",iopins,Wiring.PUSHBTN1);
		PushButton pbtn2 = new PushButton("2",iopins,Wiring.PUSHBTN2);
		PushButton nbtn = new PushButton("N",iopins,Wiring.PUSHNET);
		
		PIRSensor pirsensor = new PIRSensor("PIR",iopins,Wiring.PIR);

		HBox btnhbox = new HBox(pbtn1,pbtn2,nbtn);
	
		VBox vbox = new VBox(pirsensor,btnhbox,ledhbox);
		
		Scene scene = new Scene(vbox, 300, 250);
		stage.setScene(scene);
		
		stage.setOnCloseRequest(e -> {
	        Platform.exit();
	        System.exit(0);
	    });
		
		stage.show();
		
		// start the microcontroller software
		MicroController controller = new AccessController();
		controller.connectPins(iopins);
		controller.start();
		
	}

	public void btn1click () {
		
	}
	public static void main(String[] args) {
			
			Application.launch(args);
			
		}
}
