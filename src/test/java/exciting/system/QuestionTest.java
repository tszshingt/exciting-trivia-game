package exciting.system;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import exciting.util.Choice;
import exciting.util.Level;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Leigh Chin
 */
public class QuestionTest {
    
    public QuestionTest() {
    }
    
    /**
     * Test of setQ method, of class Question.
     */
    @Test
    public void testSetQ() {
        System.out.println("setQ");
        String Q = "This is a test.";
        Question instance = new Question();
        instance.setQ(Q);
        assertEquals(Q, instance.getQuestion());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setA method, of class Question.
     */
    @Test
    public void testSetA() {
        System.out.println("setA");
        String A = "This is Option A.";
        Question instance = new Question();
        instance.setA(A);
        assertEquals(A, instance.getA());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setB method, of class Question.
     */
    @Test
    public void testSetB() {
        System.out.println("setB");
        String B = "This is Option B.";
        Question instance = new Question();
        instance.setB(B);
        assertEquals(B, instance.getB());
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of setC method, of class Question.
     */
    @Test
    public void testSetC() {
        System.out.println("setC");
        String C = "This is Option C.";
        Question instance = new Question();
        instance.setC(C);
        assertEquals(C, instance.getC());
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of setD method, of class Question.
     */
    @Test
    public void testSetD() {
        System.out.println("setD");
        String D = "This is option D.";
        Question instance = new Question();
        instance.setD(D);
        assertEquals(D, instance.getD());
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of setCorrect method, of class Question.
     */
    @Test
    public void testSetCorrect() {
        System.out.println("setCorrect");
        Choice ch = Choice.B;
        Question instance = new Question();
        instance.setCorrect(ch);
        assertEquals(ch, instance.getCorrectAnswer());
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of setLevel method, of class Question.
     */
    @Test
    public void testSetLevel() {
        System.out.println("setLevel");
        Level lvl = Level.ADVANCED;
        Question instance = new Question();
        instance.setLevel(lvl);
        assertEquals(lvl, instance.getLevel());
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of getQuestionText method, of class Question.
     */
    @Test
    public void testGetQuestionText() {
        System.out.println("getQuestionText");
        Question instance = new Question();
        instance.setQ("What is 1 + 1?");
        instance.setA("5");
        instance.setB("4");
        instance.setC("3");
        instance.setD("2");
        String expResult = "What is 1 + 1?\n(A) 5\n(B) 4\n(C) 3\n(D) 2";
        String result = instance.getQuestionText();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of getQuestion method, of class Question.
     */
    @Test
    public void testGetQuestion() {
        System.out.println("getQuestion");
        Question instance = new Question();
        instance.setQ("What color is the sky?");
        String expResult = "What color is the sky?";
        String result = instance.getQuestion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of getA method, of class Question.
     */
    @Test
    public void testGetA() {
        System.out.println("getA");
        Question instance = new Question();
        instance.setA("red");
        String expResult = "red";
        String result = instance.getA();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of getB method, of class Question.
     */
    @Test
    public void testGetB() {
        System.out.println("getB");
        Question instance = new Question();
        instance.setB("blue");
        String expResult = "blue";
        String result = instance.getB();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of getC method, of class Question.
     */
    @Test
    public void testGetC() {
        System.out.println("getC");
        Question instance = new Question();
        instance.setC("green");
        String expResult = "green";
        String result = instance.getC();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of getD method, of class Question.
     */
    @Test
    public void testGetD() {
        System.out.println("getD");
        Question instance = new Question();
        instance.setD("yellow");
        String expResult = "yellow";
        String result = instance.getD();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of getCorrectAnswer method, of class Question.
     */
    @Test
    public void testGetCorrectAnswer() {
        System.out.println("getCorrectAnswer");
        Question instance = new Question();
        instance.setCorrect(Choice.C);
        Choice expResult = Choice.C;
        Choice result = instance.getCorrectAnswer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of getLevel method, of class Question.
     */
    @Test
    public void testGetLevel() {
        System.out.println("getLevel");
        Question instance = new Question();
        instance.setLevel(Level.INTERMEDIATE);
        Level expResult = Level.INTERMEDIATE;
        Level result = instance.getLevel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Question.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Question instance = new Question();
        instance.setQ("What is 1 + 1?");
        instance.setA("5");
        instance.setB("4");
        instance.setC("3");
        instance.setD("2");
        instance.setCorrect(Choice.D);
        instance.setLevel(Level.NOVICE);
        String expResult = "What is 1 + 1?\n5\n4\n3\n2\nD\nNovice";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }
    
}
