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
        // TODO
        return null;
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
        // TODO
    }

    @Override
    public E remove(int i)
    {
        // TODO
        return null;
    }

    public void rotate()
    {
        // TODO
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
        // TODO
        return null;
    }

    @Override
    public E removeLast()
    {
        // TODO
        return null;
    }

    @Override
    public void addFirst(E e)
    {
        // TODO
    }

    @Override
    public void addLast(E e)
    {
        // TODO
    }


    public String toString()
    {
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
