import javax.swing.*;
import java.awt.*;

public class TicTacToeGame {

    final int MOVES_FOR_WIN = 5;
    final int MOVES_FOR_TIE = 7;

    String player = "X";
    int moveCnt = 0;

    public TicTacToeFrame frame;
    public TicTacToeBoard board;

    public TicTacToeGame(TicTacToeBoard board, TicTacToeFrame frame)
    {
        this.board = board;
        this.frame = frame;
        this.initializeGame();
    }

    public void makeMove(int row, int col)
    {
        board.setBoardValue(row, col, player);
        moveCnt++;

        if (moveCnt >= MOVES_FOR_WIN && isWin(player))
        {
            JOptionPane.showMessageDialog(frame,
                    "Player " + player + " wins!",
                    "Game Over",
                    JOptionPane.INFORMATION_MESSAGE);
            board.clearBoard();
            playAgain();
            return;
        }

        if (moveCnt >= MOVES_FOR_TIE && isTie())
        {
            JOptionPane.showMessageDialog(frame,
                    "It's a Tie!",
                    "Game Over",
                    JOptionPane.INFORMATION_MESSAGE);
            board.clearBoard();
            playAgain();
            return;
        }

        player = player.equals("X") ? "O" : "X";
        frame.setPlayerText(player);
    }

    private void playAgain()
    {
        int response = JOptionPane.showConfirmDialog(frame,
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
        if(board.getBoardValue(0, 0).equals(player) &&
                board.getBoardValue(1, 1).equals(player) &&
                board.getBoardValue(2, 2).equals(player) )
        {
            return true;
        }
        if(board.getBoardValue(0, 2).equals(player) &&
                board.getBoardValue(1, 1).equals(player) &&
                board.getBoardValue(2, 0).equals(player) )
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
            if(board.getBoardValue(row, 0).equals("X") ||
                    board.getBoardValue(row, 1).equals("X") ||
                    board.getBoardValue(row, 2).equals("X"))
            {
                xFlag = true; // there is an X in this row
            }
            if(board.getBoardValue(row, 0).equals("O") ||
                    board.getBoardValue(row, 1).equals("O") ||
                    board.getBoardValue(row, 2).equals("O"))
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
            if(board.getBoardValue(0, col).equals("X") ||
                    board.getBoardValue(1, col).equals("X") ||
                    board.getBoardValue(2, col).equals("X"))
            {
                xFlag = true; // there is an X in this col
            }
            if(board.getBoardValue(0, col).equals("O") ||
                    board.getBoardValue(1, col).equals("O") ||
                    board.getBoardValue(2, col).equals("O"))
            {
                oFlag = true; // there is an O in this col
            }

            if(! (xFlag && oFlag) )
            {
                return false; // No tie can still have a col win
            }
        }

        xFlag = oFlag = false;

        if(board.getBoardValue(0, 0).equals("X") ||
                board.getBoardValue(1, 1).equals("X") ||
                board.getBoardValue(2, 2).equals("X") )
        {
            xFlag = true;
        }
        if(board.getBoardValue(0, 0).equals("O") ||
                board.getBoardValue(1, 1).equals("O") ||
                board.getBoardValue(2, 2).equals("O") )
        {
            oFlag = true;
        }
        if(! (xFlag && oFlag) )
        {
            return false; // No tie can still have a diag win
        }
        xFlag = oFlag = false;

        if(board.getBoardValue(0, 2).equals("X") ||
                board.getBoardValue(1, 1).equals("X") ||
                board.getBoardValue(2, 0).equals("X") )
        {
            xFlag =  true;
        }
        if(board.getBoardValue(0, 2).equals("O") ||
                board.getBoardValue(1, 1).equals("O") ||
                board.getBoardValue(2, 0).equals("O") )
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
        player = "X";
        moveCnt = 0;
        board.clearBoard();
        frame.setPlayerText("X");
    }
}
