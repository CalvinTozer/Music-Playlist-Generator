// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Calvin Tozer (calvintozer)
package dailymixes;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import student.TestCase;
import queue.EmptyQueueException;

// -------------------------------------------------------------------------
/**
 * tests the methods of the ArrayQueue class
 * 
 * @author ctoze
 * @version Nov 5, 2024
 */
public class ArrayQueueTest
    extends TestCase
{
    private ArrayQueue<String> a1;
    private ArrayQueue<String> a2;
    private Object o;

    // ----------------------------------------------------------
    /**
     * sets up objects for testing
     */
    @Before
    public void setUp()
        throws Exception
    {
        a1 = new ArrayQueue<String>();
        a2 = new ArrayQueue<String>(10);
        o = new Object();

    }


    // ----------------------------------------------------------
    /**
     * tests the enqueue method
     */
    @Test
    public void testEnqueue()
    {
        a1.enqueue("1");
        assertEquals("[1]", a1.toString());

        for (int i = 0; i < 20; i++)
        {
            a1.enqueue(i + "");
        }
        assertEquals(21, a1.getSize());
    }


    // ----------------------------------------------------------
    /**
     * tests the enqueue method
     */
    @Test
    public void testDequeue()
    {
        Exception ex = null;
        try
        {
            a1.dequeue();
        }
        catch (EmptyQueueException e)
        {
            ex = e;
        }
        assertNotNull(ex);

        for (int i = 0; i < 5; i++)
        {
            a1.enqueue(i + "");
        }
        assertEquals(5, a1.getSize());

        a1.dequeue();
        a1.dequeue();
        assertEquals("[2, 3, 4]", a1.toString());

    }


    // ----------------------------------------------------------
    /**
     * tests the clear method
     */
    @Test
    public void testClear()
    {
        for (int i = 0; i < 10; i++)
        {
            a1.enqueue(i + "");
        }
        assertEquals(10, a1.getSize());

        a1.clear();
        assertEquals(0, a1.getSize());

    }


    // ----------------------------------------------------------
    /**
     * tests the toString method
     */
    @Test
    public void testToString()
    {
        for (int i = 0; i < 5; i++)
        {
            a1.enqueue(i + "");
        }
        assertEquals("[0, 1, 2, 3, 4]", a1.toString());
    }


    // ----------------------------------------------------------
    /**
     * tests the equals method
     */
    @Test
    public void testEquals()
    {
        for (int i = 0; i < 5; i++)
        {
            a1.enqueue(i + "");
        }
        for (int i = 0; i < 4; i++)
        {
            a2.enqueue(i + "");
        }
        assertTrue(a1.equals(a1));
        assertFalse(a1.equals(null));
        assertFalse(a1.equals(a2));
        assertFalse(a1.equals(o));

        a2.enqueue("4");

        assertTrue(a1.equals(a2));

        a1.enqueue("6");
        a2.enqueue("5");

        assertFalse(a1.equals(a2));

        a1.clear();
        assertFalse(a1.equals(a2));
        a2.clear();
        assertTrue(a1.equals(a2));
    }


    // ----------------------------------------------------------
    /**
     * tests the isEmpty method
     */
    @Test
    public void testIsEmpty()
    {
        assertTrue(a1.isEmpty());

        a1.enqueue("1");
        assertFalse(a1.isEmpty());
    }


    // ----------------------------------------------------------
    /**
     * tests the getLengthOfUnderlyingArray method
     */
    @Test
    public void testGetLengthOfUnderlyingArray()
    {
        assertEquals(21, a1.getLengthOfUnderlyingArray());

        a1.enqueue("wow");

        assertEquals(21, a1.getLengthOfUnderlyingArray());

        assertEquals(11, a2.getLengthOfUnderlyingArray());

    }


    // ----------------------------------------------------------
    /**
     * tests the getFront method
     */
    @Test
    public void testGetFront()
    {
        Exception ex = null;
        try
        {
            a1.getFront();
        }
        catch (EmptyQueueException e)
        {
            ex = e;
        }
        assertNotNull(ex);

        a1.enqueue("begin");
        a1.enqueue("end");
        assertEquals("begin", a1.getFront());

    }


    // ----------------------------------------------------------
    /**
     * tests the getSize method
     */
    @Test
    public void testGetSize()
    {
        assertEquals(0, a1.getSize());

        a1.enqueue("wow");

        assertEquals(1, a1.getSize());
    }


    // ----------------------------------------------------------
    /**
     * tests the toArray method
     */
    @Test
    public void testToArray()
    {
        Exception ex = null;
        try
        {
            a1.toArray();
        }
        catch (EmptyQueueException e)
        {
            ex = e;
        }
        assertNotNull(ex);

        for (int i = 0; i < 5; i++)
        {
            a1.enqueue(i + "");
        }

        a1.dequeue();
        a1.dequeue();
        a1.enqueue("7");

        String[] array = { "2", "3", "4", "7" };
        Object[] a1Arr = a1.toArray();

        for (int i = 0; i < array.length; i++)
        {
            assertEquals(array[i], a1Arr[i]);
        }
    }

}
