// Aaron Belschner
// CST-239
// Milestone 4
// 11/13/25

package model;

/**
 *  Creates an Armor object extending from the SalableProduct class.
 */
public class Armor extends SalableProduct {
	
	private int defense; // Milestone 4

	/**
	 * Default constructor 
	 */
	public Armor() {
 	}

	/**
	 * Parameterized constructor from SalableProduct to create an Armor object.
	 * @param name Current name
	 * @param description Current description
	 * @param price Current price
	 * @param quantity Current quantity
	 * @param defense Current defense points // Milestone 4
	 */
	public Armor(String name, String description, double price, int quantity, int defense) {
		super(name, description, price, quantity);
		this.defense = defense;
	}
	
	/**
	 * Method to get defense
	 * @return Returns defense
	 */
	public int getDefense() { // Milestone 4
		return defense;
	}
	
	/**
	 * Method to set defense
	 * @param defense Current defense
	 */
	public void setDefense(int defense) { // Milestone 4
		this.defense = defense;
	}
	
	/**
	 * Method to write object as string
	 */
	@Override
	public String toString() { // Milestone 4
		return String.format("%s | Defense Points: %d", super.toString(), defense);
	}
}