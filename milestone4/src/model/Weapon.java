// Aaron Belschner
// CST-239
// Milestone 4
// 11/13/25

package model;

/**
 * Creates a Weapon object extending from the SalableProduct class.
 */
public class Weapon extends SalableProduct {
	
	private int attack; // Milestone 4
	
	/**
	 * Default constructor
	 */
	public Weapon() {
	}

	/**
	 * Parameterized constructor from SalableProduct to create a Weapon.
	 * @param name Current name
	 * @param description Current description
	 * @param price Current price
	 * @param quantity Current quantity
	 * @param attack Current attack damage // New for Milestone 4
	 */
	public Weapon(String name, String description, double price, int quantity, int attack) {
		super(name, description, price, quantity);
		this.attack = attack;
	}
	
	/**
	 * Method to get attack
	 * @return Returns attack
	 */
	public int getAttack() { // Milestone 4
		return attack;
	}
	
	/**
	 * Method to set attack
	 * @param attack Current attack
	 */
	public void setAttack(int attack) { // Milestone 4
		this.attack = attack;
	}
	
	/**
	 * Method to write object as string.
	 */
	@Override
	public String toString() { // Milestone 4
		return String.format("%s | Attack Damage: %d", super.toString(), attack);
	}
}