/**
 * February 2018 Object Oriented Principals Project 1
 * @author Dan Coleman
 * @Version 2.0: Gui works, Prizes are acceptable.
 *
 * Prize Tab: The Gui and implementation of the Prize tab,
 * Allows players to choose prizes they unlocked by winning the games
 * Tab appears in Main
 */
package games;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

//The below line Connects this class to the main
public class PrizeGui extends Tab {
	//-------------------------
	//GUI Stuff
	//------------------------
	Button accept;

	ComboBox<String> menu4;
	ComboBox<String> menu5;
	ComboBox<String> menu6;
	HBox prizeH0;
	HBox prizeH1;
	HBox prizeH2;
	HBox prizeH3;
	HBox prizeH4;

	VBox prizeV1;

	StackPane sPane;
	//=====================
	//	Implementation
	//======================
	private static PrizeUnlocker unlock = new PrizeUnlocker();
	String star4 = "4 * Prizes:";
	String star5 = "5 * Prizes:";
	String star6 = "6 * Prizes:";

	Map<String, Prize> prizeMap; //HashMap
	java.util.Set<String> prizeKeys; //Used to move the Key for the Prize from Hashmap to Array
	String[] keyValues; //Stores the Key Strings
	String[] starValue; //Stores the Star Value
	String[] prizeName; //Stores the Prizes

	private int lineCounter;
	public PrizeGui() throws FileNotFoundException, IOException, Exception { //Constructor
		// -----------------------------
		// Labels
		// -----------------------------
		Label prompt = new Label("Please Choose a Prize!");
		// -----------------------------
		// Buttons
		// -----------------------------
		accept = new Button();//Creates Button "accept"
		accept.setText("Accept Prize!");//Sets Button text to "Accept Prize"

		//------------------------------
		//Combo Boxes
		//-----------------------------
			 menu4 = new ComboBox<String>();
			 menu4.setPromptText(star4);
			 menu4.setDisable(true);

			 menu5 = new ComboBox<String>();
			 menu5.setPromptText(star5);
			 menu5.setDisable(true);

			 menu6 = new ComboBox<String>();
			 menu6.setPromptText(star6);
			 menu6.setDisable(true);

		//-------------------------------
		//Hashmap
		//-------------------------------
		@SuppressWarnings("unused")
		Hashmap input = new Hashmap(); //Creates a new Hashmao instance
		lineCounter = Hashmap.getCounter();//Counts the number of lines in the txt file
		prizeMap = Hashmap.getHashmap(); //The hashmap for prizes
		prizeKeys = prizeMap.keySet(); //Gets the keys from the map
		//What does Key set do? : It puts each of the keys in the map in a separate section (Think like array)
		keyValues = new String[lineCounter]; //String Array length of number of lines in txt file
		starValue = new String[lineCounter];//String Array length of number of lines in txt file
		prizeName = new String[lineCounter];//String Array length of number of lines in txt file

		int q = 0; //used to count number of key values
		for (String count : prizeKeys) {//Creates local Variable String count, sets it to PrizeKeys
			keyValues[q] = count; //If there is a String
			q++;//Increments
		}

		for (int i = 0; i < keyValues.length; i++) { //loops for each key

			prizeName[i] = prizeMap.get(keyValues[i]).getName(); //Sets prize name at that point to the same as key Value in Hashmap
			starValue[i] = prizeMap.get(keyValues[i]).getStar();//Sets Star of prize

		}

		for (int i = 0; i < prizeName.length; i++) { //Loops for each prize
			if (starValue[i].equals("4")) { //Separates the Prizes by star
				menu4.getItems().add(prizeName[i]); //Adds to comboBox
			}

			else if (starValue[i].equals("5")) {//Separates the Prizes by star
				menu5.getItems().add(prizeName[i]);//Adds to comboBox
			}

			else if (starValue[i].equals("6")) {//Separates the Prizes by star
				menu6.getItems().add(prizeName[i]);//Adds to comboBox
			}
		}
		// ------------------------------
		// HBoxes
		// ------------------------------
		// Prize HBox 0
		// HBox shows the prompt
		prizeH0 = new HBox(); //Creates Hbox
		prizeH0.getChildren().add(prompt);//Adds prompt to the Hbox
		prizeH0.setAlignment(Pos.CENTER);//Sets the Hbox to the Center


		// Prize HBox 1
		// HBox shows prizes for 4*
		prizeH1 = new HBox(10); //Creates Hbox
		prizeH1.getChildren().add(menu4);//Adds dropdown to the Hbox
		prizeH1.setAlignment(Pos.CENTER);//Sets the Hbox to the Center

		// Prize HBox 2
		// HBox shows prizes for 5*
		prizeH2 = new HBox(10); //Creates Hbox
		prizeH2.getChildren().add(menu5);//Adds dropdown to the Hbox
		prizeH2.setAlignment(Pos.CENTER);//Sets the Hbox to the Center

		// Prize HBox 3
		// HBox shows prizes for 6*
		prizeH3 = new HBox(10); //Creates Hbox
		prizeH3.getChildren().add(menu6);//Adds dropdown to the Hbox
		prizeH3.setAlignment(Pos.CENTER);//Sets the Hbox to the Center

		// Prize HBox 4
		// Accept Button
		prizeH4 = new HBox(); //Creates Hbox
		prizeH4.getChildren().add(accept);//Adds button to the Hbox
		prizeH4.setAlignment(Pos.CENTER);//Sets the Hbox to the Center

		//---------------------
		//VBOXES
		//---------------------
		prizeV1 = new VBox(30); //Creates VBox
		prizeV1.getChildren().addAll(prizeH0,prizeH1,prizeH2,prizeH3,prizeH4); //Adds HBoxes to VBox
		prizeV1.setPadding(new Insets(15, 15, 15, 15)); //Pads in 15 pixels each way
		// ---------------------------------
		// StackPane
		// ----------------------------------
		sPane = new StackPane(); //Stack pane created
		sPane.getChildren().add(prizeV1);//Adds Vbox to StackPane
		//----------------------------------
		//Tab Functionality
		//----------------------------------
		accept.setOnAction(e -> {//Put here as not to cause issue with parts of the gui not yet built.
			Alert alertBox = new Alert(AlertType.INFORMATION);//Creates pop up alertBox
			//-------------------
			//Sorts Prizes picked
			//-------------------
			if (menu4.getValue() == null && menu5.getValue() == null && menu6.getValue() == null){ //No Prizes
				alertBox.setTitle("Uh oh");
				alertBox.setHeaderText("Uh oh");
				alertBox.setContentText("You need to win a game, and select a prize to accept prizes!");
					}
			if (menu4.getValue() != null && menu5.getValue() == null && menu6.getValue() == null){
				alertBox.setTitle("Great Job!");
				alertBox.setHeaderText("Congratulations!");
				alertBox.setContentText("You have won the " + menu4.getValue() );
			}
			if (menu4.getValue() == null && menu5.getValue() != null && menu6.getValue() == null){
				alertBox.setTitle("Great Job!");
				alertBox.setHeaderText("Congratulations!");
				alertBox.setContentText("You have won the " + menu5.getValue() );
			}
			if (menu4.getValue() == null && menu5.getValue() == null && menu6.getValue() != null){
				alertBox.setTitle("Great Job!");
				alertBox.setHeaderText("Congratulations!");
				alertBox.setContentText("You have won the " + menu6.getValue() );
			}
			if (menu4.getValue() != null && menu5.getValue() != null && menu6.getValue() == null){
				alertBox.setTitle("Great Job!");
				alertBox.setHeaderText("Congratulations!");
				alertBox.setContentText("You have won the " + menu4.getValue() + " and the " +menu5.getValue() );
			}
			if (menu4.getValue() != null && menu5.getValue() == null && menu6.getValue() != null){
				alertBox.setTitle("Great Job!");
				alertBox.setHeaderText("Congratulations!");
				alertBox.setContentText("You have won the " + menu4.getValue() + " and the " +menu6.getValue()  );
			}
			if (menu4.getValue() == null && menu5.getValue() != null && menu6.getValue() != null){
				alertBox.setTitle("Great Job!");
				alertBox.setHeaderText("Congratulations!");
				alertBox.setContentText("You have won the " + menu5.getValue() + " and the " +menu6.getValue()  );
			}
			if (menu4.getValue() != null && menu5.getValue() != null && menu6.getValue() != null){
				alertBox.setTitle("Great Job!");
				alertBox.setHeaderText("Congratulations!");
				alertBox.setContentText("You have won the " + menu4.getValue() + " and the " +menu5.getValue()+ " and the " +menu6.getValue() );
				}

			alertBox.show();
		});

		setClosable(false);//Doesn't allow the Tab Guess Game to Close
		setText("Prizes");//Sets Tab name
		setContent(sPane);//Sets the contents of this class to the Main
		setOnSelectionChanged(e -> { //Enables buttons on unlock
			if(unlock.getFourStarUnlock() == true){
				menu4.setDisable(false);
			}
			if (unlock.getFiveStarUnlock() == true) {
				menu5.setDisable(false);
				}
			if (unlock.getSixStarUnlock() == true) {
				menu6.setDisable(false);
			}
		});

	}
}