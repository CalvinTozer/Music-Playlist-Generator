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
 * tests the methods of the Song Class Follow it with additional details about
 * its purpose, what abstraction it represents, and how to use it.
 * 
 * @author ctoze
 * @version Nov 5, 2024
 */
public class SongTest
    extends TestCase
{

    private Song s1;
    private Song s2;
    private Object o;

    // ----------------------------------------------------------
    /**
     * sets up objects for testing
     */
    @Before
    public void setUp()
        throws Exception
    {
        s1 = new Song("Name", 20, 18, 24, "Playlist");
        s2 = new Song("second", 20, 18, 24, "");
        o = new Object();
    }


    // ----------------------------------------------------------
    /**
     * tests the getPlaylistName method
     */
    @Test
    public void testGetPlaylistName()
    {
        assertEquals("Playlist", s1.getPlaylistName());
    }


    // ----------------------------------------------------------
    /**
     * tests the toString method
     */
    @Test
    public void testToString()
    {
        assertEquals(
            "Name Pop:20 Rock:18 Country:24 Suggested: Playlist",
            s1.toString());
        assertEquals(
            "No-Playlist second Pop:20 Rock:18 Country:24",
            s2.toString());

    }


    // ----------------------------------------------------------
    /**
     * tests the equals method
     */
    @Test
    public void testEquals()
    {
        assertFalse(s1.equals(null));
        assertTrue(s1.equals(s1));
        assertFalse(s1.equals(s2));
        assertFalse(s1.equals(o));
        assertFalse(s1.equals(new Song("Name", 21, 18, 24, "Playlist")));
        assertFalse(s1.equals(new Song("Name", 20, 19, 24, "Playlist")));
        assertFalse(s1.equals(new Song("Name", 20, 18, 25, "owjf")));
        assertTrue(s1.equals(new Song("Name", 20, 18, 24, "Playlist")));

    }


    // ----------------------------------------------------------
    /**
     * tests the getName method
     */
    @Test
    public void testGetName()
    {
        assertEquals("second", s2.getName());
    }


    // ----------------------------------------------------------
    /**
     * tests the getGenreSet method
     */
    @Test
    public void testGetGenreSet()
    {
        assertEquals(new GenreSet(20, 18, 24), s1.getGenreSet());
    }

}
