// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Calvin Tozer (calvintozer)
package dailymixes;

import list.AList;
import java.util.Arrays;

// -------------------------------------------------------------------------
/**
 * Handles the major calculations and decision-making for the playlists
 * 
 * @author ctoze
 * @version Nov 9, 2024
 */
public class PlaylistCalculator
{
    private Playlist[] playlists;
    /**
     * the number of total playlists
     */
    public static final int NUM_PLAYLISTS = 3;
    /**
     * minimum percent of a genre a song can be
     */
    public static final int MIN_PERCENT = 0;
    private AList<Song> rejectedTracks;
    private ArrayQueue<Song> songQueue;
    /**
     * maximum percent of a genre a song can be
     */
    public static final int MAX_PERCENT = 100;

    // ----------------------------------------------------------
    /**
     * Create a new PlaylistCalculator object.
     * 
     * @param a
     *            queue of songs to be added
     * @param p
     *            array of playlists
     */
    public PlaylistCalculator(ArrayQueue<Song> a, Playlist[] p)
    {
        if (a == null)
        {
            throw new IllegalArgumentException();
        }

        songQueue = a;
        playlists = p;
        rejectedTracks = new AList<Song>();

    }


    // ----------------------------------------------------------
    /**
     * adds the song at the front of the queue to the rejectTracks list and
     * dequeues it from the queue
     */
    public void reject()
    {
        rejectedTracks.add(songQueue.dequeue());
    }


    private Playlist getPlaylistWithMaximumCapacity(Song song)
    {
        Playlist[] sortedArray = Arrays.copyOf(playlists, NUM_PLAYLISTS);
        Arrays.sort(sortedArray);

        for (int i = 0; i < NUM_PLAYLISTS; i++)
        {
            if (canAccept(sortedArray[i], song))
            {
                return sortedArray[i];
            }
        }

        return null;
    }


    // ----------------------------------------------------------
    /**
     * adds a song to the recommended playlist if possible
     * 
     * @return true if the song was successfully added and false if it could not
     *             be added or there was no song to add
     */
    public boolean addSongToPlaylist()
    {
        if (songQueue.isEmpty())
        {
            return false;
        }

        Song next = songQueue.getFront();
        Playlist suggested = getPlaylistForSong(next);
        if (suggested != null)
        {
            suggested.addSong(next);
            songQueue.dequeue();
            return true;
        }
        return false;
    }


    // ----------------------------------------------------------
    /**
     * determines what playlist a song should be placed in
     * 
     * @param nextSong
     *            the song a playlist is being found for
     * @return the best-fitting playlist that can add the song or null if there
     *             is no playlist
     */
    public Playlist getPlaylistForSong(Song nextSong)
    {
        if (nextSong == null)
        {
            return null;
        }
        String suggestedName = nextSong.getPlaylistName();
        int suggestedIndex = getPlaylistIndex(suggestedName);

        if (suggestedIndex != -1)
        {
            Playlist suggestedPlaylist = playlists[suggestedIndex];

            if (canAccept(suggestedPlaylist, nextSong))
            {
                return suggestedPlaylist;
            }
            return null;
        }

        return getPlaylistWithMaximumCapacity(nextSong);

    }


    // ----------------------------------------------------------
    /**
     * returns the queue of songs to be added
     * 
     * @return the queue of songs to be added
     */
    public ArrayQueue<Song> getQueue()
    {
        return songQueue;
    }


    private boolean canAccept(Playlist playlist, Song song)
    {
        return !playlist.isFull() && playlist.isQualified(song);
    }


    // ----------------------------------------------------------
    /**
     * finds the playlist whose name matches the input string
     * 
     * @param playlist
     *            the name used to search for a playlist
     * @return the index of the playlist, or -1 if it could not be found
     */
    public int getPlaylistIndex(String playlist)
    {
        for (int i = 0; i < NUM_PLAYLISTS; i++)
        {
            if (playlists[i].getName().equals(playlist))
            {
                return i;
            }
        }
        return -1;
    }


    // ----------------------------------------------------------
    /**
     * returns the array of playlists
     * 
     * @return the array of playlists
     */
    public Playlist[] getPlaylists()
    {
        return playlists;
    }


    // ----------------------------------------------------------
    /**
     * returns the list of rejected tracks
     * 
     * @return the list of rejected tracks
     */
    public AList<Song> getRejectedTracks()
    {
        return rejectedTracks;
    }

}
