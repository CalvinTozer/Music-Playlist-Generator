// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Calvin Tozer (calvintozer)
package dailymixes;

import java.io.FileNotFoundException;
import java.text.ParseException;

// -------------------------------------------------------------------------
/**
 * contains main method to run project
 * 
 * @author ctoze
 * @version Nov 11, 2024
 */
public class ProjectRunner
{

    // ----------------------------------------------------------
    /**
     * Create a new ProjectRunner object.
     */
    public ProjectRunner()
    {

    }


    // ----------------------------------------------------------
    /**
     * main method that runs the program
     * 
     * @param args
     *            song and playlist input files
     * @throws FileNotFoundException
     *             file name does not match file
     * @throws ParseException
     *             file format is incorrect
     * @throws DailyMixDataException
     *             genre set values are out of range
     */
    public static void main(String[] args)
        throws FileNotFoundException,
        ParseException,
        DailyMixDataException
    {
        if (args.length == 2)
        {
            PlaylistReader reader = new PlaylistReader(args[0], args[1]);
        }
        PlaylistReader reader =
            new PlaylistReader("input.txt", "playlists.txt");

    }

}
