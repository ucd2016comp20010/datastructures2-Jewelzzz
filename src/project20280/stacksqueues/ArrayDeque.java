package project20280.stacksqueues;

import project20280.interfaces.Deque;

public class ArrayDeque<E> implements Deque<E> {

    private static final int CAPACITY = 1000;
    private E[] data;
    private int front = 0;   // index of first element
    private int size = 0;    // number of elements in deque


    @SuppressWarnings("unchecked")
    public ArrayDeque(int capacity)
    {
        data = (E[]) new Object[capacity];
    }


    public ArrayDeque()
    {
        this(CAPACITY);
    }


    @Override
    public int size()
    {
        return size;
    }


    @Override
    public boolean isEmpty()
    {
        return size == 0;
    }


    @Override
    public E first()
    {
        return isEmpty() ? null : data[front];
    }


    @Override
    public E last()
    {
        return isEmpty() ? null : data[(front + size - 1) % data.length];
    }


    @Override
    public void addFirst(E e)
    {
        if (size == data.length)
        {
            throw new IllegalStateException("deque is full");
        }

        else
        {
            front = (front - 1 + data.length) % data.length;
            data[front] = e;
            size++;
        }

    }


    @Override
    public void addLast(E e)
    {
        // TODO
        if (size == data.length)
        {
            throw new IllegalStateException("Deque is full");
        }

        else
        {
            int avail = (front + size) % data.length;
            data[avail] = e;
            size++;
        }

    }


    @Override
    public E removeFirst()
    {
        // TODO
        if (isEmpty())
        {
            return null;
        }

        else
        {
            E answer = data[front];
            data[front] = null;
            front = (front + 1) % data.length;
            size--;
            return answer;
        }

    }


    @Override
    public E removeLast()
    {
        // TODO
        if (isEmpty())
        {
            return null;
        }

        else
        {
            int backIndex = (front + size - 1) % data.length;
            E answer = data[backIndex];
            data[backIndex] = null;
            size--;
            return answer;
        }

    }


    public String toString()
    {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; ++i)
        {
            E res = data[(front + i) % data.length];
            sb.append(res);
            if (i != size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args)
    {
        ArrayDeque<Integer> dq = new ArrayDeque<>();

        System.out.println("Initial deque: " + dq);
        System.out.println("isEmpty: " + dq.isEmpty());
        System.out.println("size: " + dq.size());

        // addLast a few elements
        dq.addLast(1);
        dq.addLast(2);
        dq.addLast(3);
        System.out.println("After addLast(1,2,3): " + dq);
        System.out.println("first: " + dq.first() + ", last: " + dq.last());

        // addFirst some elements
        dq.addFirst(0);
        dq.addFirst(-1);
        System.out.println("After addFirst(0) and addFirst(-1): " + dq);
        System.out.println("first: " + dq.first() + ", last: " + dq.last());
        System.out.println("size: " + dq.size());

        // removeFirst
        Integer rf = dq.removeFirst();
        System.out.println("removeFirst -> " + rf + ", deque: " + dq);

        // removeLast
        Integer rl = dq.removeLast();
        System.out.println("removeLast -> " + rl + ", deque: " + dq);
    }
}

