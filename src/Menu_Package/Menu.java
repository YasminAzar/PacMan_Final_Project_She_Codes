package Menu_Package;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Main_Package.Main;

public class Menu implements  ActionListener  {
	static JFrame _frame;
	public Menu(JFrame frame) {
		_frame = frame;
		creatPanel();
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
		panel.add(Box.createRigidArea(new Dimension(_frame.getWidth()/3, 80)));
		panel.add(loadGame);
		panel.add(Box.createRigidArea(new Dimension(_frame.getWidth()/3, 80)));
		panel.add(leaderBoard);
		panel.add(Box.createRigidArea(new Dimension(_frame.getWidth()/3, 80)));
		panel.setSize(new Dimension(200, 200));
		//panel.setLocation(400, 500);

		_frame.add(panel);
		_frame.pack();
		_frame.setVisible(true);
		return panel;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if("newGame".equals(arg0.getActionCommand()))
			System.out.println("New Game is pressed");
		if("loadGame".equals(arg0.getActionCommand()))
			System.out.println("Load Game is pressed");
		if("leaderBoard".equals(arg0.getActionCommand()))
			System.out.println("Leader Board is pressed");

	}
}
