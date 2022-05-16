//Reevan Mathews RXM180076

package LinkedInventoryManagement.PersistentStorage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import LinkedInventoryManagement.Common.InventoryLinkedList;
import LinkedInventoryManagement.Security.User;

/**
 * UsersOperations: This class should contain all the Users.dat file read, write 
 * and update operations.
 */
public class UsersOperations 
{
    private static InventoryLinkedList<User> Users = new InventoryLinkedList<User>();

    public static InventoryLinkedList<User> ReadUsers()  
    {

        try {
            Scanner userScanner = new Scanner(new File("Users.dat"));

            while (userScanner.hasNextLine()) {
                String line = userScanner.nextLine();

                String[] userComponents = line.split(",");

                String stringFirstName = userComponents[0];
                String stringLastName = userComponents[1];
                String stringUserName = userComponents[2];
                String stringPassword = userComponents[3];
                String stringManager = userComponents[4];
                boolean isManager = Boolean.parseBoolean(stringManager.trim());

                User tempUser = new User(stringUserName.trim(), (stringPassword.trim()), isManager, stringFirstName.trim(),
                        stringLastName.trim());

                Users.addLast(tempUser);
            }
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }

        return Users;
        
    }
    
    public static void WriteUsers(User newUser) {

        try (FileWriter f = new FileWriter("Users.dat", true);
                BufferedWriter b = new BufferedWriter(f);
                PrintWriter p = new PrintWriter(b);) {
            p.println(newUser.getFirstName() + ", " + newUser.getLastName() + ", " + newUser.getUsername() + ", "
                    + newUser.getHashedPassword() + ", " + newUser.getIsManager());
        } catch (IOException i) {
        }
    }
    
    public static void UpdateUsers(InventoryLinkedList<User> Items) throws IOException {
         try (FileWriter fw = new FileWriter("Users.dat", false)) {
            for (int i = 0; i < Items.GetLength(); i++) {
                String userInfo = Items.GetElement(i).getFirstName() + ", " + Items.GetElement(i).getLastName() + ", "
                        + Items.GetElement(i).getUsername() + ", " + Items.GetElement(i).getHashedPassword() + ", "
                        + Items.GetElement(i).getIsManager();
                fw.write(userInfo + "\n");
            }
        }
    }

}
