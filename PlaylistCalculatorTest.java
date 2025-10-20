// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Calvin Tozer (calvintozer)
package dailymixes;

import static org.junit.Assert.*;
import list.AList;
import org.junit.Before;
import org.junit.Test;
import student.TestCase;

// -------------------------------------------------------------------------
/**
 * tests the methods of the PlaylistCalculator class
 * 
 * @author ctoze
 * @version Nov 11, 2024
 */
public class PlaylistCalculatorTest
    extends TestCase
{

    private PlaylistCalculator calc;
    private PlaylistCalculator nullCalc;

    @Override
    @Before
    public void setUp()
        throws Exception
    {
        // set up calc
        ArrayQueue<Song> a = new ArrayQueue<Song>();
        a.enqueue(new Song("Hotel California", 23, 47, 1, "Daily Mix 2"));
        a.enqueue(new Song("Billie Jean", 47, 34, 5, "Daily Mix 2"));
        a.enqueue(new Song("Dancing Queen", 62, 27, 8, "Daily Mix 1"));
        a.enqueue(
            new Song("Smells Like Teen Spirit", 13, 41, 2, "Daily Mix 2"));
        a.enqueue(new Song("Poker Face", 78, 21, 1, "Daily Mix 1"));

        Playlist[] p = { new Playlist("Daily Mix 1", 50, 20, 0, 99, 49, 10, 10),
            new Playlist("Daily Mix 2", 22, 58, 2, 43, 95, 6, 8),
            new Playlist("Daily Mix 3", 20, 2, 50, 30, 14, 100, 12) };
        calc = new PlaylistCalculator(a, p);

        // set up calc with null songs
        ArrayQueue<Song> a1 = new ArrayQueue<Song>();
        a1.enqueue(new Song("Hotel California", 23, 47, 1, "Daily Mix 2"));
        try
        {
            nullCalc = new PlaylistCalculator(null, p);
        }
        catch (Exception e)
        {
            nullCalc = new PlaylistCalculator(a1, p);
        }

    }


    // ----------------------------------------------------------
    /**
     * tests the reject method
     */
    @Test
    public void testReject()
    {
        calc.reject();
        AList<Song> a = new AList<Song>();
        a.add(new Song("Hotel California", 23, 47, 1, "Daily Mix 2"));
        assertEquals(a, calc.getRejectedTracks());
    }


    // ----------------------------------------------------------
    /**
     * tests the addSongToPlaylist method
     */
    @Test
    public void testAddSongToPlaylist()
    {

        nullCalc.reject();
        assertFalse(nullCalc.addSongToPlaylist());
        assertFalse(calc.addSongToPlaylist());
        calc.reject();
        assertFalse(calc.addSongToPlaylist());
        calc.reject();
        assertTrue(calc.addSongToPlaylist());

    }


    // ----------------------------------------------------------
    /**
     * tests the getPlaylistForSong method
     */
    @Test
    public void testGetPlaylistForSong()
    {
        nullCalc.reject();
        assertEquals(null, nullCalc.getPlaylistForSong(null));

        assertEquals(
            null,
            calc.getPlaylistForSong(
                new Song("Hotel California", 23, 47, 1, "Daily Mix 2")));
        assertEquals(
            calc.getPlaylists()[0],
            calc.getPlaylistForSong(
                new Song("Dancing Queen", 62, 27, 8, "Daily Mix 1")));

        // test when getPlaylistWithMaximumCapacity is called
        assertEquals(
            calc.getPlaylists()[0],
            calc.getPlaylistForSong(new Song("Dancing Queen", 62, 27, 8, "")));

        assertEquals(
            calc.getPlaylists()[0],
            calc.getPlaylistForSong(
                new Song("Dancing Queen", 62, 27, 8, "Fake")));
    }


    // ----------------------------------------------------------
    /**
     * tests the getQueue method
     */
    @Test
    public void testGetQueue()
    {
        ArrayQueue<Song> a = new ArrayQueue<Song>();
        a.enqueue(new Song("Hotel California", 23, 47, 1, "Daily Mix 2"));
        a.enqueue(new Song("Billie Jean", 47, 34, 5, "Daily Mix 2"));
        a.enqueue(new Song("Dancing Queen", 62, 27, 8, "Daily Mix 1"));
        a.enqueue(
            new Song("Smells Like Teen Spirit", 13, 41, 2, "Daily Mix 2"));
        a.enqueue(new Song("Poker Face", 78, 21, 1, "Daily Mix 1"));

        assertEquals(a, calc.getQueue());
    }


    // ----------------------------------------------------------
    /**
     * tests the getPlaylist method
     */
    @Test
    public void testGetPlaylists()
    {
        Playlist[] p = { new Playlist("Daily Mix 1", 50, 20, 0, 99, 49, 10, 10),
            new Playlist("Daily Mix 2", 22, 58, 2, 43, 95, 6, 8),
            new Playlist("Daily Mix 3", 20, 2, 50, 30, 14, 100, 12) };
        for (int i = 0; i < p.length; i++)
        {
            assertEquals(p[i], calc.getPlaylists()[i]);
        }
    }


    // ----------------------------------------------------------
    /**
     * tests the getRejectedTracks method
     */
    @Test
    public void testGetRejectedTracks()
    {

        AList<Song> a = new AList<Song>();
        assertEquals(a, calc.getRejectedTracks());

        calc.reject();
        a.add(new Song("Hotel California", 23, 47, 1, "Daily Mix 2"));
        assertEquals(a, calc.getRejectedTracks());
    }

}
