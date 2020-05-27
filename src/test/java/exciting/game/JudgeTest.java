/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exciting.game;

import exciting.util.Choice;
import exciting.util.Pair;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JudgeTest tests the Judge class.
 *
 * @author Tsz Shing Tsoi
 */
public class JudgeTest {

    public JudgeTest() {
    }

    private Judge judge;

    @Before
    public void setUp() {
        judge = new Judge();
    }

    /**
     * Test of setCorrectAnswer method, of class Judge.
     */
    @Test
    public void testSetCorrectAnswer() {
        System.out.println("Test setCorrectAnswer");
        judge.setCorrectAnswer(Choice.A);
        assertTrue(judge.getCorrectAnswer() == Choice.A);
    }

    /**
     * Test of getCorrectAnswer method, of class Judge.
     */
    @Test
    public void testGetCorrectAnswer() {
        System.out.println("Test getCorrectAnswer");
        assertTrue(judge.getCorrectAnswer() == null);
        judge.setCorrectAnswer(Choice.B);
        assertTrue(judge.getCorrectAnswer() == Choice.B);
    }

    /**
     * Test of setPlayerAnswer method, of class Judge.
     */
    @Test
    public void testSetPlayerAnswer() {
        System.out.println("Test setPlayerAnswer");
        Player p1 = new RealPlayer("Tom");
        Pair<Player, Choice> pair1 = new Pair<>(p1, Choice.A);
        judge.setPlayerAnswer(p1, Choice.A);
        Pair<Player, Choice> pair2 = judge.getPlayerAnswer().get(0);
        assertTrue(pair1.equals(pair2));
    }

    /**
     * Test of getPlayerAnswer method, of class Judge.
     */
    @Test
    public void testGetPlayerAnswer() {
        System.out.println("Test getPlayerAnswer");
        Player p1 = new RealPlayer("Tom");
        Player p2 = new SimulatedPlayer("Jane");
        Player p3 = new RealPlayer("John");

        List<Pair<Player, Choice>> list1 = new ArrayList<>();
        list1.add(new Pair<>(p1, Choice.A));
        list1.add(new Pair<>(p2, Choice.B));
        list1.add(new Pair<>(p3, Choice.C));

        judge.setPlayerAnswer(p1, Choice.A);
        judge.setPlayerAnswer(p2, Choice.B);
        judge.setPlayerAnswer(p3, Choice.C);
        List<Pair<Player, Choice>> list2 = judge.getPlayerAnswer();
        assertTrue(list1.equals(list2));
    }

    /**
     * Test of reset method, of class Judge.
     */
    @Test
    public void testReset() {
        System.out.println("Test reset");
        // initial settings
        assertTrue(judge.getCorrectAnswer() == null);
        assertTrue(judge.getPlayerAnswer().equals(new ArrayList<Pair<Player, Choice>>()));
        assertTrue(judge.getWinner() == null);

        // set some parameters
        Player p1 = new RealPlayer("Tom");
        List<Pair<Player, Choice>> list1 = new ArrayList<>();
        list1.add(new Pair<>(p1, Choice.A));
        judge.setCorrectAnswer(Choice.A);
        judge.setPlayerAnswer(p1, Choice.A);
        assertTrue(judge.getCorrectAnswer() == Choice.A);
        assertTrue(judge.getPlayerAnswer().equals(list1));
        assertTrue(judge.getWinner() == p1);

        // reset
        judge.reset();
        assertTrue(judge.getCorrectAnswer() == null);
        assertTrue(judge.getPlayerAnswer().equals(new ArrayList<Pair<Player, Choice>>()));
        assertTrue(judge.getWinner() == null);
    }

    /**
     * Test of getWinner method, of class Judge.
     */
    @Test
    public void testGetWinner() {
        System.out.println("Test getWinner");
        Player p1 = new RealPlayer("Tom");
        judge.setCorrectAnswer(Choice.A);
        judge.setPlayerAnswer(p1, Choice.A);
        assertTrue(judge.getWinner() == p1);
    }

    /**
     * Test of setChangeListener method, of class Judge.
     */
    @Test
    public void testSetChangeListener() {
        System.out.println("Test getWinner");
        final String winnerMessage = "Jane is the winner!";
        final String[] str = {""};
        judge.setChangeListener((e) -> {
            str[0] = winnerMessage;
        });

        judge.setCorrectAnswer(Choice.B);
        Player p1 = new RealPlayer("Tom");
        Player p2 = new SimulatedPlayer("Jane");
        Player p3 = new RealPlayer("John");
        judge.setPlayerAnswer(p1, Choice.A);
        judge.setPlayerAnswer(p2, Choice.B);
        judge.setPlayerAnswer(p3, Choice.C);

        assertTrue(str[0].equals(winnerMessage));
    }

}
