//John McGlumphy 2021444158

package LinkedInventoryManagement.Menu;

import LinkedInventoryManagement.Product.ProductCatalog;
import LinkedInventoryManagement.Security.SecurityOperations;
//Reevan Mathews RXM180076

import LinkedInventoryManagement.Security.User;

public class ChangePasswordCommand extends Command {

	User userChanging;
	String updatedPassword;

	public ChangePasswordCommand(ProductCatalog productCatalog, User loggedOnUser) {
		super(productCatalog, loggedOnUser);
		this.userChanging = loggedOnUser;
	}

	public ChangePasswordCommand(ProductCatalog productCatalog, User loggedOnUser, String updatedPassword) {
		super(productCatalog, loggedOnUser);
		this.userChanging = loggedOnUser;
		this.updatedPassword = updatedPassword;
	}

	public ChangePasswordCommand(User userChanging2, String updatedPassword2) {
		this.userChanging = userChanging2;
		this.updatedPassword = updatedPassword2;
	}

	@Override
	public void Execute() {
		SecurityOperations.ChangePassword(userChanging.getUsername(), userChanging.getHashedPassword(),
				updatedPassword);
	}
}
