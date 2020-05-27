/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exciting.game;

import exciting.system.Question;
import exciting.system.QuestionList;
import exciting.util.Choice;
import exciting.util.GameState;
import exciting.util.Level;
import exciting.util.Pair;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * GameTest tests the Game class.
 *
 * @author ShingShing
 */
public class GameTest {

    public GameTest() {
    }

    private Game game;
    private Player p1 = new SimulatedPlayer("Jane");
    private Player p2 = new RealPlayer("Tom", Level.INTERMEDIATE);
    private Player p3 = new RealPlayer("John", Level.ADVANCED);
    private Question q;
    private QuestionList qList;

    @Before
    public void setUp() {
        // setup question list
        q = new Question();
        q.setQ("This is a question.");
        q.setA("Choice a");
        q.setB("Choice b");
        q.setC("Choice c");
        q.setD("Choice d");
        q.setCorrect(Choice.A);
        q.setLevel(Level.INTERMEDIATE);
        qList = new QuestionList();
        qList.addQuestion(q);

        // setup player list
        p1 = new SimulatedPlayer("Jane");
        p2 = new RealPlayer("Tom", Level.INTERMEDIATE);
        p3 = new RealPlayer("John", Level.ADVANCED);
        Player[] arr = {p1, p2, p3};
        List<Player> pList = new ArrayList<>(Arrays.asList(arr));

        // setup game
        game = new Game(qList.getQuestionIterator((q) -> {
            return q.getLevel().equals(Level.INTERMEDIATE);
        }), pList);

    }

    /**
     * Test of initialize method, of class Game.
     */
    @Test

    public void testInitialize() {
        System.out.println("Test initialize");
        assertTrue(game.getState() == GameState.GAME_BEGAN);
        game.initialize();
        assertTrue(game.getState() == GameState.INITIAL_GAME_SCREEN);
        assertTrue(p1.getDifficulty() == p2.getDifficulty());
    }

    /**
     * Test of getNextQuestion method, of class Game.
     */
    @Test
    public void testGetNextQuestion() {
        System.out.println("Test getNextQuestion");
        game.initialize();
        assertTrue(game.getState() == GameState.INITIAL_GAME_SCREEN);

        // get next question
        String q2 = game.getNextQuestion();
        assertTrue(game.getState() == GameState.QUESTION_ASKED);
        assertTrue(q2.equals(q.getQuestionText()));

        // get next question again
        q2 = game.getNextQuestion();
        assertTrue(game.getState() == GameState.QUESTION_ASKED);
        assertTrue(q2 == null);

        // submit answers and end the current round
        game.submitAnswerToJudge(p1, Choice.A);
        game.submitAnswerToJudge(p2, Choice.B);
        game.submitAnswerToJudge(p3, Choice.C);
        game.getResults();
        game.getCorrectAnswer();

        // get question in the next round
        q2 = game.getNextQuestion();
        assertTrue(game.getState() == GameState.QUESTION_EXHAUSTED);
        assertTrue(q2 == null);
    }

    /**
     * Test of submitAnswerToJudge method, of class Game.
     */
    @Test
    public void testSubmitAnswerToJudge() {
        System.out.println("Test submitAnswerToJudge");
        game.initialize();
        assertTrue(game.getState() == GameState.INITIAL_GAME_SCREEN);

        // no effect
        game.submitAnswerToJudge(p1, Choice.A);
        game.submitAnswerToJudge(p2, Choice.B);
        game.submitAnswerToJudge(p3, Choice.C);
        assertTrue(game.getState() == GameState.INITIAL_GAME_SCREEN);

        // get next question
        game.getNextQuestion();
        assertTrue(game.getState() == GameState.QUESTION_ASKED);

        // submit answers and end the current round
        game.submitAnswerToJudge(p1, Choice.A);
        game.submitAnswerToJudge(p2, Choice.B);
        game.submitAnswerToJudge(p3, Choice.C);

        // get results and compare
        List<Pair<String, Choice>> list1 = new ArrayList<>();
        list1.add(new Pair<String, Choice>("Jane", Choice.A));
        list1.add(new Pair<String, Choice>("Tom", Choice.B));
        list1.add(new Pair<String, Choice>("John", Choice.C));
        assertTrue(game.getState() == GameState.CURRENT_SCORE_UPDATED);
        assertTrue(game.getResults().equals(list1));
    }

    /**
     * Test of getResults method, of class Game.
     */
    @Test
    public void testGetResults() {
        System.out.println("Test getResults");
        game.initialize();
        assertTrue(game.getState() == GameState.INITIAL_GAME_SCREEN);

        // no effect
        assertTrue(game.getResults() == null);
        assertTrue(game.getState() == GameState.INITIAL_GAME_SCREEN);

        // get next question
        game.getNextQuestion();
        assertTrue(game.getState() == GameState.QUESTION_ASKED);

        // no effect
        assertTrue(game.getResults() == null);
        assertTrue(game.getState() == GameState.QUESTION_ASKED);

        // submit answers and end the current round
        game.submitAnswerToJudge(p1, Choice.A);
        game.submitAnswerToJudge(p2, Choice.B);
        game.submitAnswerToJudge(p3, Choice.C);

        // get results and compare
        List<Pair<String, Choice>> list1 = new ArrayList<>();
        list1.add(new Pair<String, Choice>("Jane", Choice.A));
        list1.add(new Pair<String, Choice>("Tom", Choice.B));
        list1.add(new Pair<String, Choice>("John", Choice.C));
        assertTrue(game.getState() == GameState.CURRENT_SCORE_UPDATED);
        assertTrue(game.getResults().equals(list1));
        assertTrue(game.getState() == GameState.CURRENT_SCORE_UPDATED);
    }

    /**
     * Test of getCorrectAnswer method, of class Game.
     */
    @Test
    public void testGetCorrectAnswer() {
        System.out.println("Test getCorrectAnswer");
        game.initialize();
        assertTrue(game.getState() == GameState.INITIAL_GAME_SCREEN);

        // no effect
        assertTrue(game.getCorrectAnswer() == null);
        assertTrue(game.getState() == GameState.INITIAL_GAME_SCREEN);

        // get next question
        game.getNextQuestion();
        assertTrue(game.getState() == GameState.QUESTION_ASKED);

        // no effect
        assertTrue(game.getCorrectAnswer() == null);
        assertTrue(game.getState() == GameState.QUESTION_ASKED);

        // submit answers and end the current round
        game.submitAnswerToJudge(p1, Choice.A);
        game.submitAnswerToJudge(p2, Choice.B);
        game.submitAnswerToJudge(p3, Choice.C);
        assertTrue(game.getState() == GameState.CURRENT_SCORE_UPDATED);

        // get correct answer and compare        
        assertTrue(game.getCorrectAnswer() == Choice.A);
        assertTrue(game.getState() == GameState.CORRECT_ANSWER_REVEALED);
    }

    /**
     * Test of endGame method, of class Game.
     */
    @Test
    public void testEndGame() {
        System.out.println("Test endGame");
        game.initialize();
        assertTrue(game.getState() == GameState.INITIAL_GAME_SCREEN);

        // no effect
        game.endGame();
        assertTrue(game.getState() == GameState.INITIAL_GAME_SCREEN);

        // get next question
        game.getNextQuestion();
        assertTrue(game.getState() == GameState.QUESTION_ASKED);

        // no effect
        game.endGame();
        assertTrue(game.getState() == GameState.QUESTION_ASKED);

        // submit answers and end the current round
        game.submitAnswerToJudge(p1, Choice.A);
        game.submitAnswerToJudge(p2, Choice.B);
        game.submitAnswerToJudge(p3, Choice.C);
        assertTrue(game.getState() == GameState.CURRENT_SCORE_UPDATED);

        // no effect
        game.endGame();
        assertTrue(game.getState() == GameState.CURRENT_SCORE_UPDATED);

        // get correct answer
        assertTrue(game.getCorrectAnswer() == Choice.A);
        assertTrue(game.getState() == GameState.CORRECT_ANSWER_REVEALED);

        // no effect
        game.endGame();
        assertTrue(game.getState() == GameState.SCORE_HISTORY_UPDATED);
    }

    /**
     * Test of cleanUp method, of class Game.
     */
    @Test
    public void testCleanUp() {
        System.out.println("Test cleanUp");
        game.initialize();
        assertTrue(game.getState() == GameState.INITIAL_GAME_SCREEN);

        // no effect
        game.cleanUp();
        assertTrue(game.getState() == GameState.INITIAL_GAME_SCREEN);

        // get next question
        game.getNextQuestion();
        assertTrue(game.getState() == GameState.QUESTION_ASKED);

        // no effect
        game.cleanUp();
        assertTrue(game.getState() == GameState.QUESTION_ASKED);

        // submit answers and end the current round
        game.submitAnswerToJudge(p1, Choice.A);
        game.submitAnswerToJudge(p2, Choice.B);
        game.submitAnswerToJudge(p3, Choice.C);
        assertTrue(game.getState() == GameState.CURRENT_SCORE_UPDATED);

        // no effect
        game.cleanUp();
        assertTrue(game.getState() == GameState.CURRENT_SCORE_UPDATED);

        // get correct answer
        assertTrue(game.getCorrectAnswer() == Choice.A);
        assertTrue(game.getState() == GameState.CORRECT_ANSWER_REVEALED);

        // no effect
        game.cleanUp();
        assertTrue(game.getState() == GameState.CORRECT_ANSWER_REVEALED);

        // end the game
        game.endGame();
        assertTrue(game.getState() == GameState.SCORE_HISTORY_UPDATED);

        // clearn up
        game.cleanUp();
        assertTrue(game.getState() == GameState.GAME_ENDED);
    }

    /**
     * Test of getScore method, of class Game.
     */
    @Test
    public void testGetScore() {
        System.out.println("Test getScore");
        game.initialize();
        assertTrue(game.getState() == GameState.INITIAL_GAME_SCREEN);

        Integer[] arr = {0, 0, 0};
        List<Integer> list1 = new ArrayList<>(Arrays.asList(arr));

        // get scores
        assertTrue(game.getScore().equals(list1));

        // get next question
        game.getNextQuestion();
        assertTrue(game.getState() == GameState.QUESTION_ASKED);

        // get scores
        assertTrue(game.getScore().equals(list1));

        // submit answers and end the current round
        game.submitAnswerToJudge(p1, Choice.A);
        game.submitAnswerToJudge(p2, Choice.B);
        game.submitAnswerToJudge(p3, Choice.C);
        assertTrue(game.getState() == GameState.CURRENT_SCORE_UPDATED);

        arr[0] = 1;
        list1 = new ArrayList<>(Arrays.asList(arr));

        // get scores
        assertTrue(game.getScore().equals(list1));
    }

    /**
     * Test of getPlayersEnteringGame method, of class Game.
     */
    @Test
    public void testGetPlayersEnteringGame() {
        System.out.println("Test getPlayersEnteringGame");
        game.initialize();
        assertTrue(game.getState() == GameState.INITIAL_GAME_SCREEN);

        String[] arr = {"Jane", "Tom", "John"};
        List<String> list1 = new ArrayList<>(Arrays.asList(arr));

        assertTrue(game.getPlayersEnteringGame().equals(list1));
    }

    /**
     * Test of addChangeListener method, of class Game.
     */
    @Test
    public void testAddChangeListener() {
        System.out.println("Test addChangeListener");
        final String winnerMessage = "Jane is the winner!";
        final String[] str = {""};
        game.addChangeListener((e) -> {
            str[0] = winnerMessage;
        });

        game.initialize();
        game.getNextQuestion();

        // submit answers and end the current round
        game.submitAnswerToJudge(p1, Choice.A);
        game.submitAnswerToJudge(p2, Choice.B);
        game.submitAnswerToJudge(p3, Choice.C);

        assertTrue(str[0].equals(winnerMessage));
    }

    /**
     * Test of getState method, of class Game.
     */
    @Test
    public void testGetState() {
        System.out.println("Test getState");
        assertTrue(game.getState() == GameState.GAME_BEGAN);
        game.initialize();
        assertTrue(game.getState() == GameState.INITIAL_GAME_SCREEN);
        game.getNextQuestion();
        assertTrue(game.getState() == GameState.QUESTION_ASKED);
    }
}
