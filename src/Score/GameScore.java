package Score;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Game_Constants_Package.GameConstants;

public class GameScore extends JPanel {
	int score = 0;
	String [][]scoreMap = Game_Constants_Package.GameConstants.BOARD_OPTION_1.clone() ;
	String WHITE = "0";
	
	public GameScore() {
		JTextField t1;
		t1=new JTextField("Score: " + getScore());  
	    t1.setBounds((GameConstants.SCREEN_HEIGHT-GameConstants.BOARD_HEIGHT)/2,
	    		GameConstants.SCREEN_WIDTH/2 ,200,30);
	    this.add(t1);
	}
	/*@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		//String s = "Score: " + score;
		/*g.drawString(s, (GameConstants.SCREEN_HEIGHT-GameConstants.BOARD_HEIGHT)/2,
				GameConstants.SCREEN_WIDTH/2);
	}*/
	
	//Write here a code for update score
	/*public int updateScore(int x, int y) {
		//int score = 0;
		if(scoreMap[x][y] == WHITE) {
			score = score + 10;
		}
		else if(scoreMap[x][y] == "pb1" || scoreMap[x][y] == "pb2" || 
				scoreMap[x][y] == "pb3" || scoreMap[x][y] == "pb4") {
			score = score + 50;
		}
		System.out.println("Score: " + score);
		return score;
	}*/
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
