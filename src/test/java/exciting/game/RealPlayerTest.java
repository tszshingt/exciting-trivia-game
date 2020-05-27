/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exciting.game;

import exciting.util.Level;
import exciting.util.Pair;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * RealPlayerTest tests the RealPlayer class.
 *
 * @author Tsz Shing Tsoi
 */
public class RealPlayerTest {

    public RealPlayerTest() {
    }

    private Player p1;

    @Before
    public void setUp() {
        p1 = new RealPlayer("Tom");
    }

    /**
     * Test the setName() method.
     *
     */
    @Test
    public void testSetName() {
        System.out.println("Test setName");
        p1.setName("Jane");
        assertTrue(p1.getName().equals("Jane"));
    }

    /**
     * Test the getName() method.
     *
     */
    @Test
    public void testGetName() {
        System.out.println("Test getName");
        assertTrue(p1.getName().equals("Tom"));
    }

    /**
     * Test the getCurrentScore() method.
     *
     */
    @Test
    public void testGetCurrentScore() {
        System.out.println("Test getCurrentScore");
        assertTrue(p1.getCurrentScore() == 0);
        p1.incrementScore(1);
        assertTrue(p1.getCurrentScore() == 1);
    }

    /**
     * Test the incrementScore() method.
     *
     */
    @Test
    public void testIncrementScore() {
        System.out.println("Test incrementScore");
        p1.incrementScore(1);
        assertTrue(p1.getCurrentScore() == 1);
        p1.incrementScore(-1);
        assertTrue(p1.getCurrentScore() == 0);
    }

    /**
     * Test the resetCurrentScore() method.
     *
     */
    @Test
    public void testResetCurrentScore() {
        System.out.println("Test resetCurrentScore");
        p1.incrementScore(1);
        assertTrue(p1.getCurrentScore() == 1);
        p1.resetCurrentScore();
        assertTrue(p1.getCurrentScore() == 0);
    }

    /**
     * Test the updateScoreHistory() method.
     *
     */
    @Test
    public void testUpdateScoreHistory() {
        System.out.println("Test updateScoreHistory");
        Pair<Date, Integer> pair1 = new Pair<>(new Date(), 0); // #1
        p1.updateScoreHistory(); // #2
        Pair<Date, Integer> pair2 = p1.getScoreHistory(1).get(0);
        assertTrue(pair1.equals(pair2)); // assume execution time between statements #1 and #2 are negligible
    }

    /**
     * Test the getScoreHistory() method.
     *
     */
    @Test
    public void testGetScoreHistory() {
        System.out.println("Test getScoreHistory");
        Pair<Date, Integer> pair1 = new Pair<>(new Date(), 0); // #1    
        p1.updateScoreHistory(); // #2
        p1.incrementScore(1);
        Pair<Date, Integer> pair2 = new Pair<>(new Date(), 1); // #1  
        p1.updateScoreHistory(); // #2

        List<Pair<Date, Integer>> list1 = new ArrayList<>();
        list1.add(pair1);
        list1.add(pair2);
        List<Pair<Date, Integer>> list2 = p1.getScoreHistory(2);
        assertTrue(list1.equals(list2)); // assume execution time between statements #1 and #2 are negligible       
    }

    /**
     * Test the setDifficulty() method.
     *
     */
    @Test
    public void testSetDifficulty() {
        System.out.println("Test setDifficulty");
        p1.setDifficulty(Level.ADVANCED);
        assertTrue(p1.getDifficulty() == Level.ADVANCED);
    }

    /**
     * Tests the getDifficulty() method.
     *
     */
    @Test
    public void testGetDifficulty() {
        System.out.println("Test getDifficulty");
        assertTrue(p1.getDifficulty() == Level.NOVICE);
    }
}
