/**
 * February 2018 Object Oriented Principals Project 1
 * @author Dan Coleman
 * @Version 1:Reads in and Getters work
 *
 * Reads input from .txt file and creates Hashmap
 */
package games;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Hashmap {
	private File prizeFile; //Takes in the text file for Prizes
	private String store; //Stores a line from the file to split up
	private String[] prizeList; //An Array of String that holds the prize info to put into Hashmap
	private BufferedReader readIn; //Used to read .txt file
	private Prize prize; //Prize object

	static int counter; //Used to find amount of prizes

	private static Map<String, Prize> hashmap; //Creates the hashmap that holds<Codename, Prize object>




 public Hashmap() throws FileNotFoundException, IOException,Exception {

		counter = 0;//Used to find amount of prizes

		try {
			hashmap = new HashMap<>(); //Creates a hashmap
			prizeFile = new File("src\\games\\Prizes.txt"); //Takes in the file from Prizes txt file
			readIn = new BufferedReader(new FileReader(prizeFile)); //Reads the File

			while ((store = readIn.readLine()) != null) { //If there's anything in the line
				prizeList = store.split("/"); //it splits the text up by / into sections (of 3 in the case of my file)
				for (int i = 0; i < prizeList.length; i++) { //Loops for how many sections it was split into
					prizeList[i] = prizeList[i].trim(); //Removes any whitespace between string
				}
				prize = new Prize(prizeList[1], prizeList[2]); //Sets the Prize Description, then Star value to Prize object
				hashmap.put(prizeList[0], prize); //Adds the String for the codename as the hashmaps key, and the Prize object as the value
				counter++; //Increments for each prize
			}
			readIn.close();//Stops reading file
		} catch (IOException e) { //Catches any possible errors
			System.err.println("An IOException was caught!"); //Prompts there was an error
			e.printStackTrace();

		}
	}

 //---------------------------
 //Getters
 //---------------------------
 	public static int getCounter() {
				return counter;
			}

		public static Map<String, Prize> getHashmap() {
				return hashmap;
			}
		}
