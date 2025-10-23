import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GameStats {// class to track and display statistics
    private final GameState gameState;//reference to the game state
    private GameGrid gameGrid;// reference to the game grid for hint button
    private JPanel statsPanel;// panel to display statistics
    private JLabel pathLengthLabel;// label to display the path length
    private JLabel sumLabel;// label for sum
    private JLabel scoreLabel;// label for score
    private JLabel timerLabel;// label for timer
    private Timer timer;// Timer to track and display time
    private JButton hintButton;// hint button
    private int elapsedTime = 0;// elapsed time in seconds

    public GameStats(GameState gameState) {// constructor that initializes the stats panel
        this.gameState = gameState;// link the game state
        initializeStatsPanel();// set up the stats panel
        initializeTimer();// set up the timer
    }

    public void setGameGrid(GameGrid gameGrid) {
        this.gameGrid = gameGrid;// set a reference to the game grid for hint button
    }

    public JPanel getStatsPanel() {// returns the panel containing the stats
        return statsPanel;
    }

    private void initializeStatsPanel() {// initializes the stats panel
        statsPanel = new JPanel();//new panel for stats
        statsPanel.setLayout(new GridLayout(1, 5));//set layout

        pathLengthLabel = new JLabel("Path Length: 0");//create the path length label
        sumLabel = new JLabel("Sum: 0");//create the sum label
        scoreLabel = new JLabel("Score: 0.00");//create the score label
        timerLabel = new JLabel("Time: 0s");//create the timer label

        statsPanel.add(pathLengthLabel);//add the path length to stats panel
        statsPanel.add(sumLabel);// add the sum label
        statsPanel.add(timerLabel);// add the timer label
        statsPanel.add(scoreLabel);// add the score label

        hintButton = new JButton("Hint");// create and add the hint button
        hintButton.addActionListener(e -> gameGrid.displayHint());//call the hint functionality in game grid
        statsPanel.add(hintButton);// add the hint button
    }

    private void initializeTimer() { // initializes timer
        timer = new Timer(1000,(ActionEvent e) -> {//creating a timer that updates every second
            elapsedTime++;//increment by 1 second
            timerLabel.setText("Time: " + elapsedTime+"s");//update the timer label
        });
        timer.start();//start the timer when the game begins
    }

    public void resetTimer(){//resets the timer to 0 and restarts it
        timer.stop();
        elapsedTime = 0;
        timerLabel.setText("Time: 0s");
        timer.start();
    }

    public void reset(){//resets all game statistics to their initial values
        resetTimer();
        pathLengthLabel.setText("Path Length: 0");
        sumLabel.setText("Sum: 0");
        scoreLabel.setText("Score: 0.00");
    }

    public void updateStats(){//updates the statistics panel with current values from the game state
        double score = (double) gameState.getScoresum() / gameState.getPathLength();
        scoreLabel.setText(String.format("Score: %.2f", score));
        pathLengthLabel.setText("Path Length: " + gameState.getPathLength());
        sumLabel.setText("Sum: "+ gameState.getScoresum());
    }
}
