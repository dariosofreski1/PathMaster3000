import javax.swing.*;
import java.awt.*;

public class GameMenu {//class for managing the menu
    private final GameState gameState;//reference to the game state
    private final PathMaster3000 mainApp;// reference to the main app
    private final GameGrid gameGrid;// reference to the game grid
    private JMenuBar menuBar;// menu bar for the app

        //constructor that sets up the game menu
    public GameMenu(GameState gameState, PathMaster3000 mainApp, GameGrid gameGrid) {
        this.gameState = gameState;//link game state
        this.mainApp = mainApp;// link the main app
        this.gameGrid = gameGrid;//link the game grid
        initializeMenu();// set up the menu and menu items
    }

    public JMenuBar getMenuBar() {// returns the menu to add it to the main frame
        return menuBar;
    }

    private void initializeMenu() {// initializes menu and it's items
        menuBar = new JMenuBar();//create the menu bar

        JMenu optionsMenu = new JMenu("Options");//create options menu
        JMenuItem restartItem = new JMenuItem("Restart Game");//add restart game
        restartItem.addActionListener(e -> mainApp.restartGame());//restart game when clicked
        JMenuItem resizeGridItem = new JMenuItem("Change Grid Size");//add item to change grid size
        resizeGridItem.addActionListener(e -> handleResizeGrid());//open a window to resize grid
        optionsMenu.add(restartItem);//add both items to the menu
        optionsMenu.add(resizeGridItem);

        JMenu appearanceMenu = new JMenu("Customize Appearance");//create a customize appearence menu
        JMenuItem pathColorItem = new JMenuItem("Change Path Color");// add item to change path color
        pathColorItem.addActionListener(e -> handleChangePathColor());//// Open a color chooser for the path color
        JMenuItem gameColorItem = new JMenuItem("Change Game Color");//add item to change game color
        gameColorItem.addActionListener(e -> handleChangeGameColor());// Open a color chooser for the game color
        appearanceMenu.add(pathColorItem);//add both to the appearance menu
        appearanceMenu.add(gameColorItem);

        menuBar.add(optionsMenu);//add options to the menu
        menuBar.add(appearanceMenu);// add appearance to the menu
    }

    private void handleResizeGrid() {//method to handle the change the grid action
        String input = JOptionPane.showInputDialog(null, "Enter new grid size (n x n): ");
        //dialog to prompt the user to enter a new grid size
        try {
            int newSize = Integer.parseInt(input);// Parse the input as an integer
            if (newSize > 1){//ensure the grid size is valid
                mainApp.resizeGrid(newSize);//resize the grid in the main app
            } else {
                JOptionPane.showMessageDialog(null, "Grid size must be greater than 1.");
            }
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Grid size must be an integer.");
        }
    }
    private void handleChangePathColor() {// method to handle the change color of the path
        // Open a color chooser dialog to select the new path color
        Color newColor = JColorChooser.showDialog(null, "Choose Path Color", gameState.getPathColor());
        if (newColor != null){// If the user selected a color
            gameState.setPathColor(newColor);// update the path color in the game state
        }
    }

    private void handleChangeGameColor() {// handles the change game color
        // Open a color chooser dialog to select the new game color
        Color newColor = JColorChooser.showDialog(null, "Choose Game Color", gameState.getGameColor());
        if (newColor != null) {// If the user selected a color
            gameState.setGameColor(newColor);//update the game color in the game state

            for (String position : gameState.getVisitedButtons()) {
                String[] parts = position.split(",");
                int row = Integer.parseInt(parts[0]);
                int col = Integer.parseInt(parts[1]);
                gameGrid.getButton(row, col).setBackground(newColor);
            }
            gameGrid.getButton(0, 0).setBackground(newColor);
        }
    }

}
