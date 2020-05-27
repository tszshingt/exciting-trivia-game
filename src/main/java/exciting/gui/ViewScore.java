package exciting.gui;

import exciting.util.Constant;
import exciting.util.Pair;
import javax.swing.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ViewScore class contains a JPanel with labels for the history of users games
 * played. It has 10 labels for a possible list of the last 10 games played.
 *
 * @author Alejandro Reyes
 * @version 1.1
 */
public class ViewScore {

    /**
     * Constructor of the ViewScoree class. Initially all the labels are empty
     * until the user plays a game.
     *
     * @return return ViewScore object.
     */
    public ViewScore() {
        lbtitle = new JLabel();
        btBackToGameMenu = new JButton();
        pnViewScore = new JPanel();
        lbtitle.setText("Last 10 Games");
        btBackToGameMenu.setText("Back to Game Menu");
        scoreLabels = new ArrayList<>();

        for (int i = 0; i < Constant.MAX_SCORE_HISTORY; i++) {
            scoreLabels.add(new JLabel());
        }

        componentLayout();
    }

    /**
     * Add action listener to the Back to Game Menu button that is on the
     * JPanel.
     *
     */
    public void addListener(ActionListener listener) {
        btBackToGameMenu.addActionListener(listener);
    }

    /**
     * Get the View Score UI.
     *
     * @return returns the JPanel with the score history.
     */
    public JPanel getViewScorePanel() {
        return pnViewScore;
    }

    /**
     * Fills history for the user in the View Scores UI.
     *
     * @param scores is a list of the score history for each player.
     * @precondition scores has real player history first and AI player Jane
     * history last.
     * @precondition scores should have history of three players exactly.
     * @postcondition JPanel will contain labels with the history of games for
     * the user to see in the proper format.
     */
    public void showFinalScores(List<List<Pair<Date, Integer>>> scores) {
        DateFormat df = DateFormat.getInstance();
        String yourScore = "";
        String tomsScore = "";
        String janesScore = "";
        String dateTime = "";
        
        for (int i = 0; i < Constant.MAX_SCORE_HISTORY; i++){
            scoreLabels.get(i).setText("");
        }
        
        if (scores.size() > 0) {
            for (int i = 0; i < Constant.MAX_SCORE_HISTORY; i++) {

                if (i < scores.get(0).size()) {
                    dateTime = df.format((scores.get(0).get(i).k()));
                    yourScore = scores.get(0).get(i).v().toString();
                    tomsScore = scores.get(1).get(i).v().toString();
                    janesScore = scores.get(2).get(i).v().toString();

                    scoreLabels.get(i).setText((i + 1) + ". " + dateTime + "  "
                            + "  Your Score: " + yourScore
                            + "  Tom's Score: " + tomsScore
                            + "  Jane's Score: " + janesScore + "\n");
                }
            }
        }
    }

    /**
     * Inserts all components in the JPanel in the proper location.
     *
     */
    private void componentLayout() {
        GroupLayout layout = new GroupLayout(pnViewScore);
        pnViewScore.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(lbtitle)
                                                        .addComponent(scoreLabels.get(0))
                                                        .addComponent(scoreLabels.get(1))
                                                        .addComponent(scoreLabels.get(2))
                                                        .addComponent(scoreLabels.get(3))
                                                        .addComponent(scoreLabels.get(4))
                                                        .addComponent(scoreLabels.get(5))
                                                        .addComponent(scoreLabels.get(6))
                                                        .addComponent(scoreLabels.get(7))
                                                        .addComponent(scoreLabels.get(8))
                                                        .addComponent(scoreLabels.get(9))
                                                ))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(132, 132, 132)
                                                .addComponent(btBackToGameMenu)))
                                .addContainerGap(145, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lbtitle)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scoreLabels.get(0))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scoreLabels.get(1))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scoreLabels.get(2))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scoreLabels.get(3))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scoreLabels.get(4))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scoreLabels.get(5))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scoreLabels.get(6))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scoreLabels.get(7))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scoreLabels.get(8))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scoreLabels.get(9))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 225, Short.MAX_VALUE)
                                .addComponent(btBackToGameMenu)
                                .addContainerGap())
        );
    }

    // instance variables:           
    private final JButton btBackToGameMenu;
    private final JLabel lbtitle;
    private final JPanel pnViewScore;
    private final ArrayList<JLabel> scoreLabels;
}
