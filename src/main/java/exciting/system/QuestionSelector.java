/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exciting.system;

/**
 * QuestionSelector determines if a question should be used for a game.
 *
 * @author Tsz Shing Tsoi
 * @version 1.1
 */
public interface QuestionSelector {

    /**
     * Check if a question should be selected for a game.
     *
     * @param q the question to be tested against
     * @return true if the question should be used, and false otherwise
     */
    public boolean isValidQuestion(Question q);
}
