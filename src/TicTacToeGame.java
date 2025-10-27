import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGame extends Component implements ActionListener
{

    private final int MOVES_FOR_WIN = 5;
    private final int MOVES_FOR_TIE = 7;

    private String player = "X";
    private int moveCnt = 0;
    private boolean gameActive = true;

    public void TicTacToeGame(String[] args)
    {
        this.initializeGame();

    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        // Nothing clicked
        if (!gameActive || !(e.getSource() instanceof TicTacToeTile))
        {
            return;
        }

        // Get the row and col of clicked tile
        TicTacToeTile clickedTile = (TicTacToeTile) e.getSource();
        int row = clickedTile.getRow();
        int col = clickedTile.getCol();

        // Check if the move is valid
        if (!isValidMove(row, col))
        {
            JOptionPane.showMessageDialog(this, "Invalid move! Cell is already occupied.",
                    "Invalid Move", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Make move
        makeMove(clickedTile, row, col);

        // Check game state
        checkGameState();
    }

    private void makeMove(TicTacToeTile tile, int row, int col)
    {
        TicTacToeBoard.board[row][col] = player;
        tile.setText(player);
        moveCnt++;
        TicTacToeFrame.setPlayerText(player);;
    }

    private void checkGameState()
    {
        // The game should check for wins after each move
        if (moveCnt >= MOVES_FOR_WIN && isWin(player))
        {
            gameActive = false;
            // 3. Use JOptionPane to msg the user as needed for illegal moves
            // and when the game is won or tied, or the user quits.
            // Do not use any console (System.out.print…) output as this is a GUI program.
            JOptionPane.showMessageDialog(this,
                    "Player " + player + " wins!",
                    "Game Over",
                    JOptionPane.INFORMATION_MESSAGE);
            playAgain();
            return;
        }

        // Check for win
        if (moveCnt >= MOVES_FOR_WIN && isWin(player))
        {
            // 3. Use JOptionPane to msg the user as needed for illegal moves
            // and when the game is won or tied, or the user quits.
            // Do not use any console (System.out.print…) output as this is a GUI program.
            JOptionPane.showMessageDialog(this, "Player " + player + " wins!",
                    "Game Over", JOptionPane.INFORMATION_MESSAGE);
            TicTacToeBoard.clearBoard();
            return;
        }

        // Check for tie
        if (moveCnt >= MOVES_FOR_TIE && isTie())
        {
            // 3. Use JOptionPane to msg the user as needed for illegal moves
            // and when the game is won or tied, or the user quits.
            // Do not use any console (System.out.print…) output as this is a GUI program.
            JOptionPane.showMessageDialog(this, "It's a Tie!",
                    "Game Over", JOptionPane.INFORMATION_MESSAGE);
            TicTacToeBoard.clearBoard();
            return;
        }

        // and then switches to receive input from the other player.
        // Switch players
        player = player.equals("X") ? "O" : "X";
    }

    private void playAgain()
    {
        // 3. Use JOptionPane to msg the user as needed for illegal moves
        // and when the game is won or tied, or the user quits.
        // Do not use any console (System.out.print…) output as this is a GUI program.
        int response = JOptionPane.showConfirmDialog(this,
                "Would you like to play again?",
                "New Game",
                JOptionPane.YES_NO_OPTION);

        if (response == JOptionPane.YES_OPTION)
        {
            TicTacToeBoard.clearBoard();
        } else {
            System.exit(0);
        }
    }

    private boolean isValidMove(int row, int col)
    {
        boolean retVal = false;
        if(TicTacToeBoard.board[row][col].equals(" "))
            retVal = true;

        return retVal;
    }

    private boolean isWin(String player)
    {
        if(isColWin(player) || isRowWin(player) || isDiagonalWin(player))
        {
            return true;
        }
        return false;
    }

    private boolean isColWin(String player)
    {
        // checks for a col win for specified player
        for(int col=0; col < TicTacToeBoard.COL; col++)
        {
            if(TicTacToeBoard.board[0][col].equals(player) &&
                    TicTacToeBoard.board[1][col].equals(player) &&
                    TicTacToeBoard.board[2][col].equals(player))
            {
                return true;
            }
        }
        return false; // no col win
    }

    private boolean isRowWin(String player)
    {
        // checks for a row win for the specified player
        for(int row=0; row < TicTacToeBoard.ROW; row++)
        {
            if(TicTacToeBoard.board[row][0].equals(player) &&
                    TicTacToeBoard.board[row][1].equals(player) &&
                    TicTacToeBoard.board[row][2].equals(player))
            {
                return true;
            }
        }
        return false; // no row win
    }

    private boolean isDiagonalWin(String player)
    {
        // checks for a diagonal win for the specified player
        if(TicTacToeBoard.board[0][0].equals(player) &&
                TicTacToeBoard.board[1][1].equals(player) &&
                TicTacToeBoard.board[2][2].equals(player) )
        {
            return true;
        }
        if(TicTacToeBoard.board[0][2].equals(player) &&
                TicTacToeBoard.board[1][1].equals(player) &&
                TicTacToeBoard.board[2][0].equals(player) )
        {
            return true;
        }
        return false;
    }

    // checks for a tie before board is filled.
    private boolean isTie()
    {
        boolean xFlag = false;
        boolean oFlag = false;
        // Check all 8 win vectors for an X and O so
        // no win is possible
        // Check for row ties
        for(int row=0; row < TicTacToeBoard.ROW; row++)
        {
            if(TicTacToeBoard.board[row][0].equals("X") ||
                    TicTacToeBoard.board[row][1].equals("X") ||
                    TicTacToeBoard.board[row][2].equals("X"))
            {
                xFlag = true; // there is an X in this row
            }
            if(TicTacToeBoard.board[row][0].equals("O") ||
                    TicTacToeBoard.board[row][1].equals("O") ||
                    TicTacToeBoard.board[row][2].equals("O"))
            {
                oFlag = true; // there is an O in this row
            }

            if(! (xFlag && oFlag) )
            {
                return false; // No tie can still have a row win
            }

            xFlag = oFlag = false;

        }

        // Now scan the columns
        for(int col=0; col < TicTacToeBoard.COL; col++)
        {
            if(TicTacToeBoard.board[0][col].equals("X") ||
                    TicTacToeBoard.board[1][col].equals("X") ||
                    TicTacToeBoard.board[2][col].equals("X"))
            {
                xFlag = true; // there is an X in this col
            }
            if(TicTacToeBoard.board[0][col].equals("O") ||
                    TicTacToeBoard.board[1][col].equals("O") ||
                    TicTacToeBoard.board[2][col].equals("O"))
            {
                oFlag = true; // there is an O in this col
            }

            if(! (xFlag && oFlag) )
            {
                return false; // No tie can still have a col win
            }
        }

        // Now check for the diagonals
        xFlag = oFlag = false;

        if(TicTacToeBoard.board[0][0].equals("X") ||
                TicTacToeBoard.board[1][1].equals("X") ||
                TicTacToeBoard.board[2][2].equals("X") )
        {
            xFlag = true;
        }
        if(TicTacToeBoard.board[0][0].equals("O") ||
                TicTacToeBoard.board[1][1].equals("O") ||
                TicTacToeBoard.board[2][2].equals("O") )
        {
            oFlag = true;
        }
        if(! (xFlag && oFlag) )
        {
            return false; // No tie can still have a diag win
        }
        xFlag = oFlag = false;

        if(TicTacToeBoard.board[0][2].equals("X") ||
                TicTacToeBoard.board[1][1].equals("X") ||
                TicTacToeBoard.board[2][0].equals("X") )
        {
            xFlag =  true;
        }
        if(TicTacToeBoard.board[0][2].equals("O") ||
                TicTacToeBoard.board[1][1].equals("O") ||
                TicTacToeBoard.board[2][0].equals("O") )
        {
            oFlag =  true;
        }
        if(! (xFlag && oFlag) )
        {
            return false; // No tie can still have a diag win
        }

        // Checked every vector so I know I have a tie
        return true;
    }
    private void initializeGame()
    {
        TicTacToeBoard.clearBoard();

        player = "X";
        moveCnt = 0;
        gameActive = true;

        TicTacToeFrame.setPlayerText(player);
    }
}
