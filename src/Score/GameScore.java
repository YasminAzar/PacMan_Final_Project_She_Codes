package Score;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Game_Constants_Package.GameConstants;

public class GameScore extends JPanel {
	int score = 0;
	
	
	public GameScore() {
		setBorder(new EmptyBorder(5,5,5,5));
		setLayout(new GridBagLayout());
		//setLocation(new Point(500, getHeight()));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.VERTICAL;
		gbc.gridheight = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		creatPanel(gbc);
		
	}
	
	public void creatPanel(GridBagConstraints gbc)  {
		
		JTextField tScore, tPbEaten, tTimer, tWarning;
		JPanel score_panel = new JPanel();
		score_panel.setSize(300, 200);
		score_panel.setLocation(550, 350);
		score_panel.getMaximumSize();
		
		tScore = new JTextField("Score: " + getScore(), 15); 
		tPbEaten = new JTextField("Power Balls Eaten: ", 15);
		tTimer = new JTextField("Time: ", 15);
		tWarning = new JTextField("Ghost is Close", 15);
		//tScore.setBounds((GameConstants.SCREEN_HEIGHT-GameConstants.BOARD_HEIGHT)/2,
	    //		GameConstants.SCREEN_WIDTH/2 ,200,30);
		score_panel.add(tScore, gbc);
		score_panel.add(tPbEaten, gbc);
		score_panel.add(tTimer, gbc);
		score_panel.add(tWarning, gbc);
		this.add(score_panel);
		this.revalidate();
		this.repaint();
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
