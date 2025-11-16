// Aaron Belschner
// CST-239
// Milestone 4
// 11/13/25

package service;

import java.util.ArrayList;
import java.util.List;
import model.SalableProduct;

/**
 * Creates the ShoppingCart
 */
public class ShoppingCart {
    private List<SalableProduct> items;

    /**
     * Default constructor
     */
    public ShoppingCart() {
    	this.items = new ArrayList<>();
    }
    
    

    /**
     * Method to add a store product to the cart.
     * @param product Current product in the store inventory
     * @param quantity Current quantity of the store item.
     */
    public void addProduct(SalableProduct product, int quantity) {
        for (int i = 0; i < quantity; i++) {
        	items.add(product);
        }
    }
    
    /**
     * Method to remove a product from the current store inventory.
     * @param product Current product being removed
     */
    public void removeProduct(SalableProduct product) {
        for (int i = 0; i < items.size(); i++) {
        	if (items.get(i).getName().equals(product.getName())) {
        		items.remove(i);
        		i--;
        	}
        }
    }
    
    /**
     * Method to return current ShoppingCart item List.
     * @return Returns ShoppingCart List
     */
    public List<SalableProduct> getItems() {
        return new ArrayList<>(items); 
    }
    
    /**
     * Method to calculate total price of ShoppingCart items.
     * @return Returns total price
     */
    public double getTotal() {
        double totalPrice = 0.0;
        
        for (int i = 0; i < items.size(); i++) {
        	SalableProduct item = items.get(i);
        	totalPrice += item.getPrice();
        }
        return totalPrice;
    }
    
    /**
     * Method to get product from the cart if search matches name.
     * @param name Current item name
     * @return Returns item data
     */
    public SalableProduct getProductByName(String name) {
        for (SalableProduct product : items) {
        	if (product.getName().equalsIgnoreCase(name)) {
        		return product;
        	}
        }
        return null; // no products were found
    }
    
    /**
     * Method to clear contents of ShoppingCart.
     */
    public void clear() {
        items.clear();
    }
}