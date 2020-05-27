/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exciting.game;

import exciting.util.Choice;
import exciting.util.Constant;
import exciting.util.Pair;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Judge keeps track of player's answers and determines the winner.
 *
 * @author Tsz Shing Tsoi
 * @version 1.1
 */
public class Judge {

    /**
     * Construct a Judge object.
     *
     * @postcondition playerAnswer is initialized
     */
    public Judge() {
        correctAnswer = null;
        playerAnswer = new ArrayList<>();
        winner = null;
        listener = null;
    }

    /**
     * Set the correct answer.
     *
     * @param ans the correct answer
     */
    public void setCorrectAnswer(Choice ans) {
        correctAnswer = ans;
    }

    /**
     * Get the correct answer.
     *
     * @return the correct answer
     */
    public Choice getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * Set the answer of a player. If the player has already submitted an answer
     * in the current round, no changes would be made. If all users have
     * answered, it notifies the ChangeListener that a change has occurred.
     *
     * @param player the player who submits the answer
     * @param ans the answer of the player
     */
    public void setPlayerAnswer(Player player, Choice ans) {
        if (!isPlayerAnswered(player)) {
            playerAnswer.add(new Pair<>(player, ans));
            if (ans == correctAnswer && winner == null) {
                winner = player;
            }
            // if all players have answered
            if (playerAnswer.size() == Constant.MAX_PLAYER_NUM && listener != null) {
                listener.stateChanged(new ChangeEvent(this));
            }
        }
    }

    /**
     * Get the list of players who submitted answer and their answer. The list
     * is in the order of when players submitted their answer.
     *
     * @return the list of players with answers
     */
    public List<Pair<Player, Choice>> getPlayerAnswer() {
        return List.copyOf(playerAnswer);
    }

    /**
     * Reset all answers and winner in the Judge object.
     *
     */
    public void reset() {
        correctAnswer = null;
        playerAnswer.clear();
        winner = null;
    }

    /**
     * Get the winner of the current round.
     *
     * @return the winner of the current round. If no winners, return null
     */
    public Player getWinner() {
        return winner;
    }

    /**
     * Set the change listener.
     *
     * @param listener the listener to be set
     */
    public void setChangeListener(ChangeListener listener) {
        this.listener = listener;
    }

    // helper function:
    /**
     * Check if the player has already answered.
     *
     * @param player the player to check
     * @return true if the player has answered, and false otherwise
     */
    private boolean isPlayerAnswered(Player player) {
        boolean answered = false;
        for (Pair p : playerAnswer) {
            if (p.k() == player) {
                answered = true;
                break;
            }
        }
        return answered;
    }

    // instance variables:
    private Choice correctAnswer;
    private final ArrayList<Pair<Player, Choice>> playerAnswer; // the list of player-answer pair
    private Player winner;
    private ChangeListener listener;
}
