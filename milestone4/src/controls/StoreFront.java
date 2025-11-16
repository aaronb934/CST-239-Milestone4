// Aaron Belschner
// CST-239
// Milestone 4
// 11/13/25

package controls;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import model.SalableProduct;
import service.InventoryManager;
import service.ShoppingCart;

/**
 * Creates storeFront
 */
public class StoreFront {
	
	private InventoryManager inventoryManager;
	private ShoppingCart shoppingCart;
	
	/**
	 * Default constructor for StoreFront.
	 */
	public StoreFront() {
		inventoryManager = new InventoryManager();
		shoppingCart = new ShoppingCart(); 
	}
	
	/**
	 * Method to display Main menu.
	 */
	public void displayMenu() { 
		System.out.println("\n--- Main Menu ---");
		System.out.println("Enter menu choice");
		System.out.println("1. View Products");
		System.out.println("2. Add Product to Cart");
		System.out.println("3. Remove Product from Cart");
		System.out.println("4. View Cart");
		System.out.println("5. Checkout");
		System.out.println("6. Exit the Store");
		System.out.println("");
	}
	
	/**
	 * Method to control the main menu operations
	 */
	public void runStore() {
		Scanner scnr = new Scanner(System.in);
		int num;
		
		do {
			displayMenu();
			System.out.println("Enter your selection.\n");
			try {
				num = scnr.nextInt();
				scnr.nextLine();
				switch (num) {
				case 1:
					System.out.println("");
					displayProducts();
					break;
				case 2:
					System.out.println("");
					addProductToCart(scnr);
					break;
				case 3: 
					System.out.println("");
					removeProductFromCart(scnr);
					break;
				case 4: 
					System.out.println("");
					displayCart();
					break;
				case 5:
					System.out.println("");
					checkout();
					break;
				case 6:
					System.out.println("");
					System.out.println("Closing store. Items in Shopping Cart were returned to the Store inventory.");
					cancelOrder();
					break;
				default:
					System.out.println("");
					System.out.println("Error with selection. Please try again.");
					break;
				}
			} catch (java.util.InputMismatchException e) { // Milestone 4
				System.out.println("Invalid input. Please enter a number (1-8).");
				scnr.next();
				num = 0;
			}

		} while (num != 6); 
		scnr.close();
	}

	/**
	 * Method to display current products.
	 */
	public void displayProducts() {
		List<SalableProduct> products = inventoryManager.getProducts();
		System.out.println("--- Product List ---");
		Collections.sort(products);
		for (SalableProduct product : products) {
			System.out.println(product.toString()); // Changed for Milestone 4
		}
	}

	/**
	 * Method to add an object to the cart from the store items.
	 * @param scnr Current item being added to cart.
	 */
	private void addProductToCart(Scanner scnr) {
		System.out.println("Enter the name of the product you want to add to the cart:");
		String name = scnr.nextLine();
		SalableProduct product = inventoryManager.getProductByName(name);

		if (product != null) {
			System.out.println("Enter the quantity.");
			int quantity = scnr.nextInt();
			scnr.nextLine();
			shoppingCart.addProduct(product, quantity);
			System.out.println("Added " + quantity + " " + name + "(s) to the cart.");
			
		} else {
			System.out.println("Product not found.");
		}
	}

	/**
	 * Method to remove an item from the cart.
	 * @param scnr Current item being removed from cart.
	 */
	private void removeProductFromCart(Scanner scnr) {
		System.out.println("Enter the name of the product you want to remove from the cart:");
		String name = scnr.nextLine();
		
		List<SalableProduct> cartProducts = shoppingCart.getItems();
		
		SalableProduct productToRemove = null;
		for (SalableProduct p : cartProducts) {
			if (p.getName().equalsIgnoreCase(name)) {
				productToRemove = p;
				break;
			}
		}
		
		if (productToRemove != null) {
			shoppingCart.removeProduct(productToRemove);
			System.out.println("Removed the " + name + "(s) from the cart");
		}
		else {
			System.out.println("Product not found in the cart");
		}
	}

	/**
	 * Method to display current cart items.
	 */
	public void displayCart() {
		List<SalableProduct> items = shoppingCart.getItems();
		if (items.isEmpty()) {
			System.out.println("Your cart is empty.");
		}
		else {
			System.out.println("Items in your cart.");
			for (SalableProduct item : items) {
				System.out.println(item);
			}
			System.out.println("Total Price: " + shoppingCart.getTotal());
		}
	}

	/**
	 * Method to operate the check out function.
	 */
	public void checkout() {
		removeFromInventory();
		double total = shoppingCart.getTotal();
		if (total > 0) {
			System.out.println("\nChecking out. Total Price: " + total);
			shoppingCart.clear(); 
			System.out.println("Thank you for your purchase!");
			inventoryManager.saveInventory(); // Milestone 4
		}
		else {
			System.out.println("Your cart is empty.");
		}
	}

	/**
	 * Method to remove an item from the inventory.
	 */
	public void removeFromInventory() {
		List<SalableProduct> shoppingCartItems = shoppingCart.getItems();
		for (SalableProduct itemInCart : shoppingCartItems) {
			inventoryManager.lowerProductStock(itemInCart.getName(), 1);
		}
	}
	
	/**
	 * Method to add an item to the inventory.
	 */
	public void addToInventory() {
		List<SalableProduct> shoppingCartItems = shoppingCart.getItems();
		for (SalableProduct itemInCart : shoppingCartItems) {
			inventoryManager.raiseProductStock(itemInCart.getName(), 1);
		}
	}
	
	/**
	 * Method to cancel order.
	 */
	public void cancelOrder() {
		addToInventory();
		inventoryManager.saveInventory();
	}
	
	/**
	 * Main method of StoreFront
	 * @param args Argument for main method.
	 */
	public static void main(String[] args) {
		StoreFront store = new StoreFront();
		System.out.println("\nWelcome to the the Battle Store!");
		store.runStore();
	}
}