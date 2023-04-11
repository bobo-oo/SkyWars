package skyWars;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import java.awt.SystemColor;

public class SkyWarsGameBoardUI {

	private static JFrame frame;
	private static JPanel gridPanel;
	private static ArrayList<Row> rows = new ArrayList<>();
	private static final int ROWS = 4;
	private static final int SQUARES = 4;
	private static JButton btnStart;
	private SkyWarsGameBoard gameBoard = new SkyWarsGameBoard(); 
	private static JLabel lblScoresVal = new JLabel("0");
	private JButton btnReset, btnMove;
	private JButton btnOffensiveMode, btnDefensiveMode;
	private JLabel lblHighScoresVal;
	private JLabel lblScores;
	private JLabel lblHighScores;
	private JButton btnSave, btnLoad;
	
	private static String MASTER_SPACESHIP_PATH = "images/master_spaceship.png";
	private static String SHOOTER_SPACESHIP_PATH = "images/shooter_spaceship.png";
	private static String STAR_SPACESHIP_PATH = "images/star_spaceship.png";
	private static String CRUISER_SPACESHIP_PATH = "images/cruiser_spaceship.png";


	private static SaveLoad saveLoadLogic = new SaveLoad();
	private JLabel lblShooter;
	private JLabel lblCruiser;
	private JLabel lblStar;
	private JPanel panel_1;
	private JLabel lblPoints_1;
	private JLabel lblNewLabel;
	private JLabel lblPoints;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					SkyWarsGameBoardUI window = new SkyWarsGameBoardUI();
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
	public SkyWarsGameBoardUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
        frame.setBounds(100, 100, 700, 520);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setTitle("SkyWars");
        frame.setResizable(false);
        UIManager.put("Button.background", Color.WHITE); // set background color for all buttons

        // Initializing the grid panel and adding it to the frame
        gridPanel = new JPanel(new GridLayout(ROWS, SQUARES));
        gridPanel.setBounds(50, 50, 400, 400);
        gridPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        frame.getContentPane().add(gridPanel);

        // Register Observers to list of observers
        gameBoard.registerObserver(gameBoard.getScore());

        // Create rows and squares
        for (int i = 0; i < ROWS; i++) {
            Row row = new Row(i);
            rows.add(row);

            for (int j = 0; j < SQUARES; j++) {
            	
                Square square = new Square(j);
                row.getTheSquares().add(square);
                JPanel squarePanel = new JPanel();
                squarePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

                squarePanel.setBackground(Color.WHITE);;
                squarePanel.removeAll();
                gridPanel.add(squarePanel);
            }
        }
        btnMove= new JButton("Move");
        btnMove.setBackground(Color.WHITE);
        btnMove.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		clearUIGrids();
		        
		        List<Object> status = gameBoard.moveMasterSpaceship();
		        
		        ArrayList<Spaceship> newSpaceships = gameBoard.getSky().getListOfSpaceships();
		        for (Spaceship spaceship : newSpaceships) {
			        drawSpaceship(spaceship);
		        }
		        if ((int) status.get(0) == 2) {
		        	clearUIGrids();
		        	String message = "Game Over!!!";
		            JOptionPane.showMessageDialog(null, message, "Alert", JOptionPane.WARNING_MESSAGE);
		            btnMove.setEnabled(false);
		            btnStart.setEnabled(true);
				}
				else if ((int) status.get(0) == 1) {
					String message = "Hit Enemy!!!";
		            JOptionPane.showMessageDialog(null, message, "Success", JOptionPane.INFORMATION_MESSAGE);
					
				}
				else {
					System.out.println("No Conflict!!!");
				}
		        // Notify observers that the scores have been updated
				gameBoard.notifyObservers();
		        lblScoresVal.setText(Integer.toString(gameBoard.getScore().getScore()));
		        lblHighScoresVal.setText(Integer.toString(gameBoard.getScore().getHighScore()));
                
        	}
        });
        btnMove.setBounds(527, 328, 89, 23);
        btnMove.setEnabled(false);
        frame.getContentPane().add(btnMove);
        
		btnStart = new JButton("Start");
		btnStart.setBackground(Color.WHITE);
		btnStart.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	clearUIGrids();
		        gameBoard.play();
		        
		        ArrayList<Spaceship> newSpaceships = gameBoard.getSky().getListOfSpaceships();
		        for (Spaceship spaceship : newSpaceships) {
			        drawSpaceship(spaceship);
		        }
		        btnMove.setEnabled(true);
		        btnStart.setEnabled(false);

		    }
		});
        btnStart.setBounds(527, 294, 89, 23);
        frame.getContentPane().add(btnStart);       
        
        lblScoresVal.setBounds(572, 90, 49, 14);
        frame.getContentPane().add(lblScoresVal);
        
        btnReset = new JButton("Reset");
        btnReset.setBackground(Color.WHITE);
        btnReset.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnMove.setEnabled(false);
        		btnStart.setEnabled(true);
        		clearUIGrids();
        		gameBoard.reset();
        		
        		
        	}
        });
        btnReset.setBounds(527, 362, 89, 23);
        frame.getContentPane().add(btnReset);
        
        btnOffensiveMode = new JButton("Offensive Mode");
        btnOffensiveMode.setBackground(Color.WHITE);
        btnOffensiveMode.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		gameBoard.setGameMode(1);
                btnOffensiveMode.setEnabled(false);
                btnDefensiveMode.setEnabled(true);
        	}
        });
        btnOffensiveMode.setBounds(506, 114, 136, 23);
        btnOffensiveMode.setEnabled(false);
        frame.getContentPane().add(btnOffensiveMode);
        
        btnDefensiveMode = new JButton("Defensive Mode");
        btnDefensiveMode.setBackground(Color.WHITE);
        btnDefensiveMode.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		gameBoard.setGameMode(0);
        		btnDefensiveMode.setEnabled(false);
                btnOffensiveMode.setEnabled(true);
        	}
        });
        btnDefensiveMode.setBounds(506, 148, 136, 23);
        frame.getContentPane().add(btnDefensiveMode);
        
        lblHighScoresVal = new JLabel("0");
        lblHighScoresVal.setBounds(572, 65, 49, 14);
        frame.getContentPane().add(lblHighScoresVal);
        
        lblScores = new JLabel("Score:");
        lblScores.setHorizontalAlignment(SwingConstants.RIGHT);
        lblScores.setBounds(482, 90, 83, 14);
        frame.getContentPane().add(lblScores);
        
        lblHighScores = new JLabel("High Scores:");
        lblHighScores.setHorizontalAlignment(SwingConstants.RIGHT);
        lblHighScores.setBounds(482, 61, 83, 23);
        frame.getContentPane().add(lblHighScores);
        
        btnSave = new JButton("Save");
        btnSave.setBackground(Color.WHITE);
        btnSave.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
					saveLoadLogic.serializeGameLogicState(gameBoard);
					saveLoadLogic.saveHighScore(gameBoard.getScore().getHighScore());
				} catch (IOException e1) {					
					e1.printStackTrace();
				}
        	}
        });
        btnSave.setBounds(527, 396, 89, 23);
        frame.getContentPane().add(btnSave);
        
        btnLoad = new JButton("Load");
        btnLoad.setBackground(Color.WHITE);
        btnLoad.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.out.println("load clicked");
				File tmpDir = new File("SavedGame.txt");
				if (!tmpDir.exists()) {
					JOptionPane.showMessageDialog(null, "SaveGame.txt not found.\nSelect \"New Game\" instead.", "Save Not Found", JOptionPane.ERROR_MESSAGE);
				} else {
					clearUIGrids();
	        		gameBoard.reset();
					try {
						gameBoard = new SkyWarsGameBoard();
						gameBoard = saveLoadLogic.deserializeGameLogicState();
					} catch (ClassNotFoundException e1) {						
						e1.printStackTrace();
					} catch (IOException e1) {						
						e1.printStackTrace();
					}
					ArrayList<Spaceship> newSpaceships = gameBoard.getSky().getListOfSpaceships();
			        for (Spaceship spaceship : newSpaceships) {

				        drawSpaceship(spaceship);
			        }
					try {
						gameBoard.getScore().setHighScore(saveLoadLogic.loadHighScore());
					} catch (NumberFormatException | IOException e1) {
						e1.printStackTrace();
					}
					if (gameBoard.getGameMode() == 1) {
						btnOffensiveMode.setEnabled(false);
		                btnDefensiveMode.setEnabled(true);
					}
					else {
						btnDefensiveMode.setEnabled(false);
		                btnOffensiveMode.setEnabled(true);
					}
					gameBoard.setGameMode(gameBoard.getGameMode());
					lblScoresVal.setText(Integer.toString(gameBoard.getScore().getScore()));
			        lblHighScoresVal.setText(Integer.toString(gameBoard.getScore().getHighScore()));
				}	
        	}
        });
        btnLoad.setBounds(527, 427, 89, 23);
        frame.getContentPane().add(btnLoad);
        
        // Load image from file
        ImageIcon imageIcon = new ImageIcon("images/background.png");
        
        JPanel panel = new JPanel();
        panel.setBackground(UIManager.getColor("Button.background"));
        panel.setBounds(480, 54, 196, 56);
        frame.getContentPane().add(panel);
        // Set the layout manager to null to allow for absolute positioning of components
        frame.getContentPane().setLayout(null);
        
        ImageIcon iconShooter = new ImageIcon(SHOOTER_SPACESHIP_PATH);
        Image imgShooter = iconShooter.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon scaledIconShooter = new ImageIcon(imgShooter);
        lblShooter = new JLabel(scaledIconShooter);
        lblShooter.setHorizontalAlignment(SwingConstants.RIGHT);
        lblShooter.setBounds(497, 184, 30, 30);
        frame.getContentPane().add(lblShooter);
        
        ImageIcon iconStar = new ImageIcon(STAR_SPACESHIP_PATH);
        Image imgStar = iconStar.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon scaledIconStar = new ImageIcon(imgStar);
        lblStar = new JLabel(scaledIconStar);
        lblStar.setHorizontalAlignment(SwingConstants.RIGHT);
        lblStar.setBounds(497, 222, 30, 30);
        frame.getContentPane().add(lblStar);
        
        ImageIcon iconCruiser = new ImageIcon(CRUISER_SPACESHIP_PATH);
        Image imgCruiser = iconCruiser.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon scaledIconCruiser = new ImageIcon(imgCruiser);
        lblCruiser = new JLabel(scaledIconCruiser);
        lblCruiser.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCruiser.setBounds(497, 249, 30, 30);
        frame.getContentPane().add(lblCruiser);

        
        panel_1 = new JPanel();
        panel_1.setForeground(UIManager.getColor("Button.background"));
        panel_1.setBackground(SystemColor.menu);
        panel_1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panel_1.setBounds(482, 179, 194, 104);
        frame.getContentPane().add(panel_1);
        panel_1.setLayout(null);
        
        lblPoints_1 = new JLabel("= 25 points (Shooter)");
        lblPoints_1.setBounds(51, 11, 133, 14);
        panel_1.add(lblPoints_1);
        
        lblNewLabel = new JLabel("= 50 points (Star)");
        lblNewLabel.setBounds(51, 45, 133, 14);
        panel_1.add(lblNewLabel);
        
        lblPoints = new JLabel("= 75 points (Cruiser)");
        lblPoints.setBounds(51, 76, 133, 14);
        panel_1.add(lblPoints);
        
        // Create a label with the image
        JLabel background = new JLabel(imageIcon);
        // Set size of the label to the size of the frame
        background.setBounds(0, 0, 700, 483);
        // Add the label to the frame
        frame.getContentPane().add(background);
        
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(frame, "Do you want to save before quitting?", "Confirm Save", JOptionPane.YES_NO_OPTION);

                if (confirmed == JOptionPane.YES_OPTION) {
                	try {
    					saveLoadLogic.serializeGameLogicState(gameBoard);
    					saveLoadLogic.saveHighScore(gameBoard.getScore().getHighScore());
    				} catch (IOException e1) {					
    					e1.printStackTrace();
    				}
                }
                else {
                	frame.dispose();
                    System.exit(0);

                }
            }
		});
	}
	// method for drawing master ship
	private static void drawSpaceship(Spaceship spaceship) {
		
	    	int row = spaceship.getSpaceshipPositionY();
	        int col = spaceship.getSpaceshipPositionX(); // 0-indexed
	        System.out.println("drawSpaceship");
	        JPanel squarePanel = (JPanel) gridPanel.getComponent(row * SQUARES + col);
	        ImageIcon icon = new ImageIcon();
	        switch (spaceship.getSpaceshipTypeId()) {
	            case 0:
	                icon = new ImageIcon(MASTER_SPACESHIP_PATH);
	                break;
	            case 1:
	                icon = new ImageIcon(SHOOTER_SPACESHIP_PATH);
	                break;
	            case 2:
	                icon = new ImageIcon(STAR_SPACESHIP_PATH);
	                break;
	            case 3:
	                icon = new ImageIcon(CRUISER_SPACESHIP_PATH);
	                break;
	        }

	        Image img = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
	        ImageIcon scaledIcon = new ImageIcon(img);
	        JLabel iconLabel = new JLabel(scaledIcon);
	        JPanel panel = new JPanel();
	        panel.add(iconLabel);
	        squarePanel.add(panel);
        	frame.revalidate();
			frame.repaint();

	}
	private static void clearUIGrids() {
	    
	    for (int i = 0; i < ROWS; i++) {
	        
	        for (int j = 0; j < SQUARES; j++) {
	        	JPanel squarePanel = (JPanel) gridPanel.getComponent(i * SQUARES + j);
	            squarePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
	            squarePanel.removeAll(); //clear the panel
	        }
	    }
	}
}
