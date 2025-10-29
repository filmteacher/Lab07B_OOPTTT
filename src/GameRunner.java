import javax.swing.JFrame;

/**
 * Tic Tac Toe game with a GUI refactored to be object-oriented
 *
 * @author Matt Bennett
 * @author Tom Wulf - original console game
 */

public class GameRunner
{
    public static void main(String[] args) {
        JFrame ticTacToeFrame = new TicTacToeFrame();
        ticTacToeFrame.setVisible(true);
    }
}