/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exciting.util;

/**
 * Constant class describes all game parameter constants.
 *
 * @author Tsz Shing Tsoi
 * @version 1.1
 */
public final class Constant {

    public static final int MAX_SCORE_HISTORY = 10; // maximum number of score history to be displayed
    public static final int MAX_PLAYER_NUM = 3; // maximum number of players in a game
    public static final int MAX_NUM_CHOICES = Choice.values().length; // maximum number of answer choices
    public static final int SCORE_FOR_ONE_ROUND = 1; // score for winner of a round
    public static final int MAX_TIME_FOR_ONE_ROUND = 20000; // maximum time in milliseonds to answer one round
    public static final int MIN_TIME_TO_ANSWER = 10000; // minimum time to answer for simulated player
    public static final int MAX_TIME_TO_ANSWER = 15000; // maximum time to answer for simulated player
    public static final int MAX_CHANCE = Level.values().length + 1; // affect the chance the simulated player will return correct answer
}
