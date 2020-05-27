/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exciting.system;

import exciting.game.Player;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Data Saver writes the player profiles to a data file 
 * @author Leigh Chin
 *
 */
public class DataSaver {
    
     /**
     * saves player data.  errors are caught here.  does not guarantee save occurred.
     * @param players List of Player objects to save
     */
    public void savePlayers (List<Player> players) {

        try {
            String filename = "players.dat";
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);

            for (Player p: players) {
                out.writeObject(p);
            }

            out.close();
            file.close();
        } catch (IOException e) {
            // possibiity here for displaying an error message
        }
    }
    
     /**
     * Deletes player data.Errors are caught here.
     */
    public void deletePlayers() {
        
        try {
            String filename = "players.dat";
            File file = new File(filename);
            file.delete();
        } catch (Exception e) {
            // possibiity here for displaying an error message
        }
    }
}
