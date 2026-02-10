package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class CircularlyLinkedList<E> implements List<E> {

    private class Node<E>
    {
        private E data;
        private Node<E> next;

        public Node(E e, Node<E> n)
        {
            data = e;
            next = n;
        }

        public E getData()
        {
            return data;
        }

        public void setNext(Node<E> n)
        {
            next = n;
        }

        public Node<E> getNext()
        {
            return next;
        }
    }

    private Node<E> tail = null;
    private int size = 0;

    public CircularlyLinkedList()
    {

    }

    @Override
    public boolean isEmpty()
    {
        return size == 0;
    }

    @Override
    public E get(int i)
    {
        if (i < 0 || i >= size)
        {
            return null; //can't get a node from an index that doesn't exist
        }

        else
        {
            Node<E> curr = tail.next;
            for (int x = 0; x < i; x++)
            {
                curr = curr.next;
            }

            return curr.data;
        }
    }

    /**
     * Inserts the given element at the specified index of the list, shifting all
     * subsequent elements in the list one position further to make room.
     *
     * @param i the index at which the new element should be stored
     * @param e the new element to be stored
     */
    @Override
    public void add(int i, E e)
    {
        if (i < 0 || i > size)
        {
            throw new IndexOutOfBoundsException("invalid index");
        }

        else //finds element before insertion position and sets that node's next pointer to the new node
        {
            if (isEmpty())
            {
                tail = new Node<>(e, tail);
                tail.next = tail;
                size++;
            }

            else //(1 -> 1)
            {
                Node<E> prev = tail;
                for (int x = 0; x < i; x++)
                {
                    prev = prev.next;
                }

                prev.next = new Node<>(e, prev.next);
                size++;
            }

        }
    }

    @Override
    public E remove(int i)
    {
        if (i < 0 || i >= size)
        {
            throw new IndexOutOfBoundsException("invalid index");
        }

        else // (1)
        {
            Node<E> dump = tail.next; //first element after tail, so like index 0
            Node<E> prev = tail; //"last" element, bc it just loops back to the first
            for (int x = 0; x < i; x++)
            {
                prev = dump;
                dump = dump.next;
            }

            if (dump == prev) //this means tail was just pointing to itself bc it was the only element
            {
                Node<E> temp = tail;
                tail = null;
                size--;
                return temp.data;
            }

            else
            {
               prev.next = dump.next;
               if (dump == tail)
               {
                   tail = prev; //replaces the tail that gets removed
               }

            }

            size--;

            return dump.data;
        }
    }

    public void rotate()
    {
        if (tail != null)
        {
            tail = tail.next;
        }
    }

    private class CircularlyLinkedListIterator<E> implements Iterator<E>
    {
        Node<E> curr = (Node<E>) tail;

        @Override
        public boolean hasNext()
        {
            return curr != tail;
        }

        @Override
        public E next()
        {
            E res = curr.data;
            curr = curr.next;
            return res;
        }

    }

    @Override
    public Iterator<E> iterator()
    {
        return new CircularlyLinkedListIterator<E>();
    }

    @Override
    public int size()
    {
        return size;
    }

    @Override
    public E removeFirst()
    {
        if (tail == null) //means empty list
        {
            return null;
        }

        else if (size == 1) // Only one element
        {
            Node<E> temp = tail;
            tail = null;
            size--;

            return temp.data;
        }

        else
        {
            Node<E> dump = tail.next;
            tail.next = dump.next;
            size--;

            return dump.data;
        }
    }

    @Override
    public E removeLast()
    {
        if (tail == null) //means empty list
        {
            return null;
        }

        else if (size == 1)
        {
            Node<E> temp = tail;
            tail = null;
            size--;

            return temp.data;
        }

        else
        {
            Node<E> cursor = tail.next;
            for (int i = 0; i < size - 2; i++)
            {
                cursor = cursor.next;
            }

            Node<E> temp = tail;
            cursor.next = tail.next;
            tail = cursor;
            size--;

            return temp.data;
        }
    }

    @Override
    public void addFirst(E e)
    {
        if (tail == null) //means empty list
        {
            tail = new Node<>(e, tail);
            tail.next = tail;
            size++;
        }

        else
        {
            tail.next = new Node<>(e, tail.next);
            size++;
        }
    }

    @Override
    public void addLast(E e)
    {
        addFirst(e);
        tail = tail.next;
    }


    public String toString()
    {
        if (tail == null) //handle empty list
        {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = tail;
        do
        {
            curr = curr.next;
            sb.append(curr.data);
            if (curr != tail)
            {
                sb.append(", ");
            }
        } while (curr != tail);
        sb.append("]");
        return sb.toString();
    }


    public static void main(String[] args)
    {
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<Integer>();
        for (int i = 10; i < 20; ++i) {
            ll.addLast(i);
        }

        System.out.println(ll);

        ll.removeFirst();
        System.out.println(ll);

        ll.removeLast();
        System.out.println(ll);

        ll.rotate();
        System.out.println(ll);

        ll.removeFirst();
        ll.rotate();
        System.out.println(ll);

        ll.removeLast();
        ll.rotate();
        System.out.println(ll);

        for (Integer e : ll) {
            System.out.println("value: " + e);
        }
    }
}
