package Menu_Package;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Main_Package.Main;

public class Menu {
	static JFrame _frame;
	public Menu(JFrame frame) {
		_frame = frame;
		creatPanel();
	}
	public static JPanel creatPanel() {
		
		JButton newGame = new JButton(new ImageIcon("button_new_game.png"));
		JButton loadGame = new JButton("Load Game");
		JButton leaderBoard = new JButton("Leader Board");
		JPanel panel = new JPanel();
		BoxLayout bl = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.add(Box.createVerticalGlue());   // expandable vertical space.
		panel.add(Box.createHorizontalGlue());  // expandable horizontal space.

		panel.setLayout(bl);
	       panel.add(newGame);
	       panel.add(Box.createRigidArea(new Dimension(10, 20)));
	       panel.add(loadGame);
	       panel.add(Box.createRigidArea(new Dimension(10, 20)));
	       panel.add(leaderBoard);
	       panel.add(Box.createRigidArea(new Dimension(10, 20)));
	       
	       _frame.add(panel);
	       _frame.pack();
	       _frame.setVisible(true);
	       return panel;
	}

}
