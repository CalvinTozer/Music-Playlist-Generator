// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Calvin Tozer (calvintozer)
package dailymixes;

// -------------------------------------------------------------------------
/**
 * Represents the percent composition of genres, including pop, rock, and
 * country
 * 
 * @author ctoze
 * @version Oct 31, 2024
 */
public class GenreSet
    implements Comparable<GenreSet>
{
    private int pop;
    private int rock;
    private int country;

    // ----------------------------------------------------------
    /**
     * Create a new GenreSet object.
     * 
     * @param pop
     *            the percent pop
     * @param rock
     *            the percent rock
     * @param country
     *            the percent country
     */
    GenreSet(int pop, int rock, int country)
    {
        this.pop = pop;
        this.rock = rock;
        this.country = country;
    }


    // ----------------------------------------------------------
    /**
     * returns the percent pop
     * 
     * @return the percent pop
     */
    public int getPop()
    {
        return pop;
    }


    // ----------------------------------------------------------
    /**
     * returns the percent rock
     * 
     * @return the percent rock
     */
    public int getRock()
    {
        return rock;
    }


    // ----------------------------------------------------------
    /**
     * returns the percent country
     * 
     * @return the percent country
     */
    public int getCountry()
    {
        return country;
    }


    private boolean isLessThanOrEqualTo(GenreSet other)
    {
        return this.pop <= other.pop && this.rock <= other.rock
            && this.country <= other.country;
    }


    // ----------------------------------------------------------
    /**
     * returns true if a genreset composition is within a given range and false
     * if not
     * 
     * @param minGenreSet
     *            the bond end of the range
     * @param maxGenreSet
     *            the upper bound of the range
     * @return true if a genreset composition is within a given range and false
     *             if not
     */
    public boolean isWithinRange(GenreSet minGenreSet, GenreSet maxGenreSet)
    {
        return isLessThanOrEqualTo(maxGenreSet)
            && minGenreSet.isLessThanOrEqualTo(this);
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
            GenreSet other = (GenreSet)obj;
            return pop == other.pop && rock == other.rock
                && country == other.country;
        }
        return false;
    }


    @Override
    public int compareTo(GenreSet o)
    {
        return pop + rock + country - (o.pop + o.rock + o.country);
    }


    @Override
    public String toString()
    {
        return "Pop:" + pop + " Rock:" + rock + " Country:" + country;
    }
}
