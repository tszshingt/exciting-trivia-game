package exciting.gui;

import exciting.util.Choice;
import exciting.util.Constant;
import exciting.util.Pair;
import java.awt.Dimension;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Game screen is the screen where the user reads questions and provides answers
 * to score points and attempt to win the game.
 *
 * @author Alejandro Reyes
 * @version 1.1
 */
public class GameScreen {

    /**
     * Constructor of the Game Screen UI class and all its components.
     *
     */
    public GameScreen() {
        pnGameScreen = new JPanel();
        pnGameScreen.setPreferredSize(new Dimension(400, 375));
        lbUser = new JLabel();
        lbAIPlayer1 = new JLabel();
        lbAIPlayer2 = new JLabel();
        lbUserScore = new JLabel();
        lbAIPlayer1Score = new JLabel();
        lbAIPlayer2Score = new JLabel();
        jScrollPane1 = new JScrollPane();
        taDisplay = new JTextArea();
        taDisplay.setEditable(false);
        btEndGame = new JButton();
        btNext = new JButton();
        btA = new JButton();
        btB = new JButton();
        btC = new JButton();
        btD = new JButton();

        taDisplay.setColumns(20);
        taDisplay.setRows(10);
        jScrollPane1.setViewportView(taDisplay);

        btEndGame.setText("End Game");
        btNext.setText("Next");
        btA.setText("A");
        btB.setText("B");
        btC.setText("C");
        btD.setText("D");

        componentLayout();

        // add actionable components to array
        componentArray = new ArrayList<>();
        componentArray.add(btA); // 0 JButton
        componentArray.add(btB); // 1 JButton
        componentArray.add(btC); // 2 JButton
        componentArray.add(btD); // 3 JButton
        componentArray.add(btEndGame); // 4 JButton
        componentArray.add(btNext); // 5 JButton

        numPlayersEnteredGame = 0;

    }

    /**
     * Update the score for the user to see current score.
     *
     * @param score is a list of player scores.
     * @param num is the number of scores to display.
     * @precondition score has the real player score first and Jane's score
     * last.
     * @postcondition score values display on the screen correctly.
     */
    public void updateScore(List<Integer> score, int num) {
        if (score.size() >= Constant.MAX_PLAYER_NUM) {
            lbUserScore.setText(" ");
            lbAIPlayer1Score.setText(" ");
            lbAIPlayer2Score.setText(" ");
            if (num >= 1) {
                lbUserScore.setText("Score: " + score.get(0));
            }
            if (num >= 2) {
                lbAIPlayer1Score.setText("Score: " + score.get(1));
            }
            if (num >= 3) {
                lbAIPlayer2Score.setText("Score: " + score.get(2));
            }
        }
    }

    /**
     * Set the next question on the screen for the user.
     *
     * @param aformattedQuestion is a formatted question to display on the
     * screen.
     */
    public void showNextQuestion(String aformattedQuestion) {
        taDisplay.setText(aformattedQuestion);
    }

    /**
     * Show the results of a round on the screen for the user to see.
     *
     * @param result has the answer provided by each player of the game.
     * @param currectAnswer is the correct answer to the question of the round.
     */
    public void showResults(List<Pair<String, Choice>> result, Choice currectAnswer) {
        String str = new String();
        for (Pair p : result) {
            str += p.k() + " answered " + p.v() + "\n";
        }
        str += "Correct answer is: " + currectAnswer;
        taDisplay.setText(str);
    }

    /**
     * Gets the number of players playing the game.
     *
     * @return returns the number of players playing the game.
     */
    public int getNumPlayersEnteredGame() {
        return numPlayersEnteredGame;
    }

    /**
     * Reset the number of players playing the game to zero.
     */
    public void resetNumPlayersEnteredGame() {
        numPlayersEnteredGame = 0;
    }

    /**
     * Shows the players entering the game. Simulation to give the user the
     * impression that other players are coming in to join the game.
     *
     * @param name is a list of the players. One real player and 2 AI players.
     */
    public void showPlayersEnteringGame(List<String> name) {
        lbUser.setText(" ");
        lbAIPlayer1.setText(" ");
        lbAIPlayer2.setText(" ");
        if (name.size() >= Constant.MAX_PLAYER_NUM) {
            lbUser.setText(name.get(0) + " has Entered the Game.");
            onePlayerEntered();

            Timer t = new Timer(2000, (ActionEvent e) -> {
                lbAIPlayer1.setText(name.get(1) + " has Entered the Game.");
                onePlayerEntered();
                if (numPlayerListener != null) {
                    numPlayerListener.stateChanged(new ChangeEvent(this));
                }
            });
            t.setRepeats(false);
            t.start();

            Timer tt = new Timer(4000, (ActionEvent e) -> {
                lbAIPlayer2.setText(name.get(2) + " has Entered the Game.");
                onePlayerEntered();
                if (numPlayerListener != null) {
                    numPlayerListener.stateChanged(new ChangeEvent(this));
                }
            });
            tt.setRepeats(false);
            tt.start();
        }
    }

    /**
     * @precondition Listener must be instance of ActionListener for Buttons,
     * and ChangeListener for others.
     */
    public void addListener(EventListener listener, JItem item) {
        if (item == JItem.NUM_PLAYER_CHANGE
                && listener instanceof ChangeListener) {
            numPlayerListener = (ChangeListener) listener;
        } else if (componentArray.get(item.ordinal()).getClass() == JButton.class
                && listener instanceof ActionListener) {
            ((JButton) componentArray.get(item.ordinal())).addActionListener((ActionListener) listener);
        }
    }

    /**
     * Gets the game screen UI.
     *
     * @return returns JPanel with all of the components for the UI.
     */
    public JPanel getGameScreenPanel() {
        return pnGameScreen;
    }

    /**
     * Inserts all components in the JPanel in the proper location.
     *
     */
    private void componentLayout() {
        GroupLayout layout = new GroupLayout(pnGameScreen);
        pnGameScreen.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(lbUser)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 197, Short.MAX_VALUE)
                                                .addComponent(lbUserScore))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(lbAIPlayer2)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(lbAIPlayer2Score))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(lbAIPlayer1)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(lbAIPlayer1Score))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(btEndGame)
                                                        .addComponent(btA)
                                                        .addComponent(btB))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(btNext, GroupLayout.Alignment.TRAILING)
                                                        .addComponent(btC, GroupLayout.Alignment.TRAILING)
                                                        .addComponent(btD, GroupLayout.Alignment.TRAILING)))
                                        .addComponent(jScrollPane1))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbUser)
                                        .addComponent(lbUserScore))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbAIPlayer1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbAIPlayer1Score))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbAIPlayer2)
                                        .addComponent(lbAIPlayer2Score))
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(btEndGame)
                                        .addComponent(btNext))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(btA)
                                        .addComponent(btC))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(btB)
                                        .addComponent(btD))
                                .addContainerGap(40, Short.MAX_VALUE))
        );
    }

    /**
     * Method to update number of players entered game.
     */
    private synchronized void onePlayerEntered() {
        numPlayersEnteredGame++;
    }

    // constants to select components to add listeners to
    enum JItem {
        A_BUTTON,
        B_BUTTON,
        C_BUTTON,
        D_BUTTON,
        END_GAME_BUTTON,
        NEXT_BUTTON,
        NUM_PLAYER_CHANGE;
    }

    // instance variables:     
    private final ArrayList<JComponent> componentArray;
    private final JPanel pnGameScreen;
    private final JButton btA;
    private final JButton btB;
    private final JButton btC;
    private final JButton btD;
    private final JButton btEndGame;
    private final JButton btNext;
    private final JLabel lbAIPlayer1;
    private final JLabel lbAIPlayer1Score;
    private final JScrollPane jScrollPane1;
    private final JTextArea taDisplay;
    private final JLabel lbAIPlayer2;
    private final JLabel lbAIPlayer2Score;
    private final JLabel lbUser;
    private final JLabel lbUserScore;

    // added variables to keep track how many players have entered the game
    private int numPlayersEnteredGame;
    private ChangeListener numPlayerListener;
}
