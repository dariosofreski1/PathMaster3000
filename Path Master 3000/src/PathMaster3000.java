import javax.swing.*;
import java.awt.*;

public class PathMaster3000 {//main class for the game and it's components
    private final GameState gameState; //stores the state of the game
    private final GameGrid gameGrid; // Manages the grid and gameplay logic
    private final GameStats gameStats;// Tracks and displays statistics
    private final GameMenu gameMenu; // //Manages the menu

    private JFrame frame;// main frame of the app

    public PathMaster3000() { // Constructor to set up the game
        gameState = new GameState();// Initialize the game state
        gameStats = new GameStats(gameState); // initialize the statistics panel
        gameGrid = new GameGrid(gameState, gameStats);// initialize the game grid
        gameStats.setGameGrid(gameGrid);// inject game grid into game stats for the hint button
        gameMenu = new GameMenu(gameState, this, gameGrid);// initialize the menu bar

        createAndShowGUI();// Sets up the gui and displays the game
    }

    private void createAndShowGUI() { // method to display and create the main game
        frame = new JFrame("Path Master 3000");// creating the main window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit on close
        frame.setSize(500, 600);// set the window size
        frame.setLayout(new BorderLayout());// use a border layout for the frame

        frame.setJMenuBar(gameMenu.getMenuBar());// add the menu bar to the frame
        frame.add(gameGrid.getGridPanel(), BorderLayout.CENTER);// add the grid panel
        frame.add(gameStats.getStatsPanel(), BorderLayout.SOUTH);// add the stats panel

        frame.setVisible(true);// make the window visible
    }

    public void restartGame() {//method to restart the game
        gameState.resetGameState();// reset all variables
        frame.remove(gameGrid.getGridPanel());//remove the current grid
        gameGrid.initializeGrid();//reinitialize the grid
        frame.add(gameGrid.getGridPanel(), BorderLayout.CENTER);//add the new grid
        gameStats.reset();//reset the stats
        frame.revalidate();//revalidate to reflect the changes
        frame.repaint();//repaint to update the ui
    }

    public void resizeGrid(int newSize){//method to resize the grid
        gameState.setGridSize(newSize);//update the grid size in game state
        restartGame();//restart the game with new grid size
    }

    public static void main(String[] args) {
        new PathMaster3000();// creating and running the game
    }
}

