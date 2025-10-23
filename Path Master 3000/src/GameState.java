import java.awt.*;
import java.util.HashSet;

public class GameState {//class to manage the current state of the game
    private int gridSize = 3;//size of the grid(starting with 3x3)
    private int currentRow = 0;//current position of the player(row)
    private int currentCol = 0;//current position of the player(col)
    private HashSet<String> visitedButtons = new HashSet<>();//tracks visited buttons
    private int scoresum = 0;//total score
    private int pathLength = 0;//number of steps
    private Color pathColor = Color.GREEN;//color of the path
    private Color gameColor = Color.CYAN;//color of the game

    //getters and setters for all variables of the game

    public int getGridSize(){
        return gridSize;
    }
    public void setGridSize(int gridSize){
        this.gridSize = gridSize;
    }
    public int getCurrentRow(){
        return currentRow;
    }
    public int getCurrentCol(){
        return currentCol;
    }
    public void setCurrentPosition(int row, int col){
        this.currentRow = row;
        this.currentCol = col;
    }

    public HashSet<String> getVisitedButtons(){
        return visitedButtons;
    }

    public int getScoresum(){
        return scoresum;
    }

    public void addToScoreSum(int score){
        scoresum += score;//add the value of a clicked button to the score
    }

    public int getPathLength(){
        return pathLength;
    }

    public void incrementPathLength(){
        pathLength++;//increment the path length when new button is clicked
    }

    public Color getPathColor(){
        return pathColor;
    }

    public void setPathColor(Color pathColor){
        this.pathColor = pathColor;
    }

    public Color getGameColor(){
        return gameColor;
    }

    public void setGameColor(Color gameColor){
        this.gameColor = gameColor;
    }

    public void resetGameState(){// resets game to the initial values
        currentRow = 0;
        currentCol = 0;
        visitedButtons.clear();
        visitedButtons.add("0,0");
        scoresum = 0;
        pathLength = 0;
    }
}
