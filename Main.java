/**
 * February 2018 Object Oriented Principals Project 1
 * @author Dan Coleman
 * @Version 2:Tabs all implemented
 *
 * Last part of the GUI
 */
package games;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws FileNotFoundException, IOException, Exception {
				// -----------------------------
				// TabPane
				// -----------------------------
				TabPane gameTab = new TabPane();
				// ISSUE: Buttons appear inside tab: FIXED
				// was adding code to the tab instead of building outwards

				//---------------------------------------
				//Adds the following tabs to the TabPane:
				//--------------------------------------
				gameTab.getTabs().add (new GuessingGame());
				//The Guessing Game from the class of the same name
				gameTab.getTabs().add (new Lotto());
				//The Lotto Game from the class Lotto
				gameTab.getTabs().add (new PrizeGui());
				// ----------------------------
				// BorderPane
				// ----------------------------
				BorderPane gameBP = new BorderPane();//Creates a borderPane
				gameBP.setTop(gameTab);//Puts the GameTab TabPane in the borderPane

				// -----------------------------
				// Scene
				// -----------------------------
				Scene gameScene = new Scene(gameBP); //Creates a scene with GameBP
				// -----------------------------------
				// Scene Setting
				// -----------------------------------
				primaryStage.setTitle("Dan Coleman's Tab Project R00151926");
				primaryStage.setScene(gameScene); //Sets the scene as the Primary Stage
				primaryStage.setWidth(420);//Sets width of the PrimaryStage (Application Window)
				primaryStage.setHeight(500);//Sets width of the PrimaryStage (Application Window)
				primaryStage.show();//Makes Primary Stage appear

			}


	public static void main(String[] args) {
		launch(args); //Runs the code
	}
}
