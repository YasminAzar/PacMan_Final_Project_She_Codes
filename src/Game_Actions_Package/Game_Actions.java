package Game_Actions_Package;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import Board_Package.Board;
import Menu_Package.Menu;

public class Game_Actions extends JPanel implements KeyListener, ActionListener {
	static JFrame _frame;
	private boolean play = false;
	private int score = 0;
	private Timer timer;
	private int delay = 8;
	private int pacPosX = 400;
	private int pacPosY = 400;
	private int pacDirX = -1;
	private int pacDirY = -1;
	private Board board;
	private Menu menu;
	private boolean board_1 = false;

	//Constructor
	public Game_Actions() {
		//EB
		board = new Board();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}
	//ניסיתי ליצור רקע, אבל זה לא עובד
	public void paint(Graphics g) {
		/*//background
		g.setColor(Color.black);
		g.fillRect(0, 0, 600, 800);
		g.setColor(Color.yellow);
		g.setFont(new Font("serif", Font.BOLD, 50));
		g.drawString("PAC MAN", 300, 100);*/

		/*if(board_1) {
			board.draw(g);
		}*/


	}

	@Override
	public void actionPerformed(ActionEvent arg0) {


	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
