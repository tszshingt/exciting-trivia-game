/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exciting.util;

/**
 * GameState describes the states of a game play.
 *
 * @author Tsz Shing Tsoi
 * @version 1.1
 */
public enum GameState {

    // enum constants are public, static and final by default
    GAME_BEGAN,
    INITIAL_GAME_SCREEN,
    QUESTION_ASKED,
    CURRENT_SCORE_UPDATED,
    CORRECT_ANSWER_REVEALED,
    SCORE_HISTORY_UPDATED,
    QUESTION_EXHAUSTED,
    GAME_ENDED;
}
