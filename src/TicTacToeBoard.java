import javax.swing.*;
import java.awt.event.ActionListener;

public class TicTacToeBoard implements ActionListener
{
    public static final int ROW = 3;
    public static final int COL = 3;
    public static String[][] board = new String[ROW][COL];

    static TicTacToeTile[][] tiles = new TicTacToeTile[ROW][COL];

    public void TicTacToeBoard(String[] args) {
        // builds board
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                tiles[row][col] = new TicTacToeTile(row, col);
                tiles[row][col].addActionListener(this);
                TicTacToeFrame.boardPnl.add(tiles[row][col]);
            }
        }
    }

    public static void clearBoard() {
        for (int row = 0; row < ROW; row++)
        {
            for (int col = 0; col < COL; col++)
            {
                TicTacToeBoard.board[row][col] = " ";
                TicTacToeBoard.tiles[row][col].setText("");
            }
        }
    }
}
