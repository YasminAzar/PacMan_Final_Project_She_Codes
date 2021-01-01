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

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Log_Package.PacmanLog;
import Game_Constants_Package.*;

public class Board extends JPanel implements ActionListener{

	public BufferedImage title;
	int [][]map = Game_Constants_Package.GameConstants.BOARD_OPTION_1.clone() ;
	public int blockWidth;
	public int blockHeight;

	//Constructor
	public 	Board() {
		blockWidth = calcBlockSize(map.length, Game_Constants_Package.GameConstants.BOARD_WIDTH,
				Game_Constants_Package.GameConstants.BOARD_HEIGHT)[0];
		System.out.println("block width: " + blockWidth);
		blockHeight = calcBlockSize(map.length, Game_Constants_Package.GameConstants.BOARD_WIDTH,
				Game_Constants_Package.GameConstants.BOARD_HEIGHT)[1];
		System.out.println("block height: " + blockHeight);
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
		// YP I delete it because it draws a blue square from the top left
		/*g2d.setColor(Color.BLUE);
		g2d.drawRect(10,10,blockWidth,blockHeight);
		g2d.setColor(Color.BLUE);
		g2d.fillRect(10,10,blockWidth,blockHeight);*/
		this.repaint();

		PacmanLog.log("creatBoard","map.length "+map.length+" map[0].length "+map[0].length);
		int index = 0;

		// EB back to 15
		int size = 15;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				PacmanLog.log("creatBoard","i "+i+" j "+j + " index " + index);
				index+=1;
				if(map[i][j] > 0) {
					//Draws and paints the block
					g2d.setColor(Color.BLUE);
					g2d.fillRect(j*blockWidth+90, i*blockHeight, blockWidth, blockHeight);
					g2d.setStroke(new BasicStroke(8f));
					this.repaint();
					PacmanLog.log("creatBoard","DRAW");
					g2d.setStroke(new BasicStroke(5));
					g2d.setColor(Color.BLUE);
				} 
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		// this is the transform I was using when I found the bug.
		creatBoard(g2);
	}

	//This function calculates the size of the blocks that make up the walls
	public int[] calcBlockSize(int arraySize, int boardWidth, int boardHeight) {
		blockWidth = boardWidth/arraySize;
		blockHeight = boardHeight/arraySize;
		int [] cubeSize = {blockWidth, blockHeight};
		return cubeSize;	
	}

	public void init() {

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}
