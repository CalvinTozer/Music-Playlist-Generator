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
 * tests the methods of the Playlist Class
 * 
 * @author ctoze
 * @version Nov 5, 2024
 */
public class PlaylistTest
    extends TestCase
{
    private Playlist p1;
    private Playlist p2;
    private Playlist p3;
    private Playlist p4;
    private Playlist p5;
    private Playlist p6;
    private Playlist p7;
    private Playlist p8;
    private Object o;

    @Override
    @Before
    public void setUp()
        throws Exception
    {
        p1 = new Playlist("favorites", 10, 15, 13, 30, 50, 25, 5);
        p2 = new Playlist("favorites", 10, 15, 13, 30, 50, 25, 4);
        p3 = new Playlist("favorites", 10, 15, 13, 30, 50, 25, 5);
        p3.addSong(new Song("song", 15, 20, 20, ""));
        p4 = new Playlist("favorites", 10, 15, 14, 30, 50, 25, 5);
        p5 = new Playlist("favorites", 10, 15, 13, 32, 51, 26, 5);
        p6 = new Playlist("real", 10, 15, 13, 30, 50, 25, 5);
        p7 = new Playlist("favorites", 10, 15, 13, 30, 50, 25, 5);
        p8 = new Playlist("favorites", 10, 15, 13, 30, 50, 25, 5);

        o = new Object();

    }


    // ----------------------------------------------------------
    /**
     * tests the getMinGenreSet method
     */
    @Test
    public void testGetMinGenreSet()
    {
        assertEquals(new GenreSet(10, 15, 13), p1.getMinGenreSet());
    }


    // ----------------------------------------------------------
    /**
     * tests the setName method
     */
    @Test
    public void testSetName()
    {
        p1.setName("Everything");
        assertEquals("Everything", p1.getName());
    }


    // ----------------------------------------------------------
    /**
     * tests the getSpacesLeft method
     */
    @Test
    public void testGetSpacesLeft()
    {
        assertEquals(5, p1.getSpacesLeft());
        p1.addSong(new Song("song", 15, 20, 20, ""));
        assertEquals(4, p1.getSpacesLeft());

    }


    // ----------------------------------------------------------
    /**
     * tests the getMaxGenreSet method
     */
    @Test
    public void testGetMaxGenreSet()
    {
        assertEquals(new GenreSet(30, 50, 25), p1.getMaxGenreSet());
    }


    // ----------------------------------------------------------
    /**
     * tests the getMaxGenreSet method
     */
    @Test
    public void testCompareTo()
    {
        assertEquals(1, p1.compareTo(p2));
        assertEquals(1, p1.compareTo(p3));
        assertEquals(-1, p1.compareTo(p4));
        assertEquals(-4, p1.compareTo(p5));
        assertEquals(-12, p1.compareTo(p6));

    }


    // ----------------------------------------------------------
    /**
     * tests the getNumberOfSongs method
     */
    @Test
    public void testGetNumberOfSongs()
    {
        assertEquals(0, p1.getNumberOfSongs());
        p1.addSong(new Song("song", 15, 20, 20, ""));
        assertEquals(1, p1.getNumberOfSongs());

    }


    // ----------------------------------------------------------
    /**
     * tests the addSong method
     */
    @Test
    public void testAddSong()
    {
        assertFalse(p1.addSong(new Song("song", 15, 20, 40, "")));

        for (int i = 0; i < p1.getCapacity(); i++)
        {
            p1.addSong(new Song("song", 15, 20, 20, ""));
        }

        assertFalse(p1.addSong(new Song("song", 15, 20, 20, "")));
    }


    // ----------------------------------------------------------
    /**
     * tests the toString method
     */
    @Test
    public void testToString()
    {
        assertEquals(
            "Playlist: favorites, # of songs: 0 (cap: 5), Requires: "
                + "Pop:10%-30%, Rock:15%-50%, Country:13%-25%",
            p1.toString());
    }


    // ----------------------------------------------------------
    /**
     * tests the isFull method
     */
    @Test
    public void testIsFull()
    {
        for (int i = 0; i < p1.getCapacity(); i++)
        {
            assertFalse(p1.isFull());
            p1.addSong(new Song("song", 15, 20, 20, ""));
        }

        assertTrue(p1.isFull());
    }


    // ----------------------------------------------------------
    /**
     * tests the equals method
     */
    @Test
    public void testEquals()
    {
        assertFalse(p1.equals(null));
        assertTrue(p1.equals(p1));
        assertFalse(p1.equals(o));
        assertFalse(p1.equals(p2));
        assertFalse(p1.equals(p3));
        assertFalse(p1.equals(p4));
        assertFalse(p1.equals(p5));
        assertFalse(p1.equals(p6));

        for (int i = 0; i < p1.getCapacity(); i++)
        {
            p1.addSong(new Song("song", 15, 20, 20, ""));
            p7.addSong(new Song("song", 15, 20, 20, ""));
        }

        assertTrue(p1.equals(p7));

        for (int i = 0; i < p1.getCapacity(); i++)
        {
            p1.addSong(new Song("song", 15, 20, 20, ""));
            p8.addSong(new Song("another", 15, 20, 20, ""));
        }
        assertFalse(p1.equals(p8));

    }


    // ----------------------------------------------------------
    /**
     * tests the getSongs method
     */
    @Test
    public void testGetSongs()
    {
        Song[] same = new Song[5];
        p1.addSong(new Song("song", 15, 20, 20, ""));
        same[0] = new Song("song", 15, 20, 20, "");

        for (int i = 0; i < p1.getNumberOfSongs(); i++)
        {
            assertEquals(same[i], p1.getSongs()[i]);
        }
    }


    // ----------------------------------------------------------
    /**
     * tests the getCapacity method
     */
    @Test
    public void testGetCapacity()
    {
        assertEquals(5, p1.getCapacity());
    }


    // ----------------------------------------------------------
    /**
     * tests the getName method
     */
    @Test
    public void testGetName()
    {
        assertEquals("favorites", p1.getName());
    }


    // ----------------------------------------------------------
    /**
     * tests the isQualified method
     */
    @Test
    public void testIsQualified()
    {
        assertTrue(p1.isQualified(new Song("song", 15, 20, 20, "")));
        assertFalse(p1.isQualified(new Song("song", 15, 200, 20, "")));

    }

}
