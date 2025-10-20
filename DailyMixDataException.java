// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Calvin Tozer (calvintozer)
package dailymixes;

// -------------------------------------------------------------------------
/**
 * Exception thrown when data is incorrect in an input file
 * 
 * @author ctoze
 * @version Nov 9, 2024
 */
public class DailyMixDataException
    extends Exception
{
    // ----------------------------------------------------------
    /**
     * Create a new DailyMixDataException object.
     * 
     * @param message
     */
    DailyMixDataException(String message)
    {
        super(message);
    }

}
