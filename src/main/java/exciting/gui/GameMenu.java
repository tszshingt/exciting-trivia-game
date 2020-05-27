package exciting.gui;

import exciting.util.Level;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.EventListener;

/**
 * Game Menu UI class is the starting point for the user of the Game. The user
 * will be able to start a new game or view score history and more from this UI.
 *
 * @author Alejandro Reyes
 * @version 1.1
 */
public class GameMenu {

    /**
     * Constructor of the Game Menu UI class and all its components.
     *
     */
    public GameMenu() {
        pnGameMenu = new JPanel();
        lbGameMenu = new JLabel();
        lbName = new JLabel();
        tfName = new JTextField();
        lbChangeDifficulty = new JLabel();
        cbDifficultyLevel = new JComboBox<>();
        btStart = new JButton();
        btViewScores = new JButton();
        btReset = new JButton();
        btExit = new JButton();

        lbGameMenu.setText("Game Menu");
        lbName.setText("Name:");
        lbChangeDifficulty.setText("Change Difficulty Level:");
        cbDifficultyLevel.setModel(new DefaultComboBoxModel<>(new String[]{Level.NOVICE.toString(), Level.INTERMEDIATE.toString(), Level.ADVANCED.toString()}));

        btStart.setText("Start");
        btViewScores.setText("View Scores");
        btReset.setText("Reset");
        btExit.setText("Exit");

        componentLayout();

        // add actionable components to array
        componentArray = new ArrayList<>();
        componentArray.add(tfName); // 0 JTextField
        componentArray.add(cbDifficultyLevel); // 1 JCombobox
        componentArray.add(btStart); // 2 JButton
        componentArray.add(btViewScores); // 3 JButton
        componentArray.add(btReset); // 4 JButton
        componentArray.add(btExit); // 5 JButton
    }

    /**
     * Gets the game menu UI.
     *
     * @return returns JPanel with all of the components for the UI.
     */
    public JPanel getGameMenuPanel() {
        return pnGameMenu;
    }

    /**
     * Get the users name entered by the user in the game menu UI.
     *
     * @return returns users name.
     * @precondition username is not empty.
     */
    public String getUserName() {
        return tfName.getText();
    }

    /**
     * Gets the difficult level entered by the user on the game menu UI.
     *
     * @return returns a level, Novice, Intermediate or Advanced.
     */
    public Level getDifficultyLevel() {
        if (cbDifficultyLevel.getSelectedItem().getClass() == String.class) {
            return Level.valueOf(((String) cbDifficultyLevel.getSelectedItem()).toUpperCase());
        } else {
            return null;
        }
    }

    /**
     * Set the users name on the screen if the system is able to recognize the
     * user as a previous player.
     */
    public void setUserName(String name) {
        tfName.setText(name);
    }

    /**
     * Set the users difficulty level on the screen if the system is able to
     * recognize the user as a previous player.
     *
     */
    public void setDifficultyLevel(Level lv) {
        cbDifficultyLevel.setSelectedItem(lv.toString());
    }

    /**
     * Enable or disable (if no user information) the menu items
     *
     * @param b True to enable and false to disable the game menu.
     */
    public void setEnabledMenu(boolean b) {
        cbDifficultyLevel.setEnabled(b);
        btReset.setEnabled(b);
        btStart.setEnabled(b);
        btViewScores.setEnabled(b);
    }

    /**
     * @precondition Listener must be instance of KeyListener for TextFields and
     * ActionListener for ComboBoxes or Buttons, otherwise no changes are made.
     */
    public void addListener(EventListener listener, JItem item) {
        if (componentArray.get(item.ordinal()).getClass() == JTextField.class
                && listener instanceof KeyListener) {
            ((JTextField) componentArray.get(item.ordinal())).addKeyListener((KeyListener) listener);
        } else if (componentArray.get(item.ordinal()).getClass() == JComboBox.class
                && listener instanceof ActionListener) {
            ((JComboBox) componentArray.get(item.ordinal())).addActionListener((ActionListener) listener);
        } else if (componentArray.get(item.ordinal()).getClass() == JButton.class
                && listener instanceof ActionListener) {
            ((JButton) componentArray.get(item.ordinal())).addActionListener((ActionListener) listener);
        }
    }

    /**
     * Inserts all components in the JPanel in the proper location.
     *
     */
    private void componentLayout() {
        GroupLayout layout = new GroupLayout(pnGameMenu);
        layout.setAutoCreateContainerGaps(true);
        layout.setAutoCreateGaps(true);
        pnGameMenu.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(lbGameMenu)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(lbName)
                                .addComponent(tfName)
                                .addComponent(lbChangeDifficulty)
                                .addComponent(cbDifficultyLevel, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btStart, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btViewScores, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btReset, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btExit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        )
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(lbGameMenu)
                        .addComponent(lbName)
                        .addComponent(tfName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbChangeDifficulty)
                        .addComponent(cbDifficultyLevel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btStart)
                        .addComponent(btViewScores)
                        .addComponent(btReset)
                        .addComponent(btExit)
        );
    }

    // constants to select actionable components to add listeners to
    enum JItem {
        NAME_TEXTFIELD,
        DIFFICULT_LEVEL_CB,
        START_BUTTON,
        VIEW_SCORES_BUTTON,
        RESET_BUTTON,
        EXIT_BUTTON;
    }

    // instance variables:  
    private final ArrayList<JComponent> componentArray;
    private final JPanel pnGameMenu;
    private final JButton btExit;
    private final JButton btReset;
    private final JButton btStart;
    private final JButton btViewScores;
    private final JComboBox<String> cbDifficultyLevel;
    private final JLabel lbChangeDifficulty;
    private final JLabel lbGameMenu;
    private final JLabel lbName;
    private final JTextField tfName;
}
