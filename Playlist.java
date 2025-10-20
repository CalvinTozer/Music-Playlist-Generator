// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Calvin Tozer (calvintozer)
package dailymixes;

// -------------------------------------------------------------------------
/**
 * A playlist that contains songs of a certain genre percentage
 * 
 * @author ctoze
 * @version Nov 5, 2024
 */
public class Playlist
    implements Comparable<Playlist>
{
    private GenreSet minGenreSet;
    private GenreSet maxGenreSet;
    private Song[] songs;
    private int capacity;
    private int numberOfSongs;
    private String name;

    // ----------------------------------------------------------
    /**
     * Create a new Playlist object.
     * 
     * @param playlistName
     *            name of the playlist
     * @param minPop
     *            mininum pop composition
     * @param minRock
     *            mininum rock composition
     * @param minCountry
     *            mininum country composition
     * @param maxPop
     *            maximum pop composition
     * @param maxRock
     *            maximum rock composition
     * @param maxCountry
     *            maximum country composition
     * @param playlistCap
     *            capacity of the playlist
     */
    public Playlist(
        String playlistName,
        int minPop,
        int minRock,
        int minCountry,
        int maxPop,
        int maxRock,
        int maxCountry,
        int playlistCap)
    {
        name = playlistName;
        minGenreSet = new GenreSet(minPop, minRock, minCountry);
        maxGenreSet = new GenreSet(maxPop, maxRock, maxCountry);
        capacity = playlistCap;

        numberOfSongs = 0;
        songs = new Song[capacity];
    }


    // ----------------------------------------------------------
    /**
     * returns the minimum genre composition of the playlist
     * 
     * @return the minimum genre composition of the playlist
     */
    public GenreSet getMinGenreSet()
    {
        return minGenreSet;
    }


    // ----------------------------------------------------------
    /**
     * sets the name of the playlist
     * 
     * @param newName
     *            the new name of the playlist
     */
    public void setName(String newName)
    {
        name = newName;
    }


    // ----------------------------------------------------------
    /**
     * returns the number of spaces left in the playlist
     * 
     * @return the number of spaces left in the playlist
     */
    public int getSpacesLeft()
    {
        return capacity - numberOfSongs;
    }


    // ----------------------------------------------------------
    /**
     * returns the maximum genre composition of the playlist
     * 
     * @return the maximum genre composition of the playlist
     */
    public GenreSet getMaxGenreSet()
    {
        return maxGenreSet;
    }


    @Override
    public int compareTo(Playlist o)
    {
        // compare capacity
        int order = capacity - o.getCapacity();
        if (order == 0)
        {
            order = (capacity - numberOfSongs)
                - (o.getCapacity() - o.getNumberOfSongs());
            if (order == 0)
            {
                order = minGenreSet.compareTo(o.getMinGenreSet());
                if (order == 0)
                {
                    order = maxGenreSet.compareTo(o.getMaxGenreSet());
                    if (order == 0)
                    {
                        order = name.compareTo(o.getName());
                    }
                }
            }
        }

        return order;

    }


    // ----------------------------------------------------------
    /**
     * returns the number of songs in the playlist
     * 
     * @return the number of songs in the playlist
     */
    public int getNumberOfSongs()
    {
        return numberOfSongs;
    }


    // ----------------------------------------------------------
    /**
     * adds a new song to the playlist; returns false if the song is not
     * qualified or if the playlist is full
     * 
     * @param newSong
     *            the new song to be added
     * @return true if the song was added and false if not
     */
    public boolean addSong(Song newSong)
    {
        if (isFull() || !isQualified(newSong))
        {
            return false;
        }
        songs[numberOfSongs] = newSong;
        numberOfSongs++;
        return true;
    }


    @Override
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        s.append("Playlist: " + name);
        s.append(", # of songs: " + numberOfSongs);
        s.append(" (cap: " + capacity + "), Requires: ");
        s.append(
            "Pop:" + minGenreSet.getPop() + "%-" + maxGenreSet.getPop()
                + "%, ");
        s.append(
            "Rock:" + minGenreSet.getRock() + "%-" + maxGenreSet.getRock()
                + "%, ");
        s.append(
            "Country:" + minGenreSet.getCountry() + "%-"
                + maxGenreSet.getCountry() + "%");

        return s.toString();
    }


    // ----------------------------------------------------------
    /**
     * returns whether the playlist is full or not
     * 
     * @return true if full and false if not
     */
    public boolean isFull()
    {
        return numberOfSongs == capacity;
    }


    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (this.getClass().equals(obj.getClass()))
        {
            Playlist other = (Playlist)obj;
            if (!name.equals(other.getName())
                || !minGenreSet.equals(other.getMinGenreSet())
                || !maxGenreSet.equals(other.getMaxGenreSet())
                || numberOfSongs != other.getNumberOfSongs()
                || capacity != other.getCapacity())
            {
                return false;
            }

            for (int i = 0; i < numberOfSongs; i++)
            {
                Song[] otherSongs = other.getSongs();
                if (!songs[i].equals(otherSongs[i]))
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
     * returns an array of songs in the playlist
     * 
     * @return an array of songs in the playlist
     */
    public Song[] getSongs()
    {
        return songs;
    }


    // ----------------------------------------------------------
    /**
     * returns the capacity of the playlist
     * 
     * @return the capacity of the playlist
     */
    public int getCapacity()
    {
        return capacity;
    }


    // ----------------------------------------------------------
    /**
     * Returns the name of the playlist
     * 
     * @return the name of the playlist
     */
    public String getName()
    {
        return name;
    }


    // ----------------------------------------------------------
    /**
     * determines whether a song is qualified to be added to the playlist based
     * of its genreSet
     * 
     * @param song
     *            the song to be judged
     * @return true if it is qualified and false if not
     */
    public boolean isQualified(Song song)
    {
        return song.getGenreSet().isWithinRange(minGenreSet, maxGenreSet);
    }

}
