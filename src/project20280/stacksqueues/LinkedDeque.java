package project20280.stacksqueues;

import project20280.interfaces.Deque;
import project20280.list.DoublyLinkedList;

public class LinkedDeque<E> implements Deque<E> {

    DoublyLinkedList<E> ll;

    public LinkedDeque()
    {
        ll = new DoublyLinkedList<>();
    }

    public static void main(String[] args)
    {
        // TODO Auto-generated method stub

        LinkedDeque<Integer> dq = new LinkedDeque<>();

        System.out.println("Initial deque: " + dq);
        System.out.println("isEmpty: " + dq.isEmpty());
        System.out.println("size: " + dq.size());

        // addLast some elements
        dq.addLast(1);
        dq.addLast(2);
        dq.addLast(3);
        System.out.println("After addLast(1,2,3): " + dq);
        System.out.println("first: " + dq.first() + ", last: " + dq.last());

        // removeFirst
        Integer rf1 = dq.removeFirst();
        System.out.println("removeFirst -> " + rf1 + ", deque: " + dq);

        // removeLast
        Integer rl1 = dq.removeLast();
        System.out.println("removeLast -> " + rl1 + ", deque: " + dq);

    }

    @Override
    public int size()
    {
        return ll.size();
    }

    @Override
    public boolean isEmpty()
    {
        return ll.isEmpty();
    }

    @Override
    public E first()
    {
        return ll.first();
    }

    @Override
    public E last()
    {
        return ll.last();
    }

    @Override
    public void addFirst(E e)
    {
        ll.addFirst(e);
    }

    @Override
    public void addLast(E e)
    {
        ll.addLast(e);
    }

    @Override
    public E removeFirst()
    {
        return ll.removeFirst();
    }

    @Override
    public E removeLast()
    {
        return ll.removeLast();
    }

    public String toString()
    {
        return ll.toString();
    }
}
