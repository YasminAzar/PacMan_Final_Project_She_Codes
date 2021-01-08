package Board_Package;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.stream.IntStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Log_Package.PacmanLog;
import Game_Constants_Package.*;
import Game_Objects.Ghosts;
import Game_Objects.Pacman;
import Game_Objects.Power_Ball;

public class Board extends JPanel implements ActionListener{

	public BufferedImage redGhostBI, blueGhostBI, pinkGhostBI, orangeGhostBI;
	String [][]map = Game_Constants_Package.GameConstants.BOARD_OPTION_1.clone() ;
	public int boardOffset = (int) (GameConstants.SCREEN_WIDTH*(0.13));
	public int blockWidth;
	public int blockHeight;
	public int locationBallX;
	public int locationBallY;
	public int randEmptyRow;
	public int firstIndexInEmptyRow;
	public int firstPBlocationX, firstPBlocationY;
	public int pbIndex1, pbIndex2, pbIndex3, pbIndex4, pbIndex5,pbIndex6;
	int [] cubeSize = new int[2];
	int [] ballsLocation = new int[2];
	int [] pbLocation = new int[6];
	//Image redGhostImage = new ImageIcon("src/Images/redGhostGIF.gif").getImage();
	//Image blueGhostImage = new ImageIcon("src/Images/blueGhostGIF.gif").getImage();
	//Image pinkGhostImage = new ImageIcon("src/Images/pinkGhostGIF.gif").getImage();
	//Image orangeGhostImage = new ImageIcon("src/Images/orangeGhostGIF.gif").getImage();
	//Image pacmanImage = new ImageIcon("src/Images/pacman_rightGIF.gif").getImage();
	Ghosts redGhost, blueGhost, pinkGhost, orangeGhost;
	Pacman pacman;
	//int ghost_w = redGhost.getGameCharacterImage().getWidth(null);
	//int ghost_h = redGhost.getGameCharacterImage().getHeight(null);
	Power_Ball powerBall_1, powerBall_2, powerBall_3, powerBall_4;


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
		randEmptyRow = findEmptyRow(map);
		//System.out.println("randEmptyRow = " + randEmptyRow);
		firstIndexInEmptyRow = findFirstIndex(randEmptyRow);
		System.out.println("firstIndexInEmptyRow = " + firstIndexInEmptyRow);
		//map [randEmptyRow][firstIndexInEmptyRow] = Game_Constants_Package.GameConstants.GHOST_1_STRING;
		//map [randEmptyRow][firstIndexInEmptyRow+blockWidth] = "gh2";
		//map [randEmptyRow][firstIndexInEmptyRow+blockWidth*2] = "gh3";
		//map [randEmptyRow][firstIndexInEmptyRow+blockWidth*3] = "gh4";
		/*int [] locationBalls = calcLocationBall(blockWidth);
		for (int i = 0; i < locationBalls.length; i++) {
			System.out.println("locationBalls["+i+"] = " + locationBalls[i]);
		}*/
		locationBallX = calcLocationBall(blockWidth)[0];
		//System.out.println("locationBallX = " + locationBallX);
		locationBallY = calcLocationBall(blockHeight)[1];
		//System.out.println("locationBallY = " + locationBallY);
		createPowerBalls();
		//pbLocation = findPBlocation(map);
		//pbIndex1 = pbLocation[0]; 
		//pbIndex2 = pbLocation[1];
		//pbIndex3 = pbLocation[2];
		//pbIndex4 = pbLocation[3];
		//pbIndex5 = pbLocation[4];
		//pbIndex6 = pbLocation[5];
		//System.out.println("pbIndex1 = " + pbIndex1 + " ,  pbIndex2 = " + pbIndex2 + ", pbIndex3 = " + pbIndex3 + " , pbIndex4 = " + pbIndex4 + " , pbIndex5 = " + pbIndex5 + " , pbIndex6 = " + pbIndex6);
	}

	//EB change to screen size
	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(600, 600);
	}

	private void createPowerBalls() {
		pbLocation = findPBlocation(map);
		pbIndex1 = pbLocation[0]; 
		pbIndex2 = pbLocation[1];
		pbIndex3 = pbLocation[2];
		pbIndex4 = pbLocation[3];
		pbIndex5 = pbLocation[4];
		pbIndex6 = pbLocation[5];
		System.out.println("pbIndex1 = " + pbIndex1 + " ,  pbIndex2 = " + pbIndex2 + ", pbIndex3 = " + pbIndex3 + " , pbIndex4 = " + pbIndex4 + " , pbIndex5 = " + pbIndex5 + " , pbIndex6 = " + pbIndex6);
	}

	private void createBoard(Graphics2D g2d) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		int w = getSize().width;
		int h = getSize().height;
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, w, h);
		// EB check for null
		// YP I delete it because it draws a blue square from the top left
		/*g2d.setColor(Color.BLUE);
		g2d.drawRect(10,10,blockWidth,blockHeight);
		g2d.setColor(Color.BLUE);
		g2d.fillRect(10,10,blockWidth,blockHeight);*/
		//this.repaint();

		PacmanLog.log("creatBoard","map.length "+map.length+" map[0].length "+map[0].length);
		int index = 0;

		// EB back to 15
		int size = 15;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				PacmanLog.log("creatBoard","i "+i+" j "+j + " index " + index);
				index+=1;
				if(map[i][j] == "1") {
					//Draws and paints the block
					g2d.setColor(Color.BLUE);
					g2d.fillRect(j*blockWidth+boardOffset, i*blockHeight, blockWidth, blockHeight);
					g2d.setStroke(new BasicStroke(8f));
					//this.repaint();
					PacmanLog.log("creatBoard","DRAW");
					g2d.setStroke(new BasicStroke(5));
					g2d.setColor(Color.BLUE);
				}
				else if(map[i][j] == "0") {
					//Draws and paints the white balls
					g2d.setColor(Color.WHITE);
					//Addition and subtraction to place the balls in the middle of the block
					g2d.fillOval(j*blockWidth+boardOffset+(int)(blockWidth*0.4), i*blockHeight+(int)(blockWidth*0.4), (int)(blockWidth*0.25), (int)(blockHeight*0.25));
					g2d.setStroke(new BasicStroke(8f));
					//this.repaint();
					PacmanLog.log("creatBoard","DRAW");
					g2d.setStroke(new BasicStroke(5));
					g2d.setColor(Color.WHITE);
				}
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//Black background
		//int w = getSize().width;
		//int h = getSize().height;
		double offsetGhostPacman_w = 1.05;
		double offsetGhostPacman_h = 1.015;
		//double offsetPowerBall_w_h = 0.3;
		//double offsetDiameterPowerBall = 0.5;
		//int position_x = blockWidth/2 - ghost_width/2;


		Graphics2D g2 = (Graphics2D) g;
		// this is the transform I was using when I found the bug.
		createBoard(g2);
		callGhosts();
		callPacman();
		callPowerBalls();
		drawGhost(g2, redGhost);
		drawGhost(g2, blueGhost);
		drawGhost(g2, pinkGhost);
		drawGhost(g2, orangeGhost);
		drawPacman(g2, pacman, (int)(firstIndexInEmptyRow*blockWidth*offsetGhostPacman_w)+blockWidth*5+boardOffset, (int)(randEmptyRow*blockHeight*offsetGhostPacman_h));
		drawPowerBall(g2, powerBall_1);
		drawPowerBall(g2, powerBall_2);
		drawPowerBall(g2, powerBall_3);
		drawPowerBall(g2, powerBall_4);
		//addCharacter();
	}

	//This function calculates the size of the blocks that make up the walls
	public int[] calcBlockSize(int arraySize, int boardWidth, int boardHeight) {
		blockWidth = boardWidth/arraySize;
		blockHeight = boardHeight/arraySize;
		cubeSize[0] = blockWidth;
		cubeSize[1] = blockHeight;
		return cubeSize;	
	}

	//This function calculates the location of the balls
	public int[] calcLocationBall(int blockSize) {
		//loationBallX = (int) (boardOffset+boardOffset*0.13+blockWidth);
		locationBallX = blockWidth+boardOffset+(int)(blockWidth*0.4);
		//loationBallY = blockHeight+blockHeight/2;
		locationBallY = (int) (blockHeight*1.39);
		ballsLocation[0] = locationBallX;
		ballsLocation[1] = locationBallY;
		return ballsLocation;	
	}

	public void addCharacter() {
		//findEmptyRow(map);
	}

	//This function finds where there are rows with at least 8 balls in a row,
	//and returns such a row randomly
	public int findEmptyRow(String[][] gameBoard) {
		boolean[] rowIsEmpty = new boolean[gameBoard.length]; //boolean array 
		int [] rowIsEmpty0_1 = new int[gameBoard.length]; //help array
		int count = 0;
		for (int i = 0; i < gameBoard.length; i++) {
			rowIsEmpty[i] = false;
			rowIsEmpty0_1[i] = i;
			for (int j = 0; j < gameBoard.length; j++) {
				if(gameBoard[i][j] != "0") {
					count = 0;
				}
				else  
				{
					count++;
					if(count == 8) {
						rowIsEmpty[i] = true;
						rowIsEmpty0_1[i] = i;
						break;
					}	
				}	
			}
			//System.out.println("rowIsEmpty["+i+"] = " +rowIsEmpty[i]);
			count = 0;	
		}
		int[] choices = IntStream.range(0, rowIsEmpty.length).filter(i->rowIsEmpty[i]  == true).map(i->rowIsEmpty0_1[i]).toArray();
		int randVal = choices[(int)(Math.random()*choices.length)];
		System.out.println("The empty row is: " + randVal);
		return randVal;
	}
	//This function finds an initial index for the ghosts on the selected empty row
	public int findFirstIndex(int randVal) {
		int firstColIndex = 0;
		int count2 = 0;
		for (int i = 0; i < map.length; i++) {
			for (int k = 0; k < map.length; k++) {
				if(i == randVal) {
					if(map[i][k] == "0") {
						count2++;
						if(count2 == 8) 
							firstColIndex = k - 3;
					}
				}
			}
		}
		return firstColIndex;
	}

	//This function find the power balls indexes
	public int[] findPBlocation(String[][] gameBoard) {
		int min_i = gameBoard.length-1;
		int max_i = 0;
		int min_j_firstRow = gameBoard.length-1;
		int max_j_firstRow = 0;
		int min_j_lastRow = gameBoard.length-1;
		int max_j_lastRow = 0;
		int [] indexPB = new int[6];
		for (int i = 0; i < gameBoard.length; i++) {
			for (int j = 0; j < gameBoard.length; j++) {
				if(gameBoard[i][j] == "0") {
					//Saves the indexes to the top balls
					if(i < min_i) {
						min_i = i;
						indexPB[0] = i;
						if(j < min_j_firstRow) {
							indexPB[1] = j;
							max_j_firstRow = j;
						}
					}
					else if(i == min_i){
						if(j > max_j_firstRow) {
							indexPB[2] = j;
							max_j_firstRow = j;
						}
					}
					//Saves the indexes to the bottom balls
					if(i > max_i) {
						max_i = i;
						indexPB[3] = i;
						min_j_lastRow = gameBoard.length-1;
						if(j < min_j_lastRow) {
							indexPB[4] = j;
							min_j_lastRow = j;
						}
					}
					if(i >= max_i) {
						max_j_lastRow = 0;
						if(j > max_j_lastRow) {
							indexPB[5] = j;
							max_j_lastRow = j;
						}
					}
				}
			}
		}
		return indexPB;
	}


	public void callGhosts() {
		GridBagConstraints gbc = new GridBagConstraints();
		double offsetGhostPacman_w = 1.05;
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		redGhost = new Ghosts(null);
		blueGhost = new Ghosts(null);
		pinkGhost = new Ghosts(null);
		orangeGhost = new Ghosts(null);
		
		Image redGhostImage = new ImageIcon("src/Images/redGhostGIF.gif").getImage();
		Image blueGhostImage = new ImageIcon("src/Images/blueGhostGIF.gif").getImage();
		Image pinkGhostImage = new ImageIcon("src/Images/pinkGhostGIF.gif").getImage();
		Image orangeGhostImage = new ImageIcon("src/Images/orangeGhostGIF.gif").getImage();
		double ghost_offset = blockWidth/2 - redGhostImage.getHeight(null)/2;
		redGhost.setGameCharacterImage(redGhostImage);
		redGhost.setGrid_x(randEmptyRow);
		redGhost.setGrid_y(firstIndexInEmptyRow);
		redGhost.setLocation_x(redGhost.getGrid_x()*blockHeight + (int)ghost_offset);
		redGhost.setLocation_y((int) (redGhost.getGrid_y()*blockWidth/**offsetGhostPacman_w)*/+blockWidth*1+ghost_offset));
		System.out.println("redGhost grid_x: " + redGhost.getGrid_x() + " y "+redGhost.getGrid_y());
		System.out.println("redGhost location_x: " + redGhost.getLocation_x() + " y "+redGhost.getLocation_y());
		blueGhost.setGameCharacterImage(blueGhostImage);
		blueGhost.setGrid_x(randEmptyRow);
		blueGhost.setGrid_y(firstIndexInEmptyRow+1);
		blueGhost.setLocation_x(blueGhost.getGrid_x()*blockHeight);
		blueGhost.setLocation_y((int) (blueGhost.getGrid_y()*blockWidth*offsetGhostPacman_w)+blockWidth*2+boardOffset);
		System.out.println("blueGhost grid_x: " + blueGhost.getGrid_x() + " y "+blueGhost.getGrid_y());
		System.out.println("blueGhost location_x: " + blueGhost.getLocation_x() + " y "+blueGhost.getLocation_y());
		pinkGhost.setGameCharacterImage(pinkGhostImage);
		pinkGhost.setGrid_x(randEmptyRow);
		pinkGhost.setGrid_y(firstIndexInEmptyRow+2);
		pinkGhost.setLocation_x(pinkGhost.getGrid_x()*blockHeight);
		pinkGhost.setLocation_y((int) (pinkGhost.getGrid_y()*blockWidth*offsetGhostPacman_w)+blockWidth*3+boardOffset);
		System.out.println("pinkGhost grid_x: " + pinkGhost.getGrid_x() + " y "+pinkGhost.getGrid_y());
		System.out.println("pinkGhost location_x: " + pinkGhost.getLocation_x() + " y "+pinkGhost.getLocation_y());
		orangeGhost.setGameCharacterImage(orangeGhostImage);
		orangeGhost.setGrid_x(randEmptyRow);
		orangeGhost.setGrid_y(firstIndexInEmptyRow+3);
		System.out.println("orangeGhost grid_x: " + orangeGhost.getGrid_x() + " y "+orangeGhost.getGrid_y());
		orangeGhost.setLocation_x(orangeGhost.getGrid_x()*blockHeight);
		orangeGhost.setLocation_y((int) (orangeGhost.getGrid_y()*blockWidth*offsetGhostPacman_w)+blockWidth*4+boardOffset);
		System.out.println("orangeGhost location_x: " + orangeGhost.getLocation_x() + " y "+orangeGhost.getLocation_y());
		//ImageIcon blueGhostImage = new ImageIcon("src/Images/ghostGIF.gif");
		//ImageIcon pinkGhostImage = new ImageIcon("src/Images/ghostGIF.gif");
		//ImageIcon orangeGhostImage = new ImageIcon("src/Images/ghostGIF.gif");
	}


	//This function creates the pacman
	public void callPacman() {
		pacman = new Pacman(null);
		Image pacmanImage = new ImageIcon("src/Images/pacman_rightGIF.gif").getImage();
		pacman.setGameCharacterImage(pacmanImage);
	}

	//This function creates the power balls
	public void callPowerBalls(Graphics2D g2d, int x, int y, int width, int height) {
		//Draws and paints the white balls
		g2d.setColor(Color.WHITE);
		g2d.fillOval(x, y, width,height);
		g2d.setStroke(new BasicStroke(8f));
		//this.repaint();
		//PacmanLog.log("creatBoard","DRAW");
		g2d.setStroke(new BasicStroke(5));
		g2d.setColor(Color.WHITE);
	}

	public void callPowerBalls() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		powerBall_1 = new Power_Ball(null);
		powerBall_2 = new Power_Ball(null);
		powerBall_3 = new Power_Ball(null);
		powerBall_4 = new Power_Ball(null);
		Image powerBall_1Image = new ImageIcon("src/Images/powerball.png").getImage();
		Image powerBall_2Image = new ImageIcon("src/Images/powerball.png").getImage();
		Image powerBall_3Image = new ImageIcon("src/Images/powerball.png").getImage();
		Image powerBall_4Image = new ImageIcon("src/Images/powerball.png").getImage();
		powerBall_1.setGameCharacterImage(powerBall_1Image);
		powerBall_1.setGrid_x(pbIndex2);
		powerBall_1.setGrid_y(pbIndex1);
		powerBall_2.setGameCharacterImage(powerBall_2Image);
		powerBall_2.setGrid_x(pbIndex3);
		powerBall_2.setGrid_y(pbIndex1);
		powerBall_3.setGameCharacterImage(powerBall_3Image);
		powerBall_3.setGrid_x(pbIndex5);
		powerBall_3.setGrid_y(pbIndex4);
		powerBall_4.setGameCharacterImage(powerBall_4Image);
		powerBall_4.setGrid_x(pbIndex6);
		powerBall_4.setGrid_y(pbIndex4);

		//ImageIcon blueGhostImage = new ImageIcon("src/Images/ghostGIF.gif");
		//ImageIcon pinkGhostImage = new ImageIcon("src/Images/ghostGIF.gif");
		//ImageIcon orangeGhostImage = new ImageIcon("src/Images/ghostGIF.gif");
	}


	/*private void imagePanel() {
		try {                
			redGhostBI = ImageIO.read(new File("src/Images/ghost.png"));
			blueGhostBI = ImageIO.read(new File("src/Images/ghostGIF.gif"));
			pinkGhostBI = ImageIO.read(new File("src/Images/ghostGIF.gif"));
			orangeGhostBI = ImageIO.read(new File("src/Images/ghostGIF.gif"));
		} catch (IOException ex) {
			// handle exception...
		}
	}*/

	/* @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters            
	    }*/

	/**
	 * @param g2d
	 * @param ghost
	 */
	private void drawGhost(Graphics2D g2d, Ghosts ghost) {

		/*double offsetGhostPacman_w = 1.05;
		double offsetGhostPacman_h = 1.015;
		//ghost.setGrid_x(firstIndexInEmptyRow);
		//ghost.setGrid_y(randEmptyRow);
		int xIndex = ghost.getGrid_x();
		ghost.setLocation_x((int)(xIndex*blockWidth*offsetGhostPacman_w)+boardOffset);
		ghost.setLocation_y((int)(randEmptyRow*blockHeight*offsetGhostPacman_h));
		//(int)(firstIndexInEmptyRow*blockWidth*offsetGhostPacman_w)+boardOffset, (int)(randEmptyRow*blockHeight*offsetGhostPacman_h)*/
		g2d.drawImage(ghost.getGameCharacterImage(), ghost.getLocation_y(), ghost.getLocation_x(), this);

	}



	private void drawPacman(Graphics2D g2d, Pacman player, int x, int y) {
		g2d.drawImage(player.getGameCharacterImage(), x, y, this);
	}

	private void drawPowerBall(Graphics2D g2d, Power_Ball powerBall) {
		double offsetPowerBall_w_h = 0.2;
		int xIndexPB = powerBall.getGrid_x();
		int yIndexPB = powerBall.getGrid_y();
		powerBall.setLocation_x(xIndexPB*blockWidth+(int)(blockWidth*offsetPowerBall_w_h)+boardOffset);
		powerBall.setLocation_y(yIndexPB*blockHeight+(int)(blockHeight*offsetPowerBall_w_h));
		//(int)(firstIndexInEmptyRow*blockWidth*offsetGhostPacman_w)+boardOffset, (int)(randEmptyRow*blockHeight*offsetGhostPacman_h)
		g2d.drawImage(powerBall.getGameCharacterImage(), powerBall.getLocation_x(), powerBall.getLocation_y(), this);
	}

	public void init() {

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}
}