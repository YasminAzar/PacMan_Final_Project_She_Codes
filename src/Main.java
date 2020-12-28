
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import Log_Package.PacmanLog;
import Menu_Package.Menu;

public class Main implements ActionListener {
	//static String logFileName = "src\\log_files\\log_messages.txt";
	//public JFrame frame;
	//public PacmanLog logMessages = new PacmanLog();

	public static void main(String[] args) {
		//Board frame = new Board();
		Main instance = new Main();
		initFrame(instance);
	}
	
	//This function defines and initializes the frame
	private static void initFrame(Main instance) {
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

		//Game_Actions gameActions = new Game_Actions();
		//frame.add(gameActions);
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

		initMenu(instance,frame);
		//createLogFile();
		PacmanLog.log("main", "1");
		PacmanLog.log("main", "2");
		PacmanLog.log("main", "3");

		//JPanel p = Menu.creatPanel().panel;
	}
	private static void initMenu(Main instannce, JFrame frame) {
		Menu menu = new Menu();
		frame.add(menu);
		frame.pack();
		menu.newGame.addActionListener(instannce);
		menu.loadGame.addActionListener(instannce);
		menu.leaderBoard.addActionListener(instannce);
		/*menu.newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Your Event on click of Button
				System.out.println("New Game is pressed");
				//Log.log(" initGame ", "New Game is pressed");
				//logMessages.log(" initGame ", "New Game is pressed");
			}});
		menu.loadGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Your Event on click of Button
				System.out.println("Load Game is pressed");
			}});
		menu.leaderBoard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Your Event on click of Button
				System.out.println("Leader Board is pressed");
			}});
		frame.pack();*/
	}

	
	//This function describes the actions that will happen if you press the main buttons
	// EB remove override
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
