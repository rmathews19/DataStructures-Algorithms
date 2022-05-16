package RedboxInventory.Common;

import java.io.PrintWriter;

//dxb126530 2021156501
public class BinarySearchTree<E extends Comparable<E>>
{
    Node<E> root = null;

    public void insert(E data)
    {
        Node<E> newNode = new Node(data);
        Node<E> current = root;
        insert(newNode, current);
    }

    public void insert(Node newNode, Node current)
    {
        if(root == null)
        {
            root = newNode;
        }
        else
        {
            //if the number is negative, it goes to the left
            if(newNode.compareTo(current) < 0)
            {
                if(current.left == null)
                {
                    current.left = newNode;
                }
                else
                {
                    insert(newNode, current.left);
                }
            }
            
            //if the number is positive, it goes to the right
            else if(newNode.compareTo(current) > 0)
            {
                if(current.right == null)
                {
                    current.right = newNode;
                }
                else
                {
                    insert(newNode, current.right);
                }
            }
        }
    }
    
    public String printTree(PrintWriter pw)
    {
        String line = "";
        line += printTree(root, line, pw);
        return line;
    }
    
    public String printTree(Node<E> node, String bstree, PrintWriter pw)
    {
        if(node == null)
        {
            return "";
        }
        printTree(node.left, bstree, pw);
        bstree = node.data.toString() + '\n';
        pw.print(bstree);
        printTree(node.right, bstree, pw);
        
        return bstree;
    }
    

    public E searchTree(E data)
    {
        Node<E> newNode = new Node(data);
        return searchTree(root, newNode);
    }
    
    public E searchTree(Node<E> currentNode, Node<E> newNode)
    {
        if(currentNode == null)
            return null;
        else if(newNode.compareTo(currentNode) < 0)
        {
            return (E)searchTree(currentNode.left, newNode);
        }
        else if(newNode.compareTo(currentNode) > 0)
        {
            return (E)searchTree(currentNode.right, newNode);
        }
        else if(newNode.compareTo(currentNode) == 0)
        {
            return currentNode.data;
        }
        
        return null;
    }
            

    public E deleteNode(E data)
    {
        Node<E> parent = null;
        Node<E> currentNode = root;
       
        while(currentNode != null && data.compareTo(currentNode.data) != 0)
        {
            parent = currentNode;
            if(data.compareTo(currentNode.data) < 0)
            {
            	currentNode = currentNode.left;
            }
            else
            {
            	currentNode = currentNode.right;
            }
        }
        
        if(currentNode != null)
        {
            if(currentNode.left == null && currentNode.right == null)
            {
                if(parent == null)
                {
                    root = null;
                }
                else if(currentNode.compareTo(parent) > 0)
                {
                	parent.right = null;
                }
                else
                {
                	parent.left = null;
                }
            }
            else if(currentNode.left == null ^ currentNode.right == null)
            {
                if(currentNode.left == null)
                {
                    //if the node we were looking for was the root
                    if(parent == null)
                    {
                        root = currentNode.right;
                    }
                    else if(currentNode.compareTo(parent) > 0)
                    {
                        parent.right = currentNode.right;
                    }
                    else
                    {
                        parent.left = currentNode.right;
                    }
                }
                else
                {
                    if(parent == null)
                    {
                        root = currentNode.left;
                    }
                    else if(currentNode.compareTo(parent) > 0)
                    {
                        parent.right = currentNode.left;
                    }
                    else
                    {
                        parent.left = currentNode.left;
                    }
                }
            }
            else
            {
                parent = currentNode;
                currentNode = currentNode.left;
                
                while(currentNode.right != null)
                {
                	currentNode = currentNode.right;
                }
                parent.data = deleteNode(currentNode.data);
            }
        }
        
        return currentNode.data;
    }
}
