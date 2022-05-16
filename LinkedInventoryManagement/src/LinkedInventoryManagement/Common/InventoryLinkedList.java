//Reevan Mathews RXM180076
package LinkedInventoryManagement.Common;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * InventoryLinkedList
 */
public class InventoryLinkedList<E extends Comparable<E>> implements Iterable<E> 
{
    private Node head, tail;
    private int size = 0;

    public InventoryLinkedList() 
    {
    }

    public InventoryLinkedList(E[] elements) 
    {
        for (int i = 0; i < elements.length; i++) {
            add(elements[i]);
        }
    }

    public E GetFirst()
    {
        Node temp = this.head;
        if (head == null)
            throw new NoSuchElementException();
        return temp.element;
    }

    public E GetLast()
    {
        if (size == 0) {
            return null;
        } else {
            return tail.element;
        }
    }

    public void add(E element) 
    {
        Insert(size, element);
    }

    public void Insert(int position, E element) 
    {
        if (position < 0 || position == 0) {
            addFirst(element);
        } else if (position > GetLength() || position == GetLength()) {
            addLast(element);
        } else {

            Node temp = head;
            Node newnode = new Node(element);
            for (int i = 0; i < position - 1; i++) {
                temp = temp.getNextNode();
            }

            newnode.setNextNode(temp.getNextNode());
            temp.getNextNode().setPreviousNode(newnode);
            temp.setNextNode(newnode);
            newnode.setPreviousNode(temp);
        }
    }

    public E Remove(int index) 
    {
        if (index < 0 || index >= size)
            return null;
        else if (index == 0)
            return removeFirst();
        else if (index == size - 1)
            return removeLast();
        else {
            Node previous = head;
            for (int i = 1; i < index; i++) {
                previous = previous.next;
            }
            Node current = previous.next;
            previous.next = current.next;
            size--;
            return current.element;
        }
    }

    public E removeFirst() 
    {
        if (size == 0)
            return null;
        else {
            Node temp = head;
            head = head.next;
            size--;
            if (head == null)
                tail = null;
            return temp.element;
        }
    }

    public E removeLast() 
    {
        if (size == 0)
            return null;
        else if (size == 1) {
            Node temp = head;
            head = tail = null;
            size = 0;
            return temp.element;
        } else {
            Node current = head;
            for (int i = 0; i < size - 2; i++)
                current = current.next;
            Node temp = tail;
            tail = current;
            tail.next = null;
            size--;
            return temp.element;
        }
    }

    public void addFirst(E element) 
    {
        Node newnode = new Node(element);
        if (head == null) {
            head = newnode;
            tail = newnode;
            newnode.setNextNode(null);
            newnode.setPreviousNode(null);
        } else {
            newnode.setNextNode(head);
            head.setPreviousNode(newnode);
            head = newnode;
        }
    }

    public void addLast(E element) 
    {
        Node newnode = new Node(element);
        if (tail == null) {
            head = newnode;
            tail = newnode;
            newnode.setNextNode(null);
            newnode.setPreviousNode(null);
        } else {
            newnode.setPreviousNode(tail);
            tail.setNextNode(newnode);
            tail = newnode;
        }
        size++;
    }

    public boolean Contains(E element) 
    {
        Node current = head;
        while (current != null) {
            if (current.element.compareTo(element) == 0) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public E SetElement(int index, E element) 
    {
        if (index < 0 || index > size - 1)
            return null;
        else {
            Node current = head;
            for (int i = 0; i < index; i++)
                current = current.next;

            current.element = element;
            return current.element;
        }
    }

    public E GetElement(int index)
    {
        if (index < 0 || index >= GetLength())
            return null;
        else if (index == 0)
            return head.element;
        else if (index == size - 1)
            return tail.element;
        else {
            Node current = head.next;

            for (int i = 1; i < index; i++)
                current = current.next;
            return current.element;
        }
    }

    public String toString() {
        StringBuilder result = new StringBuilder("[");

        Node current = head;
        for (int i = 0; i < size; i++) {
            result.append(current.element);
            current = current.next;
            if (current != null) {
                result.append(", ");
            } else {
                result.append("]");
            }
        }
        return result.toString();
    }

    public int GetLength() {
        int count = 0;
        if (head == null)
            return count;
        else {
            Node temp = head;
            do {
                temp = temp.getNextNode();
                count++;
            } while (temp != null);
        }
        return count;
    }

    public E GetPrev(E ele) {
        Node current = head;
        Node newnode = null;
        while (current != null) {
            if (current.element.compareTo(ele) == 0) {
                newnode = current.previous;
                return newnode.element;
            }
            current = current.next;
        }
        return null;
    }

    public E GetNext(E ele) {
        Node current = head;
        Node newnode = null;
        while (current != null) {
            if (current.element.compareTo(ele) == 0) {
                newnode = current.next;
                return newnode.element;
            }
            current = current.next;
        }
        return null;
    }

    class Node {
        private E element;
        private Node next;
        private Node previous;

        Node() {
            next = null;
            previous = null;
            element = null;
        }

        Node(E element) {
            this(element, null, null);
        }

        Node(E e, Node next, Node previous) {
            this.element = e;
            this.next = next;
            this.previous = previous;
        }

        E getElement() {
            return element;
        }

        // next + previous
        public void setNextNode(Node next) {
            this.next = next;
        }

        public Node getPreviousNode() {
            return previous;
        }

        public void setPreviousNode(Node previous) {
            this.previous = previous;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public Node getNextNode() {
            return next;
        }
    }

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
}

