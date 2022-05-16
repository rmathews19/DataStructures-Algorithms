//Reevan Mathews RXM180076

package LinkedInventoryManagement.PersistentStorage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static java.lang.Double.parseDouble;

import LinkedInventoryManagement.Common.InventoryLinkedList;
import LinkedInventoryManagement.Product.Product;

/**
 * InventoryOperations: This class should contain all the Inventory.dat file read, write 
 * and update operations. 
 */
public class InventoryOperations {

    private static InventoryLinkedList<Product> Items = new InventoryLinkedList<Product>();

    public static InventoryLinkedList<Product> InventoryReader() 
    {
        try (BufferedReader reader = new BufferedReader(new FileReader("Inventory.dat"))) {

            for (String line; (line = reader.readLine()) != null;) {
                String[] parts = line.split(",");

                Items.addLast(new Product(Integer.parseInt(parts[0]), parts[1].trim(), parseDouble(parts[2]),
                        Integer.parseInt(parts[3].trim()), Integer.parseInt(parts[4].trim())));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Items;
    }
    
    public static void UpdateFile() throws IOException {
        try (FileWriter fw = new FileWriter("Inventory.dat", false)) {
            for (int i = 0; i < Items.GetLength(); i++) {
                String productInformation = Items.GetElement(i).getId() + ", " + Items.GetElement(i).getName().trim() + ", "
                        + Items.GetElement(i).getCost() + ", " + Items.GetElement(i).getQuantity() + ", "
                        + Items.GetElement(i).getMargin();
                fw.write(productInformation + "\n");
            }
            fw.close();
        }
    }

    public static void WriteProduct(Product newProduct) {

        try (FileWriter f = new FileWriter("Inventory.dat", true);
                BufferedWriter b = new BufferedWriter(f);
                PrintWriter p = new PrintWriter(b);) {
            p.println(newProduct.getId() + ", " + newProduct.getName() + ", "
                        + newProduct.getCost() + ", " + newProduct.getQuantity() + ", "
                        + newProduct.getMargin());
        } catch (IOException i) {
        }
    }

}
