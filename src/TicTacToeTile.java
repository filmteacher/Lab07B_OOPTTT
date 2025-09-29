
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author wulft
 * @author Matt Bennett - added display options
 */
public class TicTacToeTile extends JButton
{
    private int row;
    private int col;

    public TicTacToeTile(int row, int col) {
        super();
        this.row = row;
        this.col = col;

        setPreferredSize(new Dimension(100, 100));
        setFont(new Font("Verdana", Font.BOLD, 48));
        setForeground(Color.BLACK);
        setVerticalTextPosition(JLabel.CENTER);
        setHorizontalTextPosition(JLabel.CENTER);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

}
