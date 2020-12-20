package Board_Package;
import javax.swing.*;   
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;



public class Board extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public BufferedImage title;
	File pac_man_title = new File("pac_man_title2.png");
	
	JFrame f;
	  
	

	/*public Board() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//init();

	}*/
	public void init() {

		f.setSize(1000,800); 
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

	public void paint(Graphics g) {
		g.drawImage(title,0 ,0, this.getWidth(), this.getHeight(), this);

	}
}
