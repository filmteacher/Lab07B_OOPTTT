import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Tic Tac Toe game with a GUI
 *
 * @author Matt Bennett
 * @author Tom Wulf - original console game
 */
public class TicTacToeFrame extends JFrame implements ActionListener
{
    private static final int ROW = 3;
    private static final int COL = 3;
    private final int MOVES_FOR_WIN = 5;
    private final int MOVES_FOR_TIE = 7;

    private String[][] board = new String[ROW][COL];
    private String player = "X";
    private int moveCnt = 0;
    private boolean gameActive = true;

    JPanel mainPnl;
    JPanel topPnl;
    JPanel middlePnl;
    JPanel bottomPnl;

    JTextField textFld;

    JLabel titleLbl;

    JOptionPane msgBox;

    // I suggest that you sub-class the JButton to create a TicTacToeButton class
    // that holds the state of the button.
    // There is a sample example of this TicTacToeTile.java
    // and a test program TicTacToeTileTester.java)
    TicTacToeTile[][] tiles = new TicTacToeTile[ROW][COL];

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

        // Initialize the game
        clearBoard();
        textFld.setText("Enter move for " + player);
    }

    private void createTopPanel()
    {
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

    private void createMiddlePanel()
    {
        middlePnl = new JPanel();
        middlePnl.setBackground(Color.LIGHT_GRAY);
        middlePnl.setOpaque(true);
        middlePnl.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        textFld = new JTextField(14);
        textFld.setText(" ");
        textFld.setFont(new Font("Verdana", Font.BOLD, 14));
        textFld.setForeground(Color.BLACK);
        textFld.setHorizontalAlignment(SwingConstants.CENTER);
        textFld.setEditable(false);
        middlePnl.add(textFld);
    }

    private void createBottomPanel()
    {
        bottomPnl = new JPanel();
        bottomPnl.setBackground(Color.LIGHT_GRAY);
        bottomPnl.setOpaque(true);
        bottomPnl.setLayout(new GridLayout(3, 3));
        bottomPnl.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

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
                bottomPnl.add(tiles[row][col]);
            }
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
        if (!gameActive || !(e.getSource() instanceof TicTacToeTile))
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
        if (!isValidMove(row, col))
        {
            // 3. Use JOptionPane to msg the user as needed for illegal moves
            // and when the game is won or tied, or the user quits.
            // Do not use any console (System.out.print…) output as this is a GUI program.
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
        board[row][col] = player;
        tile.setText(player);
        moveCnt++;
        textFld.setText("Enter move for " + (player.equals("X") ? "O" : "X") + ".");
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
            clearBoard();
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
            clearBoard();
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
            clearBoard();
        } else {
            dispose();
        }
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
        player = "X";
        moveCnt = 0;
        gameActive = true;
        textFld.setText("Enter move for " + player + ".");
    }

    private boolean isValidMove(int row, int col)
    {
        boolean retVal = false;
        if(board[row][col].equals(" "))
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
        for(int col=0; col < COL; col++)
        {
            if(board[0][col].equals(player) &&
                    board[1][col].equals(player) &&
                    board[2][col].equals(player))
            {
                return true;
            }
        }
        return false; // no col win
    }

    private boolean isRowWin(String player)
    {
        // checks for a row win for the specified player
        for(int row=0; row < ROW; row++)
        {
            if(board[row][0].equals(player) &&
                    board[row][1].equals(player) &&
                    board[row][2].equals(player))
            {
                return true;
            }
        }
        return false; // no row win
    }

    private boolean isDiagonalWin(String player)
    {
        // checks for a diagonal win for the specified player
        if(board[0][0].equals(player) &&
                board[1][1].equals(player) &&
                board[2][2].equals(player) )
        {
            return true;
        }
        if(board[0][2].equals(player) &&
                board[1][1].equals(player) &&
                board[2][0].equals(player) )
        {
            return true;
        }
        return false;
    }

    // starting with the 5th and should check for a tie (7th move).
    // checks for a tie before board is filled.
    // check for the win first to be efficient
    private boolean isTie()
    {
        boolean xFlag = false;
        boolean oFlag = false;
        // Check all 8 win vectors for an X and O so
        // no win is possible
        // Check for row ties
        for(int row=0; row < ROW; row++)
        {
            if(board[row][0].equals("X") ||
                    board[row][1].equals("X") ||
                    board[row][2].equals("X"))
            {
                xFlag = true; // there is an X in this row
            }
            if(board[row][0].equals("O") ||
                    board[row][1].equals("O") ||
                    board[row][2].equals("O"))
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
        for(int col=0; col < COL; col++)
        {
            if(board[0][col].equals("X") ||
                    board[1][col].equals("X") ||
                    board[2][col].equals("X"))
            {
                xFlag = true; // there is an X in this col
            }
            if(board[0][col].equals("O") ||
                    board[1][col].equals("O") ||
                    board[2][col].equals("O"))
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

        if(board[0][0].equals("X") ||
                board[1][1].equals("X") ||
                board[2][2].equals("X") )
        {
            xFlag = true;
        }
        if(board[0][0].equals("O") ||
                board[1][1].equals("O") ||
                board[2][2].equals("O") )
        {
            oFlag = true;
        }
        if(! (xFlag && oFlag) )
        {
            return false; // No tie can still have a diag win
        }
        xFlag = oFlag = false;

        if(board[0][2].equals("X") ||
                board[1][1].equals("X") ||
                board[2][0].equals("X") )
        {
            xFlag =  true;
        }
        if(board[0][2].equals("O") ||
                board[1][1].equals("O") ||
                board[2][0].equals("O") )
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
}