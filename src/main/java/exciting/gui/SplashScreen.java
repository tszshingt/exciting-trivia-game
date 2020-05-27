package exciting.gui;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Splash Screen class displays a banner to welcome the user to the game.
 *
 * @author Alejandro Reyes
 * @version 1.1
 */
public class SplashScreen {

    /**
     * Constructor of the Splash Screen.
     *
     */
    public SplashScreen() {
        lbWelcomeUser = new JLabel("Welcome to the Exciting Trivia Game.");
        splashScreenPanel = new JPanel();
        GroupLayout layout = new GroupLayout(splashScreenPanel);
        layout.setAutoCreateContainerGaps(true);
        layout.setAutoCreateGaps(true);
        splashScreenPanel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(lbWelcomeUser)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(lbWelcomeUser)
        );
    }

    /**
     * Gets the splash screen JPanel.
     *
     * @return returns the JPanel with the welcome message to the user.
     */
    public JPanel getSplashScreenPanel() {
        return splashScreenPanel;
    }

    // instance variables:
    private final JLabel lbWelcomeUser;
    private final JPanel splashScreenPanel;
}
