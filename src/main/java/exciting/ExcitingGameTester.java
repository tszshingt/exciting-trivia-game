package exciting;

import exciting.gui.GameSession;

/**
 * ExcitingGameTester tests the Exciting Game application.
 *
 * @author Tsz Shing Tsoi
 * @version 1.1
 */
public class ExcitingGameTester {

    /**
     * Main function to test the Exciting Game.
     *
     * @param args (command line arguments are not used)
     */
    public static void main(String[] args) {
        GameSession session = new GameSession();
        session.run();
    }
}
