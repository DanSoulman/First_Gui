/**
 * February 2018 Object Oriented Principals Project 1
 * @author Dan Coleman
 * @Version 1
 *
 * Prize Object, used to fit all the prize information into the Hash map
 */

package games;

public class Prize {
	// ---------------------------------------
	// Attributes
	// ---------------------------------------
	String name; //What the prize actually is
	String star; //The level of the prize

	// ---------------------------------------
	// Constructor
	// ---------------------------------------

	public Prize(String pName, String pStar) {
		this.name = pName;
		this.star = pStar;
	}

	// ---------------------------------------
	// Get Methods
	// ---------------------------------------
	public String getName() {
		return name;
	}


	public String getStar() {
		return star;
	}
}
