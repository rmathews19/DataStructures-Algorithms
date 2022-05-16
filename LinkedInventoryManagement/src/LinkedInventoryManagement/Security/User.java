//Reevan Mathews RXM180076

package LinkedInventoryManagement.Security; 

/**
 * User
 */
public class User implements Comparable<User>
{
    private String firstName;
    private String lastName;
    private String user;
    private String hashedPassword;
    private boolean isManager;

    public User()
    {
        
    }

    public User(String username, String hashedPassword, boolean isManager, String firstName, String lastName)
    {
        this.user = username;
        this.hashedPassword = hashedPassword;
        this.isManager = isManager;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public User(String username2, String pass) {
        this.user = username2;
        this.hashedPassword = pass;
	}

	public void setUsername(String username)
    {
        this.user = username;
    }

    public String getUsername()
    {
        return user;
    }

    public void setHashedPassword(String hashedPassword)
    {
        this.hashedPassword = hashedPassword;
    }

    public String getHashedPassword()
    {
        return hashedPassword;
    }

    public void setIsManager(boolean isManager)
    {
        this.isManager = isManager;
    }
    
    public boolean getIsManager()
    {
        return isManager;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

	@Override
	public int compareTo(User o) {
		return this.user.compareTo(o.user);
	}
}
