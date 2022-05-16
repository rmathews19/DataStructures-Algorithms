package RedboxInventory.Common;

//dxb126530 2021156501
public class Node<E extends Comparable<E>>
{
    E data;
    
    Node left = null;
    Node right = null;
    
    //default constructor
    Node()
    {}
    
    //overloaded constructor
    Node(E data, Node left, Node right)
    {
        this.data = data;
        this.left = left;
        this.right = right;
    }
    
    //overloaded constructor only for the payload
    Node(E data)
    {
        this.data = data;
    }
    
    //setters
    public void setData(E data)
    {
    	this.data = data;
    }
    public void setLeft(Node left) 
    {
    	this.left = left;
    }
    public void setRight(Node right) 
    {
    	this.right = right;
    }
    
    //getters
    public E getData() 
    {
    	return data;
    }
    public Node getLeft() 
    {
    	return left;
    }
    public Node getRight()
    {
    	return right;
    }

    public int compareTo(Node<E> newNode)
    {
        int i = 0;
        i = data.compareTo(newNode.data);
        return i;
    }
}