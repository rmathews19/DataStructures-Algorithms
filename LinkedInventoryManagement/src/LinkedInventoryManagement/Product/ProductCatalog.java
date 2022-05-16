//Reevan Mathews RXM180076

package LinkedInventoryManagement.Product;

import java.io.IOException;

import LinkedInventoryManagement.Common.InventoryLinkedList;
import LinkedInventoryManagement.PersistentStorage.InventoryOperations;


/**
 * ProductCatalog
 */
public class ProductCatalog {

    public InventoryLinkedList<Product> Items = new InventoryLinkedList<Product>();
    
    public ProductCatalog() 
    {
        this.Items = InventoryOperations.InventoryReader();
    }

    public void AddUpdateProduct(Product product)
    {
        boolean updated = false;

        for (int i = 0; i < Items.GetLength(); i++) {
            if (Items.GetElement(i).getId() == product.getId()) {
                Items.GetElement(i).setName(product.getName());
                Items.GetElement(i).setCost(product.getCost());
                Items.GetElement(i).setQuantity(product.getQuantity());
                Items.GetElement(i).setMargin(product.getMargin());
                updated = true;
            }
        }
        if (!updated) {
            Items.add(product);
        }
        try {
                    InventoryOperations.UpdateFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
    }


    public boolean RemoveProduct(Product product)
    {
        boolean success = false; 
        for (int i = 0; i < Items.GetLength(); i++) {
            if (Items.GetElement(i).getName().trim().compareToIgnoreCase(product.getName().trim()) == 0) {
                Items.Remove(i);
                success = true;
            }
        } 

		try {
			InventoryOperations.UpdateFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
        return success; 
    }

    public boolean FindProduct(Product product)
    {
        return Items.Contains(product); 
    }

    public String PrintProductInformation(Product product)
    {
        String productInfo = null;

        if (FindProduct(product)) {
            for (int i = 0; i < Items.GetLength(); i++) {
                if (Items.GetElement(i).getName().compareToIgnoreCase(product.getName()) == 0) {
                    product = Items.GetElement(i);
                }
            }
            String id = "Id", name = "Name", cost = "Cost", quantity = "Quantity", retailed = "Retail";
            productInfo = String.format("%-6s", id);
            productInfo += String.format("%-26s", name);
            productInfo += String.format("%-14s", cost);
                productInfo += String.format("%-14s", quantity);
                productInfo += String.format("%-8s", retailed);
                productInfo += "\n";
                productInfo += "------------------------------------------------------------\n";

                Double retail = (product.getCost() + (product.getMargin() * 
                product.getCost() / 100));

                productInfo += String.format("%-6s",product.getId());
                productInfo += String.format("%-20s", product.getName());
                productInfo += "$";
                productInfo += String.format("%-13s", product.getCost());
                productInfo += String.format("%-14s", product.getQuantity());
                productInfo += "$";
                productInfo += String.format("%-7s", String.format("%.2f", retail)); 
        }
        else {
            productInfo = "Could not find the product";
        }

        return productInfo;
    }

    public String PrintInventoryList()
    {
        StringBuilder informationInventory = new StringBuilder();
        String id = "Id", name = "Name", cost = "Cost", quantity = "Quantity", retailed = "Retail";
        informationInventory.append(String.format("%-6s", id));
        informationInventory.append(String.format("%-20s", name));
        informationInventory.append(String.format("%-14s", cost));
        informationInventory.append(String.format("%-14s", quantity));
        informationInventory.append(String.format("%-8s", retailed));
        informationInventory.append("\n");
        String productInformation = null;
        for (int a = 0; a < Items.GetLength(); a++) {
            informationInventory.append("------------------------------------------------------------\n");
            Double retail = (Items.GetElement(a).getCost() + (Items.GetElement(a).getMargin() * Items.GetElement(a).getCost() / 100));

            productInformation = String.format("%-6s",Items.GetElement(a).getId()) + String.format("%-20s",Items.GetElement(a).getName().trim()) + "$"
                    + String.format("%-13s",Items.GetElement(a).getCost()) + String.format("%-14s",Items.GetElement(a).getQuantity()) + "$"
                    + String.format("%-7s",String.format("%.2f", retail));
            informationInventory.append(productInformation);
            informationInventory.append("\n");
        }
        return informationInventory.toString(); 
    }
}
