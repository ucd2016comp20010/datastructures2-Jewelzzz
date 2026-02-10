package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class DoublyLinkedList<E> implements List<E>, Iterable<E> {

    private static class Node<E>
    {
        private E data;
        private Node<E> next;
        private Node<E> prev;

        public Node(E e, Node<E> p, Node<E> n)
        {
            data = e;
            prev = p;
            next = n;
        }

        public E getData()
        {
            return data;
        }

        public Node<E> getNext()
        {
            return next;
        }

        public Node<E> getPrev()
        {
            return prev;
        }

    }

    private Node<E> head;
    private Node<E> tail;
    private int size = 0;

    public DoublyLinkedList() //constructor that initializes head and tail bc u need that for a doubly linked list
    {
        head = new Node<E>(null, null, null);
        tail = new Node<E>(null, head, null);
        head.next = tail;
    }

    private void addBetween(E e, Node<E> pred, Node<E> succ) //changes the pointers that were going between pred and succ to point to the new node
    {
        pred.next = new Node<>(e, pred, succ);
        succ.prev = pred.next;
        size++;
    }

    @Override
    public int size()
    {
        return size;
    }

    @Override
    public boolean isEmpty()
    {
        return head.next == tail; //if head points to tail then there's no elements in between them, meaning empty list
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
            Node<E> curr = head.next; //start at index 0
            for (int x = 0; x < i; x++)
            {
                curr = curr.next;
            }

            return curr.data; // QUESTION: should i be using the getter method, getData(), here? or is direct access fine?
                              // or does it not matter since its all being accessed from inside the data structure and not some external class??
                              // bc isn't the getter about encapsulation and just protecting the data from outside classes?
        }
    }

    @Override
    public void add(int i, E e)
    {
        if (i < 0 || i > size)
        {
            throw new IndexOutOfBoundsException("invalid index");
        }

        else //(head, 0, 1, 2, 3, tail)
        {
            Node<E> curr = head;
            for (int x = 0; x < i; x++)
            {
                curr = curr.next;
            }

            addBetween(e, curr, curr.next);
        }
    }

    @Override
    public E remove(int i)
    {
        if (i < 0 || i >= size)
        {
            throw new IndexOutOfBoundsException("invalid index");
        }

        else
        {
            Node<E> dump = head.next;

            for (int x = 0; x < i; x++)
            {
                dump = dump.next;
            }

            dump.prev.next = dump.next;
            dump.next.prev = dump.prev;
            size--;

            return dump.data;
        }
    }

    @Override
    public Iterator<E> iterator()
    {
        return new DoublyLinkedListIterator<E>();
    }

    private class DoublyLinkedListIterator<E> implements Iterator<E>
    {
        Node<E> curr = (Node<E>) head.next;

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

    private E remove(Node<E> n)
    {
        n.prev.next = n.next; //the predecessor to n has its next reassigned to n's next
        n.next.prev = n.prev; //the successor to n has its prev reassigned to n's prev
        return n.data;
    }

    public E first()
    {
        if (isEmpty())
        {
            return null;
        }
        return head.next.getData(); //like is using getData() necessary or preferred here because it shouldn't be direct access
                                    //or was this just a design decision? like is using head.next.data instead a valid choice?
    }

    public E last()
    {
        if (isEmpty())
        {
            return null;
        }
        return tail.prev.data; //good or bad?
    }

    @Override
    public E removeFirst()
    {
        if (isEmpty())
        {
            return null;
        }
        return remove(0);
    }

    @Override
    public E removeLast()
    {
        if (isEmpty())
        {
            return null;
        }

        Node<E> last = tail.prev;
        last.prev.next = tail;
        tail.prev = last.prev;

        size--;

        return last.data;
    }

    @Override
    public void addLast(E e)
    {
        Node<E> newNode = new Node<E>(e, tail.prev, tail);
        tail.prev.next = newNode;
        tail.prev = newNode;
        size++;
    }

    @Override
    public void addFirst(E e)
    {
        Node<E> newNode = new Node<E>(e, head, head.next);
        head.next.prev = newNode;
        head.next = newNode;
        size++;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head.next;
        while (curr != tail)
        {
            sb.append(curr.data);
            curr = curr.next;
            if (curr != tail)
            {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args)
    {
        DoublyLinkedList<Integer> ll = new DoublyLinkedList<Integer>();
        ll.addFirst(0);
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addLast(-1);
        System.out.println(ll);

        ll.removeFirst();
        System.out.println(ll);

        ll.removeLast();
        System.out.println(ll);

        for (Integer e : ll) {
            System.out.println("value: " + e);
        }
    }
}