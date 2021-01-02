
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import Board_Package.Board;
import Game_Constants_Package.GameConstants;
import Menu_Package.Menu;

public class Main extends JFrame implements ActionListener {

	private static Main game_main;
	private Menu menu;
	
	public static void main(String[] args) {
		Main game_main = new Main();
		game_main.initFrame(args);
	}

	//This function defines and initializes the frame
	private void initFrame(String[] args) {
		int height = GameConstants.SCREEN_HEIGHT;
		int width = GameConstants.SCREEN_WIDTH;
		// set the frame height and width
		this.setPreferredSize(new Dimension(width, height));
		this.setBounds(0, 0, width, height);
		this.setTitle("Pac Man Game");
		this.setResizable(true);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.paint(this.getGraphics());

		initMenu();
	}

	private void initMenu() {
		menu = new Menu();
		menu.newGame.addActionListener(this);
		menu.loadGame.addActionListener(this);
		menu.leaderBoard.addActionListener(this);
		this.add(menu);
		this.pack();
	}

	//This function describes the actions that will happen if you press the main buttons
	// EB remove override
	public void actionPerformed(ActionEvent arg0) {
		int height = GameConstants.SCREEN_HEIGHT;
		int width = GameConstants.SCREEN_WIDTH;
		if("newGame".equals(arg0.getActionCommand())) {
			this.menu.setVisible(false);
			this.remove(menu);
			System.out.println("New Game is pressed");
			Board gameBoard = new Board();
			gameBoard.setPreferredSize(new Dimension(width,height));
			this.add(gameBoard, BorderLayout.CENTER);
			this.revalidate();
			this.repaint();
			this.pack();
		}

		else if("loadGame".equals(arg0.getActionCommand())) {
			System.out.println("Load Game is pressed");
		}

		//if we press the "leaderBoard" button, a new window 
		//will open with the score table
		if("leaderBoard".equals(arg0.getActionCommand())) {
			System.out.println("Leader Board is pressed");
		}

	}

}
