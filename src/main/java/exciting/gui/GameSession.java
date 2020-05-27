package exciting.gui;

import exciting.game.Game;
import exciting.system.GameSystem;
import exciting.util.Choice;
import exciting.util.Constant;
import exciting.util.GameState;
import exciting.util.Level;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * GameSession manages the state of the game application.
 *
 * @author Alejandro Reyes, Tsz Shing Tsoi
 */
public class GameSession {

    /**
     * Construct a GameSession object, initialize all graphical user interface
     * (GUI) and game system objects and add listeners, and create the main
     * JFrame object.
     *
     * @postcondition state == GameSessionState.GAME_SESSION_BEGAN
     */
    public GameSession() {
        cardLayout = new CardLayout();
        gameScreen = new GameScreen();
        gameMenu = new GameMenu();
        viewScore = new ViewScore();
        splashScreen = new SplashScreen();

        mainPanel = new JPanel(cardLayout);
        mainPanel.add("SplashScreen", splashScreen.getSplashScreenPanel());
        mainPanel.add("GameMenu", gameMenu.getGameMenuPanel());
        mainPanel.add("GameScreen", gameScreen.getGameScreenPanel());
        mainPanel.add("ViewScore", viewScore.getViewScorePanel());

        mainFrame = new JFrame();
        mainFrame.add(mainPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setResizable(false);

        splashScreenTimer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "GameMenu");
            }
        });
        splashScreenTimer.setRepeats(false);

        gameSystem = new GameSystem();
        game = null;
        addGameSystemListener();
        addGameMenuListener();
        addGameScreenListener();
        addViewScoreScreenListener();
        setState(GameSessionState.GAME_SESSION_BEGAN);

        stateLock = new ReentrantLock();
    }

    /**
     * Run the Exciting Game application.
     *
     */
    public void run() {
        mainFrame.setVisible(true);
        splashScreenTimer.start();
        gameSystem.initialize();
    }

    // private methods and helper functions:
    /**
     * Set the state of the GameSession object.
     *
     * @param state the state to be set to
     */
    private void setState(GameSessionState state) {
        this.state = state;
    }

    /**
     * Add listener to the GameSystem object to update user name and difficulty
     * level.
     *
     */
    private void addGameSystemListener() {
        gameSystem.addChangeListener((e) -> {
            stateLock.lock();
            try {
                if (state == GameSessionState.GAME_SESSION_BEGAN
                        || state == GameSessionState.OBTAINED_PLAYER_INFO) {
                    if (gameSystem.isUserDataExist()) {
                        gameMenu.setUserName(gameSystem.getUserName());
                        gameMenu.setDifficultyLevel(gameSystem.getDiff());
                        gameMenu.setEnabledMenu(true);
                        setState(GameSessionState.OBTAINED_PLAYER_INFO);
                    } else {
                        gameMenu.setUserName("");
                        gameMenu.setDifficultyLevel(Level.NOVICE);
                        gameMenu.setEnabledMenu(false);
                        setState(GameSessionState.MAIN_MENU);
                    }
                }
            } finally {
                stateLock.unlock();
            }

        });
    }

    /**
     * Add listener to the Game object to update game screen labels.
     *
     */
    private void addGameListener() {
        game.addChangeListener((e) -> {
            if (state == GameSessionState.GAME_SCREEN) {
                gameScreen.showResults(game.getResults(), game.getCorrectAnswer());
                gameScreen.updateScore(game.getScore(), gameScreen.getNumPlayersEnteredGame());
            }
        });
    }

    /**
     * Add listener to the ViewScreen object to return to the game menu.
     *
     */
    private void addViewScoreScreenListener() {
        viewScore.addListener((e) -> {
            stateLock.lock();
            try {
                if (state == GameSessionState.DISPLAY_SCORES
                        || game.getState() == GameState.GAME_ENDED) {
                    setState(GameSessionState.OBTAINED_PLAYER_INFO);
                    cardLayout.show(mainPanel, "GameMenu");
                }
            } finally {
                stateLock.unlock();
            }
        });
    }

    /**
     * Add listeners to the GameScreen object to receive user actions.
     *
     */
    private void addGameScreenListener() {
        gameScreen.addListener((ActionListener) (ActionEvent e) -> {
            // submit answer
            game.submitAnswerToJudge(gameSystem.getRealPlayer(), Choice.A);
        }, GameScreen.JItem.A_BUTTON);

        gameScreen.addListener((ActionListener) (ActionEvent e) -> {
            // submit answer
            game.submitAnswerToJudge(gameSystem.getRealPlayer(), Choice.B);
        }, GameScreen.JItem.B_BUTTON);

        gameScreen.addListener((ActionListener) (ActionEvent e) -> {
            // submit answer
            game.submitAnswerToJudge(gameSystem.getRealPlayer(), Choice.C);
        }, GameScreen.JItem.C_BUTTON);

        gameScreen.addListener((ActionListener) (ActionEvent e) -> {
            // submit answer
            game.submitAnswerToJudge(gameSystem.getRealPlayer(), Choice.D);
        }, GameScreen.JItem.D_BUTTON);

        gameScreen.addListener((ActionListener) (ActionEvent e) -> {
            stateLock.lock();
            try {
                // end the game
                game.endGame();
                if (game.getState() == GameState.SCORE_HISTORY_UPDATED) {
                    // update score in viewScore screen                 
                    viewScore.showFinalScores(gameSystem.getScore());
                    game.cleanUp();
                    cardLayout.show(mainPanel, "ViewScore");
                    gameScreen.showNextQuestion(" ");
                    gameScreen.resetNumPlayersEnteredGame();
                } else if (state == GameSessionState.GAME_SCREEN
                        && game.getState() == GameState.INITIAL_GAME_SCREEN) { // if game is ended before it started
                    cardLayout.show(mainPanel, "GameMenu");
                    gameScreen.showNextQuestion(" ");
                    gameScreen.resetNumPlayersEnteredGame();
                    setState(GameSessionState.OBTAINED_PLAYER_INFO);
                }                
            } finally {
                stateLock.unlock();
            }
        }, GameScreen.JItem.END_GAME_BUTTON);

        gameScreen.addListener((ActionListener) (ActionEvent e) -> {
            if (state == GameSessionState.GAME_SCREEN) {
                String q = game.getNextQuestion();
                if (game.getState() == GameState.QUESTION_EXHAUSTED) {
                    game.endGame();
                    if (game.getState() == GameState.SCORE_HISTORY_UPDATED) {
                        // update score in viewScore screen                
                        viewScore.showFinalScores(gameSystem.getScore());
                        cardLayout.show(mainPanel, "ViewScore");
                        gameScreen.resetNumPlayersEnteredGame();
                    }
                } else if (q != null) {
                    gameScreen.showNextQuestion(q);
                }
            }
        }, GameScreen.JItem.NEXT_BUTTON);

        gameScreen.addListener((ChangeListener) (ChangeEvent e) -> {
            stateLock.lock();
            try {
                if (state == GameSessionState.PLAYER_ENTERING) {
                    gameScreen.updateScore(game.getScore(), gameScreen.getNumPlayersEnteredGame());
                }
                if (gameScreen.getNumPlayersEnteredGame() == Constant.MAX_PLAYER_NUM) {
                    gameScreen.showNextQuestion("Welcome! Please click \"Next\" to begin the Exciting Game!");
                    setState(GameSessionState.GAME_SCREEN);
                }
            } finally {
                stateLock.unlock();
            }
        }, GameScreen.JItem.NUM_PLAYER_CHANGE);
    }

    /**
     * Add listeners to the GameMenu object to receive user actions.
     *
     */
    private void addGameMenuListener() {
        gameMenu.addListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                stateLock.lock();
                try {
                    // set name of player                
                    String name = gameMenu.getUserName();
                    if (name.length() > 0) {
                        gameSystem.setUserName(name);
                        gameMenu.setEnabledMenu(true);
                        setState(GameSessionState.OBTAINED_PLAYER_INFO);
                    }
                } finally {
                    stateLock.unlock();
                }
            }
        }, GameMenu.JItem.NAME_TEXTFIELD);

        gameMenu.addListener((ActionListener) (ActionEvent e) -> {
            // set difficulty level of player in GameSystem
            gameSystem.changeDifficulty(gameMenu.getDifficultyLevel());
        }, GameMenu.JItem.DIFFICULT_LEVEL_CB);

        gameMenu.addListener((ActionListener) (ActionEvent e) -> {
            stateLock.lock();
            try {
                if (state == GameSessionState.OBTAINED_PLAYER_INFO) {
                    game = gameSystem.getNewGame();
                    addGameListener();
                    game.initialize();
                    cardLayout.show(mainPanel, "GameScreen");
                    gameScreen.showPlayersEnteringGame(game.getPlayersEnteringGame());
                    gameScreen.updateScore(game.getScore(), gameScreen.getNumPlayersEnteredGame());
                    setState(GameSessionState.PLAYER_ENTERING);
                }
            } finally {
                stateLock.unlock();
            }
        }, GameMenu.JItem.START_BUTTON);

        gameMenu.addListener((ActionListener) (ActionEvent e) -> {
            stateLock.lock();
            try {
                if (state == GameSessionState.OBTAINED_PLAYER_INFO) {
                    // update score in viewScore screen 
                    viewScore.showFinalScores(gameSystem.getScore());
                    cardLayout.show(mainPanel, "ViewScore");
                    setState(GameSessionState.DISPLAY_SCORES);
                }
            } finally {
                stateLock.unlock();
            }
        }, GameMenu.JItem.VIEW_SCORES_BUTTON);

        gameMenu.addListener((ActionListener) (ActionEvent e) -> {
            stateLock.lock();
            try {
                if (getResetConfirmation() == JOptionPane.YES_OPTION) {
                    if (state == GameSessionState.OBTAINED_PLAYER_INFO) {
                        gameSystem.resetData();
                    }
                }
            } finally {
                stateLock.unlock();
            }
        }, GameMenu.JItem.RESET_BUTTON);

        gameMenu.addListener((ActionListener) (ActionEvent e) -> {
            stateLock.lock();
            try {
                // save data in gamesystem
                if (state == GameSessionState.OBTAINED_PLAYER_INFO) {
                    gameSystem.saveUserData();
                }
            } finally {
                stateLock.unlock();
            }
            System.exit(0);
        }, GameMenu.JItem.EXIT_BUTTON);

    }

    /**
     * Get confirmation from the user on resetting data.
     *
     * @return the option the user selected
     */
    private int getResetConfirmation() {
        return JOptionPane.showConfirmDialog(
                mainFrame, "All user data and scores will be erased. Continue?",
                "Reset Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
    }

    /**
     * GameSessionState describes the states of a game session.
     *
     */
    enum GameSessionState {
        // enum constants are public, static and final by default
        GAME_SESSION_BEGAN,
        MAIN_MENU,
        OBTAINED_PLAYER_INFO,
        PLAYER_ENTERING,
        GAME_SCREEN,
        DISPLAY_SCORES,
    }

    // private variables:
    private final ReentrantLock stateLock; // lock to crtical section to update current state
    private GameSessionState state;

    // private variables for GUI
    private final GameMenu gameMenu;
    private final GameScreen gameScreen;
    private final ViewScore viewScore;
    private final SplashScreen splashScreen;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private final JFrame mainFrame;
    private final Timer splashScreenTimer;

    // private variables for model
    private final GameSystem gameSystem;
    private Game game;
}
