package Board_Package;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Log_Package.PacmanLog;


public class Board extends JPanel implements ActionListener{
	//static JFrame _frame;
	//Graphics2D g;
	//private static final long serialVersionUID = 1L;

	public BufferedImage title;
	//File pac_man_title = new File("pac_man_title2.png");
	//JFrame f;
	public int map[][] = {
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,1,1,1,0,1,1,1,1,0,1,1,0,1,1,1,1,0,1,1,1,1,0,1},
			{1,0,1,1,1,0,1,1,1,1,0,1,1,0,1,1,1,1,0,1,1,1,1,0,1},
			{1,0,1,1,1,0,1,1,1,1,0,1,1,0,1,1,1,1,0,1,1,1,1,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,1,1,1,1,1,1,1,0,1,1,1,1,1,0,1,1,1,1,1,1,1,0,1},
			{1,0,1,1,1,1,1,1,1,0,1,0,0,0,1,0,1,1,1,1,1,1,1,0,1},
			{1,0,0,0,0,0,0,0,0,0,1,1,0,1,1,0,0,0,0,0,0,0,0,0,1},
			{1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1},
			{0,0,0,0,0,0,0,0,0,0,1,1,0,1,1,0,0,0,0,0,0,0,0,0,0},
			{0,1,1,1,0,1,1,1,0,1,1,1,0,1,1,1,0,1,1,1,0,1,1,1,0},
			{0,1,1,1,0,0,0,1,0,1,1,1,0,1,1,1,0,1,0,0,0,1,1,1,0},
			{0,1,1,1,1,1,0,1,1,1,1,1,0,1,1,1,1,1,0,1,1,1,1,1,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{1,1,1,1,1,1,1,1,0,1,0,1,1,1,0,1,0,1,1,1,1,1,1,1,1},
			{1,0,0,0,0,0,0,0,0,1,0,1,1,1,0,1,0,0,0,0,0,0,0,0,1},
			{1,0,1,1,0,1,1,1,0,1,0,1,1,1,0,1,0,1,1,1,0,1,1,0,1},
			{1,0,1,1,0,1,0,1,0,1,0,1,1,1,0,1,0,1,0,1,0,1,1,0,1},
			{1,0,1,1,0,1,0,1,0,1,0,1,1,1,0,1,0,1,0,1,0,1,1,0,1},
			{1,0,1,1,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1,1,0,1},
			{1,0,1,1,1,1,1,1,1,1,0,1,1,1,0,1,1,1,1,1,1,1,1,0,1},
			{1,0,1,1,1,1,1,1,1,1,0,1,1,1,0,1,1,1,1,1,1,1,1,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
	};


	public int cubeWidth;
	public int cubeHeight;

	//Constructor
	public 	Board() {
		//_frame = frame;
		cubeWidth = 20;
		cubeWidth = 20;
		this.setLayout(new GridLayout(15,15));
		setBorder(new EmptyBorder(10, 10, 600, 600));
		setLayout(new GridBagLayout());

	}

	//EB change to screen size
	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(600, 600);
	}

	private void creatBoard(Graphics2D g2d) {

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		int w = getSize().width;
		int h = getSize().height;
		// EB check for null
		g2d.setColor(Color.BLUE);
		g2d.drawRect(10,10,cubeWidth,cubeHeight);
		g2d.setColor(Color.BLUE);
		g2d.fillRect(10,10,cubeWidth,cubeHeight);
		//g2d.setColor(Color.DARK_GRAY);
		//g2d.setStroke(new BasicStroke(8f));

		this.repaint();
		//g2d.setColor(Color.BLUE);
		PacmanLog.log("creatBoard","map.length "+map.length+" map[0].length "+map[0].length);
		int index = 0;
		//System.out.println("map.length "+map.length+" map[0].length "+map[0].length);
		// EB back to 15
		int size = 15;
		for (int i = 0; i < size/*map.length*/; i++) {
			for (int j = 0; j < size/*map[0].length*/; j++) {
				PacmanLog.log("creatBoard","i "+i+" j "+j + " index " + index);
				index+=1;
				if(map[i][j] > 0) {


					g2d.setColor(Color.BLUE);
					g2d.fillRect(j*cubeWidth, i*cubeHeight, cubeWidth, cubeHeight);
					//g2d.setColor(Color.DARK_GRAY);
					g2d.setStroke(new BasicStroke(8f));
					//g2d.drawRect(10,10,100,100);
					this.repaint();
					PacmanLog.log("creatBoard","DRAW");


					g2d.setStroke(new BasicStroke(5));
					g2d.setColor(Color.BLUE);
					//PacmanLog.log("creatBoard","board paint x "+j*cubeWidth+" y "+i*cubeHeight);
					//g2d.fillRect(j*cubeWidth, i*cubeHeight, cubeWidth, cubeHeight);
				} 

			}

		}
		//this.add(newGame,gbc);
		//this.add(Box.createRigidArea(new Dimension(300, 20)));

		//this.revalidate();
		//this.repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		// this is the transform I was using when I found the bug.
		creatBoard(g2);
		/*g2.setColor(Color.BLUE);
			    g2.fillRect(10,10,100,100);
			    g2.setColor(Color.DARK_GRAY);
			    g2.setStroke(new BasicStroke(8f));
			    g2.drawRect(10,10,100,100);
				this.repaint();*/
	}


	/*public Board() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//init();

	}*/

	//This function displays the background image (not working yet)
	public void init() {

		//f.setSize(800,600); 
		//f.setLayout(null); 
		//f.setVisible(true);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//try {
		//	title = ImageIO.read(pac_man_title);
		//} catch (IOException e) {
		//	e.printStackTrace();
		//}
	}



	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}
