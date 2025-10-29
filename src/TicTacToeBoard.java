import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TicTacToeBoard {
    public static final int ROW = 3;
    public static final int COL = 3;

    private String[][] board = new String[ROW][COL];

    private TicTacToeTile[][] tiles = new TicTacToeTile[ROW][COL];

    private JPanel boardPanel;

    public TicTacToeBoard(ActionListener tileListener) {
        boardPanel = new JPanel();
        boardPanel.setBackground(Color.LIGHT_GRAY);
        boardPanel.setOpaque(true);
        boardPanel.setLayout(new GridLayout(ROW, COL));
        boardPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                tiles[row][col] = new TicTacToeTile(row, col);
                tiles[row][col].addActionListener(tileListener);
                boardPanel.add(tiles[row][col]);
            }
        }
        clearBoard();
    }

    public JPanel getBoardPanel() {
        return boardPanel;
    }

    public String getBoardValue(int row, int col) {
        return board[row][col];
    }

    public void setBoardValue(int row, int col, String player) {
        board[row][col] = player;
        tiles[row][col].setText(player);
    }

    public void clearBoard() {
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                board[row][col] = " ";
                tiles[row][col].setText("");
            }
        }
    }
}