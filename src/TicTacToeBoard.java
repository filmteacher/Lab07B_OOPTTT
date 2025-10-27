import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeBoard implements ActionListener
{
    private static final int ROW = 3;
    private static final int COL = 3;
    private String[][] board = new String[ROW][COL];

    TicTacToeTile[][] tiles = new TicTacToeTile[ROW][COL];

    // 4. Use grid layout to create a 3 X 3 matrix of JButton objects
    // for the Tic Tac Toe board.
    // Provide a quit button as well.
    // Hint: use an Array of JButton objects for the squares.
    // IF you do this in a clever way as a 2D array,
    // it will interface nicely with your previous code for the game.
        for (int row = 0; row < 3; row++)
    {
        for (int col = 0; col < 3; col++)
        {
            tiles[row][col] = new TicTacToeTile(row, col);
            tiles[row][col].addActionListener(this);
            TicTacToeFrame.bottomPnl.add(tiles[row][col]);
        }
    }

    // 5. You also want to create a single listener instance
    // for all the Buttons on the board.
    // It should determine the row col position of the Button
    // and interface with the code for the game logic.
    @Override
    public void actionPerformed(ActionEvent e)
    {
        // Nothing clicked
        if (!TicTacToeGame.gameActive || !(e.getSource() instanceof TicTacToeTile))
        {
            return;
        }
        // 2. The game is the same in every respect to the previous lab from CP I
        // (where we refactored Tic Tac Toe console code from the text)
        // except that there is now a GUI to handle the display of the game state
        // and the input from the user.
        // Starting with X each player alternates making a move by clicking on a square.

        // Get the row and col of clicked tile
        TicTacToeTile clickedTile = (TicTacToeTile) e.getSource();
        int row = clickedTile.getRow();
        int col = clickedTile.getCol();

        // The game blocks until a legal move is entered
        // Check if the move is valid
        if (!TicTacToeGame.isValidMove(row, col))
        {
            // 3. Use JOptionPane to msg the user as needed for illegal moves
            // and when the game is won or tied, or the user quits.
            // Do not use any console (System.out.print…) output as this is a GUI program.
            JOptionPane.showMessageDialog(this, "Invalid move! Cell is already occupied.",
                    "Invalid Move", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Make move
        TicTacToeGame.makeMove(clickedTile, row, col);

        // Check game state
        TicTacToeGame.checkGameState();
    }

    private void clearBoard()
    {
        for (int row = 0; row < ROW; row++)
        {
            for (int col = 0; col < COL; col++)
            {
                board[row][col] = " ";
                tiles[row][col].setText("");
            }
        }
    }

}
