//Reevan Mathews RXM180076

package LinkedInventoryManagement.Menu;

import java.io.IOException;
import java.util.Scanner;

import LinkedInventoryManagement.Common.InventoryLinkedList;
import LinkedInventoryManagement.Common.ScannerFactory;
import LinkedInventoryManagement.PersistentStorage.MenuListOperations;
import LinkedInventoryManagement.Product.Product;
import LinkedInventoryManagement.Product.ProductCatalog;
import LinkedInventoryManagement.Security.SecurityOperations;
import LinkedInventoryManagement.Security.User;

/**
 * MenuList
 */
public class MenuList {

    static InventoryLinkedList<MenuItem> list = new InventoryLinkedList<MenuItem>();
    static InventoryLinkedList<Product> products = new InventoryLinkedList<Product>();
    private String menuHeader;
    private ProductCatalog productCatalog;
    
	public MenuList(String menuHeader, ProductCatalog product, User user)
    {
        this.menuHeader = menuHeader;
        this.productCatalog = product;
        MenuList.list = MenuListOperations.ReadList(product, user);
    }

    public static void AddMenuItem(MenuItem menuItem)
    {
        list.addLast(menuItem);
    }

    public void StartMenu(User user)
    {
        Scanner userInp = ScannerFactory.GetScannerInstance();
        boolean exit = false;

        do {
            int choice = 0;
            int choiceNumber = 1;
            System.out.println("" + this.menuHeader + "\n");

            for (int i = 0; i < list.GetLength(); i++) {
                list.GetElement(i).setChoiceNumber(choiceNumber);
                System.out.println(
                        list.GetElement(i).getChoiceNumber() + ") " + list.GetElement(i).getDescription());
                choiceNumber++;
            }

            System.out.println("\nEnter Choice: ");
            choice = userInp.nextInt();
            String choiceName = "";
            choiceName = MenuList.getMenuItemById(choice).getDescription();
            
            if (choiceName.contains("Add User")) {
                String newFirstName, newLastName, newUserName, newPassword;
                Boolean ismanager = null;

                System.out.print("\nEnter the New User's First Name: ");
                userInp.nextLine();
                newFirstName = userInp.nextLine();
                System.out.print("Enter the New User's Last Name: ");
                newLastName = userInp.nextLine();
                System.out.print("Enter the New Username: ");
                newUserName = ScannerFactory.GetScannerInstance().nextLine();
                System.out.print("Enter the New User's password: ");
                newPassword = ScannerFactory.GetScannerInstance().nextLine();
                System.out.print("Is the User a Manager? enter true or false: ");
                ismanager = ScannerFactory.GetScannerInstance().nextBoolean();

                User tempUser = new User(newUserName, SecurityOperations.GetPasswordHash(newPassword), ismanager,
                        newFirstName, newLastName);

                Command command1 = new AddUserCommand(tempUser);
                try {
                    command1.Execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (choiceName.contains("Remove User")) {
                User userToRemove = new User();
                System.out.print("\nEnter the UserName To Remove: ");
                userInp.nextLine();
                userToRemove.setUsername(userInp.nextLine());

                Command command1 = new RemoveUserCommand(userToRemove);
                try {
                    command1.Execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (choiceName.contains("Change Password")) 
            {
                User userToChange = new User();
                String newPassword = null;
                System.out.print("\nEnter the UserName: ");
                userInp.nextLine();
                userToChange.setUsername(userInp.nextLine());
                System.out.print("Enter the Current Password: ");
                userToChange.setHashedPassword(userInp.nextLine());
                System.out.print("Enter the New Password: ");
                newPassword = userInp.nextLine();

                Command command1 = new ChangePasswordCommand(userToChange, newPassword);

                try {
                    command1.Execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            if (choiceName.contains("Add New Product")) {
                Product productToAdd = new Product();
                System.out.print("\nEnter the New Product Name: ");
                userInp.nextLine();
                productToAdd.setName(userInp.nextLine());
                System.out.print("Enter the New Product ID: ");
                productToAdd.setId(userInp.nextInt());
                System.out.print("Enter the New Product Price: ");
                productToAdd.setCost(userInp.nextDouble());
                System.out.print("Enter the New Product Quantity: ");
                productToAdd.setQuantity(userInp.nextInt());
                System.out.print("Enter the New Product Margin: ");
                productToAdd.setMargin(userInp.nextInt());

                Command command1 = new AddProductCommand(productCatalog, user, productToAdd);
                try {
                    command1.Execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (choiceName.contains("Update Product Information")) {
                Product productToUpdate = new Product();
                System.out.print("\nEnter Product ID: ");
                userInp.nextLine();
                int id2 = Integer.parseInt(userInp.nextLine());
                productToUpdate.setId(id2);
                System.out.print("Enter the New Product Name: ");
                productToUpdate.setName(userInp.nextLine());
                System.out.print("Enter the New Product Cost: ");
                productToUpdate.setCost(userInp.nextDouble());
                System.out.print("Enter the New Product Quantity: ");
                productToUpdate.setQuantity(userInp.nextInt());
                System.out.print("Enter the New Product Margin: ");
                productToUpdate.setMargin(userInp.nextInt());

                Command command1 = new UpdateProductCommand(productCatalog, user, productToUpdate);
                try {
					command1.Execute();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }

            if (choiceName.contains("Display Product Information")) {
                Product currentProduct = new Product();
                boolean quit = false;
                boolean exist = false;
                
                do {
                    System.out.print("\nEnter the product name: ");
                    userInp.nextLine();
                    String startingProductName = userInp.nextLine();
                    currentProduct.setName(startingProductName);

                    if (productCatalog.Items.Contains(currentProduct))
                    {
                        exist = true;
                        Command command1 = new DisplayProductCommand(productCatalog, user, currentProduct);
                        try {
                            command1.Execute();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        System.out.println("The product you entered doesn't exist!");
                    }
                } while (!exist);
                
                do {
                    
                    System.out.println();
                    System.out.print(
                            "Type \"Next\" or \"Previous\" to display next/previous product, press enter to return: ");
                    String answer = userInp.nextLine();

                    if (answer.compareToIgnoreCase("next") == 0) {
                        currentProduct = productCatalog.Items.GetNext(currentProduct);
                        if (currentProduct == null) {
                            System.out.println();
                            System.out.println("End of products list - ");
                        }
                        else {
                            Command command2 = new DisplayProductCommand(productCatalog, user, currentProduct);
                            try {
                                command2.Execute();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    else if (answer.compareToIgnoreCase("previous") == 0) {
                        currentProduct = productCatalog.Items.GetPrev(currentProduct);
                        if (currentProduct == null) {
                            System.out.println();
                            System.out.println("Start of products list - ");
                        }
                        else {
                            Command command3 = new DisplayProductCommand(productCatalog, user, currentProduct);
                            try {
                                command3.Execute();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    else if (answer.compareToIgnoreCase("") == 0) {
                        quit = true;
                    }

                } while (!quit);
                  
            }

            if (choiceName.contains("Delete Product")) {
                Product productToRemove = new Product();
                String productName;
                System.out.println("\nEnter the name of the product you would like to remove: ");
                userInp.nextLine();
                productName = userInp.nextLine();
                productToRemove.setName(productName);
                Command command1 = new RemoveProductCommand(productCatalog, user, productToRemove);
                try {
					command1.Execute();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }

            if (choiceName.contains("Display Inventory"))
            {
                Command command1 = new DisplayInventoryCommand(productCatalog, user);
                try {
					command1.Execute();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }

            if(choiceName.contains("Exit"))
            {
                exit = true;
            }

        } while (!exit);
    }
    
    public static MenuItem getMenuItemById(int id)
    {
        for (int i = 0; i < list.GetLength(); i++) {
            if (list.GetElement(i).getChoiceNumber() == id) {
                return list.GetElement(i);
            }
        }
        return null;
    }
}
