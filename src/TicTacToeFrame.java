import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeFrame extends JFrame implements ActionListener
{
    JPanel mainPnl;
    JPanel topPnl;
    JPanel middlePnl;
    JPanel bottomPnl;

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
        mainPnl.add(topPnl, BorderLayout.NORTH);

        createMiddlePanel();
        mainPnl.add(middlePnl, BorderLayout.CENTER);

        createBottomPanel();
        mainPnl.add(bottomPnl, BorderLayout.SOUTH);

        add(mainPnl);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
    }

    private void setPlayerText(String player)
    {
        textFld.setText("Enter move for " + player + ".");
    }
}