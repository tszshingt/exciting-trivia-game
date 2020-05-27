package exciting.system;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import exciting.game.Player;
import exciting.game.RealPlayer;
import exciting.game.SimulatedPlayer;
import exciting.util.Level;
import exciting.util.Pair;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Leigh Chin
 */
public class GameSystemTest {
    
    public GameSystemTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Test
    public void testInitialize() {
        DataSaver ds = new DataSaver();
        System.out.println("initialize");
        GameSystem instance = new GameSystem();
        List<Player> players = new ArrayList<>();
        
        // for testing, do savedata first which will guarantee file exists
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
        instance.initialize();
                            
        String expResult = "Leigh";
        String result = instance.getUserName();
        assertEquals(expResult, result);
        
        ds.deletePlayers();
        instance.initialize();
        expResult = "";
        result = instance.getUserName();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetRealPlayer() {
        System.out.println("getRealPlayer");
        GameSystem instance = new GameSystem();
        DataSaver ds = new DataSaver();
        List<Player> players = new ArrayList<>();
       
        // save first to make sure the file exists
        // if file did not exist, null would be returned
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
        instance.initialize();
       
        // if data was loaded the real player will be Leigh,
        // if data was reset first, real player will be ""
        assertEquals("Leigh", instance.getRealPlayer().getName());
        
        // if this is the first time the app is run, the user will have
        // to enter the player's name next
        ds.deletePlayers();
        instance.initialize();
        assertEquals("", instance.getRealPlayer().getName());
    }

    @Test
    public void testGetScore() {
        System.out.println("getScore");
        GameSystem instance = new GameSystem();
        List<Player> players = new ArrayList<>();
        Integer expResult = 10;
        DataSaver ds = new DataSaver();
        
        // for testing, data must exist
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
        instance.initialize();
        
        List<List<Pair<Date, Integer>>> result = instance.getScore();
        
        assertEquals(expResult, result.get(0).get(0).v());
    }

    @Test
    public void testResetData() {
        System.out.println("resetData");
        GameSystem instance = new GameSystem();  

        String filename = "players.dat";
        File file = new File(filename);
         
        instance.resetData();
        boolean After = file.exists();
        assertEquals(false, After);
    }

    @Test
    public void testGetDiff() {
        System.out.println("getDiff");
        GameSystem instance = new GameSystem();
        Level expResult = Level.ADVANCED;
        List<Player> players = new ArrayList<>();
        DataSaver ds = new DataSaver();
       
        // for testing, data must exist
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
        instance.initialize();        
        Level result = instance.getDiff();
        assertEquals(expResult, result);
        
        ds.deletePlayers();
        instance.initialize();
        expResult = Level.NOVICE;
        result = instance.getDiff();
        assertEquals(expResult, result);     
    }

    @Test
    public void testChangeDifficulty() {
        System.out.println("changeDifficulty");
        Level lv = Level.INTERMEDIATE;
        GameSystem instance = new GameSystem();
        
        // for testing real player must be defined
        testSaveUserData();
        instance.initialize();
        
        instance.changeDifficulty(lv);
        assertEquals(lv, instance.getDiff());
    }

    @Test
    public void testGetUserName() {
        System.out.println("getUserName");
        GameSystem instance = new GameSystem();
        List<Player> players = new ArrayList<>();
        DataSaver ds = new DataSaver();

        
        // for testing, do saveuserdata first which will guarantee file exists
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
        instance.initialize();
                            
        String expResult = "Leigh";
        String result = instance.getUserName();
        assertEquals(expResult, result);
    }

    @Test
    public void testIsUserDataExist() {
        System.out.println("isUserDataExist");
        GameSystem instance = new GameSystem();
        List<Player> players = new ArrayList<>();
        DataSaver ds = new DataSaver();

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
        instance.initialize();
                 
        boolean expResult = true;
        boolean result = instance.isUserDataExist();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetUserName() {
        System.out.println("setUserName");
        GameSystem instance = new GameSystem();
        testSaveUserData();
        instance.initialize();
        String name = "Mary";
        instance.setUserName(name);
        assertEquals(name, instance.getRealPlayer().getName());
    }
    
    @Test
    public void testSaveUserData() {
        System.out.println("saveUserData");
        GameSystem instance = new GameSystem();
        List<Player> players = new ArrayList<>();
        
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
        
        instance.initialize();
        instance.saveUserData();
        
        String filename = "players.dat";  
        File file = new File(filename);
        
        boolean exists = file.exists();
      
        assertEquals(true, exists);
    }

}
