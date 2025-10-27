import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Toolkit;

public class TicTacToeFrame extends JFrame
{
    JPanel mainPnl;
    JPanel titlePnl;
    JPanel msgPnl;
    static JPanel boardPnl;

    JTextField textFld;

    JLabel titleLbl;

    JOptionPane msgBox;

    public TicTacToeFrame()
    {
        this.setTitle("Tic Tac Toe");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int frameWidth = (int) (screenSize.width * 0.20);
        int frameHeight = 500;
        this.setSize(frameWidth, frameHeight);

        int x = (screenSize.width - frameWidth) / 2;
        int y = (screenSize.height - frameHeight) / 2;
        this.setLocation(x, y);

        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());

        createTopPanel();
        mainPnl.add(titlePnl, BorderLayout.NORTH);

        createMiddlePanel();
        mainPnl.add(msgPnl, BorderLayout.CENTER);

        createBottomPanel();
        mainPnl.add(boardPnl, BorderLayout.SOUTH);

        add(mainPnl);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createTopPanel()
    {
        titlePnl = new JPanel();
        titlePnl.setBackground(Color.LIGHT_GRAY);
        titlePnl.setOpaque(true);
        titlePnl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        titleLbl = new JLabel("Tic Tac Toe", JLabel.CENTER);
        titleLbl.setFont(new Font("Verdana", Font.BOLD, 32));
        titleLbl.setForeground(Color.BLACK);
        titleLbl.setVerticalTextPosition(JLabel.TOP);
        titleLbl.setHorizontalTextPosition(JLabel.CENTER);

        titlePnl.add(titleLbl);
    }

    private void createMiddlePanel()
    {
        msgPnl = new JPanel();
        msgPnl.setBackground(Color.LIGHT_GRAY);
        msgPnl.setOpaque(true);
        msgPnl.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        textFld = new JTextField(14);
        textFld.setText(" ");
        textFld.setFont(new Font("Verdana", Font.BOLD, 14));
        textFld.setForeground(Color.BLACK);
        textFld.setHorizontalAlignment(SwingConstants.CENTER);
        textFld.setEditable(false);
        msgPnl.add(textFld);
    }

    private void createBottomPanel()
    {
        boardPnl = new JPanel();
        boardPnl.setBackground(Color.LIGHT_GRAY);
        boardPnl.setOpaque(true);
        boardPnl.setLayout(new GridLayout(3, 3));
        boardPnl.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    public void setPlayerText(String player) {
        msgPnl.textFld.setText("Enter move for " + player + ".");
    }
}