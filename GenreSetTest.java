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

// -------------------------------------------------------------------------
/**
 * tests the methods of the GenreSet Class
 * 
 * @author ctoze
 * @version Nov 5, 2024
 */
public class GenreSetTest
    extends TestCase
{
    private GenreSet g1;
    private GenreSet gMin;
    private GenreSet gMax;
    private Object o;

    // ----------------------------------------------------------
    /**
     * sets up objects for testing
     */
    @Before
    public void setUp()
    {
        g1 = new GenreSet(25, 15, 18);
        gMin = new GenreSet(1, 1, 1);
        gMax = new GenreSet(33, 33, 33);
        o = new Object();
    }


    // ----------------------------------------------------------
    /**
     * tests the get method for all three genres
     */
    @Test
    public void testGetGenre()
    {
        assertEquals(25, g1.getPop());
        assertEquals(15, g1.getRock());
        assertEquals(18, g1.getCountry());
    }


    // ----------------------------------------------------------
    /**
     * tests the isWithin range method
     */
    @Test
    public void testIsWithinRange()
    {
        assertTrue(g1.isWithinRange(gMin, gMax));
        assertFalse(g1.isWithinRange(gMax, g1));
        assertFalse(g1.isWithinRange(g1, gMin));
        assertFalse(g1.isWithinRange(g1, new GenreSet(25, 14, 18)));
        assertFalse(g1.isWithinRange(g1, new GenreSet(25, 15, 17)));
    }


    // ----------------------------------------------------------
    /**
     * tests the equals method
     */
    @Test
    public void testEquals()
    {
        assertFalse(g1.equals(null));
        assertTrue(g1.equals(g1));
        assertFalse(g1.equals(o));
        assertTrue(g1.equals(new GenreSet(25, 15, 18)));
        assertFalse(g1.equals(new GenreSet(24, 15, 18)));
        assertFalse(g1.equals(new GenreSet(25, 14, 18)));
        assertFalse(g1.equals(new GenreSet(25, 15, 17)));
    }


    // ----------------------------------------------------------
    /**
     * tests the compareTo method
     */
    @Test
    public void testCompareTo()
    {
        assertEquals(25 + 18 + 15 - 3, g1.compareTo(gMin));
    }


    // ----------------------------------------------------------
    /**
     * tests the toString method
     */
    @Test
    public void testToString()
    {
        String s = g1.toString();
        assertEquals("Pop:25 Rock:15 Country:18", s);
    }
}
