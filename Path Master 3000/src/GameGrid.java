import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GameGrid {// manages the game grid (ui and gameplay logic)
    private final GameState gameState;// reference to the game state
    private final GameStats gameStats;// reference to the game stats
    private JPanel gridPanel;// panel that contains the buttons
    private JButton[][] gridButtons;// array of buttons that represents the grid

    public GameGrid(GameState gameState, GameStats gameStats) {// constructor to initialize gameGrid
        this.gameState = gameState;// link the game state
        this.gameStats = gameStats;//link the game stats
        initializeGrid();// create and set up the grid
    }

    public JPanel getGridPanel() {// returns the panel containing the grid to add to the main frame
        return gridPanel;
    }

    public JButton getButton(int row, int col) {
        return gridButtons[row][col];
    }

    public void initializeGrid() {// initializes the grid of buttons
        gridPanel = new JPanel();//create panel for the grid
        gridPanel.setLayout(new GridLayout(gameState.getGridSize(), gameState.getGridSize()));
        gridButtons = new JButton[gameState.getGridSize()][gameState.getGridSize()];
        Random rand = new Random(); // random number generator for the values
        // loop through each cell in grid
        for (int row = 0; row < gameState.getGridSize(); row++) {
            for (int col = 0; col < gameState.getGridSize(); col++) {
                JButton button = new JButton();//create a button for each cell
                gridButtons[row][col] = button;// add button to the array

                int number = rand.nextInt(10);//generate a number
                button.setText(String.valueOf(number));//set the text of the button to the number

                int finalRow = row, finalCol = col;//keep track of buttons position
                button.addActionListener(e -> handleButtonClick(finalRow, finalCol));//add click listener to the button

                gridPanel.add(button);//add the button to the grid
            }
        }
        // Set the "Start" and "End" cells
        gridButtons[0][0].setText("Start");
        gridButtons[gameState.getGridSize() - 1][gameState.getGridSize() - 1].setText("End");
        gridButtons[0][0].setBackground(gameState.getGameColor());
    }
    private void handleButtonClick(int row, int col) {// to handle a button click when a player moves
        if (isNeighbour(row, col)) {//check if move is valid
            JButton button = gridButtons[row][col];//get the clicked button

            if(gameState.getVisitedButtons().contains(row+","+col)) {//check if the cell has already been visited
                JOptionPane.showMessageDialog(gridPanel, "You cannot revisit this field!");
                return;
            }
                //mark the cell as visited
            gameState.getVisitedButtons().add(row+","+col);
            button.setBackground(gameState.getPathColor());//change the buttons background color

            gameState.setCurrentPosition(row, col);//update the player's position

            if(!button.getText().equals("End")) {//if not end add the cell value to the score
                gameState.addToScoreSum(Integer.parseInt(button.getText()));
            }
            gameState.incrementPathLength();//increment path length
            gameStats.updateStats();//update the stats panel

            if(button.getText().equals("End")) {//check if end is reached
                JOptionPane.showMessageDialog(gridPanel, "You have reached the end of the grid!");
            }
        } else { //invalid move not a neighbor
            JOptionPane.showMessageDialog(gridPanel, "INVALID MOVE! You can only move to a neighboring field!");
        }
    }
    private boolean isNeighbour(int row, int col) {//helper method to check if a cell is a valid neighbor
        return (Math.abs(row - gameState.getCurrentRow()) == 1 && col == gameState.getCurrentCol() || Math.abs(col - gameState.getCurrentCol()) == 1 && row == gameState.getCurrentRow());
    }
    public void displayHint() {// displays a hint for the next best move

        if(gridButtons == null || gameState == null) { // make sure everything is initialized
            return;// exit if not initialized
        }

        int currentRow = gameState.getCurrentRow();//get the players current row
        int currentCol = gameState.getCurrentCol();//get the players current col
        int gridSize = gameState.getGridSize();// get the grid size

        int[][] moves = {// define all possible moves
                {currentRow - 1, currentCol}, //up
                {currentRow + 1, currentCol}, //down
                {currentRow, currentCol - 1}, //left
                {currentRow, currentCol + 1}, //right
        };

        int bestValue = Integer.MIN_VALUE;// to track the highest value among valid neighbors
        String bestMove = "None";// track the direction of the best move

        for(int[] move : moves) {// iterate through all possible moves
            int row = move[0];// row of the neighboring cell
            int col = move[1];// col of the neighboring cell
            //check if the move is within bounds of grid
            if(row >= 0 && col >= 0 && row < gridSize && col < gridSize) {
                if(!gameState.getVisitedButtons().contains(row+","+col)) {
                    // parse the button text to get the value of the cell
                    try{
                    int value = Integer.parseInt(gridButtons[row][col].getText());
                    if(value > bestValue) {// check if the move has the highest value so far
                        bestValue = value;// update the best value

                    //determine the move direction
                    if (row == currentRow - 1) bestMove = "Move up!";//if its above
                    else if (row == currentRow + 1) bestMove = "Move down!";//if below
                    else if (col == currentCol - 1) bestMove = "Move left!";//if to the left
                    else if (col == currentCol + 1) bestMove = "Move right!";//if to the right
                }
            } catch(NumberFormatException e) {
                    System.err.println("Error parsing value at("+ row +", "+ col + "): "+gridButtons[row][col].getText());
                    }
                }
        }
    }       //displays the best move as a hint to the player
        if(bestMove.equals("None")) {//no valid moves available
        JOptionPane.showMessageDialog(gridPanel, "No valid moves available!","Hint", JOptionPane.INFORMATION_MESSAGE);
        }else { //show the best move and it's value
            JOptionPane.showMessageDialog(gridPanel, "Hint: " + bestMove + " (Value: " + bestValue + ")", "Hint", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}