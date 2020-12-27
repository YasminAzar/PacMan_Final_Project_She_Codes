package Main_Package;

import java.awt.Dimension;

import javax.swing.JFrame;
import java.io.File;  // Import the File class
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Game_Actions_Package.Game_Actions;
import Menu_Package.Menu;

public class Main {
	static String logFileName = "src\\log_files\\log_messages.txt";
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
		
		
		//menu.creatPanel();
		//frame.add(Menu.creatPanel());
		Menu menu = new Menu();
		frame.add(menu);
		frame.pack();
		
		//JPanel p = Menu.creatPanel().panel;
	}
		private static void createLogFile() {
			try {
			     File myObj = new File(logFileName);
			     if (myObj.createNewFile()) {
			       System.out.println("File created: " + myObj.getName());
			     } else {
			       System.out.println("File already exists.");
			     }
			   } catch (IOException e) {
			     System.out.println("An error occurred "+e.getMessage());
			     e.printStackTrace();
			   }
			}

			public static void log(String functionName, String msg) {
			try {
			     FileWriter myWriter = new FileWriter(logFileName);
			     DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			     LocalDateTime now = LocalDateTime.now();
			     System.out.println(dtf.format(now));
			     myWriter.write(dtf.format(now) + ":" + functionName + ' ' + msg);
			     myWriter.close();
			     System.out.println("Successfully wrote to the file.");
			   } catch (IOException e) {
			     System.out.println("An error occurred.");
			     e.printStackTrace();
			   }
			}

	
}
