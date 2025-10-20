// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Calvin Tozer (calvintozer)
package dailymixes;

// -------------------------------------------------------------------------
/**
 * Song object with name, suggested playlist, and genreset
 * 
 * @author ctoze
 * @version Oct 31, 2024
 */
public class Song
{
    private String name;
    private String suggestedPlaylist;
    private GenreSet genreSet;

    // ----------------------------------------------------------
    /**
     * Create a new Song object.
     * 
     * @param name
     *            name of the song
     * @param pop
     *            percent pop used for genreset
     * @param rock
     *            percent rock used for genreset
     * @param country
     *            percent country used for genreset
     * @param suggested
     *            the suggested playlist for the song
     */
    public Song(String name, int pop, int rock, int country, String suggested)
    {
        this.name = name;
        this.suggestedPlaylist = suggested;
        genreSet = new GenreSet(pop, rock, country);
    }


    // ----------------------------------------------------------
    /**
     * Returns the name of the suggested playlist
     * 
     * @return name of the suggested playlist
     */
    public String getPlaylistName()
    {
        return suggestedPlaylist;
    }


    @Override
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        s.append(name + " ");
        s.append(genreSet.toString());
        if (suggestedPlaylist.length() != 0)
        {
            s.append(" Suggested: " + suggestedPlaylist);
        }
        else
        {
            s.insert(0, "No-Playlist ");
        }
        return s.toString();
    }


    @Override
    public boolean equals(Object obj)
    {
        if (obj == this)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (this.getClass().equals(obj.getClass()))
        {
            Song other = (Song)obj;
            return name == other.name
                && suggestedPlaylist == other.suggestedPlaylist
                && genreSet.equals(other.genreSet);
        }
        return false;
    }


    // ----------------------------------------------------------
    /**
     * returns the name of the song
     * 
     * @return the name of the song
     */
    public String getName()
    {
        return name;
    }


    // ----------------------------------------------------------
    /**
     * returns the genreSet of the song
     * 
     * @return the genreSet of the song
     */
    public GenreSet getGenreSet()
    {
        return genreSet;
    }

}
