/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exciting.system;

import exciting.util.Choice;
import exciting.util.Level;

/**
 * Defines Question object and associated methods
 * @author Leigh Chin
 */
public class Question {
    
    public Question() {
        question = null;
        answerA = null;
        answerB = null;
        answerC = null;
        answerD = null;
        correctAnswer = null;
        level = null;
    }

    /**
     * Sets the question text
     * @param Q 
     */
    public void setQ (String Q) {question = Q;}
    
    /**
     * Sets Option A text
     * @param A 
     */
    public void setA (String A) {answerA = A;}
    
    /**
     * Sets Option B text
     * @param B 
     */
    public void setB (String B) {answerB = B;}
    
    /**
     * Sets Option C text
     * @param C 
     */
    public void setC (String C) {answerC = C;}
    
    /**
     * Sets Option D text
     * @param D 
     */
    public void setD (String D) {answerD = D;}
    
    /**
     * sets the correct answer choice
     * @param ch Choice correct answer
     */
    public void setCorrect (Choice ch) {correctAnswer = ch;}

    /**
     * Set the difficulty level for the question
     * @param lvl difficulty level
     */
    public void setLevel (Level lvl) {level = lvl;}
    
    /**
     * Constructs the Question string to display onscreen
     * @return String Question with Answer Choices
     */
    public String getQuestionText() {
        return this.question + 
               "\n(A) " + this.getA() + 
               "\n(B) " + this.getB() + 
               "\n(C) " + this.getC() + 
               "\n(D) " + this.getD();
    }
    
    /**
     * Returns question text
     * @return String question text
     */
    public String getQuestion() {
        return this.question;
    }
    
    /**
     * Returns option A text
     * @return String option A
     */
    public String getA() {
        return this.answerA;
    }
    
    /**
     * Returns option B text
     * @return String option B
     */
    public String getB() {
        return this.answerB;
    }
    
    /**
     * Returns option C text
     * @return String option C
     */
    public String getC() {
        return this.answerC;
    }
    
    /**
     * Returns option D text
     * @return String option D
     */
    public String getD() {
        return this.answerD;
    }
    
    /**
     * Returns correct answer choice
     * @return Choice correct answer
     */
    public Choice getCorrectAnswer() {
        return this.correctAnswer;
    }
    
    /**
     * Returns difficulty level of question
     * @return Level difficulty level of this question
     */
    public Level getLevel() {
        return this.level;
    }
      
    /**
     * Returns String of question and options
     * @return String of question, options, correct answer, and level
     */
    @Override
    public String toString() {
        String out;
        
        out = this.question + "\n" +
              this.answerA + "\n" +
              this.answerB + "\n" +
              this.answerC + "\n" +
              this.answerD + "\n" +
              this.correctAnswer + "\n" +
              this.level;
        
        return out;
    }
   
    private String question;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private Choice correctAnswer;
    private Level level;  
}

