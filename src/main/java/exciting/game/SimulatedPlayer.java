/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exciting.game;

import exciting.util.Choice;
import exciting.util.Constant;
import exciting.util.Level;
import java.util.Random;

/**
 * SimulatedPlayer manages a simulated player's information.
 *
 * @author Tsz Shing Tsoi
 * @version 1.1
 */
public class SimulatedPlayer extends Player {

    /**
     * Construct a SimulatedPlayer object given a name.
     *
     * @param name the name of the simulated player
     */
    public SimulatedPlayer(String name) {
        super(name);
    }

    /**
     * Set the correct answer for the simulated player.
     *
     * @param ans the correct answer to a question
     */
    public void setCorrectAnswer(Choice ans) {
        correctAnswer = ans;
    }

    /**
     * Get an answer from the simulated player based on the difficulty level.
     * The higher the difficulty level, the higher the chance the correct answer
     * is returned. For example, for Constant.MAX_CHANCE = 4,
     * Constant.MAX_NUM_CHOICES = 4, and lv = Level.NOVICE (lv.ordinal() = 0),
     * the chance of returning the correct answer is: (lv.ordinal() + 1) /
     * Constant.MAX_CHANCE + (1 - (lv.ordinal() + 1) / Constant.MAX_CHANCE) * 1
     * / MAX_NUM_CHOICES = 1 / 4 + (1 - 1 / 4) * 1 / 4 = 0.4375
     *
     * @return the answer from the simulated player
     */
    public Choice getAnswer() {
        Random ran = new Random();
        int chance = ran.nextInt(Constant.MAX_CHANCE);
        Level lv = super.getDifficulty();
        if (chance <= lv.ordinal()) {
            return correctAnswer;
        } else {
            return Choice.values()[ran.nextInt(Constant.MAX_NUM_CHOICES)];
        }
    }

    // instance variables:
    private Choice correctAnswer;

}
