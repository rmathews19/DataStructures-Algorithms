//John McGlumphy 2021444158

package LinkedInventoryManagement.Menu;

import LinkedInventoryManagement.Product.Product;
import LinkedInventoryManagement.Product.ProductCatalog;
import LinkedInventoryManagement.Security.User;

public class RemoveProductCommand extends Command {

    public RemoveProductCommand(ProductCatalog productCatalog, User loggedOnUser) {
        super(productCatalog, loggedOnUser);
    }

    Product product;
    ProductCatalog productCatalog;

    public RemoveProductCommand(ProductCatalog productCatalog, User loggedOnUser, Product product) {
        super(productCatalog, loggedOnUser);
        this.product = product;
        this.productCatalog = productCatalog;
    }

    @Override
    public void Execute() {
        System.out.println("\nRemoving Product - ");
        productCatalog.RemoveProduct(product);
    }
}