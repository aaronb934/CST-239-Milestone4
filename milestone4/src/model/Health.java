// Aaron Belschner
// CST-239
// Milestone 4
// 11/13/25

package model;

/**
 * Creates a Health object extending from the SalableProduct class.
 */
public class Health extends SalableProduct {

	private int healthPoints; // Milestone 4

	/**
	 * Default constructor
	 */
	public Health() {
	}

	/**
	 * Parameterized constructor from SalableProduct to create a Health object.
	 * @param name Current name
	 * @param description Current description
	 * @param price Current price
	 * @param quantity Current quantity
	 * @param healthPoints Current health points // Milestone 4
	 */
	public Health(String name, String description, double price, int quantity, int healthPoints) {
		super(name, description, price, quantity);
		this.healthPoints = healthPoints;
	}
	
	/**
	 * Method to get healthPoints
	 * @return Returns healthPoints
	 */
	public int getHealthPoints() { // Milestone 4
		return healthPoints;
	}
	
	/**
	 * Method to set healthPoints
	 * @param healthPoints Current healthPoints
	 */
	public void setHealthPoints(int healthPoints) { // Milestone 4
		this.healthPoints = healthPoints;
	}
	
	/**
	 * Method to write object as string
	 */
	@Override
	public String toString() { // Milestone 4
		return String.format("%s | Health Points: %d", super.toString(), healthPoints);
	}
}