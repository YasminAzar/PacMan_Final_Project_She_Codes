package Menu_Package;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Board_Package.Board;

public class Menu implements  ActionListener  {
	static JFrame _frame;
	static Board _board;
//	private Graphics g;

	
	public Menu(JFrame frame) {
		_frame = frame;
		creatPanel();
		//this.paint(g); //ניסיתי לעשות רקע- אבל זה לא מצליח
		
		
	}
	public JPanel creatPanel()  {

		JButton newGame = new JButton(new ImageIcon("button_new_game.png"));
		JButton loadGame = new JButton(new ImageIcon("button_load_game.png"));
		JButton leaderBoard = new JButton(new ImageIcon("button_leader_board.png"));
		JPanel panel = new JPanel();
		BoxLayout bl = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.add(Box.createVerticalGlue());   // expandable vertical space.
		panel.add(Box.createHorizontalGlue());  // expandable horizontal space.
		//panel.add(newGame, BorderLayout.LINE_START);
		//panel.add(loadGame, BorderLayout.CENTER);
		//panel.add(leaderBoard, BorderLayout.PAGE_END);
		newGame.setActionCommand("newGame");
		loadGame.setActionCommand("loadGame");
		leaderBoard.setActionCommand("leaderBoard");
		newGame.addActionListener(this);
		loadGame.addActionListener(this);
		leaderBoard.addActionListener(this);


		panel.setLayout(bl);
		panel.add(newGame);
		panel.add(Box.createRigidArea(new Dimension(300, 20)));
		panel.add(loadGame);
		panel.add(Box.createRigidArea(new Dimension(300, 20)));
		panel.add(leaderBoard);
		panel.add(Box.createRigidArea(new Dimension(300, 20)));
		panel.setSize(new Dimension(200, 200));
		//panel.setLocation(400, 500);

		_frame.add(panel);
		_frame.pack();
		_frame.setVisible(true);
		return panel;
	}
	
	public void paint(Graphics g) {
		//background
		g.setColor(Color.black);
		g.fillRect(0, 0, 600, 800);
		g.setColor(Color.yellow);
		g.setFont(new Font("serif", Font.BOLD, 50));
		g.drawString("PAC MAN", 300, 100);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if("newGame".equals(arg0.getActionCommand())) {
			System.out.println("New Game is pressed");
		}
		if("loadGame".equals(arg0.getActionCommand()))
			System.out.println("Load Game is pressed");
		
		//if we press the "leaderBoard" button, a new window 
		//will open with the score table
		if("leaderBoard".equals(arg0.getActionCommand())) {
			System.out.println("Leader Board is pressed");
			JFrame leaderBoardFrame = new JFrame();
			leaderBoardFrame.setPreferredSize(new Dimension(200, 400));
			leaderBoardFrame.setBounds(800, 0, 400, 600);
			leaderBoardFrame.setTitle("Leader Score Board");
			leaderBoardFrame.setResizable(true);
			leaderBoardFrame.setVisible(true);
			//leaderBoardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
			

	}
}
