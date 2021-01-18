package Board_Package;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.Timer;

import Game_Constants_Package.GameConstants;

public class UpdatedData extends JPanel  {
	int score;
	int countOfPb;
	Timer timer;
	String warningSign;
	
	public UpdatedData(Graphics2D g2) {
		//drawScore(g2);
	}

	/*private void drawScore(Graphics2D g) {
		// g.setFont(smallFont);
		g.setColor(Color.WHITE);
		String s = "Score: " + score;
		g.drawString(s, (GameConstants.SCREEN_HEIGHT-GameConstants.BOARD_HEIGHT)/2,
				GameConstants.SCREEN_WIDTH/2);
	}*/
	/*public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}*/
	public int getCountOfPb() {
		return countOfPb;
	}

	public void setCountOfPb(int countOfPb) {
		this.countOfPb = countOfPb;
	}
	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	public String getWarningSign() {
		return warningSign;
	}

	public void setWarningSign(String warningSign) {
		this.warningSign = warningSign;
	}
}

