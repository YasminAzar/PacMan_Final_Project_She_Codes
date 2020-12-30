package Menu_Package;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Board_Package.Board;

public class Menu extends JPanel /*implements  ActionListener*/  {
	static Board _board;
	//	private Graphics g;
	// EB declare jbutton here so we can reference from main
	public JButton newGame;
	public JButton loadGame;
	public JButton leaderBoard;
	//Constructor
	public Menu() {
		setBorder(new EmptyBorder(10,10,10,10));
		setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		creatPanel(gbc);
		
	}

	//This function display the panel with the main buttons
	public void creatPanel(GridBagConstraints gbc)  {
		newGame = new JButton(new ImageIcon("src/Images/button_new_game.png"));
		loadGame = new JButton(new ImageIcon("src/Images/button_load_game.png"));
		leaderBoard = new JButton(new ImageIcon("src/Images/button_leader_board.png"));

		newGame.setActionCommand("newGame");
		loadGame.setActionCommand("loadGame");
		leaderBoard.setActionCommand("leaderBoard");
		this.revalidate();
		this.repaint();
		// EB remove we control it from the main
		//newGame.addActionListener(this);
		//loadGame.addActionListener(this);
		//leaderBoard.addActionListener(this);

		this.add(newGame, gbc);
		//this.add(Box.createRigidArea(new Dimension(300, 20)));
		this.add(loadGame, gbc);
		//this.add(Box.createRigidArea(new Dimension(300, 20)));
		this.add(leaderBoard, gbc);
		//this.add(Box.createRigidArea(new Dimension(300, 20)));
		//this.setSize(new Dimension(200, 200));
		//panel.setLocation(400, 500);

		//_frame.add(panel);
		//_frame.pack();
		//_frame.setVisible(true);
		//return panel;
	}

	//This function defines a black background
	public void paint(Graphics g) {
		int w = getSize().width;
		int h = getSize().height;
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, w, h);
	}	
}
