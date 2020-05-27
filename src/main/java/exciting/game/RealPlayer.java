/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exciting.game;
import exciting.util.Level;

/**
 * RealPlayer manages a human player's information.
 *
 * @author Tsz Shing Tsoi
 * @version 1.1
 */
public class RealPlayer extends Player {

    /**
     * Construct a RealPlayer object given a name.
     *
     * @param name the name of the player
     */
    public RealPlayer(String name) {
        super(name);
    }

    /**
     * Construct a RealPlayer object given a name and difficulty level.
     *
     * @param name the name of the human player
     * @param difficulty the level of the human player
     */
    public RealPlayer(String name, Level difficulty) {
        super(name, difficulty);
    }

}
