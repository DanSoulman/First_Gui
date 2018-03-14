/**
 * February 2018 Object Oriented Principals Project 1
 * @author Dan Coleman
 * @Version 2:Works finally
 *
 * Is used to control Prize Unlocks
 */
package games;

public class PrizeUnlocker {

private static boolean fourStarUnlock;
private static boolean fiveStarUnlock;
private static boolean sixStarUnlock;
static int counter = 0;
public PrizeUnlocker() {
	if(counter == 0){
	fourStarUnlock = false;
	fiveStarUnlock = false;
	sixStarUnlock = false;
	}
	counter++;
}
public static void winGuess() {
	setFourStarUnlock(true);
}
public static void winLotto4() {
	setFourStarUnlock(true);
}
public static void winLotto5() {
	setFiveStarUnlock(true);
}
public static void winLotto6() {
	setSixStarUnlock(true);
}

//----------------------------------
//Getters & Setters
//----------------------------------
public boolean getFourStarUnlock() {
	return fourStarUnlock;
}

public boolean getFiveStarUnlock() {
	return fiveStarUnlock;
}

public boolean getSixStarUnlock() {
	return sixStarUnlock;
}

public static void setFourStarUnlock(boolean fourStarUnlocked) {
	fourStarUnlock = fourStarUnlocked;
}

public static void setFiveStarUnlock(boolean fiveStarUnlocked) {
	fiveStarUnlock = fiveStarUnlocked;
}

public static void setSixStarUnlock(boolean sixStarUnlocked) {
	sixStarUnlock = sixStarUnlocked;
}


}
