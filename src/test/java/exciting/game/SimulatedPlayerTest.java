/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exciting.game;

import exciting.util.Choice;
import exciting.util.Constant;
import exciting.util.Level;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * SimulatedPlayerTest tests the SimulatedPlayer class.
 *
 * @author Tsz Shing Tsoi
 */
public class SimulatedPlayerTest {

    public SimulatedPlayerTest() {
    }

    private SimulatedPlayer p1;

    @Before
    public void setUp() {
        p1 = new SimulatedPlayer("Tom");
    }

    /**
     * Test of setCorrectAnswer method, of class SimulatedPlayer.
     */
    @Test
    public void testSetCorrectAnswer() {
        System.out.println("Test setCorrectAnswer");
        System.out.println("  Skipped due to the lack"
                + " of accessor for correctAnswer. See \"Test getAnswer\"");
    }

    /**
     * Test of getAnswer method, of class SimulatedPlayer.
     */
    @Test
    public void testGetAnswer() {
        System.out.println("Test getAnswer");
        Choice ans = Choice.A;
        p1.setCorrectAnswer(ans);

        // test if the number of correct answers matches the expected number
        for (Level lv : Level.values()) {
            p1.setDifficulty(lv);
            int numRepetitions = 10000;
            int numCorrectAnswers = 0;
            double chanceOfCorrect = ((double) (lv.ordinal() + 1)) / Constant.MAX_CHANCE;
            double expectedPercentCorrect = chanceOfCorrect + (1 - chanceOfCorrect) * 1.0 / Constant.MAX_NUM_CHOICES;
            double delta = 0.05;
            for (int i = 0; i < numRepetitions; i++) {
                if (p1.getAnswer() == ans) {
                    numCorrectAnswers++;
                }
            }
            assertTrue(Math.abs(expectedPercentCorrect
                    - ((double) numCorrectAnswers) / numRepetitions)
                    <= delta);
        }
    }

}
