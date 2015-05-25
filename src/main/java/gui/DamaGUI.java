package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.util.*;

import model.*;
import controller.*;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import pkg.Main;







import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DamaGUI extends JFrame {
	private static Logger	logger = LoggerFactory.getLogger(DamaGUI.class);
	private JPanel 	contentPane;
	private JPanel 	damaField;
	private JLabel 	notification;
	private static List<DamaButton>	buttons;
	public int 	buttonSize;
	public int 	offset;
	private static Game	g;
	private static DamaService ds;
	private static DamaGUI frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new DamaGUI();
					frame.setVisible(true);
					frame.setResizable(false);
					frame.setTitle("Dáma");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public DamaGUI() {
		this.buttonSize = 56;
		this.offset = 25;
		g = new Game();
		ds = new DamaService();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				ds.closeDatabaseConnection();
			}
		});
		
		addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					ds.closeDatabaseConnection();
					System.exit(0);
				}
			}
		});
		setBounds(0, 0, ((buttonSize * 8) + (offset * 2)) + 0,
				((buttonSize * 9) + (offset * 2))+offset+20);
		
		JMenuBar menuBar = new JMenuBar();
//		menuBar.add(Box.createRigidArea(new Dimension(100,25)));
		setJMenuBar(menuBar);
		
		JMenu mnApplication = new JMenu("Application");
		menuBar.add(mnApplication);
		
		JMenuItem mntmNewGame = new JMenuItem("New Game");
		mntmNewGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				g.startNewGame();
				clearPreviousClickedButton();
				updateButtonIcons();
			}
		});
		mnApplication.add(mntmNewGame);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}
		});
		
		JMenuItem mntmSaveToDatabase = new JMenuItem("Save to database");
		mntmSaveToDatabase.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				ds.uploadGameState(g);
			}
		});
		mnApplication.add(mntmSaveToDatabase);
		
		JMenuItem mntmLoadFromDatabase = new JMenuItem("Load from database");
		mntmLoadFromDatabase.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				g.startNewGame();
				g.clearTable();
				ds.downloadGameState(g);
				updateButtonIcons();
				notification.setText(g.getActualPlayer().getName() + "'s turn");
			}
		});
		mnApplication.add(mntmLoadFromDatabase);
		mnApplication.add(mntmExit);
		
		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
		
		JMenuItem mntmAuthor = new JMenuItem("Author");
		mntmAuthor.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new About();
			}
		});
		mnAbout.add(mntmAuthor);
		contentPane = new JPanel();
		/*contentPane.setBounds(0, 0, ((buttonSize * 8) + (offset * 2)),
				((buttonSize * 8) + (offset * 2)));*/
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.setBackground(Color.BLACK);
		setContentPane(contentPane);
		
		
		
		damaField = new JPanel(new GridLayout(8, 8, 1, 1));
		damaField.setBorder(new EmptyBorder(2, 2, 2, 2));
		damaField.setBounds(offset, offset, ((buttonSize * 8)),
				((buttonSize * 8)));
		damaField.setBackground(Color.LIGHT_GRAY);
		contentPane.add(damaField);
		
		notification = new JLabel(g.getActualPlayer().getName() + " starts the game!");
		notification.setHorizontalAlignment(SwingConstants.CENTER);
		notification.setForeground(Color.LIGHT_GRAY);
//		notification.setBounds(20, 441, buttonSize*8, 15);
		notification.setBounds(offset, offset + 8*buttonSize + (int)(buttonSize/3), buttonSize*8, 15);
		contentPane.add(notification);
		
		JLabel label = new JLabel("0");
		label.setForeground(Color.LIGHT_GRAY);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(26, 12, 47, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("1");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.LIGHT_GRAY);
		label_1.setBounds(85, 12, 47, 15);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("2");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(Color.LIGHT_GRAY);
		label_2.setBounds(144, 12, 47, 15);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("3");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setForeground(Color.LIGHT_GRAY);
		label_3.setBounds(203, 12, 40, 15);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("4");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setForeground(Color.LIGHT_GRAY);
		label_4.setBounds(255, 12, 47, 15);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("5");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setForeground(Color.LIGHT_GRAY);
		label_5.setBounds(302, 12, 59, 15);
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel("6");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setForeground(Color.LIGHT_GRAY);
		label_6.setBounds(361, 12, 59, 15);
		contentPane.add(label_6);
		
		JLabel label_7 = new JLabel("7");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setForeground(Color.LIGHT_GRAY);
		label_7.setBounds(407, 12, 66, 15);
		contentPane.add(label_7);
		
		JLabel label_8 = new JLabel("0");
		label_8.setForeground(Color.LIGHT_GRAY);
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setBounds(12, 25, 17, 46);
		contentPane.add(label_8);
		
		JLabel label_9 = new JLabel("1");
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setForeground(Color.LIGHT_GRAY);
		label_9.setBounds(12, 83, 17, 46);
		contentPane.add(label_9);
		
		JLabel label_10 = new JLabel("2");
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setForeground(Color.LIGHT_GRAY);
		label_10.setBounds(12, 141, 17, 46);
		contentPane.add(label_10);
		
		JLabel label_11 = new JLabel("3");
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		label_11.setForeground(Color.LIGHT_GRAY);
		label_11.setBounds(12, 199, 17, 46);
		contentPane.add(label_11);
		
		JLabel label_12 = new JLabel("4");
		label_12.setHorizontalAlignment(SwingConstants.CENTER);
		label_12.setForeground(Color.LIGHT_GRAY);
		label_12.setBounds(12, 257, 17, 46);
		contentPane.add(label_12);
		
		JLabel label_13 = new JLabel("5");
		label_13.setHorizontalAlignment(SwingConstants.CENTER);
		label_13.setForeground(Color.LIGHT_GRAY);
		label_13.setBounds(12, 315, 17, 46);
		contentPane.add(label_13);
		
		JLabel label_14 = new JLabel("6");
		label_14.setHorizontalAlignment(SwingConstants.CENTER);
		label_14.setForeground(Color.LIGHT_GRAY);
		label_14.setBounds(12, 373, 17, 46);
		contentPane.add(label_14);
		
		JLabel label_15 = new JLabel("7");
		label_15.setHorizontalAlignment(SwingConstants.CENTER);
		label_15.setForeground(Color.LIGHT_GRAY);
		label_15.setBounds(12, 431, 17, 46);
		contentPane.add(label_15);
		
		buttons = new ArrayList<DamaButton>();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				DamaButton dbutton = new DamaButton(new Coordinate(i, j));
				dbutton.setPreferredSize(new Dimension(damaField.getX()/8, damaField.getY()/8));
				buttons.add(dbutton);
				damaField.add(dbutton);
			}
		}
		
		for(DamaButton dbutton : buttons) {
			dbutton.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					if(g.getActualPlayer().getPiecesNumber() == 0) {
						g.getNotActualPlayer().setWon(true);
						g.setGameOver(true);
						notification.setText(g.getNotActualPlayer().getName() + " won!");
						logger.info(g.getNotActualPlayer().getName() + " won!");
					}
						
					if(g.getNotActualPlayer().getPiecesNumber() == 0) {
						g.getActualPlayer().setWon(true);
						g.setGameOver(true);
						notification.setText(g.getActualPlayer().getName() + " won!");
						logger.info(g.getNotActualPlayer().getName() + " won!");
					}
					if(g.isGameOver()) { 
						return;
					}
					
					DamaButton actButton = (DamaButton)e.getSource();
					DamaButton prevButton = getPreviousClickedButton();
					Player ap = g.getActualPlayer();
					Player nap = g.getNotActualPlayer();
					List<Integer> signs = new ArrayList<Integer>(Arrays.asList(1, 11, 2, 21));
					List<Integer> p1signs = new ArrayList<Integer>(Arrays.asList(1, 11));
					List<Integer> p2signs = new ArrayList<Integer>(Arrays.asList(2, 21));
					String apName = g.getActualPlayer().getName();
					String napName = g.getActualPlayer().getName();
					int aSign = -1;
					int pSign = -1;
					Coordinate aCoord = null;
					Coordinate pCoord = null;
					if(getPreviousClickedButton() != null ) {
						pSign = g.getTableVal(getPreviousClickedButton().getCoordinate());
						pCoord = getPreviousClickedButton().getCoordinate();
						aSign = g.getTableVal(actButton.getCoordinate());
						aCoord = actButton.getCoordinate();
					} 
					
					
					if(getPreviousClickedButton() == null 
							&& (g.getTableVal(actButton.getCoordinate()) == 0
								|| g.getTableVal(actButton.getCoordinate()) == 3)) {
						return;
					}
					
					/*if(pSign == 3) {
						clearPreviousClickedButton();
						updateButtonIcons();
						return;
					}
					
					/*if((getPreviousClickedButton() == null && aSign == 0)) {
						clearPreviousClickedButton();
						updateButtonIcons();
						return;
					}*/
					
					/* kijelölés */
					if(getPreviousClickedButton() == null) {
						actButton.setSelected(true);
						updateButtonIcons();
						return;
					} else {
						notification.setText(g.getActualPlayer().getName() + "'s turn");
					}
					if(actButton.equals(getPreviousClickedButton())) {
						actButton.setSelected(false);
						clearPreviousClickedButton();
						updateButtonIcons();
						return;
					} 
					/* ************ */
					
					if(getPreviousClickedButton() != null 
							&& g.getActualPlayer().getSigns().contains(
							g.getTableVal(getPreviousClickedButton().getCoordinate()))) {
						Coordinate pc = getPreviousClickedButton().getCoordinate();
						Coordinate ac = actButton.getCoordinate();
						if(g.move(pc, ac, g.getActualPlayer())) {
							clearPreviousClickedButton();
							updateButtonIcons();
							//pgt();
							g.switchPlayer();
							while(g.doHit()) {
								if(g.getActualPlayer().getPiecesNumber() == 0) {
									g.getNotActualPlayer().setWon(true);
									g.setGameOver(true);
									notification.setText(g.getNotActualPlayer().getName() + " won!");
								}
									
								if(g.getNotActualPlayer().getPiecesNumber() == 0) {
									g.getActualPlayer().setWon(true);
									g.setGameOver(true);
									notification.setText(g.getActualPlayer().getName() + " won!");
								}
										
//								System.out.println(g.getActualPlayer().getName() + " hit" );
								
								updateButtonIcons();
								g.switchPlayer();
								notification.setText(g.getActualPlayer().getName() + "'s turn");
								logger.info(g.getActualPlayer().getName() + "'s turn");
								
								/*synchronized (getGui()) {
									try {
										getGui().wait(100);
									} catch (InterruptedException e1) {
										e1.printStackTrace();
									}
								}*/
							}
							
							if(g.getActualPlayer().getPiecesNumber() == 0) {
								g.getNotActualPlayer().setWon(true);
								g.setGameOver(true);
								notification.setText(g.getNotActualPlayer().getName() + " won!");
							}
								
							if(g.getNotActualPlayer().getPiecesNumber() == 0) {
								g.getActualPlayer().setWon(true);
								g.setGameOver(true);
								notification.setText(g.getActualPlayer().getName() + " won!");
							}
							notification.setText(g.getActualPlayer().getName() + "'s turn");
						} else {
							notification.setText("Invalid step.");
							logger.warn("Invalid step");
						}
					}
					//updateButtonIcons();
				}
			});
		}
		updateButtonIcons();
	}
	
	/**
	 * Returns previous clicked game button.
	 * 
	 * @return Previous clicked game button.
	 */
	public DamaButton getPreviousClickedButton() {
		for (DamaButton damaButton : buttons)
			if (damaButton.isSelected())
				return damaButton;
		return null;
	}

	/**
	 * Clears all button from clicked state.
	 */
	public void clearPreviousClickedButton() {
		for (DamaButton damaButton : buttons)
			if (damaButton.isSelected())
				damaButton.setSelected(false);
	}
	
	public void pgt() {
		for (int i = 0; i < g.getTable().length; i++) {
			for (int j = 0; j < g.getTable().length; j++) {
				System.out.print(g.getTableVal(new Coordinate(i, j)) + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public DamaGUI getGui() {
		return this;
	}
	
	/**
	 * Updates every button icon.
	 */
	public void updateButtonIcons() {
		for (DamaButton damaButton : buttons)	{
			int value = g.getTableVal(damaButton.getCoordinate());
			if (value == 0) {
				damaButton.setIcon(
						new ImageIcon(getClass().getClassLoader().getResource(
								"Empty.png")));
			} else if (value == 3) {
				damaButton.setIcon(
						new ImageIcon(getClass().getClassLoader().getResource(
								"Unstepable.png")));
			} else if (value == 2) {
				if (damaButton.isSelected()) {
					damaButton.setIcon(
							new ImageIcon(getClass().getClassLoader()
									.getResource("W-highlight.png")));
				} else {
					damaButton.setIcon(
							new ImageIcon(getClass().getClassLoader()
									.getResource("W.png")));
				}
			} else if (value == 21) {
				if (damaButton.isSelected()) {
					damaButton.setIcon(
							new ImageIcon(getClass().getClassLoader()
									.getResource("W-dama-highlight.png")));
				} else {
					damaButton.setIcon(
							new ImageIcon(getClass().getClassLoader()
									.getResource("W-dama.png")));
				}
			} else if (value == 1) {
				if (damaButton.isSelected()) {
					damaButton.setIcon(
							new ImageIcon(getClass().getClassLoader()
									.getResource("D-highlight.png")));
				} else {
					damaButton.setIcon(
							new ImageIcon(getClass().getClassLoader()
									.getResource("D.png")));
				}
			} else if (value == 11) {
				if (damaButton.isSelected()) {
					damaButton.setIcon(
							new ImageIcon(getClass().getClassLoader()
									.getResource("D-dama-highlight.png")));
				} else {
					damaButton.setIcon(
							new ImageIcon(getClass().getClassLoader()
									.getResource("D-dama.png")));
				}
			}
		}
			
	}
}
