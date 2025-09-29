import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

public class TicTacToeRunner
{
    public static void main(String[] args) {
        JFrame ticTacToeFrame = new TicTacToeFrame();
        ticTacToeFrame.setTitle("Tic Tac Toe");
        ticTacToeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int frameWidth = (int) (screenSize.width * 0.20);
        int frameHeight = 500;
        ticTacToeFrame.setSize(frameWidth, frameHeight);

        int x = (screenSize.width - frameWidth) / 2;
        int y = (screenSize.height - frameHeight) / 2;
        ticTacToeFrame.setLocation(x, y);

        ticTacToeFrame.setVisible(true);
    }
}