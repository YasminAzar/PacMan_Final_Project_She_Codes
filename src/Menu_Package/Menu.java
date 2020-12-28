package Menu_Package;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

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
		creatPanel();
		//this.paint(g); //ניסיתי לעשות רקע- אבל זה לא מצליח	
	}

	//This function display the panel with the main buttons
	public void creatPanel()  {
		newGame = new JButton(new ImageIcon("src/Images/button_new_game.png"));
		loadGame = new JButton(new ImageIcon("src/Images/button_load_game.png"));
		leaderBoard = new JButton(new ImageIcon("src/Images/button_leader_board.png"));
		//JPanel panel = new JPanel();
		BoxLayout bl = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.add(Box.createVerticalGlue());   // expandable vertical space.
		this.add(Box.createHorizontalGlue());  // expandable horizontal space.
		//panel.add(newGame, BorderLayout.LINE_START);
		//panel.add(loadGame, BorderLayout.CENTER);
		//panel.add(leaderBoard, BorderLayout.PAGE_END);
		newGame.setActionCommand("newGame");
		loadGame.setActionCommand("loadGame");
		leaderBoard.setActionCommand("leaderBoard");
		// EB remove we control it from the main
		//newGame.addActionListener(this);
		//loadGame.addActionListener(this);
		//leaderBoard.addActionListener(this);

		this.setLayout(bl);
		this.add(newGame);
		this.add(Box.createRigidArea(new Dimension(300, 20)));
		this.add(loadGame);
		this.add(Box.createRigidArea(new Dimension(300, 20)));
		this.add(leaderBoard);
		this.add(Box.createRigidArea(new Dimension(300, 20)));
		this.setSize(new Dimension(200, 200));
		//panel.setLocation(400, 500);

		//_frame.add(panel);
		//_frame.pack();
		//_frame.setVisible(true);
		//return panel;
	}

	//Another function that tries to draw a background (not working yet)
	public void paint_1(Graphics g) {
		//background
		g.setColor(Color.black);
		g.fillRect(0, 0, 600, 800);
		g.setColor(Color.yellow);
		g.setFont(new Font("serif", Font.BOLD, 50));
		g.drawString("PAC MAN1", 300, 100);
	}	
}
