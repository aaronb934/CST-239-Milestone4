// Aaron Belschner
// CST-239
// Milestone 4
// 11/13/25

package service;

import java.util.List;
import java.util.ArrayList;
import model.SalableProduct;

/**
 * Creates the InventoryManager
 */
public class InventoryManager {

	private List<SalableProduct> products; 
	private FileService fileService; // Milestone 4
	
	/**
	 * Constructor to create the InventoryManager.
	 */
	public InventoryManager() { // Changed for Milestone 4
		this.fileService = new FileService();
		try {
			this.products = fileService.readProductsFromFile();
			if (this.products.isEmpty()) {
				System.out.println("Inventory loaded but is empty.");
			}
		} catch (InventoryLoadException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			this.products = new ArrayList<>();
			System.err.println("Continuing with an empty inventory.");
		}
	}
	
	/**
	 * Method to return products List.
	 * @return Current product List
	 */
	public List<SalableProduct> getProducts() {
		return products;
	}
	
	/**
	 * Method to return product from products if name is matched.
	 * @param name Product name
	 * @return Returns product data
	 */
	public SalableProduct getProductByName(String name) {
		for (SalableProduct product : products) {
			if (product.getName().equalsIgnoreCase(name)) {
				return product;
			}
		}
		return null; // no products were found.
	}
	
	/**
	 * Method to lower the inventory stock when an item is purchased.
	 * @param name Current name
	 * @param quantity Current quantity 
	 * @return Returns boolean determination
	 */
	public boolean lowerProductStock(String name, int quantity) { 
		SalableProduct product = getProductByName(name);
		if (product != null) {
			if (product.getQuantity() >= quantity) {
				product.setQuantity(product.getQuantity() - quantity);
				return true;
			}
			else {
				System.out.println("We are sold out of this item.");
				return false;
			}
		}
		System.out.println("Product not found.");
		return false;
	}
	
	/**
	 * Method to raise the inventory stock when an order is canceled.
	 * @param name Current name
	 * @param quantity Current quantity being added
	 */
	public void raiseProductStock(String name, int quantity) {
		SalableProduct product = getProductByName(name);
		if (product != null) {
			product.setQuantity(product.getQuantity() + quantity);
		}
	}

	/**
	 * Method to save the current inventory to the json file.
	 */
	public void saveInventory() { // Milestone 4
		try {
			fileService.writeProductsToFile(products);
		} catch (InventoryLoadException e) {
			System.err.println("Failed to save inventory updates: " + e.getMessage());
		}
	}

}
