//Reevan Mathews RXM180076

package LinkedInventoryManagement.PersistentStorage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import LinkedInventoryManagement.Common.InventoryLinkedList;
import LinkedInventoryManagement.Common.ScannerFactory;
import LinkedInventoryManagement.Menu.Command;
import LinkedInventoryManagement.Menu.MenuItem;
import LinkedInventoryManagement.Product.ProductCatalog;
import LinkedInventoryManagement.Security.User;

/**
 * MenuListOperations: This class should contain all the MenuList.dat file read, write 
 * and update operations.
 */
public class MenuListOperations
{
    static InventoryLinkedList<MenuItem> menu = new InventoryLinkedList<MenuItem>();

    public static InventoryLinkedList<MenuItem> ReadList(ProductCatalog product, User user)
    {
        String description;
        Boolean isRestricted;

        Scanner input = ScannerFactory.GetScannerInstance();
        try {
            input = new Scanner(new File("MenuList.dat"));
        } catch (FileNotFoundException ex) {
        }
        while (input.hasNextLine()) {
            boolean removeItem = false;
            String line = input.nextLine();
            String[] tokens = line.split(", ");
            String inp = null;

            description = tokens[0];
            isRestricted = Boolean.parseBoolean(tokens[1]);
            inp = tokens[2];
            Command dynamicCommand = Command.CreateCommandDynamically(product, user, inp);
            MenuItem pass = new MenuItem(dynamicCommand, description, isRestricted);
            

            if (menu.GetLength() == 0) {
                if ((!user.getIsManager()) && (!pass.getIsRestricted()))
                {
                    menu.addLast(pass);
                }
                else if (user.getIsManager()) {
                    menu.addLast(pass);
                }
            }
            else {
                for (int i = 0; i < menu.GetLength(); i++) {
                    if (menu.GetElement(i).compareTo(pass) == 0) {
                        removeItem = true;
                    }
                }
                if (!removeItem) {
                    if ((!user.getIsManager()) && (!pass.getIsRestricted())) {
                        menu.addLast(pass);
                    }
                    else if (user.getIsManager()){
                        menu.addLast(pass);
                    }
                }
            }
        }
        menu.addLast(new MenuItem(null, "Exit", false));
        return menu;
    }
    
}
