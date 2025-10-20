// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Calvin Tozer (calvintozer)
package dailymixes;

import queue.EmptyQueueException;
import queue.QueueInterface;

// -------------------------------------------------------------------------
/**
 * Underlying queue used for songs in a playlist
 * 
 * @param <T>
 *            generic type for queue entries
 * @author ctoze
 * @version Nov 3, 2024
 */
public class ArrayQueue<T>
    implements QueueInterface<T>
{
    /**
     * the default capacity of the queue when no number is provided
     */
    public static final int DEFAULT_CAPACITY = 20;
    private T[] queue;
    private int dequeueIndex;
    private int size;
    private int enqueueIndex;

    // ----------------------------------------------------------
    /**
     * Create a new ArrayQueue object with a given capacity.
     * 
     * @param capacity
     *            the number of entries able to be stored in the queue
     */
    @SuppressWarnings("unchecked")
    public ArrayQueue(int capacity)
    {
        size = 0;
        queue = (T[])new Object[capacity + 1];
        dequeueIndex = 0;
        enqueueIndex = 0;
    }


    // ----------------------------------------------------------
    /**
     * Create a new ArrayQueue object using default capacity.
     */
    @SuppressWarnings("unchecked")
    public ArrayQueue()
    {
        size = 0;
        queue = (T[])new Object[DEFAULT_CAPACITY + 1];
        dequeueIndex = 0;
        enqueueIndex = 0;

    }


    // ----------------------------------------------------------
    /**
     * clears all entries from the queue
     */
    @SuppressWarnings("unchecked")
    @Override
    public void clear()
    {
        size = 0;
        queue = (T[])new Object[DEFAULT_CAPACITY + 1];
        dequeueIndex = 0;
        enqueueIndex = 0;
    }


    private int incrementIndex(int index)
    {
        return ((index + 1) % queue.length);
    }


    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder();
        str.append("[");
        for (int i = 0; i < size; i++)
        {
            str.append(queue[(i + dequeueIndex) % queue.length].toString());
            if (i < size - 1)
            {
                str.append(", ");
            }
        }
        str.append("]");
        return str.toString();
    }


    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (this == obj)
        {
            return true;
        }
        if (this.getClass().equals(obj.getClass()))
        {
            ArrayQueue<T> objQueue = ((ArrayQueue<T>)obj);
            if (isEmpty() && objQueue.isEmpty())
            {
                return true;
            }
            if (size != objQueue.getSize())
            {
                return false;
            }
            Object[] orderedArray = toArray();
            Object[] objOrderedArray = objQueue.toArray();
            for (int i = 0; i < orderedArray.length; i++)
            {
                if (!orderedArray[i].equals(objOrderedArray[i]))
                {
                    return false;
                }
            }
            return true;
        }
        return false;
    }


    // ----------------------------------------------------------
    /**
     * Returns whether the queue is empty
     * 
     * @returns whether the queue is empty
     */
    @Override
    public boolean isEmpty()
    {
        return size == 0;
    }


    // ----------------------------------------------------------
    /**
     * removes the front entry from the queue
     * 
     * @returns the entry removed from the queue
     */
    @Override
    public T dequeue()
    {
        T dequeueValue = getFront();
        queue[dequeueIndex] = null;
        dequeueIndex = incrementIndex(dequeueIndex);
        size--;

        return dequeueValue;
    }


    private void ensureCapacity()
    {

        @SuppressWarnings("unchecked")
        T[] newArray = (T[])new Object[2 * queue.length - 1];

        for (int i = 0; i < queue.length; i++)
        {
            newArray[i] = queue[(i + dequeueIndex) % queue.length];
        }

        queue = newArray;
        dequeueIndex = 0;
        enqueueIndex = size - 1;
    }


    // ----------------------------------------------------------
    /**
     * Adds a new entry to the back of the queue
     */
    @Override
    public void enqueue(T anEntry)
    {
        if (isFull())
        {
            ensureCapacity();
        }
        if (isEmpty())
        {
            queue[enqueueIndex] = anEntry;
        }
        else
        {
            enqueueIndex = incrementIndex(enqueueIndex);
            queue[enqueueIndex] = anEntry;
        }

        size++;
    }


    // ----------------------------------------------------------
    /**
     * Returns the length of the underlying array
     * 
     * @return the length of the underlying array
     */
    public int getLengthOfUnderlyingArray()
    {
        return queue.length;
    }


    // ----------------------------------------------------------
    /**
     * Returns the front entry of the queue
     * 
     * @return the front entry of the queue
     */
    @Override
    public T getFront()
    {
        if (isEmpty())
        {
            throw new EmptyQueueException();
        }
        return queue[dequeueIndex];
    }


    // ----------------------------------------------------------
    /**
     * returns the size (number of entries) of the queue
     * 
     * @return the size (number of entries) of the queue
     */
    public int getSize()
    {
        return size;
    }


    private boolean isFull()
    {
        return size == queue.length - 1;
    }


    // ----------------------------------------------------------
    /**
     * returns the underlying queue as an array, with the front of the queue as
     * index 0 and the end of the queue as the last index of the array
     * 
     * @return an array representation of the queue
     */
    @SuppressWarnings("unchecked")
    public Object[] toArray()
    {
        if (isEmpty())
        {
            throw new EmptyQueueException("Queue is empty");
        }
        T[] newArray = (T[])new Object[size];
        for (int i = 0; i < size; i++)
        {
            newArray[i] = queue[(i + dequeueIndex) % queue.length];
        }

        return newArray;
    }

}
