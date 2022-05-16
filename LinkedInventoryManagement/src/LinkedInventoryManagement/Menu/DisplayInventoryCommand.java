//Reevan Mathews RXM180076

package LinkedInventoryManagement.Menu;

import LinkedInventoryManagement.Product.ProductCatalog;
import LinkedInventoryManagement.Security.User;

public class DisplayInventoryCommand extends Command {

	ProductCatalog productCatalog;
	public DisplayInventoryCommand(ProductCatalog productCatalog, User loggedOnUser) {
		super(productCatalog, loggedOnUser);
		this.productCatalog = productCatalog;
	}

	@Override
	public void Execute() {
		System.out.println("\nDisplaying Inventory - \n");
		System.out.println(productCatalog.PrintInventoryList());
		
	}
}
