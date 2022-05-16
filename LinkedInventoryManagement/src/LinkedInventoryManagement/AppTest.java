//Reevan Mathews RXM180076

package LinkedInventoryManagement;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import LinkedInventoryManagement.Common.InventoryLinkedList;
import LinkedInventoryManagement.Product.Product;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */

    @Test
    public void GetFirstTest()
    {
        InventoryLinkedList<Product> now = new InventoryLinkedList<>();

        if(now.GetFirst() == null)
        {
            assertTrue( true );
        }
        else
        {
            assertTrue( false );
        }
    }

    @Test
    public void GetLastTest()
    {
        InventoryLinkedList<Product> now = new InventoryLinkedList<>();

        if(now.GetLast() == null)
        {
            assertTrue( true );
        }
        else
        {
            assertTrue( false );
        }
    }

    @Test
    public void InsertTest()
    {
        InventoryLinkedList<Product> now = new InventoryLinkedList<>();
        Product given = new Product(15, "Heh", 16.3, 1, 20);
        now.Insert(1, given);

        boolean found = now.Contains(new Product(15, "Heh", 16.3, 1, 20));

        if(found)
        {
            assertTrue( true );
        }
        else
        {
            assertTrue( false );
        }
    }

    @Test
    public void RemoveTest()
    {
        InventoryLinkedList<Product> now = new InventoryLinkedList<>();
        Product given = new Product(15, "Heh", 16.3, 1, 20);
        now.Insert(0, given);
        now.Remove(0);

        boolean found = now.Contains(new Product(15, "Heh", 16.3, 1, 20));

        if(found)
        {
            assertTrue( false );
        }
        else
        {
            assertTrue( true );
        }
    }

    @Test
    public void ContainsTest()
    {
        InventoryLinkedList<Product> now = new InventoryLinkedList<>();
        Product given = new Product(15, "Heh", 16.3, 1, 20);
        now.Insert(1, given);

        boolean found = now.Contains(new Product(15, "Heh", 16.3, 1, 20));

        if(found)
        {
            assertTrue( true );
        }
        else
        {
            assertTrue( false );
        }
    }

    @Test
    public void SetElementTest()
    {
        InventoryLinkedList<Product> now = new InventoryLinkedList<>();
        Product given = new Product(15, "Heh", 16.3, 1, 20);
        Product changed = new Product(13, "Change", 0.2, 4, 5);
        now.Insert(0, given);
        now.SetElement(0, changed);

        boolean found = now.Contains(new Product(13, "Change", 0.2, 4, 5));

        if(found)
        {
            assertTrue( true );
        }
        else
        {
            assertTrue( false );
        }
    }

    @Test
    public void GetElementTest()
    {
        InventoryLinkedList<Product> now = new InventoryLinkedList<>();
        Product given = new Product(15, "Heh", 16.3, 1, 20);
        now.Insert(0, given);
        Product check = now.GetElement(0);

        if(check == given)
        {
            assertTrue( true );
        }
        else
        {
            assertTrue( false );
        }
    }}