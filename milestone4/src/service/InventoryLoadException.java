// Aaron Belschner
// CST-239
// Milestone 4
// 11/13/25

package service;

/**
 * Creates an InventoryLoadException class
 */
public class InventoryLoadException extends Exception {
	
	/**
	 * Default constructor for exception
	 * @param message Current message
	 * @param cause Throwable cause
	 */
    public InventoryLoadException(String message, Throwable cause) {
        super(message, cause);
    }
}