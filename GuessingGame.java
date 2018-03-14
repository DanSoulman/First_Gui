/**
 * February 2018 Object Oriented Principals Project 1
 * @author Dan Coleman
 * @Version 4.0: Gui and Game work in tandem, all bugs fixed, Unlocker Implemented.
 *
 * Guessing Game Tab: The Gui and implementation of the Guessing tab,
 * allows player to guess a number between 1 and 100 and are told if it is higher
 * or lower, given 5 attempts as spec required, but is scalable.
 *
 * Tab appears in Main
 */
package games;

import java.util.Random;
import java.util.Scanner;


import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
//The below line Connects this class to the main
public class GuessingGame extends Tab {

//-------------------------
//GUI BASED VARIABLES
//-------------------------

	VBox guessGameVB1;

	HBox guessGameH1;
	HBox guessGameH2;
	HBox guessGameH3;

	TextField guessBox;
//=====================
//	Implementation
//======================
	final static Scanner kb = new Scanner(System.in); //Scanner for input
	static Random random = new Random(); //Generates Random Number
	static int correctNumber = random.nextInt(100) + 1; //The number you want to guess
	static int maxAttempts = 5; //Number of guesses allowed
	static int MAX_NUMBER = 100; //Highest allowed number
	static int input; // Number the player inputs as their guess

	@SuppressWarnings("unused")
	private PrizeUnlocker win; //Used to Unlock 4 star prizes if you win
	//private PrizeGui unlock;
	public  GuessingGame() {
		// -----------------------------
		// Labels
		// -----------------------------
		Label welcome = new Label("Welcome to the random number game!");//Welcome Prompt
		Label guessPrompt = new Label("Please guess a number from 1-100");//Game Rules Prompt


		// -----------------------------
		// TextField
		// -----------------------------
		guessBox = new TextField (); //Creates a textField the user inputs into
									//And if number is too high or low
		guessBox.setPrefWidth(175); //Set it to be wide enough to fix prompts


		// -----------------------------
		// Buttons
		// -----------------------------

			//============
			//Guess button
			//============
			Button guess = new Button(); //Instantiate guess button
				guess.setText("Guess");//Sets what's on the button to Guess
				//Button implementation
				guess.setOnAction(e -> {
				//Setting Action Event Handler inside anon inner class

					try{	//Try for a try catch to only allow ints

							if (maxAttempts -1 > 0){ //While there are still guesses allowed

							input = Integer.valueOf(guessBox.getText());
							//Takes value from GuessBox

							if (input < correctNumber) //If you guess too low
							{
								//was going to use setPromptText but then I had to find a way to clear the last guess and it made it too easy to forget your last guess
								guessBox.setText("Guess Higher " + (maxAttempts-1) + " guesses left.");
								//Prompts you to guess higher and shows no. of guesses left
								maxAttempts--; //Takes a guess attempt away
							}
							else if (input > correctNumber) //If you guess too high
							{
								guessBox.setText("Guess lower " + (maxAttempts-1) + " guesses left.");
								//Prompts you to guess higher and shows no. of guesses left
								maxAttempts--;//Takes a guess attempt away
							}
							else if (correctNumber == input ) //If you guess correctly
							{
								guessBox.setText("WINNER! 4* Prize"); //Tells you you've won
								guess.setDisable(true);//Removes use of guess button til reset
								PrizeUnlocker.winGuess(); //Unlocks 4* Prizes
							}
						}
						else{
							guessBox.setText("Out of guesses! It was " + correctNumber);//Tells you you're out of guesses and tells you the correct number
							guess.setDisable(true);//Removes use of guess button til reset
						}
					}
					catch (NumberFormatException input){ //Catches any non int inputs
						guessBox.setText("Invalid Input! Please use int's");//Prompts user to use an int data type
					}
				});


		Button reset = new Button();//Instantiate guess button
			reset.setText("Reset");//Sets what's on the button to Reset
			reset.setOnAction(e -> reset(guess));//Setting Action Event Handler to the reset method below

		Button quit = new Button();//Instantiate quit button
			quit.setText("Quit"); //Sets what's on the button to Quit
			quit.setOnAction(e-> { //Setting Action Event Handler inside anon inner class
				Platform.exit(); //Quits the Program
			});


		//------------------------------
		//HBoxes
		//------------------------------

		//Guessing Game HBox 1
		//HBox shows the welcome
		guessGameH1 = new HBox(10); //Set Spacing to 10 in new Hbox
		guessGameH1.getChildren().add(welcome); //Adds welcome label to the HBox
		guessGameH1.setAlignment(Pos.CENTER);//Positions the Children to the center


		//Guessing Game HBox 2
		//Text Prompt and text field
		guessGameH2 = new HBox(10);//Set Spacing to 10 in new Hbox
		guessGameH2.getChildren().add(guessPrompt);//Adds Rule Prompt to the HBox
		guessGameH2.getChildren().add(guessBox);//Adds guessBox TextField to HBox
		guessGameH2.setAlignment(Pos.CENTER);//Positions the Children to the center


		//Guessing Game HBox 3
		guessGameH3 = new HBox(50);//Set Spacing to 50 in new Hbox
		guessGameH3.getChildren().addAll(guess, reset, quit);
		guessGameH3.setAlignment(Pos.CENTER);//Positions the Children to the center
		//------------------------------
		//VBox
		//------------------------------
		//Guess Game VBox 1, Holds all the HBoxes
		guessGameVB1 = new VBox(100);//Set Spacing to 100 in a new VBox
		guessGameVB1.setPadding(new Insets(15, 15, 15, 15)); //Sets the spacing of the Vbox inwards 15 pixels each direction
		guessGameVB1.getChildren().addAll(guessGameH1, guessGameH2, guessGameH3); //Adds Children to the VBox

		//---------------------------------
		//StackPane
		//----------------------------------
		//Stack Pane Holds the VBox, added mostly for practice
		 StackPane guessStack = new StackPane();//Creates new StackPane
		 guessStack.getChildren().add(guessGameVB1); //Adds VBox to Stack

		//---------------------
		//Tab Functionality
		//--------------------
		  setClosable(false); //Doesn't allow the Tab Guess Game to Close
		  setText("Guessing Game");//Sets Tab name
		  setContent(guessStack);//Sets the contents of this class to the Main
	}



	//------------------------------------------
	//EXTRA FUNCTIONALITY
	//-----------------------------------------
	/**
	 * Resets the guess counter, generates a new number,
	 * and re-enables guess button
	 * @param guess Guess button disables on Win/Loss to re-enable you must reset
	 */
		private void reset(Button guess) {
			maxAttempts  = 5; //Number of guesses resets
			correctNumber = random.nextInt(100) + 1; //Sets a new number
			guessBox.clear();//Clears the guess box
			guess.setDisable(false);
			guessBox.setText("" +correctNumber); //For Testing
		}

//HAD I MORE TIME: I'd like to implement a Label below the guess Box that updates with all choices made so far
//Also to stop user guessing num <0 || num >100



}

