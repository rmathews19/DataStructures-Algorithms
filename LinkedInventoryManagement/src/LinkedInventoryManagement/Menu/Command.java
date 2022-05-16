//Reevan Mathews RXM180076

package LinkedInventoryManagement.Menu;

import java.io.IOException;
import java.lang.reflect.Constructor;

import LinkedInventoryManagement.Product.ProductCatalog;
import LinkedInventoryManagement.Security.User;

/**
 * Command
 */
public abstract class Command 
{
    private static String commandName;
    

    public Command(ProductCatalog productCatalog, User loggedOnUser) {
    }
    
    public Command() {  
    }

	public static Command CreateCommandDynamically(ProductCatalog productCatalog, User user, String commandClassName)
    {
        Class<?> concreteCommandClass = null;
        Command command1 = null;
        String packageName = "LinkedInventoryManagement.Menu";

        try {
            concreteCommandClass = Class.forName(packageName + "." + commandClassName);
            Constructor<?> con = concreteCommandClass.getConstructor(ProductCatalog.class, User.class);
            command1 = (Command) con.newInstance(productCatalog, user);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        commandName = commandClassName;
        return command1;
    }

    public String getCommandName()
    {
        return commandName;
    }

    public abstract void Execute() throws IOException;
}
