/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exciting.game;
import exciting.util.Level;
import exciting.util.Pair;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Player manages player information.
 *
 * @author Tsz Shing Tsoi
 * @version 1.1
 */
public abstract class Player implements Serializable {

    /**
     * Construct a Player object given a name.
     *
     * @param name the name of the player
     * @postcondition difficulty is initialized to NOVICE
     * @postcondition scoreHistory is initialized
     */
    public Player(String name) {
        this(name, Level.NOVICE);
    }

    /**
     * Construct a Player object given a name and difficulty level.
     *
     * @param name the name of the player
     * @param difficulty the level of the player
     * @postcondition scoreHistory is initialized
     */
    public Player(String name, Level difficulty) {
        this.name = name;
        this.difficulty = difficulty;
        currentScore = 0;
        scoreHistory = new ArrayList<>();
    }

     /**
     * Set the name of the player.
     *
     * @param name the name of the player
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Get the name of the player.
     *
     * @return the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Get the current score of the player.
     *
     * @return current score of the player
     */
    public int getCurrentScore() {
        return currentScore;
    }

    /**
     * Increment the current score of the player.
     *
     * @param scoreIncrement the score to be added to the current score
     */
    public void incrementScore(int scoreIncrement) {
        currentScore += scoreIncrement;
    }

    /**
     * Reset the current score of the player.
     *
     */
    public void resetCurrentScore() {
        currentScore = 0;
    }

    /**
     * Add the current score to the score history.
     *
     */
    public void updateScoreHistory() {
        scoreHistory.add(new Pair<>(new Date(), currentScore));
    }

    /**
     * Get the most recent scores of the player, up to the number
     * specified in the parameter number. The list is ordered from 
     * the oldest score to the newest score.
     *
     * @param number the number of history items to be retrieved
     * @return the most recent scores of the player. If no scores,
     * return an empty list
     */
    public List<Pair<Date, Integer>> getScoreHistory(int number) {
        return List.copyOf(scoreHistory.subList(Math.max(0, scoreHistory.size() - number), scoreHistory.size()));
    }
    
    /**
     * Set the difficulty level of the player.
     *
     * @param difficulty the difficulty level of the player
     */
    public void setDifficulty(Level difficulty) {
        this.difficulty = difficulty;
    } 
    /**
     * Get the difficulty level of the player.
     *
     * @return the difficulty level of the player
     */
    public Level getDifficulty() {
        return difficulty;
    }

    // instance variables:
    private String name;
    private int currentScore;
    private ArrayList<Pair<Date, Integer>> scoreHistory; // list of score history
    private Level difficulty; // difficulty level of the player
}
