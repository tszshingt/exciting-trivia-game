package exciting.system;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import exciting.util.Choice;
import exciting.util.Level;
import java.util.ListIterator;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Leigh Chin
 */
public class QuestionListTest {

    public QuestionListTest() {
    }

    @Test
    public void testGetQuestionIterator() {
        System.out.println("getQuestionIterator");
        Level lvl = Level.NOVICE;
        QuestionList instance = new QuestionList();
        Question q = new Question();
        q.setQ("What is 2 + 2?");
        q.setA("2");
        q.setB("3");
        q.setC("4");
        q.setD("5");
        q.setCorrect(Choice.C);
        q.setLevel(Level.NOVICE);
        instance.addQuestion(q);
        String expResult = q.toString();
        ListIterator<Question> result = instance.getQuestionIterator((q2) -> {
            return q2.getLevel().equals(lvl);
        });
        assertEquals(expResult, result.next().toString());
    }

    @Test
    public void testAddQuestion() {
        System.out.println("addQuestion");
        Question q = new Question();
        q.setQ("What is 2 + 2?");
        q.setA("2");
        q.setB("3");
        q.setC("4");
        q.setD("5");
        q.setCorrect(Choice.C);
        q.setLevel(Level.NOVICE);
        QuestionList instance = new QuestionList();
        instance.addQuestion(q);
        assertEquals(q.toString(),
                instance.getQuestionIterator((q2) -> {
                    return q2.getLevel().equals(Level.NOVICE);
                }).next().toString());
    }

}
