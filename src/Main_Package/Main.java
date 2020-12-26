package Main_Package;

import java.awt.Dimension;

import javax.swing.JFrame;

import Game_Actions_Package.Game_Actions;
import Menu_Package.Menu;

public class Main {
	
	public static void main(String[] args) {
		//Board frame = new Board();
		
		JFrame frame = new JFrame();
		int height = 600;
		int width = 800;
		// set the frame height and width
		frame.setPreferredSize(new Dimension(width, height));
		frame.setBounds(0, 0, width, height);

		/*JButton newGame = new JButton(new ImageIcon("button_new_game.png"));
		JButton loadGame = new JButton("Load Game");
		JButton leaderBoard = new JButton("Leader Board");*/
		//newGame.setBorderPainted(true);
		//newGame.setBounds(0,0,10,20);
		//loadGame.setBounds(300,300,95,30);
		//leaderBoard.setBounds(500, 500, 70, 50);
		//frame.add(newGame);
		//frame.add(loadGame);

		frame.setTitle("Pac Man Game");
		frame.setResizable(true);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Game_Actions gameActions = new Game_Actions();
		frame.add(gameActions);
		//frame.init();
		frame.paint(frame.getGraphics());
		
		/*JPanel panel = new JPanel();
		BoxLayout bl = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.add(Box.createVerticalGlue());    // expandable vertical space.
		panel.add(Box.createHorizontalGlue());  // expandable horizontal space.

		panel.setLayout(bl);
		       panel.add(newGame);
		       panel.add(Box.createRigidArea(new Dimension(10, 20)));
		       panel.add(loadGame);
		       panel.add(Box.createRigidArea(new Dimension(10, 20)));
		       panel.add(leaderBoard);
		       panel.add(Box.createRigidArea(new Dimension(10, 20)));*/
		       
		       //frame.add(panel);
		Menu menu = new Menu(frame);
		
		//menu.creatPanel();
		//frame.add(Menu.creatPanel());
		frame.pack();
		
		//JPanel p = Menu.creatPanel().panel;
	
		
		
	}

	
}
