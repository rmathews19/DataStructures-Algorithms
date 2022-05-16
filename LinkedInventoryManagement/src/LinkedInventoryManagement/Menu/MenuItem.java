//Reevan Mathews RXM180076

package LinkedInventoryManagement.Menu;

/**
 * MenuItem
 */
public class MenuItem implements Comparable<MenuItem>
{
    String description;
    int choiceNumber;
    boolean isRestricted;

    public MenuItem()
    {

    }

    public MenuItem(Command command, 
                    int choiceNumber, 
                    String description, 
                    Boolean isRestricted)
    {
        System.out.println("Menu item created with command " + command.getClass().getSimpleName());
    }

    public MenuItem(int choiceNumber,
                    String description,
                    Boolean isRestricted)
    {
        this.choiceNumber = choiceNumber;
        this.description = description;
        this.isRestricted = isRestricted;
        System.out.println("Number 1");
    }

    public MenuItem(Command dynamicCommand, String description2, Boolean isRestricted2) {
        this.description = description2;
        this.isRestricted = isRestricted2;
        System.out.println("Number 2");
	}

    public MenuItem(String string, boolean b) {
        this.description = string;
        this.isRestricted = b;
        System.out.println("Number 3");
	}

	public String getDescription()
    {
        return description;
    }

    public boolean getIsRestricted()
    {
        return isRestricted;
    }

    public int getChoiceNumber()
    {
        return choiceNumber;
    }

    public void setChoiceNumber(int option)
    {
        this.choiceNumber = option;
    }

	@Override
    public int compareTo(MenuItem menuItemToCompare) {
        return this.description.compareTo(menuItemToCompare.description);
    }
}
