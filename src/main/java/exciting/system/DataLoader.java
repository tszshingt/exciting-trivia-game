/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exciting.system;

import exciting.game.Player;
import exciting.util.Choice;
import exciting.util.Level;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Data Loader reads questions from a text file and player profiles from a file
 * @author Leigh Chin
 */
public class DataLoader {
    /**
    * Converts a String to Difficulty Level enumerated type.  Default is Novice.
    * @invariant The returned type will be valid.
    * @param s String
    * @return Difficulty Level 
    */
    public Level ConvertToLevel(String s) {
        Level out = Level.NOVICE;
    
        switch (s) {
            case "NOVICE" : out = Level.NOVICE;
                            break;
            case "INTERMEDIATE" : out = Level.INTERMEDIATE;
                            break;
            case "ADVANCED" : out = Level.ADVANCED;
                            break;
        }
        return out;
    }
    
    /**
     * Converts a String to Choice enumerated type. Default is A.
     * @invariant The returned value will be valid.
     * @param s String
     * @return Choice 
     */
    public Choice ConvertToChoice(String s) {
        Choice out = Choice.A;
        
        switch (s) {
            case "A" : out = Choice.A;
                       break;
            case "B" : out = Choice.B;
                       break;
            case "C" : out = Choice.C;
                       break;
            case "D" : out = Choice.D;
                       break;
        }
        
        return out;
    }

    /**
     * Loads questions from a data file
     * @param ql the QuestionList Object where the questions will be loaded
     * @precondition the question file exists in the expected location
     */
    public void loadQuestions(QuestionList ql) {
        String questionFilename = "questions.txt";
       
        try {
            File file = new File(questionFilename); 
            Scanner sc = new Scanner(file); 
   
            while (sc.hasNextLine()) {
                Question newQ = new Question();
            
                try {
                    newQ.setQ(sc.nextLine());
                    newQ.setA(sc.nextLine());
                    newQ.setB(sc.nextLine());
                    newQ.setC(sc.nextLine());
                    newQ.setD(sc.nextLine());
                    newQ.setCorrect(ConvertToChoice(sc.nextLine()));
                    newQ.setLevel(ConvertToLevel(sc.nextLine()));
                    ql.addQuestion(newQ);
                }
                catch (NoSuchElementException e) {
                // If the last question is incomplete, it will not
                // be added.  The previous questions are still intact.
                }       
            }
        } catch (FileNotFoundException e) {
            // option here to display an error message
            // question list will be empty
        }
    }
    
    /**
     * Tries to load player data from player.dat
     * If there are errors or the file does not exist, null is returned
     * @return List of Player objects
     * @precondition the file must exist otherwise null is returned
     */
    public List<Player> loadPlayerInfo (){
        String filename = "players.dat";
        Player p = null;
        List<Player> pList = new ArrayList<>();
        boolean error = false;

        try {
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);
        
            while (!error) {
                try {
                    p = (Player)in.readObject();
                    pList.add(p);
                } catch (Exception e) {
                    // reached end of file
                    error = true;
                }
            }
            in.close();
            file.close();
        } catch (IOException e) {
            // any IO errors will result in an empty Player list being returned
        }

        return pList;
    }
}
