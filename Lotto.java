/**
 * February 2018 Object Oriented Principals Project 1
 * @author Dan Coleman
 * @Version 4.1: Gui and Game work in tandem, all known bugs fixed. Unlocker Implemented.
 *
 * Lotto Cure Tab: The Gui and implementation of the Lotto tab,
 * allows player to create a "ticket" of 6 numbers between 1 and 45, which is
 * compared to a computer generated ticket with the same requirements to simulate
 * the Lottery
 *
 * Tab appears in Main
 */
package games;

import java.util.Random;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
//The below line Connects this class to the main
public class Lotto extends Tab {
	//-------------------------
	//GUI BASED VARIABLES
	//-------------------------
	Button[] btn;
	TextArea[] numBox;

	HBox lottoGameH0;
	HBox lottoGameH1;
	HBox lottoGameH2;
	HBox lottoGameH3;
	HBox lottoGameH4;

	FlowPane lottoFlow;
	VBox lottoGameV1;


//=====================
//	Implementation
//======================
	static int store = 0; //This is used to loop the button object
	int[] lottoTicket = ticketGenerator(); //Generates the computers random 6 numbers

	private static PrizeUnlocker win; //Used to Unlock prizes if you win
	public Lotto() { //Constructor

		// -----------------------------
		// Labels
		// -----------------------------
		Label welcome = new Label("Welcome to the lotto cure game!"); //Welcome Prompt
		Label lottoPrompt = new Label("Please enter 6 numbers  ");//Rules Prompt
		Label result = new Label(" ");//Works to print the current result. Changes a few times

		// -----------------------------
		// Buttons
		// -----------------------------
		Button submit = new Button();//Creates Button "submit"
		submit.setText("Submit");//Sets Button text to "Submit"
		//Action Event Handler for submit is lower in the code as the method needed something not yet created

		Button reset = new Button();//Creates Button "reset"
		reset.setText("Reset");//Sets Button text to "Reset"
		reset.setOnAction(e -> reset(result));
		//Setting Action Event Handler to the reset method below


		Button quit = new Button();//Creates Button "quit"
		quit.setText("Quit");//Sets Button text to "Quit"
		quit.setOnAction(e -> {//Setting Action Event Handler inside anon inner class
			Platform.exit();//Quits program
		});

		// -----------------------------
		// TextAreas
		// -----------------------------
		/**
		 * Array of objects that hold the 6 Inputs for User ticket
		 */
		numBox = new TextArea[6]; //Instantiates Array
		for (int i = 0; i < numBox.length; i++) { //Loops for each box
			numBox[i] = new TextArea(); //sets array to it's own input box
			numBox[i].setDisable(true); //Box can't be used once it's given a number without resetting
		}

		// ------------------------------
		// HBoxes
		// ------------------------------
		// Lotto Game HBox 0
		// HBox shows the welcome
		lottoGameH0 = new HBox(); //Creates Hbox
		lottoGameH0.getChildren().add(welcome);//Adds Welcome prompt to the Hbox
		lottoGameH0.setAlignment(Pos.CENTER);//Sets the Hbox to the Center

		// Lotto Game HBox 1
		// HBox shows the TextArea
		lottoGameH1 = new HBox(1);//Creates Hbox of spacing 1
		lottoGameH1.setAlignment(Pos.CENTER);//Sets the Hbox to the Center
		for (int i = 0; i < numBox.length; i++) { //Loops for each numBox in the array
			numBox[i].setPrefSize(30, 30);//Sets size of each numBox
			lottoGameH1.getChildren().addAll(numBox[i]);//Adds the numBox to the Hbox
		}

		submit.setOnAction(e ->  result.setText(submit(lottoTicket, result).getText()));
		//Setting Action Event Handler to the submit method below, couldn't write higher as numBox was needed


		// Lotto Game HBox 3
		// HBox has option buttons
		lottoGameH3 = new HBox(50);//Creates Hbox of spacing 50
		lottoGameH3.getChildren().addAll(submit, reset, quit);//Adds Buttons to Hbox
		lottoGameH3.setAlignment(Pos.CENTER);//Sets the Hbox to the Center

		//Lotto Game Hbox 4
		//Hbox shows final result
		lottoGameH4 = new HBox(50);//Creates Hbox of spacing 50
		lottoGameH4.getChildren().add(result);//Prints the number of matches
		lottoGameH4.setAlignment(Pos.CENTER);//Sets the Hbox to the Center
		// -----------------------------
		// FlowPane
		// -----------------------------
		lottoFlow = new FlowPane(); //Creates a new flowpane (used to keep buttons in my preferred layout by default

		btn = new Button[45];// creates new button array
		lottoFlow.getChildren().add(lottoPrompt); //Displays rules with buttons
		//PROBLEM: All buttons produce 45
		//Solution: declare i inside each time so that it creates a new store each time
		for (int i = 0; i < btn.length; i++) { //loops for each button
			int store = i; //used to hold correct number
			btn[i] = new Button(Integer.toString(i + 1));//Sets button to the next number from 1 to 45
			btn[i].setPrefWidth(30); //Sets button size to 30 pixels wide
			btn[i].setOnAction(e -> {//Setting Action Event Handler inside anon inner class
				for (int x = 0; x < 6; x++) { //loops for each Numbox
					if (numBox[x].getText().isEmpty() == true) { //if that numbox is empty
						numBox[x].setText(btn[store].getText());//Put the number of clicked button into the Numbox
						btn[store].setDisable(true);//Turn off that button so it can't be chosen again
						break;//Breaks out for next numBox
					}
				}
			});
			lottoFlow.getChildren().add(btn[i]);//Adds the buttons to the flowpane
		}

		lottoFlow.setVgap(2);//Set Vertical Spacing
		lottoFlow.setHgap(4);//Set Horizontal Spacing
		lottoFlow.setPrefWrapLength(300); // default width = 300 before it wraps

		// ------------------------------
		// VBox
		// ------------------------------
		lottoGameV1 = new VBox(30); //Creates VBox of spacing 30 pixels
		lottoGameV1.setPadding(new Insets(15, 15, 15, 15)); //Pads in 15 pixels each way
		lottoGameV1.getChildren().addAll(lottoGameH0, lottoGameH1, lottoFlow, lottoGameH3, lottoGameH4);
		//Adds all the Hboxes to the Vbox
		// ---------------------------------
		// StackPane
		// ----------------------------------
		StackPane lottoStack = new StackPane(); //Stack pane created
		lottoStack.getChildren().add(lottoGameV1);//Adds Vbox to StackPane
		//----------------------------------
		//Tab Functionality
		//----------------------------------
		setClosable(false);//Doesn't allow the Tab Guess Game to Close
		setText("Lotto Cure");//Sets Tab name
		setContent(lottoStack);//Sets the contents of this class to the Main
	}


	// ------------------------------------------
	// EXTRA FUNCTIONALITY
	// -----------------------------------------
	/**
	 * Resets the Lotto input but keeps the Computers the same as asked in the Spec
	 * @param result Passed in to hide the result output until new numbers are submitted
	 */
	private void reset(Label result) {
			for (int i = 0; i < btn.length; i++) {//This loop enables all buttons again
				int store = i;
				btn[store].setDisable(false);
				for (int x = 0; x < 6; x++) {//This loop empties the Numboxes
						if (numBox[x].getText().isEmpty() == false) {
							numBox[x].clear();

					}
		}
	}
			result.setText(" "); //Hides Results until it's calculated
	}

/**
 * Creates the CPU's 6 random numbers (or "Ticket")
 * @return
 */
	private static int[] ticketGenerator() {
		final int MAX_NUMBER = 45; //Tells it the max number allowed to pick up to
		Random random = new Random();//Generates new number
		int[] ticket = new int[6];//Creates CPU ticket array to place 6 nums into

		for (int x = 0; x < 6; x++) {//Loops for each spot in ticket array
			ticket[x] = random.nextInt(MAX_NUMBER) + 1;//Chooses a number between 0 and 44, adds one to correct
				for(int i = 0; i < (x - 1); i++) {//Loop to verify number is unique
					if(ticket[x] == ticket[i]) {//If they aren't
						ticket[x] = random.nextInt(MAX_NUMBER) + 1; //Re-picks number if it isn't unique
					 }
				}
				System.out.println(" " + ticket[x]); //for testing
		}
		//IF I HAVE TIME FIX: Can randomly generate same number twice
		//Extra for loop, if to check num is unique, generates again if same in while loop
		//FIXED
			return ticket; //returns ticket array
	}

/**
 * Checks the number of matches between user and CPU generated Tickets
 * @param lTicket Computers lotto ticket
 * @param result is set to count the number of matches
 * @return the updated result label
 */
	@SuppressWarnings("static-access")
	private Label submit(int[] lTicket, Label result) {
		int match = 0; // number of matches
		int[] myTicket = new int[6]; //creates player "ticket" array
		for (int x = 0; x < numBox.length; x++) {//loops for the 6 numboxes
			//NOTE: DIDN'T HAVE TIME TO EXTENSIVELY TEST CHANGING x<6 to .length but should work. If errors swap that back
			myTicket[x] =Integer.parseInt(numBox[x].getText());
			//Parses Int from the numBox Text into the ticket array
			for(int i = 0; i < 6; i++){ //loops to count matches
			if (myTicket[x] == lottoTicket[i]) { //if they are the same
				match++;//Increase match by one
			}
			}
		}
		if(match == 4){
			win.winLotto4(); //Unlocks 4 star prize for 4 match
		}
		if(match == 5){
			win.winLotto5();//Unlocks 5 star prize for 5 match
		}
		if(match == 6){
			win.winLotto6();//Unlocks 6 star prize for 6 match
		}
		result.setText("Number of Matches: " + match); //Creates new result label to display
		return result;//returns new label
	}

}
