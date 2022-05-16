//Reevan Mathews RXM180076

package LinkedInventoryManagement.Security;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import LinkedInventoryManagement.Common.InventoryLinkedList;
import LinkedInventoryManagement.PersistentStorage.UsersOperations;

/**
 * InventoryManagementSecurity
 */
public class SecurityOperations 
{
    static InventoryLinkedList<User> Users = new InventoryLinkedList<User>();

    public SecurityOperations() throws FileNotFoundException {
        SecurityOperations.Users = UsersOperations.ReadUsers();
    }

    public static User AuthenticateUser(String username, String password)
    {
        SecurityOperations.Users = UsersOperations.ReadUsers();
        User authenticatedUser = null;

        if((username.compareToIgnoreCase("admin") == 0) && 
           (GetPasswordHash(password).compareToIgnoreCase("58c536ed8facc2c2a293a18a48e3e120") == 0))
        {
            authenticatedUser = new User(username, GetPasswordHash(password), true, "Admin", "Admin"); 
        }
        else
        {
            for (int i = 0; i < Users.GetLength(); i++) {
                if ((username.compareToIgnoreCase(Users.GetElement(i).getUsername()) == 0) && (GetPasswordHash(password)
                        .compareToIgnoreCase(Users.GetElement(i).getHashedPassword()) == 0)) 
                {
                    authenticatedUser = Users.GetElement(i);
                }
            }
        }

        return authenticatedUser;
    }

    public static void AddNewUser(User newUser)
    {
        newUser.setHashedPassword(newUser.getHashedPassword());
    
        if (Users.Contains(newUser)) {
            System.out.println("This User already exists - ");
        }
        else {
            Users.addLast(newUser);
            UsersOperations.WriteUsers(newUser);
        }
        
    }
    
    public static void RemoveUser(String userName)
    {
        for (int i = 0; i < Users.GetLength(); i++) {
                if ((userName.compareToIgnoreCase(Users.GetElement(i).getUsername()) == 0)) 
                {
                    Users.Remove(i);
                    try {
						UsersOperations.UpdateUsers(Users);
					} catch (IOException e) {
						e.printStackTrace();
					}
                }
        }
    }

    public static void ChangePassword(String username, 
                                      String currentPassword, 
                                      String newPassword)
    {
        boolean changed = false;
        for (int i = 0; i < Users.GetLength(); i++) 
        {
            if ((username.compareToIgnoreCase(Users.GetElement(i).getUsername()) == 0)
                    && (GetPasswordHash(currentPassword)
                            .compareToIgnoreCase(Users.GetElement(i).getHashedPassword()) == 0)) {
                User userToChange = new User();
                userToChange = Users.GetElement(i);
                userToChange.setHashedPassword(SecurityOperations.GetPasswordHash(newPassword));
                changed = true;
                try {
                    UsersOperations.UpdateUsers(Users);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        if (changed) {
            System.out.println("The User's Password was changed");
        }
        else {
            System.out.println("The User's Username or Password was incorrect");
        }
        
    }

    public static String GetPasswordHash(String password) 
    {        
        String generatedPassword = null;
        
        try 
        {
            byte[] salt = new byte[] {12, -12, 65, 61, 
                                      2, -6, -90, 12, 
                                      4, -7, -87, 2, 
                                      34, -102, 3, 115};

            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            // Add password bytes to digest
            md.update(salt);
            // Get the hash's bytes
            byte[] bytes = md.digest(password.getBytes());
            // This bytes[] has bytes in decimal format;
            // Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            // Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        } 

        return generatedPassword;
    }
}
