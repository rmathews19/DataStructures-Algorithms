//Reevan Mathews RXM180076

package LinkedInventoryManagement.Menu;

import LinkedInventoryManagement.Product.Product;
import LinkedInventoryManagement.Product.ProductCatalog;
import LinkedInventoryManagement.Security.User;

/**
 * AddCommand
 */


public class AddProductCommand extends Command {

    Product productAdding;
    ProductCatalog productCatalog;

    public AddProductCommand(ProductCatalog productCatalog, User loggedOnUser) {
		super(productCatalog, loggedOnUser);
	}

	public AddProductCommand(ProductCatalog productCatalog, User loggedOnUser, Product product) {
		super(productCatalog, loggedOnUser);
        this.productAdding = product;
        this.productCatalog = productCatalog;
	}

	@Override
	public void Execute() {
        System.out.println("\nAdding the Product - ");
        productCatalog.AddUpdateProduct(productAdding);
	}
}
