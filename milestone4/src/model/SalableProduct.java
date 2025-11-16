// Aaron Belschner
// CST-239
// Milestone 4
// 11/13/25

package model;

import com.fasterxml.jackson.annotation.JsonCreator; 
import com.fasterxml.jackson.annotation.JsonProperty; 
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

/**
 * Creates a Salable Product with a name, description, price, and quantity.
 */
@JsonTypeInfo(use = Id.CLASS, include = As.PROPERTY, property = "@class")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Weapon.class, name = "model.Weapon"),
    @JsonSubTypes.Type(value = Armor.class, name = "model.Armor"),
    @JsonSubTypes.Type(value = Health.class, name = "model.Health")
})
public class SalableProduct implements Comparable<SalableProduct> { // This interface implementation applies to all SalableProducts.
	// Fields changed to protected in Milestone 4 
	protected String name;
	protected String description;
	protected double price; 
	protected int quantity;
	
	/**
	 * Constructor to create the Salable Product.
	 * @param name Product name
	 * @param description Product description
	 * @param price Product price
	 * @param quantity Product quantity
	 */
    // Annotations added to primary constructor
    @JsonCreator
	public SalableProduct(
            @JsonProperty("name") String name, 
            @JsonProperty("description") String description, 
            @JsonProperty("price") double price, 
            @JsonProperty("quantity") int quantity) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
	}

    /**
     * Default constructor
     */
    public SalableProduct() {}

    /**
     * Method to get product name
     * @return Returns name
     */
    public String getName() { 
    	return name; 
    }
    
    /**
     * Method to set product name
     * @param name Current name
     */
    public void setName(String name) { 
    	this.name = name; 
    }
    
    /**
     * Method to get product description
     * @return Returns description
     */
    public String getDescription() { 
    	return description; 
    }
    
    /**
     * Method to set product description
     * @param description Current description
     */
    public void setDescription(String description) { 
    	this.description = description; 
    }
    
    /**
     * Method to get product price
     * @return Returns price
     */
    public double getPrice() { 
    	return price; 
    }
    
    /**
     * Method to set product price
     * @param price Current price
     */
    public void setPrice(double price) { 
    	this.price = price; 
    }
    
    /**
     * Method to get product quantity
     * @return Returns quantity
     */
    public int getQuantity() { 
    	return quantity; 
    }
    
    /**
     * Method to set quantity
     * @param quantity Current quantity
     */
    public void setQuantity(int quantity) { 
    	this.quantity = quantity; 
    }
    
    /**
     * Creates a string containing item name, description, price, and quantity.
     */
    @Override
	public String toString() {
		return String.format("Name: %s | Description: %s | Price: %.2f | Quantity: %d", name, description, price, quantity);
	}

    /**
     * Method to compare items by name and price
     */
	@Override
	public int compareTo(SalableProduct o) {
		int nameComparison = this.name.compareToIgnoreCase(o.name);
		if (nameComparison != 0) {
			return nameComparison;
		}
		else {
			return Double.compare(this.price, o.price);
		}
	}
}