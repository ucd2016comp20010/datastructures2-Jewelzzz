package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class SinglyLinkedList<E> implements List<E> {

    private static class Node<E>
    {

        private E element;            // reference to the element stored at this node

        /**
         * A reference to the subsequent node in the list
         */
        private Node<E> next;         // reference to the subsequent node in the list

        /**
         * Creates a node with the given element and next node.
         *
         * @param e the element to be stored
         * @param n reference to a node that should follow the new node
         */
        public Node(E e, Node<E> n)
        {
            element = e;
            next = n;
        }

        // Accessor methods

        /**
         * Returns the element stored at the node.
         *
         * @return the element stored at the node
         */
        public E getElement()
        {
            return element;
        }

        /**
         * Returns the node that follows this one (or null if no such node).
         *
         * @return the following node
         */
        public Node<E> getNext()
        {
            return next;
        }

        // Modifier methods

        /**
         * Sets the node's next reference to point to Node n.
         *
         * @param n the node that should follow this one
         */
        public void setNext(Node<E> n)
        {
            next = n;
        }
    } //----------- end of nested Node class -----------

    /**
     * The head node of the list
     */
    private Node<E> head = null;               // head node of the list (or null if empty)


    /**
     * Number of nodes in the list
     */
    private int size = 0;                      // number of nodes in the list

    public SinglyLinkedList() // constructs an initially empty list (default constructor??)
    {

    }

    //@Override
    public int size()
    {
        return size;
    }

    //@Override
    public boolean isEmpty()
    {
        return head == null; // if the head node is null then there is no list, head isnt even pointing to anything atp its just empty
    }

    @Override
    public E get(int position)
    {
        if (position < 0 || position >= size)
        {
            return null; //can't get a node from an index that doesn't exist
        }

        else //creates a new node that starts at index 0/head and reassigns itself using the pointer to the next element
             //until its index matches the desired position. curr is one ahead of i bc on i = 0 curr is 1
        {
            Node<E> curr = head;
            for (int i = 0; i < position; i++)
            {
                curr = curr.next;
            }

            return curr.element;
        }
    }

    @Override
    public void add(int position, E e)
    {
        if (position < 0 || position > size)
        {
            throw new IndexOutOfBoundsException("invalid index");
        }

        else //finds element before insertion position and sets that node's next pointer to the new node
        {
            Node<E> prev = head;
            for (int i = 0; i < position - 1; i++)
            {
                prev = prev.next;
            }

            prev.next = new Node<>(e, prev.next); // setting prev's next pointer to point to the new node, while the new node points to the old prev.next
            size++;
        }
    }


    @Override
    public void addFirst(E e)
    {
        head = new Node<>(e, head);
        size++;
    }

    @Override
    public void addLast(E e) //runs until curr.next is pointing to null, then inserts new node that points to null
    {
        if (head == null)
        {
            addFirst(e);
        }

        else
        {
            Node<E> curr = head;
            for (int i = 0; i < size - 1; i++)
            {
                curr = curr.next;
            }

            curr.next = new Node<>(e, curr.next);
            size++;
        }
    }

    @Override
    public E remove(int position)
    {
        if (position < 0 || position >= size)
        {
            throw new IndexOutOfBoundsException("invalid index");
        }

        else //finds position and makes dump's prev point to dump's next
        {
            Node<E> dump = head;
            Node<E> prev = null;
            for (int i = 0; i < position; i++)
            {
                prev = dump;
                dump = dump.next;
            }

            if (prev == null) //need this bc otherwise you'd get a null pointer exception
            {
                head = head.next;
            }

            else
            {
                prev.next = dump.next;
            }

            size--;

            return dump.element;
        }
    }

    @Override
    public E removeFirst()
    {
        if (head == null) //means empty list
        {
            return null;
        }

        else
        {
            Node<E> temp = head;
            head = head.next;
            size--;

            return temp.element;
        }
    }

    @Override
    public E removeLast()
    {
        if (head == null) //means empty list
        {
            return null;
        }

        //special case: only one element
        else if (size == 1)
        {
            E element = head.element;
            head = null;
            size--;
            return element;
        }

        else //iterate thru list
        {
            Node<E> dump = head;
            Node<E> prev = null;
            for (int i = 0; i < size - 1; i++)
            {
                prev = dump;
                dump = dump.next; //dump.next is null when this finishes running
            }

            prev.next = dump.next;

            size--;

            return dump.element;
        }
    }

    public SinglyLinkedList<E> sortedMerge(SinglyLinkedList<E> list)
    {
        SinglyLinkedList<E> result = new SinglyLinkedList<>();

        Node<E> l1 = this.head;
        Node<E> l2 = list.head;

        while (l1 != null && l2 != null)
        {
            if (((Comparable<E>) l1.element).compareTo(l2.element) < 0)
            {
                result.addLast(l1.element);
                l1 = l1.next;
            }

            else
            {
                result.addLast(l2.element);
                l2 = l2.next;
            }
        }

        while (l1 != null) //if any leftover elements in l1 after l2 is null
        {
            result.addLast(l1.element);
            l1 = l1.next;
        }

        while (l2 != null) //if any leftover elements in l1 after l2 is null
        {
            result.addLast(l2.element);
            l2 = l2.next;
        }
        return result;
    }

    public void reverse()
    {
        Node<E> prev = null;
        Node<E> curr = head;
        Node<E> next;

        while (curr != null)
        {
            next = curr.getNext();
            curr.setNext(prev);
            prev = curr;
            curr = next;
        }

        head = prev;
    }

    //@Override
    public Iterator<E> iterator()
    {
        return new SinglyLinkedListIterator<E>();
    }

    private class SinglyLinkedListIterator<E> implements Iterator<E>
    {
        Node<E> curr = (Node<E>) head;

        @Override
        public boolean hasNext()
        {
            return curr != null;
        }

        @Override
        public E next()
        {
            E res = curr.getElement();
            curr = curr.next;
            return res;
        }
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head;
        while (curr != null)
        {
            sb.append(curr.getElement());

            if (curr.getNext() != null)
                sb.append(", ");

            curr = curr.getNext();
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args)
    {
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<Integer>();
        System.out.println("ll " + ll + " isEmpty: " + ll.isEmpty());
        //LinkedList<Integer> ll = new LinkedList<Integer>();

        ll.addFirst(0);
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addFirst(3);
        ll.addFirst(4);
        ll.addLast(-1);
        //ll.removeLast();
        ll.removeFirst();
        System.out.println("I accept your apology");
        ll.add(3, 2);
        System.out.println(ll);
        ll.remove(5);
        System.out.println(ll);

        //sortedMerge test
        SinglyLinkedList<Integer> l1 = new SinglyLinkedList<>();
        l1.addLast(2);
        l1.addLast(6);
        l1.addLast(20);
        l1.addLast(24);

        SinglyLinkedList<Integer> l2 = new SinglyLinkedList<>();
        l2.addLast(1);
        l2.addLast(3);
        l2.addLast(5);
        l2.addLast(8);
        l2.addLast(12);
        l2.addLast(19);
        l2.addLast(25);

        SinglyLinkedList<Integer> result = l1.sortedMerge(l2);
        System.out.println(result);

        //reverse test
        l1.reverse();
        System.out.println(l1);
    }
}
