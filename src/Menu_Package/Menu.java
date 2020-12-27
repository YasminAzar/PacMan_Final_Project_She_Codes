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
import Main_Package.Main;

public class Menu extends JPanel implements  ActionListener  {
	static Board _board;
	//	private Graphics g;

	//Constructor
	public Menu() {
		creatPanel();
		//this.paint(g); //ניסיתי לעשות רקע- אבל זה לא מצליח	
	}

	//This function display the panel with the main butons
	public void creatPanel()  {
		JButton newGame = new JButton(new ImageIcon("button_new_game.png"));
		JButton loadGame = new JButton(new ImageIcon("button_load_game.png"));
		JButton leaderBoard = new JButton(new ImageIcon("button_leader_board.png"));
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
		newGame.addActionListener(this);
		loadGame.addActionListener(this);
		leaderBoard.addActionListener(this);

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

	//This function describes the actions that will happen if you press the main buttons
	@Override
	public void actionPerformed(ActionEvent arg0) {
		//if("newGame".equals(arg0.getActionCommand()) || 
				//"loadGame".equals(arg0.getActionCommand()) ) {
			if("newGame".equals(arg0.getActionCommand())) {
				System.out.println("New Game is pressed");
				//Main.log("actionPerformed: ", "New Game is pressed");
				/*JFrame newGameFrame = new JFrame();
				newGameFrame.setPreferredSize(new Dimension(800, 600));
				newGameFrame.setBounds(0, 0, 800, 600);
				newGameFrame.setTitle("Pac Man New Game");
				newGameFrame.setResizable(true);
				newGameFrame.setVisible(true);
				newGameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
*/
			}
			if("loadGame".equals(arg0.getActionCommand())) {
				System.out.println("Load Game is pressed");
				/*Main.log("actionPerformed: ", "Load Game is pressed");
				JFrame frameLoadGame = new JFrame();
				frameLoadGame.setPreferredSize(new Dimension(800, 600));
				frameLoadGame.setBounds(0, 0, 800, 600);
				frameLoadGame.setTitle("Load Game");
				frameLoadGame.setResizable(true);
				frameLoadGame.setVisible(true);
				frameLoadGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				JButton Game_1 = new JButton("Game_1");
				JButton Game_2 = new JButton("Game_2");
				JButton Game_3 = new JButton("Game_3");
				JPanel panelLoadGame = new JPanel();
				BoxLayout blLoadGame = new BoxLayout(panelLoadGame, BoxLayout.Y_AXIS);
				panelLoadGame.add(Box.createVerticalGlue());   // expandable vertical space.
				panelLoadGame.add(Box.createHorizontalGlue());  // expandable horizontal space.
				Game_1.setActionCommand("Game_1");
				Game_2.setActionCommand("Game_2");
				Game_3.setActionCommand("Game_3");
				Game_1.addActionListener(this);
				Game_2.addActionListener(this);
				Game_3.addActionListener(this);

				panelLoadGame.setLayout(blLoadGame);
				panelLoadGame.add(Game_1);
				panelLoadGame.add(Box.createRigidArea(new Dimension(120, 20)));
				panelLoadGame.add(Game_2);
				panelLoadGame.add(Box.createRigidArea(new Dimension(120, 20)));
				panelLoadGame.add(Game_3);
				panelLoadGame.add(Box.createRigidArea(new Dimension(120, 250)));
				panelLoadGame.setSize(new Dimension(300, 300));
				frameLoadGame.add(panelLoadGame);
				frameLoadGame.pack();
				frameLoadGame.setVisible(true);
				//return panelLoadGame;
				
				if("Game_1".equals(arg0.getActionCommand()) ||
						"Game_2".equals(arg0.getActionCommand()) ||
						"Game_3".equals(arg0.getActionCommand())) {
					if("Game_1".equals(arg0.getActionCommand())) {
							System.out.println("You have selected Game 1");
							Main.log("actionPerformed: ", "Game 1 is pressed");
							//frameLoadGame.dispose();
					}
					if("Game_2".equals(arg0.getActionCommand()))
						System.out.println("You have selected Game 2");
					if("Game_3".equals(arg0.getActionCommand()))
						System.out.println("You have selected Game 3");
					//frameLoadGame.dispose();
				}

			}

			//_frame.dispose();
			 * */
			 
			
		}
		//if we press the "leaderBoard" button, a new window 
		//will open with the score table
		if("leaderBoard".equals(arg0.getActionCommand())) {
			System.out.println("Leader Board is pressed");
			/*JFrame leaderBoardFrame = new JFrame();
			leaderBoardFrame.setPreferredSize(new Dimension(200, 400));
			leaderBoardFrame.setBounds(800, 0, 400, 600);
			leaderBoardFrame.setTitle("Leader Score Board");
			leaderBoardFrame.setResizable(true);
			leaderBoardFrame.setVisible(true);*/
		}


	}
}
