package Board_Package;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;



public class Board extends JFrame {
	static JFrame _frame;
	//Graphics2D g;
	private static final long serialVersionUID = 1L;
	public BufferedImage title;
	File pac_man_title = new File("pac_man_title2.png");
	JFrame f;
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
	public 	Board(JFrame frame) {
		_frame = frame;
		cubeWidth = 20;
		cubeWidth = 20;
		//draw(g);

	}
	
	//This function paints the walls (not working yet)
	public void draw(Graphics2D g) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if(map[i][j] > 0) {
					g.setColor(Color.blue);
					g.fillRect(j*cubeWidth, i*cubeHeight, cubeWidth, cubeHeight);
				}
				
			}
			
		}
	}




	/*public Board() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//init();

	}*/
	
	//This function displays the background image (not working yet)
	public void init() {

		f.setSize(800,600); 
		f.setLayout(null); 
		f.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {
			title = ImageIO.read(pac_man_title);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*public void init() {
		try {
			title = ImageIO.read(pac_man_title);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	//This function paints the background image (not working yet)
	public void paint(Graphics g) {
		g.drawImage(title,0 ,0, this.getWidth(), this.getHeight(), this);

	}
}
