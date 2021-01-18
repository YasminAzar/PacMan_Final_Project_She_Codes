package Score_Package;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class GameScore extends JPanel implements ActionListener {
	int score = 0;
	JTextField tScore, tPbEaten, tTimer, tWarning;
	private Timer timer;
	
	public GameScore() {
		setPreferredSize(new Dimension(660, 60));
        //setBackground(new Color(250, 230, 180));
        setFont(new Font("Serif", Font.BOLD, 20));
		//setBorder(new EmptyBorder(5,5,5,5));
		this.setBackground(Color.black);
		//setBorder(new EmptyBorder(5,5,5,5));
		//setLayout(new GridBagLayout());
		//setLocation(new Point(500, getHeight()));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.VERTICAL;
		gbc.gridheight = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		tScore = new JTextField("Score: " + getScore(), 15); 
		tPbEaten = new JTextField("Power Balls Eaten: ", 15);
		tTimer = new JTextField("Time: " + getTimer(), 15);
		tWarning = new JTextField("Ghost is Close", 15);
		this.add(tScore, gbc);
		this.add(tPbEaten, gbc);
		this.add(tTimer, gbc);
		this.add(tWarning, gbc);		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		tScore.setText("Score: " + getScore());
		tPbEaten.setText("Power Balls Eaten: ");
		tTimer.setText("Time: " + getTimer());
		tWarning.setText("Ghost is Close");
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public void timer15s() {
		 timer = new Timer(15, this);
	     timer.start();
	}
	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
