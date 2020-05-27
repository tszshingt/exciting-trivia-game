/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exciting.system;

import exciting.game.Game;
import exciting.game.Player;
import exciting.game.RealPlayer;
import exciting.game.SimulatedPlayer;
import static exciting.util.Constant.*;
import exciting.util.Level;
import exciting.util.Pair;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Game System manages main menu selections
 * @author Leigh Chin
 */
public class GameSystem {

    /**
     * constructor for GameSystem
     */
    public GameSystem() {
        this.listeners = new ArrayList<>();
        this.qList = new QuestionList();
        this.players = new ArrayList<>();
    }

    /**
     * Loads questions from disk, loads player data if it exists.
     * @postcondition the players list will contain 1 real and 2 simulated players
     */
    public void initialize() {
        DataLoader dl = new DataLoader();
        dl.loadQuestions(qList);

        // try to load from file, if no file exists, null will be returned
        players = dl.loadPlayerInfo();

        if (players.isEmpty()) {
            // 1 real player and 2 simulated players will be added
            RealPlayer p = new RealPlayer("", Level.NOVICE);
            players.add(p);
            SimulatedPlayer pl = new SimulatedPlayer("Tom");
            players.add(pl);
            pl = new SimulatedPlayer("Jane");
            players.add(pl);
        }

        listeners.forEach((l) -> l.stateChanged(new ChangeEvent(this)));
    }

    /**
     * getNewGAme creates a Game object including the initialized players list
     * and an iterator through the question list with appropriate difficulty level
     * @precondition players list must have 3 players
     * @return Game object
     */
    public Game getNewGame() {
        Game g = new Game(qList.getQuestionIterator((q) -> {
            return q.getLevel().equals(getRealPlayer().getDifficulty());
        }), players);
        return g;
    }

    /**
     * getScore assembles a list of each players scores
     * @precondition players must contain players
     * @return List of Lists of scores
     */
    public List<List<Pair<Date, Integer>>> getScore() {
        List<List<Pair<Date, Integer>>> lst = new ArrayList<>();

        for (Player p : players) {
            lst.add(p.getScoreHistory(MAX_SCORE_HISTORY));
        }
        return lst;
    }

    /**
     * Removes all players data files and removes all players from the Game
     * @postcondition player array will be reset to default players
     */
    public void resetData() {
        DataSaver ds = new DataSaver();
        ds.deletePlayers();
        initialize(); // reset player list
        listeners.forEach((l) -> l.stateChanged(new ChangeEvent(this)));
    }

    /**
     * The difficulty level of the real player will be returned.
     * @precondition players list must contain a real player
     * @return Level of real player
     */
    public Level getDiff() {
        return getRealPlayer().getDifficulty();
    }

    /**
     * Changes the difficulty level of the real player.
     * @param lv Level to change to
     * @precondition The GameSystem must have a real player defined
     * @postcondition The real player's level will be changed
     */
    public void changeDifficulty(Level lv) {
        getRealPlayer().setDifficulty(lv);
    }

    /**
     * Finds the real player in the players list Returns null if player list is
     * empty or no real player identified
     * @precondition players list must contain a real player
     * @return Player object
     */
    public Player getRealPlayer() {
        for (Player p : players) {
            if (p.getClass() == RealPlayer.class) {
                return p;
            }
        }

        return null;
    }

    /**
     * @param name Real player's name
     * @precondition the player list has 1 real player and 2 sims
     * @postcondition the real player's name will be set to name
     */
    public void setUserName(String name) {
        getRealPlayer().setName(name);
    }

    /**
     * returns true if real player established
     * @precondition real player object must exist
     * @return true if real player data is loaded
     */
    public boolean isUserDataExist() {
        return !getRealPlayer().getName().isBlank();
    }

    /**
     * Returns the name of the real player
     * @precondition player list must exist
     * @return String name of real player
     */
    public String getUserName() {
        return getRealPlayer().getName();
    }

    /**
     * Saves players data
     * @precondition the players list must be defined
     */
    public void saveUserData() {
        DataSaver ds = new DataSaver();
        ds.savePlayers(players);
    }

    /**
     * adds changeListener
     * @param listener 
     */
    public void addChangeListener(ChangeListener listener) {
        listeners.add(listener);
    }

    private final QuestionList qList;
    private List<Player> players;
    private final ArrayList<ChangeListener> listeners;
}
