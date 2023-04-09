package skyWars;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import javax.swing.*;

public class SkyWarsUI {

	private JFrame frame;
	private JPanel gridPanel;
	private ArrayList<Row> rows = new ArrayList<>();
	private final int ROWS = 4;
	private final int SQUARES = 4;
	private JButton btnPlay;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SkyWarsUI window = new SkyWarsUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SkyWarsUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
        frame.setBounds(100, 100, 650, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        gridPanel = new JPanel(new GridLayout(ROWS, SQUARES));
        gridPanel.setBounds(50, 50, 400, 400);
        gridPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        frame.getContentPane().add(gridPanel);

        // Create rows and squares
        for (int i = 0; i < ROWS; i++) {
            Row row = new Row(i);
            rows.add(row);

            for (int j = 0; j < SQUARES; j++) {
                Square square = new Square(j);
                row.getTheSquares().add(square);
                JPanel squarePanel = new JPanel();
                squarePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

                // Create custom panel with image and text
                ImageIcon icon = new ImageIcon("spaceship.png");
                JLabel label = new JLabel("Spaceship " + (i*SQUARES + j + 1));
                label.setForeground(Color.WHITE);
                JPanel panel = new JPanel();
                panel.setBackground(Color.BLACK);
                panel.add(new JLabel(icon));
                panel.add(label);
                squarePanel.add(panel);

                gridPanel.add(squarePanel);
            }
        }
		btnPlay = new JButton("Play");
		btnPlay.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        BattleShips battleShips = new BattleShips();
		        battleShips.play();
		    }
		});
        btnPlay.setBounds(511, 167, 89, 23);
        frame.getContentPane().add(btnPlay);
	}

}
