/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exciting.game;

import exciting.system.Question;
import exciting.util.Choice;
import exciting.util.Constant;
import exciting.util.GameState;
import exciting.util.Level;
import exciting.util.Pair;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Game manages a game play.
 *
 * @author Tsz Shing Tsoi
 * @version 1.1
 */
public class Game {

    /**
     * Construct a Game object and initialize all variables.
     *
     * @param iter parameter description
     * @param players parameter description
     * @postcondition state == GameState.GAME_BEGAN
     */
    public Game(Iterator<Question> iter, List<Player> players) {
        questionIter = iter;
        this.players = new ArrayList<>(players);
        judge = new Judge();
        judge.setChangeListener((e) -> endCurrentRound());
        listeners = new ArrayList<>();
        gameTimer = new Timer(Constant.MAX_TIME_FOR_ONE_ROUND, (e) -> endCurrentRound());
        gameTimer.setRepeats(false);
        setState(GameState.GAME_BEGAN);
    }

    /**
     * Initialize a game play and set the difficulty levels of all simulated
     * players.
     *
     * @precondition state == GameState.GAME_BEGAN || state ==
     * GameState.GAME_ENDED
     * @postcondition state == GameState.INITIAL_GAME_SCREEN
     */
    public void initialize() {
        if (state == GameState.GAME_BEGAN
                || state == GameState.GAME_ENDED) {
            // get real player difficulty level
            int realPlayerIndex = getRealPlayerIndex();
            Level lv;
            if (realPlayerIndex >= 0) {
                lv = players.get(realPlayerIndex).getDifficulty();
            } else {
                lv = Level.NOVICE;
            }

            // set simulated player difficulty level
            players.stream().filter(
                    (p) -> (p.getClass() == SimulatedPlayer.class)
            ).forEach((p) -> {
                p.setDifficulty(lv);
            });

            // reset all scores
            players.forEach(
                    (p) -> p.resetCurrentScore()
            );
            setState(GameState.INITIAL_GAME_SCREEN);
        }
    }

    /**
     * Get the next question from the question iterator.
     *
     * @return the next question in a formatted String if the next question
     * exists. If next question does not exist or precondition is not met,
     * return null
     * @precondition state == GameState.INITIAL_GAME_SCREEN || state ==
     * GameState.CORRECT_ANSWER_REVEALED
     * @postcondition state == GameState.QUESTION_ASKED if next question exists
     * @postcondition state == GameState.QUESTION_EXHAUSTED if next question
     * does not exist
     */
    public synchronized String getNextQuestion() {
        if (state == GameState.INITIAL_GAME_SCREEN
                || state == GameState.CORRECT_ANSWER_REVEALED) {
            judge.reset();
            if (questionIter.hasNext()) {
                Question question = questionIter.next();
                judge.setCorrectAnswer(question.getCorrectAnswer());

                // set simulated player correct answer
                players.stream().filter(
                        (p) -> (p.getClass() == SimulatedPlayer.class)
                ).forEach(
                        (p) -> {
                            ((SimulatedPlayer) p).setCorrectAnswer(question.getCorrectAnswer());
                            startSimulation((SimulatedPlayer) p);
                        });
                gameTimer.restart();
                setState(GameState.QUESTION_ASKED);
                return question.getQuestionText();
            } else {
                setState(GameState.QUESTION_EXHAUSTED);
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Submit answer to the judge for a specific player.
     *
     * @param player the player to submit answer from
     * @param ans the answer of the player
     * @precondition state == GameState.QUESTION_ASKED
     */
    public synchronized void submitAnswerToJudge(Player player, Choice ans) {
        if (state == GameState.QUESTION_ASKED) {
            judge.setPlayerAnswer(player, ans);
        }
    }

    /**
     * Get the answer chosen by each player in a list.
     *
     * @return the answer chosen by each player, in the order the player
     * submitted the answer to the judge. If precondition is not met, return
     * null
     * @precondition state == GameState.CURRENT_SCORE_UPDATED
     */
    public List<Pair<String, Choice>> getResults() {
        if (state == GameState.CURRENT_SCORE_UPDATED) {
            return judge.getPlayerAnswer().stream().map(
                    (p) -> {
                        return new Pair<String, Choice>(p.k().getName(), p.v());
                    }
            ).collect(Collectors.toList());
        } else {
            return null;
        }
    }

    /**
     * Get the correct answer to the current question.
     *
     * @return the correct answer. If precondition is not met, return null
     * @precondition state == GameState.CURRENT_SCORE_UPDATED
     */
    public Choice getCorrectAnswer() {
        if (state == GameState.CURRENT_SCORE_UPDATED) {
            setState(GameState.CORRECT_ANSWER_REVEALED);
            return judge.getCorrectAnswer();
        } else {
            return null;
        }
    }

    /**
     * Terminate the current game and update the score history to each player.
     *
     * @precondition state == GameState.CORRECT_ANSWER_REVEALED || state ==
     * GameState.QUESTION_EXHAUSTED
     * @postcondition state == GameState.SCORE_HISTORY_UPDATED
     */
    public synchronized void endGame() {
        if (state == GameState.CORRECT_ANSWER_REVEALED
                || state == GameState.QUESTION_EXHAUSTED) {
            // update all player history            
            players.forEach((p) -> p.updateScoreHistory());
            setState(GameState.SCORE_HISTORY_UPDATED);
        }
    }

    /**
     * Reset the judge object and current scores of all players.
     *
     * @precondition state == GameState.SCORE_HISTORY_UPDATED
     * @postcondition state == GameState.GAME_ENDED
     */
    public void cleanUp() {
        if (state == GameState.SCORE_HISTORY_UPDATED) {
            // reset judge and current player scores
            judge.reset();
            players.forEach(
                    (p) -> p.resetCurrentScore()
            );
            setState(GameState.GAME_ENDED);
        }
    }

    /**
     * Get the current scores of all players.
     *
     * @return the list of all current scores of all players, in the order the
     * player is initialized
     */
    public List<Integer> getScore() {
        return players.stream().map(
                (p) -> p.getCurrentScore()
        ).collect(Collectors.toList());
    }

    /**
     * Get the list of names of all players.
     *
     * @return the list of names of all players, in the order the player is
     * initialized
     */
    public List<String> getPlayersEnteringGame() {
        return players.stream().map(
                (p) -> p.getName()
        ).collect(Collectors.toList());
    }

    /**
     * Add a change listener.
     *
     * @param listener the listener to be added
     */
    public void addChangeListener(ChangeListener listener) {
        listeners.add(listener);
    }

    /**
     * Get the current state of the game object.
     *
     * @return the current state of the game object
     */
    public GameState getState() {
        return state;
    }

    // private methods and helper functions:
    /**
     * End the current round of a game play. If there is a winner of the current
     * round, increase the score of the winner. It also notifies all
     * ChangeListeners that a change has occurred.
     *
     * @precondition state == GameState.QUESTION_ASKED
     * @postcondition state == GameState.CURRENT_SCORE_UPDATED
     */
    private synchronized void endCurrentRound() {
        if (state == GameState.QUESTION_ASKED) {
            gameTimer.stop();
            if (judge.getWinner() != null) {
                judge.getWinner().incrementScore(Constant.SCORE_FOR_ONE_ROUND);
            }
            //System.out.println("Round Ended");
            setState(GameState.CURRENT_SCORE_UPDATED);

            // run state changed for all listeners
            listeners.forEach((l) -> l.stateChanged(new ChangeEvent(this)));
        }
    }

    /**
     * Start simulating the simulated player and randomly select a time to
     * submit answer to the judge.
     *
     */
    private void startSimulation(SimulatedPlayer player) {
        Random ran = new Random();
        int timeToAnswer = Constant.MIN_TIME_TO_ANSWER
                + ran.nextInt(Constant.MAX_TIME_TO_ANSWER - Constant.MIN_TIME_TO_ANSWER);
        Timer simulationTimer = new Timer(timeToAnswer,
                (e) -> submitAnswerToJudge(player, player.getAnswer()));
        simulationTimer.setRepeats(false);
        simulationTimer.start();
    }

    /**
     * Get the index of the first occurrence of a real player.
     *
     * @return the index of the first occurrence of a real player. If no real
     * player exists, return -1
     */
    private int getRealPlayerIndex() {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getClass() == RealPlayer.class) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Set the current state of the game object.
     *
     * @param state the state to be set to
     */
    private void setState(GameState state) {
        this.state = state;
    }

    // instance variables:
    private final Iterator<Question> questionIter;
    private final ArrayList<Player> players;
    private final Judge judge;
    private final Timer gameTimer;
    private final ArrayList<ChangeListener> listeners;
    private GameState state;

}
