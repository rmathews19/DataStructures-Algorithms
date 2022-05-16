//Reevan Mathews RXM180076

package LinkedInventoryManagement.Menu;

import LinkedInventoryManagement.Product.Product;
import LinkedInventoryManagement.Product.ProductCatalog;
import LinkedInventoryManagement.Security.User;

public class UpdateProductCommand extends Command {

	Product productUpdating;
	ProductCatalog productCatalog;

	public UpdateProductCommand(ProductCatalog productCatalog, User loggedOnUser) {
		super(productCatalog, loggedOnUser);
	}

	public UpdateProductCommand(ProductCatalog productCatalog, User loggedOnUser, Product productUpdating) {
		super(productCatalog, loggedOnUser);
		this.productUpdating = productUpdating;
		this.productCatalog = productCatalog;
	}

	@Override
	public void Execute() {
		System.out.println("\nUpdating the Product - ");
		productCatalog.AddUpdateProduct(productUpdating);
	}
}
