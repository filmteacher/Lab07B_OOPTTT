import javax.swing.*;
import java.awt.*;

public class TicTacToeGame extends Component {

    final int MOVES_FOR_WIN = 5;
    final int MOVES_FOR_TIE = 7;

    String player = "X";
    int moveCnt = 0;

    public TicTacToeFrame frame;
    private TicTacToeBoard board;

    public TicTacToeGame(TicTacToeBoard board, TicTacToeFrame frame)
    {
        this.board = board;
        this.frame = frame;
        this.initializeGame();
    }

    public void makeMove(TicTacToeTile tile, int row, int col)
    {
        board.setBoardValue(row, col, player);
        moveCnt++;

        //checks for win
        if (moveCnt >= MOVES_FOR_WIN && isWin(player))
        {
            JOptionPane.showMessageDialog(this,
                    "Player " + player + " wins!",
                    "Game Over",
                    JOptionPane.INFORMATION_MESSAGE);
            frame.clearBoard();
            playAgain();
            return;
        }

        // checks for tie
        if (moveCnt >= MOVES_FOR_TIE && isTie())
        {
            JOptionPane.showMessageDialog(this,
                    "It's a Tie!",
                    "Game Over",
                    JOptionPane.INFORMATION_MESSAGE);
            frame.clearBoard();
            playAgain();
            return;
        }

        player = player.equals("X") ? "O" : "X";
        frame.setPlayerText(player);
    }

    private void playAgain()
    {
        int response = JOptionPane.showConfirmDialog(this,
                "Would you like to play again?",
                "New Game",
                JOptionPane.YES_NO_OPTION);

        if (response == JOptionPane.YES_OPTION)
        {
            initializeGame();
        } else {
            System.exit(0);
        }
    }

    public boolean isValidMove(int row, int col)
    {
        return board.getBoardValue(row, col).equals(" ");
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
        for(int col=0; col < TicTacToeBoard.COL; col++)
        {
            if(board.getBoardValue(0, col).equals(player) &&
                    board.getBoardValue(1, col).equals(player) &&
                    board.getBoardValue(2, col).equals(player))
            {
                return true;
            }
        }
        return false;
    }

    private boolean isRowWin(String player)
    {
        for(int row = 0; row < TicTacToeBoard.ROW; row++)
        {
            if(board.getBoardValue(row, 0).equals(player) &&
                    board.getBoardValue(row, 1).equals(player) &&
                    board.getBoardValue(row, 2).equals(player))
            {
                return true;
            }
        }
        return false;
    }

    private boolean isDiagonalWin(String player)
    {
        if(frame.board[0][0].equals(player) &&
                frame.board[1][1].equals(player) &&
                frame.board[2][2].equals(player) )
        {
            return true;
        }
        if(frame.board[0][2].equals(player) &&
                frame.board[1][1].equals(player) &&
                frame.board[2][0].equals(player) )
        {
            return true;
        }
        return false;
    }

    private boolean isTie()
    {
        boolean xFlag = false;
        boolean oFlag = false;

        for(int row=0; row < TicTacToeBoard.ROW; row++)
        {
            if(frame.board[row][0].equals("X") ||
                    frame.board[row][1].equals("X") ||
                    frame.board[row][2].equals("X"))
            {
                xFlag = true; // there is an X in this row
            }
            if(frame.board[row][0].equals("O") ||
                    frame.board[row][1].equals("O") ||
                    frame.board[row][2].equals("O"))
            {
                oFlag = true; // there is an O in this row
            }

            if(! (xFlag && oFlag) )
            {
                return false; // No tie can still have a row win
            }

            xFlag = oFlag = false;

        }

        for(int col=0; col < TicTacToeBoard.COL; col++)
        {
            if(frame.board[0][col].equals("X") ||
                    frame.board[1][col].equals("X") ||
                    frame.board[2][col].equals("X"))
            {
                xFlag = true; // there is an X in this col
            }
            if(frame.board[0][col].equals("O") ||
                    frame.board[1][col].equals("O") ||
                    frame.board[2][col].equals("O"))
            {
                oFlag = true; // there is an O in this col
            }

            if(! (xFlag && oFlag) )
            {
                return false; // No tie can still have a col win
            }
        }

        xFlag = oFlag = false;

        if(frame.board[0][0].equals("X") ||
                frame.board[1][1].equals("X") ||
                frame.board[2][2].equals("X") )
        {
            xFlag = true;
        }
        if(frame.board[0][0].equals("O") ||
                frame.board[1][1].equals("O") ||
                frame.board[2][2].equals("O") )
        {
            oFlag = true;
        }
        if(! (xFlag && oFlag) )
        {
            return false; // No tie can still have a diag win
        }
        xFlag = oFlag = false;

        if(frame.board[0][2].equals("X") ||
                frame.board[1][1].equals("X") ||
                frame.board[2][0].equals("X") )
        {
            xFlag =  true;
        }
        if(frame.board[0][2].equals("O") ||
                frame.board[1][1].equals("O") ||
                frame.board[2][0].equals("O") )
        {
            oFlag =  true;
        }
        if(! (xFlag && oFlag) )
        {
            return false;
        }

        return true;
    }

    private void initializeGame()
    {
        frame.clearBoard();

        player = "X";
        moveCnt = 0;
    }
}
