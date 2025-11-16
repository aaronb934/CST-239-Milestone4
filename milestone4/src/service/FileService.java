// Aaron Belschner
// CST-239
// Milestone 4
// 11/13/25

package service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.SalableProduct;

/**
 * Creates FileService 
 */
public class FileService {

	private final ObjectMapper objectMapper;
	private final String fileName = "inventory.json";
	
	/**
	 * Default constructor
	 */
	public FileService() {
		this.objectMapper = new ObjectMapper();
	}
	
	/**
	 * Method to read from file
	 * @return Returns SalableProduct List
	 * @throws InventoryLoadException if the file cannot be read.
	 */
	public List<SalableProduct> readProductsFromFile() throws InventoryLoadException {
		List<SalableProduct> products = new ArrayList<>();
		try {
			File file = new File(fileName);
			if (!file.exists()) {
				System.out.println("Inventory file not found.");
				return products;
			}
			
			Scanner s = new Scanner(file);
			while (s.hasNext()) {
				String json = s.nextLine();
                if (!json.trim().isEmpty()) {
				    SalableProduct product = objectMapper.readValue(json, SalableProduct.class);
				    products.add(product);
                }
			}
			s.close();
		}
		catch (IOException e) {
			throw new InventoryLoadException("Error reading products from file: " + fileName, e);
		}
		
		return products;
	}
	
	/**
	 * Method to write current inventory to the file.
	 * @param products Current inventory
	 * @throws InventoryLoadException if the file cannot be written to.
	 */
	public void writeProductsToFile(List<SalableProduct> products) throws InventoryLoadException {
		try {
			File file = new File(fileName);
			FileWriter fw = new FileWriter(file, false); 
			PrintWriter pw = new PrintWriter(fw);
			
			for (SalableProduct product : products) {
				String json = objectMapper.writeValueAsString(product);
				pw.println(json);
			}
			
			pw.close();
			System.out.println("Store Inventory has been updated.");
		}
		catch (IOException e) {
			throw new InventoryLoadException("Error writing products to file: " + fileName, e);
		}
	}
}