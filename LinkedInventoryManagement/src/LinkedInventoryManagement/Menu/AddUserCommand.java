//Reevan Mathews RXM180076

package LinkedInventoryManagement.Menu;

import LinkedInventoryManagement.Product.ProductCatalog;
import LinkedInventoryManagement.Security.SecurityOperations;
import LinkedInventoryManagement.Security.User;

public class AddUserCommand extends Command
{
    User userAdding;

	public AddUserCommand(ProductCatalog productCatalog, User loggedOnUser) {
		super(productCatalog, loggedOnUser);
	}

    public AddUserCommand(User tempUser) {
        this.userAdding = tempUser;
	}

	@Override
    public void Execute()
    {
        System.out.println("\nAdding the User - ");
        SecurityOperations.AddNewUser(userAdding);
    }
}
