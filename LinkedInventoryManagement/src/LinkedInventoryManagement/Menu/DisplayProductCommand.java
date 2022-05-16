//Reevan Mathews RXM180076

package LinkedInventoryManagement.Menu;

import LinkedInventoryManagement.Product.Product;
import LinkedInventoryManagement.Product.ProductCatalog;
import LinkedInventoryManagement.Security.User;

public class DisplayProductCommand extends Command {

    private Product productDisplaying;
    private ProductCatalog productCatalog;

    public DisplayProductCommand(ProductCatalog productCatalog, User loggedOnUser) {
        super(productCatalog, loggedOnUser);
    }

    public DisplayProductCommand(ProductCatalog productCatalog, User loggedOnUser, Product productDisplaying) {
        super(productCatalog, loggedOnUser);
        this.productDisplaying = productDisplaying;
        this.productCatalog = productCatalog;
    }
    @Override
    public void Execute() {

            System.out.println("\nDisplaying the Product Information - ");
            System.out.println(productCatalog.PrintProductInformation(productDisplaying));

    }
}