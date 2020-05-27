package exciting.system;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import exciting.game.Player;
import exciting.game.RealPlayer;
import exciting.game.SimulatedPlayer;
import exciting.system.DataSaver;
import exciting.system.DataLoader;
import exciting.util.Level;
import java.io.File;
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
public class DataSaverTest {
    
    public DataSaverTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void testSavePlayers() {
        System.out.println("savePlayers");
        DataSaver ds = new DataSaver();
        DataLoader dl = new DataLoader();
        List<Player> players = new ArrayList<>();
        List<Player> instance = new ArrayList<>();
        
        RealPlayer p = new RealPlayer("Leigh", Level.ADVANCED);
        p.incrementScore(10);
        p.updateScoreHistory();
        p.incrementScore(15);
        p.updateScoreHistory();
        players.add(p);
        
        SimulatedPlayer sim = new SimulatedPlayer("Tom");
        sim.setDifficulty(Level.NOVICE);
        sim.incrementScore(5);
        sim.updateScoreHistory();
        players.add(sim);

        sim = new SimulatedPlayer("Jane");
        sim.setDifficulty(Level.INTERMEDIATE);
        sim.incrementScore(7);
        sim.updateScoreHistory();
        sim.incrementScore(4);
        sim.updateScoreHistory();
        players.add(sim);
        
        ds.savePlayers(players);
        instance = dl.loadPlayerInfo();
        
        assertEquals(players.get(0).getName(), instance.get(0).getName());
        assertEquals(players.get(0).getDifficulty(), instance.get(0).getDifficulty());
    }

    @Test
    public void testDeletePlayers() {
        System.out.println("deletePlayers");
        DataSaver instance = new DataSaver();
        String filename = "players.dat";
        List<Player> players = new ArrayList<>();
     
        File file = new File(filename);
        RealPlayer p = new RealPlayer("Leigh", Level.ADVANCED);
        p.incrementScore(10);
        p.updateScoreHistory();
        p.incrementScore(15);
        p.updateScoreHistory();
        players.add(p);
        
        SimulatedPlayer sim = new SimulatedPlayer("Tom");
        sim.setDifficulty(Level.NOVICE);
        sim.incrementScore(5);
        sim.updateScoreHistory();
        players.add(sim);

        sim = new SimulatedPlayer("Jane");
        sim.setDifficulty(Level.INTERMEDIATE);
        sim.incrementScore(7);
        sim.updateScoreHistory();
        sim.incrementScore(4);
        sim.updateScoreHistory();
        players.add(sim);
     
        instance.savePlayers(players);
        
        boolean Before = file.exists();
        instance.deletePlayers();
        boolean After = file.exists();
        assertEquals(!Before,After);
    }
}
