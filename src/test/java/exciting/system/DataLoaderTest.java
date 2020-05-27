package exciting.system;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import exciting.game.Player;
import exciting.game.RealPlayer;
import exciting.game.SimulatedPlayer;
import exciting.util.Choice;
import exciting.util.Level;
import java.util.ArrayList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Leigh Chin
 */
public class DataLoaderTest {

    public DataLoaderTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void testConvertToLevel() {
        System.out.println("ConvertToLevel");
        DataLoader instance = new DataLoader();

        String s = "NOVICE";
        Level expResult = Level.NOVICE;
        Level result = instance.ConvertToLevel(s);
        assertEquals(expResult, result);

        s = "INTERMEDIATE";
        expResult = Level.INTERMEDIATE;
        result = instance.ConvertToLevel(s);
        assertEquals(expResult, result);

        s = "ADVANCED";
        expResult = Level.ADVANCED;
        result = instance.ConvertToLevel(s);
        assertEquals(expResult, result);
    }

    @Test
    public void testConvertToChoice() {
        System.out.println("ConvertToChoice");
        DataLoader instance = new DataLoader();

        String s = "A";
        Choice expResult = Choice.A;
        Choice result = instance.ConvertToChoice(s);
        assertEquals(expResult, result);

        s = "B";
        expResult = Choice.B;
        result = instance.ConvertToChoice(s);
        assertEquals(expResult, result);

        s = "C";
        expResult = Choice.C;
        result = instance.ConvertToChoice(s);
        assertEquals(expResult, result);

        s = "D";
        expResult = Choice.D;
        result = instance.ConvertToChoice(s);
        assertEquals(expResult, result);
    }

    @Test
    public void testLoadQuestions() {
        System.out.println("loadQuestions");
        DataLoader instance = new DataLoader();
        QuestionList ql = new QuestionList();
        instance.loadQuestions(ql);
        String result = "";
        if (ql != null) {
            result = ql.getQuestionIterator((q2) -> {
                return q2.getLevel().equals(Level.NOVICE);
            }).next().toString();
        }
        String expResult = "Which email service is owned by Microsoft?\n" + "Hotmail\n"
                + "GMail\n" + "AOL Mail\n" + "Yahoo! Mail\n" + "A\n" + "Novice";
        assertEquals(expResult, result);
    }

    @Test
    public void testLoadPlayerInfo() {
        System.out.println("loadPlayerInfo");
        DataLoader instance = new DataLoader();
        DataSaver ds = new DataSaver();
        List<Player> expResult = new ArrayList<>();

        RealPlayer p = new RealPlayer("Leigh", Level.ADVANCED);
        p.incrementScore(10);
        p.updateScoreHistory();
        p.incrementScore(15);
        p.updateScoreHistory();
        expResult.add(p);

        SimulatedPlayer sim = new SimulatedPlayer("Tom");
        sim.setDifficulty(Level.NOVICE);
        sim.incrementScore(5);
        sim.updateScoreHistory();
        expResult.add(sim);

        sim = new SimulatedPlayer("Jane");
        sim.setDifficulty(Level.INTERMEDIATE);
        sim.incrementScore(7);
        sim.updateScoreHistory();
        sim.incrementScore(4);
        sim.updateScoreHistory();
        expResult.add(sim);
        ds.savePlayers(expResult);

        List<Player> result = instance.loadPlayerInfo();
        assertEquals(expResult.get(0).getName(), result.get(0).getName());
        assertEquals(expResult.get(0).getDifficulty(), result.get(0).getDifficulty());
    }
}
