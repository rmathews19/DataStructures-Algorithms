//Reevan Mathews RXM180076

package LinkedInventoryManagement;

import LinkedInventoryManagement.Menu.*;

import java.io.FileNotFoundException;

import LinkedInventoryManagement.Common.*;
import LinkedInventoryManagement.Product.*;
import LinkedInventoryManagement.Security.*;

/**
 * Hello world!
 */
public class Main 
{
    public static void main( String[] args ) throws FileNotFoundException
    {
        String choice = "", user, password;
        User currentUser = null;
        boolean success = false;
        
        ProductCatalog productCatalog = new ProductCatalog();
       
        do {
            System.out.print("\nEnter Username: ");
            user = ScannerFactory.GetScannerInstance().nextLine();

            System.out.print("Enter Password: ");
            password = ScannerFactory.GetScannerInstance().nextLine();

            currentUser = SecurityOperations.AuthenticateUser(user, password);

            if (currentUser != null)
            {
                success = true;
            }
            else {
                System.out.println("\nInvalid Username Or Password!");
                System.out.print("Press 'Enter' to try again Or 'Exit' to exit: ");
                choice = ScannerFactory.GetScannerInstance().nextLine();

                if (choice.compareToIgnoreCase("exit") == 0)
                {
                    System.exit(0);
                }
            }
            
        } while (!success);
                
        MenuList menuList = new MenuList("\nInventory-Management System Menu ", productCatalog, currentUser);
        
        menuList.StartMenu(currentUser);
        
        ScannerFactory.GetScannerInstance().nextLine();
        
        ScannerFactory.CloseScannerInstance();
    }
}

