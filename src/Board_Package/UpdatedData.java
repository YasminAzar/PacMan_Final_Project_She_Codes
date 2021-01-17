package Board_Package;

import javax.swing.JPanel;
import javax.swing.Timer;

public class UpdatedData extends JPanel  {
	int score;
	int countOfPb;
	Timer timer;
	String warningSign;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
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

