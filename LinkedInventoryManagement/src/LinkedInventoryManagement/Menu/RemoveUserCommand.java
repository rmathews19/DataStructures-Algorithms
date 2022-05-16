//Reevan Mathews RXM180076

package LinkedInventoryManagement.Menu;

import java.io.IOException;

import LinkedInventoryManagement.Product.ProductCatalog;
import LinkedInventoryManagement.Security.SecurityOperations;
import LinkedInventoryManagement.Security.User;

public class RemoveUserCommand extends Command {
	
	User loggedOn;

	public RemoveUserCommand(ProductCatalog productCatalog, User loggedOnUser) {
		super(productCatalog, loggedOnUser);
		this.loggedOn = loggedOnUser;
	}

	public RemoveUserCommand(User userToRemove) {
		this.loggedOn = userToRemove;
	}

	@Override
	public void Execute() throws IOException {
		SecurityOperations.RemoveUser(loggedOn.getUsername());
	}
}
