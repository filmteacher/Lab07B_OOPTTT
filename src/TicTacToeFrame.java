import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Tic Tac Toe game with a GUI
 *
 * @author Matt Bennett
 */

public class TicTacToeFrame extends JFrame
{
    JPanel mainPnl;
    JPanel topPnl;
    JPanel middlePnl;
    JPanel bottomPnl;

    JTextField textFld;

    JLabel titleLbl;

    JButton row0col0;
    JButton row0col1;
    JButton row0col2;
    JButton row1col0;
    JButton row1col1;
    JButton row1col2;
    JButton row2col0;
    JButton row2col1;
    JButton row2col2;

    JOptionPane msgBox;

    public TicTacToeFrame()
        {
           mainPnl = new JPanel();
           mainPnl.setLayout(new BorderLayout());

            createTopPanel();
            mainPnl.add(topPnl, BorderLayout.NORTH);

            createMiddlePanel();
            mainPnl.add(middlePnl, BorderLayout.CENTER);

            createBottomPanel();
            mainPnl.add(bottomPnl, BorderLayout.SOUTH);

            add(mainPnl);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

    private void createTopPanel() {
        topPnl = new JPanel();
        topPnl.setBackground(Color.LIGHT_GRAY);
        topPnl.setOpaque(true);
        topPnl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        titleLbl = new JLabel("Tic Tac Toe", JLabel.CENTER);
        titleLbl.setFont(new Font("Verdana", Font.BOLD, 32));
        titleLbl.setForeground(Color.BLACK);
        titleLbl.setVerticalTextPosition(JLabel.TOP);
        titleLbl.setHorizontalTextPosition(JLabel.CENTER);

        topPnl.add(titleLbl);
    }

    private void createMiddlePanel() {
        middlePnl = new JPanel();
        middlePnl.setBackground(Color.LIGHT_GRAY);
        middlePnl.setOpaque(true);
        middlePnl.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        textFld = new JTextField(20);
        textFld.setFont(new Font("Verdana", Font.BOLD, 14));
        textFld.setForeground(Color.BLACK);
        textFld.setEditable(false);
        middlePnl.add(textFld);
    }

    private void createBottomPanel() {
        bottomPnl = new JPanel();
        bottomPnl.setBackground(Color.LIGHT_GRAY);
        bottomPnl.setOpaque(true);
        bottomPnl.setLayout(new GridLayout(3, 3));
        bottomPnl.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        row0col0 = new TicTacToeTile(0, 0);
        row0col1 = new TicTacToeTile(0, 1);
        row0col2 = new TicTacToeTile(0, 2);
        row1col0 = new TicTacToeTile(1, 0);
        row1col1 = new TicTacToeTile(1, 1);
        row1col2 = new TicTacToeTile(1, 2);
        row2col0 = new TicTacToeTile(2, 0);
        row2col1 = new TicTacToeTile(2, 1);
        row2col2 = new TicTacToeTile(2, 2);

        bottomPnl.add(row0col0);
        bottomPnl.add(row0col1);
        bottomPnl.add(row0col2);
        bottomPnl.add(row1col0);
        bottomPnl.add(row1col1);
        bottomPnl.add(row1col2);
        bottomPnl.add(row2col0);
        bottomPnl.add(row2col1);
        bottomPnl.add(row2col2);
    }

// 2. The game is the same in every respect to the previous lab from CP I
// (where we refactored Tic Tac Toe console code from the text)
// except that there is now a GUI to handle the display of the game state
// and the input from the user.
// Starting with X each player alternates making a move by clicking on a square.
// The game blocks until a legal move is entered
// and then switches to receive input from the other player.
// The game should check for wins after each move
// starting with the 5th and should check for a tie (7th move).
//
// 3. Use JOptionPane to msg the user as needed for illegal moves
// and when the game is won or tied, or the user quits.
// Do not use any console (System.out.print…) output as this is a GUI program.
//
// 4. Use grid layout to create a 3 X 3 matrix of JButton objects
// for the Tic Tac Toe board.
// Provide a quit button as well.
// Hint: use an Array of JButton objects for the squares.
// IF you do this in a clever way as a 2D array,
// it will interface nicely with your previous code for the game.
// I suggest that you sub-class the JButton to create a TicTacToeButton class
// that holds the state of the button.
// There is a sample example of this TicTacToeTile.java
// and a test program TicTacToeTileTester.java)
//
// 5. You also want to create a single listener instance
// for all the Buttons on the board.
// It should determine the row col position of the Button
// and interface with the code for the game logic.

}
