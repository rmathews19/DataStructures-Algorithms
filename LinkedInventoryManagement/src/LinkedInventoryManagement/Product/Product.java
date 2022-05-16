//Reevan Mathews RXM180076

package LinkedInventoryManagement.Product;

/**
 * This class represent a line in Inventory.dat file
 */
public class Product implements Comparable<Product>
{
    private int id;
    private int quantity; 
    private int margin;
    private String name;
    private double cost;

    public Product(int id, String name, double cost, int quantity, int margin)
    {
        this.id = id;
        this.quantity = quantity;
        this.margin = margin;
        this.name = name;
        this.cost = cost;
    }


	public Product() {
	}


	public void setId(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setCost(double cost)
    {
        this.cost = cost;
    }

    public double getCost()
    {
        return cost;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setMargin(int margin)
    {
        this.margin = margin;
    }

    public int getMargin()
    {
        return margin;
    }

    @Override
    public int compareTo(Product productToCompare) {
        return this.name.compareToIgnoreCase(productToCompare.getName());
    }

    
}
