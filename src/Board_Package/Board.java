package Board_Package;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.IntStream;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Game_Constants_Package.GameConstants;
import Game_Objects.Ghosts;
import Game_Objects.Lives;
import Game_Objects.Pacman;
import Game_Objects.Power_Ball;
import Log_Package.PacmanLog;
import Menu_Package.Menu;
import Score_Package.GameScore;

public class Board extends JPanel implements ActionListener{

	public BufferedImage redGhostBI, blueGhostBI, pinkGhostBI, orangeGhostBI;
	public int boardOffset = (int) (GameConstants.SCREEN_WIDTH*(0.13));
	public int blockWidth;
	public int blockHeight;
	public int locationBallX;
	public int locationBallY;
	public int randEmptyRow;
	public int firstIndexInEmptyRow;
	public int firstPBlocationX, firstPBlocationY;
	public int pbIndex1, pbIndex2, pbIndex3, pbIndex4, pbIndex5,pbIndex6;
	Ghosts redGhost, blueGhost, pinkGhost, orangeGhost;
	Pacman pacman;
	Power_Ball powerBall_1, powerBall_2, powerBall_3, powerBall_4;
	Lives firstLife, secondLife, thirdLife;
	GameScore gameScore;
	String [][]map = Game_Constants_Package.GameConstants.BOARD_OPTION_1.clone() ;
	final String UP = "U";
	final String DOWN = "D";
	final String LEFT = "L";
	final String RIGHT = "R";
	final String BLUE = "1";
	final String WHITE = "0";
	final String EMPTY = "E";
	final String EXISTS = "exists";
	final String NOT_EXIST = "not exist";
	final String SMALL_BALL = "small_ball";
	final String POWER_BALL = "power_ball";
	String direction;
	ArrayList<Junction> junctionArrList = new ArrayList<Junction>();
	Junction junc = new Junction();
	GridBagConstraints gbc;
	int [] cubeSize = new int[2];
	int [] ballsLocation = new int[2];
	int [] pbLocation = new int[6];
	int [] locInArray = new int[2];
	int [] redGhostLoc = new int[2];
	int [] blueGhostLoc = new int[2];
	int [] pinkGhostLoc = new int[2];
	int [] orangeGhostLoc = new int[2];
	int [] pacmanLoc = new int[2];
	int [] pbLoc1 = new int[2];
	int [] pbLoc2 = new int[2];
	int [] pbLoc3 = new int[2];
	int [] pbLoc4 = new int[2];
	TimerCountdown timer = new TimerCountdown();
	
	public 	Board() {
		blockWidth = calcBlockSize(map.length, Game_Constants_Package.GameConstants.BOARD_WIDTH,
				Game_Constants_Package.GameConstants.BOARD_HEIGHT)[0];
		System.out.println("block width: " + blockWidth);
		blockHeight = calcBlockSize(map.length, Game_Constants_Package.GameConstants.BOARD_WIDTH,
				Game_Constants_Package.GameConstants.BOARD_HEIGHT)[1];
		System.out.println("block height: " + blockHeight);
		gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		//this.setLayout(new GridLayout(15,15));
		//setBorder(new EmptyBorder(10, 10, 600, 600));
		setLayout(new GridBagLayout());
		scorePanel();
		
		randEmptyRow = findEmptyRow(map);
		firstIndexInEmptyRow = findFirstIndex(randEmptyRow);
		System.out.println("firstIndexInEmptyRow = " + firstIndexInEmptyRow);
		locationBallX = calcLocationBall(blockWidth)[0];
		locationBallY = calcLocationBall(blockHeight)[1];
		
		createJunction();
		createPowerBalls();
		callGhosts();
		callPacman();
		callPowerBalls();
		callLives();
		addKeyBoard();	
		
		//updateScore(1,3);
		/*this.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				switch(arg0.getKeyCode()) {
				case KeyEvent.VK_UP:
					//System.out.println("press");
					if(isFree(pacman.getGrid_x()-1,pacman.getGrid_y()) == true){
						//direction = UP;
						pacman.setDirection(UP);
						pacman.setGrid_x(pacman.getGrid_x()-1);
						pacman.setGrid_y(pacman.getGrid_y());
						pacman.setLocation_x(pacman.getGrid_x()*blockHeight);
						pacman.setLocation_y(boardOffset+(pacman.getGrid_y()*blockWidth));
						map[pacman.getGrid_x()][pacman.getGrid_y()] = "pac";
						map[pacman.getGrid_x()+1][pacman.getGrid_y()] = EMPTY;

					}
					break;
				case  KeyEvent.VK_DOWN:
					if(isFree(pacman.getGrid_x()+1,pacman.getGrid_y()) == true){
						//direction = DOWN;
						pacman.setDirection(DOWN);
						pacman.setGrid_x(pacman.getGrid_x()+1);
						pacman.setGrid_y(pacman.getGrid_y());
						pacman.setLocation_x(pacman.getGrid_x()*blockHeight);
						pacman.setLocation_y(boardOffset+(pacman.getGrid_y()*blockWidth));
						map[pacman.getGrid_x()][pacman.getGrid_y()] = "pac";
						map[pacman.getGrid_x()-1][pacman.getGrid_y()] = EMPTY;
					}
					break;
				case KeyEvent.VK_LEFT:
					if(isFree(pacman.getGrid_x(),pacman.getGrid_y()-1) == true){
						//direction = LEFT;
						pacman.setDirection(LEFT);
						pacman.setGrid_x(pacman.getGrid_x());
						pacman.setGrid_y(pacman.getGrid_y()-1);
						pacman.setLocation_x(pacman.getGrid_x()*blockHeight);
						pacman.setLocation_y(boardOffset+(pacman.getGrid_y()*blockWidth));
						map[pacman.getGrid_x()][pacman.getGrid_y()] = "pac";
						map[pacman.getGrid_x()][pacman.getGrid_y()+1] = EMPTY;
					}
					break;
				case  KeyEvent.VK_RIGHT:
					if(isFree(pacman.getGrid_x(),pacman.getGrid_y()+1) == true){
						//direction = RIGHT;
						pacman.setDirection(RIGHT);
						pacman.setGrid_x(pacman.getGrid_x());
						pacman.setGrid_y(pacman.getGrid_y()+1);
						pacman.setLocation_x(pacman.getGrid_x()*blockHeight);
						pacman.setLocation_y(boardOffset+(pacman.getGrid_y()*blockWidth));
						map[pacman.getGrid_x()][pacman.getGrid_y()] = "pac";
						map[pacman.getGrid_x()][pacman.getGrid_y()-1] = EMPTY;
					}
					break;
					//TODO defult and case key == KeyEvent.VK_ESCAPE && timer.isRunning
				}

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

		});
		this.setFocusable(true);
		this.requestFocusInWindow();*/
	}

	/**
	 * This function places the power balls on the map
	 */
	private void createPowerBalls() {
		pbLocation = findPBLocation(map);
		pbIndex1 = pbLocation[0]; 
		pbIndex2 = pbLocation[1];
		pbIndex3 = pbLocation[2];
		pbIndex4 = pbLocation[3];
		pbIndex5 = pbLocation[4];
		pbIndex6 = pbLocation[5];
		System.out.println("pbIndex1 = " + pbIndex1 + " ,  pbIndex2 = " + pbIndex2 + ", pbIndex3 = " + pbIndex3 + " , pbIndex4 = " + pbIndex4 + " , pbIndex5 = " + pbIndex5 + " , pbIndex6 = " + pbIndex6);
	}

	/**
	 * This function finds all the junction on the board and saves them in an ArrayList
	 */
	private void createJunction() {
		ArrayList<Junction> junctionsArrList = new ArrayList<Junction>();
		//String BLUE = "1";
		//String WHITE = "0";
		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map[0].length; y++) {
				// check directions
				String directions="";
				if (map[x][y] == BLUE) {
					continue;
				} 
				else {
					//between two walls above and below
					if(x-1 >= 0 && x+1 <= map.length-1) {
						if(map[x-1][y] == BLUE && map[x+1][y] == BLUE) {
							continue;
						}
					}
					//between two walls right and left
					if(y-1 >= 0 && y+1 <= map.length-1) {
						if(map[x][y-1] == BLUE && map[x][y+1] == BLUE) {
							continue;
						}
					}
					//edge on the left and wall on the right
					if(y == 0 && map[x][y+1] == BLUE) {
						continue;
					}
					//edge on the right and wall on the left
					if(y == map.length-1 && map[x][y-1] == BLUE) {
						continue;
					}
					//edge on top and wall on bottom
					if(x == 0 && map[x+1][y] == BLUE) {
						continue;
					}
					//edge on bottom and wall on top
					if(x == map.length-1 && map[x-1][y] == BLUE) {
						continue;
					}
					// up
					// make sure not zero
					if (x-1 >= 0) { // top row
						if (map[x-1][y] == WHITE) {
							directions = directions.concat(UP);
						}
					}
					// down
					// make sure not bottom
					if (x+1 <= map.length-1) {
						if (map[x+1][y] == WHITE) {
							directions = directions.concat(DOWN);
						}
					}
					// left
					// make sure not zero
					if (y-1 >= 0) {
						if (map[x][y-1] == WHITE) {
							directions = directions.concat(LEFT);
						}
					}
					// right
					// make sure not right
					if (y+1 <= map.length-1) {
						if (map[x][y+1] == WHITE) {
							directions = directions.concat(RIGHT);
						}
					}
				}
				// check value
				System.out.println(" x " + x + " y " + y + " directions " + directions);
				if (directions.length() > 1) {
					Junction j = new Junction();
					j.setDirection(directions);
					j.setnumOfOption(directions.length());
					j.setX(x);
					j.setY(y);
					junctionsArrList.add(j);
					j = null;
				}
			}
		}
		System.out.println(" Number of junctions: " + junctionsArrList.size());
	}

	/**
	 * This function creates the selected game board
	 * @param g2d
	 */
	private void createBoard(Graphics2D g2d) {
		int w = getSize().width;
		int h = getSize().height;
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, w, h);
		
		PacmanLog.log("creatBoard","map.length "+map.length+" map[0].length "+map[0].length);
		int index = 0;
		// EB back to 15
		int size = 15;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				PacmanLog.log("creatBoard","i "+i+" j "+j + " index " + index);
				index += 1;
				if(map[i][j] == "1") {
					//Draws and paints the block
					g2d.setColor(Color.BLUE);
					g2d.fillRect(j*blockWidth+boardOffset, i*blockHeight, blockWidth, blockHeight);
					g2d.setStroke(new BasicStroke(8f));
					PacmanLog.log("creatBoard","DRAW");
					g2d.setStroke(new BasicStroke(5));
					g2d.setColor(Color.BLUE);
				}
				else if(map[i][j] == "0") {
					//Draws and paints the white balls
					g2d.setColor(Color.WHITE);
					//Addition and subtraction to place the balls in the middle of the block
					g2d.fillOval(j*blockWidth+boardOffset+(int)(blockWidth*0.4), 
							i*blockHeight+(int)(blockWidth*0.4), (int)(blockWidth*0.25),
							(int)(blockHeight*0.25));
					g2d.setStroke(new BasicStroke(8f));
					PacmanLog.log("creatBoard","DRAW");
					g2d.setStroke(new BasicStroke(5));
					g2d.setColor(Color.WHITE);
				}
			}
		}
	}

	/**
	 * This function draws the new status on the screen in each new frame
	 * @param g
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		// this is the transform I was using when I found the bug.
		
		createBoard(g2);
		drawGhost(g2, redGhost);
		drawGhost(g2, blueGhost);
		drawGhost(g2, pinkGhost);
		drawGhost(g2, orangeGhost);
		drawPacman(g2, pacman);
		drawPowerBall(g2, powerBall_1);
		drawPowerBall(g2, powerBall_2);
		drawPowerBall(g2, powerBall_3);
		drawPowerBall(g2, powerBall_4);
		drawLives(g2, firstLife);
		drawLives(g2, secondLife);
		drawLives(g2, thirdLife);
		//addCharacter();
		
		redGhostLoc = locationXYinTheArray(redGhost.getLocation_x(),redGhost.getLocation_y());
		map[redGhostLoc[0]][redGhostLoc[1]] = "rg";
		//System.out.println("map["+redGhostLoc[0]+ "][" + redGhostLoc[1]+ "]" + " = gh1");
		blueGhostLoc = locationXYinTheArray(blueGhost.getLocation_x(),blueGhost.getLocation_y());
		map[blueGhostLoc[0]][blueGhostLoc[1]] = "bg";
		//System.out.println("map["+blueGhostLoc[0]+ "][" + blueGhostLoc[1]+ "]" + " = gh2");
		pinkGhostLoc = locationXYinTheArray(pinkGhost.getLocation_x(),pinkGhost.getLocation_y());
		map[pinkGhostLoc[0]][pinkGhostLoc[1]] = "pg";
		//System.out.println("map["+pinkGhostLoc[0]+ "][" + pinkGhostLoc[1]+ "]" + " = gh3");
		orangeGhostLoc = locationXYinTheArray(orangeGhost.getLocation_x(),orangeGhost.getLocation_y());
		map[orangeGhostLoc[0]][orangeGhostLoc[1]] = "og";
		//System.out.println("map["+orangeGhostLoc[0]+ "][" + orangeGhostLoc[1]+ "]" + " = gh4");
		pacmanLoc = locationXYinTheArray(pacman.getLocation_x(),pacman.getLocation_y());
		//gameScore.updateScore(pacman.getGrid_x(), pacman.getGrid_y());
		//updateScore(pacman.getGrid_x(), pacman.getGrid_y());
		map[pacmanLoc[0]][pacmanLoc[1]] = "pac";
		if(powerBall_1.getStatus() == EXISTS) {
		pbLoc1 = locationXYinTheArray(powerBall_1.getLocation_x(),powerBall_1.getLocation_y());
		map[pbLoc1[0]][pbLoc1[1]] = "pb1";
		}
		if(powerBall_2.getStatus() == EXISTS) {
		pbLoc2 = locationXYinTheArray(powerBall_2.getLocation_x(),powerBall_2.getLocation_y());
		map[pbLoc2[0]][pbLoc2[1]] = "pb2";
		}
		if(powerBall_3.getStatus() == EXISTS) {
		pbLoc3 = locationXYinTheArray(powerBall_3.getLocation_x(),powerBall_3.getLocation_y());
		map[pbLoc3[0]][pbLoc3[1]] = "pb3";
		}
		if(powerBall_4.getStatus() == EXISTS) {
		pbLoc4 = locationXYinTheArray(powerBall_4.getLocation_x(),powerBall_4.getLocation_y());
		map[pbLoc4[0]][pbLoc4[1]] = "pb4";
		}
		printMap();
		System.out.println();
	}

	/**
	 * This function prints the map
	 */
	public void printMap() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				System.out.print(map[i][j] + " ");	
			}
			System.out.println();
		}
	}

	/**
	 * This function calculates the size of the blocks that make up the walls
	 * @param arraySize
	 * @param boardWidth
	 * @param boardHeight
	 * @return cubeSize - an array of block size in pixels
	 */
	public int[] calcBlockSize(int arraySize, int boardWidth, int boardHeight) {
		blockWidth = boardWidth/arraySize;
		blockHeight = boardHeight/arraySize;
		cubeSize[0] = blockWidth;
		cubeSize[1] = blockHeight;
		return cubeSize;	
	}

	/**
	 * This function calculates the location of the balls
	 * @param blockSize
	 * @return ballsLocation - an array of location of the ball in pixels
	 */
	public int[] calcLocationBall(int blockSize) {
		double x_offset = 0.4;
		double y_offset = 1.39;
		//loationBallX = (int) (boardOffset+boardOffset*0.13+blockWidth);
		locationBallX = blockWidth+boardOffset+(int)(blockWidth*x_offset);
		//loationBallY = blockHeight+blockHeight/2;
		locationBallY = (int) (blockHeight*y_offset);
		ballsLocation[0] = locationBallX;
		ballsLocation[1] = locationBallY;
		return ballsLocation;	
	}

	//Need this function?
	public void addCharacter() {
		//findEmptyRow(map);
	}

	/**
	 * This function finds where there are rows with at least 8 balls in a row, and returns such a row randomly
	 * @param gameBoard
	 * @return rand_val - the random line as an integer
	 */
	public int findEmptyRow(String[][] gameBoard) {
		boolean[] row_is_empty = new boolean[gameBoard.length]; //boolean array 
		int [] row_is_empty0_1 = new int[gameBoard.length]; //help array
		int count = 0;
		for (int i = 0; i < gameBoard.length; i++) {
			row_is_empty[i] = false;
			row_is_empty0_1[i] = i;
			for (int j = 0; j < gameBoard.length; j++) {
				if(gameBoard[i][j] != "0") {
					count = 0;
				}
				else  
				{
					count++;
					if(count == 8) {
						row_is_empty[i] = true;
						row_is_empty0_1[i] = i;
						break;
					}	
				}	
			}
			count = 0;	
		}
		int[] choices = IntStream.range(0, row_is_empty.length).filter(i->row_is_empty[i]  == true).map(i->row_is_empty0_1[i]).toArray();
		int rand_val = choices[(int)(Math.random()*choices.length)];
		System.out.println("The empty row is: " + rand_val);
		return rand_val;
	}

	/**
	 * This function finds an initial index for the ghosts on the selected empty row
	 * @param randVal
	 * @return firstColIndex - index of the first column, from which the ghosts begin
	 */
	public int findFirstIndex(int randVal) {
		int first_col_index = 0;
		int count2 = 0;
		for (int i = 0; i < map.length; i++) {
			for (int k = 0; k < map.length; k++) {
				if(i == randVal) {
					if(map[i][k] == "0") {
						count2++;
						if(count2 == 8) 
							first_col_index = k - 3;
					}
				}
			}
		}
		return first_col_index;
	}

	/**
	 * This function find the power balls indexes
	 * @param gameBoard
	 * @return indexPB - index of the power ball on the game board
	 */
	public int[] findPBLocation(String[][] gameBoard) {
		int count = 0;
		ArrayList<Integer> row_with_2_empty_blocks = new ArrayList<Integer>();
		int min_i = gameBoard.length-1;
		int max_i = 0;
		int min_j_first_row = gameBoard.length-1;
		int max_j_first_row = 0;
		int min_j_last_row = gameBoard.length-1;
		int max_j_last_row = 0;
		int [] index_pb = new int[6];
		//Keeps the rows with at least 2 empty spaces in the arrayList 
		for (int i = 0; i < gameBoard.length; i++) {
			count = 0;
			for (int j = 0; j < gameBoard.length; j++) {
				if(gameBoard[i][j] == "0") {
					count++;
					if(count > 1 && !row_with_2_empty_blocks.contains(i)) {
						row_with_2_empty_blocks.add(i);
					}
				}
			}
		}
		System.out.println(row_with_2_empty_blocks);
		for (int i = 0; i < gameBoard.length; i++) {
			for (int j = 0; j < gameBoard.length; j++) {
				//if we are in a empty block and in a row with at least 2 empty blocks
				if(gameBoard[i][j] == "0" && row_with_2_empty_blocks.contains(i)) {
					//Saves the indexes to the top balls
					if(i < min_i) {
						min_i = i;
						index_pb[0] = i;
						if(j < min_j_first_row) {
							index_pb[1] = j;
							min_j_first_row = j;
						}
					}
					else if(i == min_i){
						if(j > max_j_first_row) {
							index_pb[2] = j;
							max_j_first_row = j;
						}
					}
					//Saves the indexes to the bottom balls
					if(i > max_i) {
						max_i = i;
						index_pb[3] = i;
						min_j_last_row = gameBoard.length-1;
						if(j < min_j_last_row) {
							index_pb[4] = j;
							min_j_last_row = j;
						}
					}
					if(i >= max_i) {
						max_j_last_row = 0;
						if(j > max_j_last_row) {
							index_pb[5] = j;
							max_j_last_row = j;
						}
					}
				}
			}
		}
		return index_pb;
	}

	/**
	 * This function calls the ghosts to enter the game
	 */
	public void callGhosts() {
		
		// calculate ghost offset
		Image image_for_size = new ImageIcon("src/Images/redGhostGIF.gif").getImage();
		double ghost_offset = blockWidth/2 - image_for_size.getHeight(null)/2;
		redGhost = new Ghosts("src/Images/redGhostGIF.gif", randEmptyRow, firstIndexInEmptyRow, 
				randEmptyRow*blockHeight+(int)ghost_offset, 
				(int)(firstIndexInEmptyRow*blockWidth+blockWidth-ghost_offset), LEFT);
		System.out.println("redGhost grid_x: " + redGhost.getGrid_x() + " y "+redGhost.getGrid_y());
		System.out.println("redGhost location_x: " + redGhost.getLocation_x() + " y "+redGhost.getLocation_y());
		blueGhost = new Ghosts("src/Images/blueGhostGIF.gif", randEmptyRow, firstIndexInEmptyRow+1, 
				randEmptyRow*blockHeight+(int)ghost_offset, 
				(int)((firstIndexInEmptyRow+1)*blockWidth+blockWidth-ghost_offset), LEFT);
		System.out.println("blueGhost grid_x: " + blueGhost.getGrid_x() + " y "+blueGhost.getGrid_y());
		System.out.println("blueGhost location_x: " + blueGhost.getLocation_x() + " y "+blueGhost.getLocation_y());
		pinkGhost = new Ghosts("src/Images/pinkGhostGIF.gif", randEmptyRow, firstIndexInEmptyRow+2, 
				randEmptyRow*blockHeight+(int)ghost_offset, 
				(int) ((firstIndexInEmptyRow+2)*blockWidth+blockWidth-ghost_offset), RIGHT);
		System.out.println("pinkGhost grid_x: " + pinkGhost.getGrid_x() + " y "+pinkGhost.getGrid_y());
		System.out.println("pinkGhost location_x: " + pinkGhost.getLocation_x() + " y "+pinkGhost.getLocation_y());
		orangeGhost = new Ghosts("src/Images/orangeGhostGIF.gif", randEmptyRow, firstIndexInEmptyRow+3, 
				randEmptyRow*blockHeight+(int)ghost_offset, 
				(int)((firstIndexInEmptyRow+3)*blockWidth+blockWidth-ghost_offset), RIGHT);
		System.out.println("orangeGhost grid_x: " + orangeGhost.getGrid_x() + " y "+orangeGhost.getGrid_y());
		System.out.println("orangeGhost location_x: " + orangeGhost.getLocation_x() + " y "+orangeGhost.getLocation_y());
	}

	/**
	 * This function calls the pacman to enter the game
	 */
	public void callPacman() {
		//pacman = new Pacman(null);
		Image pacman_image_for_size = new ImageIcon("src/Images/pacman_rightGIF.gif").getImage();
		double pacman_offset = blockWidth/2 - pacman_image_for_size.getHeight(null)/2;
		//pacman.setGameCharacterImage(pacman_image);
		pacman = new Pacman("src/Images/pacman_rightGIF.gif", randEmptyRow, firstIndexInEmptyRow+5,
				randEmptyRow*blockHeight + (int)pacman_offset,
				(int)((firstIndexInEmptyRow+5)*blockWidth+blockWidth-pacman_offset),
				RIGHT);
		System.out.println("pacman grid_x: " + pacman.getGrid_x() + " y "+pacman.getGrid_y());
		System.out.println("pacman location_x: " + pacman.getLocation_x() + " y "+pacman.getLocation_y());
	}

	/**
	 * This function calls the power balls to enter the game
	 */
	public void callPowerBalls() {
		
		powerBall_1 = new Power_Ball(null);
		powerBall_2 = new Power_Ball(null);
		powerBall_3 = new Power_Ball(null);
		powerBall_4 = new Power_Ball(null);
		Image power_ball_1_image = new ImageIcon("src/Images/powerball.png").getImage();
		Image power_ball_2_image = new ImageIcon("src/Images/powerball.png").getImage();
		Image power_ball_3_image = new ImageIcon("src/Images/powerball.png").getImage();
		Image power_ball_4_image = new ImageIcon("src/Images/powerball.png").getImage();
		double offsetPowerBall_w_h = blockWidth/2 - power_ball_1_image.getHeight(null)/2;;
		powerBall_1.setGameCharacterImage(power_ball_1_image);
		powerBall_1.setGrid_x(pbIndex1);
		powerBall_1.setGrid_y(pbIndex2);
		powerBall_1.setLocation_x(powerBall_1.getGrid_x()*blockHeight + (int)offsetPowerBall_w_h);
		powerBall_1.setLocation_y((int)(powerBall_1.getGrid_y()*blockWidth+boardOffset+offsetPowerBall_w_h));
		powerBall_1.setStatus(EXISTS);
		powerBall_2.setGameCharacterImage(power_ball_2_image);
		powerBall_2.setGrid_x(pbIndex1);
		powerBall_2.setGrid_y(pbIndex3);
		powerBall_2.setLocation_x(powerBall_2.getGrid_x()*blockHeight + (int)offsetPowerBall_w_h);
		powerBall_2.setLocation_y((int)(powerBall_2.getGrid_y()*blockWidth+boardOffset+offsetPowerBall_w_h));
		powerBall_2.setStatus(EXISTS);
		powerBall_3.setGameCharacterImage(power_ball_3_image);
		powerBall_3.setGrid_x(pbIndex4);
		powerBall_3.setGrid_y(pbIndex5);
		powerBall_3.setLocation_x(powerBall_3.getGrid_x()*blockHeight + (int)offsetPowerBall_w_h);
		powerBall_3.setLocation_y((int)(powerBall_3.getGrid_y()*blockWidth+boardOffset+offsetPowerBall_w_h));
		powerBall_3.setStatus(EXISTS);
		powerBall_4.setGameCharacterImage(power_ball_4_image);
		powerBall_4.setGrid_x(pbIndex4);
		powerBall_4.setGrid_y(pbIndex6);
		powerBall_4.setLocation_x(powerBall_4.getGrid_x()*blockHeight + (int)offsetPowerBall_w_h);
		powerBall_4.setLocation_y((int)(powerBall_4.getGrid_y()*blockWidth+boardOffset+offsetPowerBall_w_h));
		powerBall_4.setStatus(EXISTS);
	}
	public void callLives() {
		
		// calculate ghost offset
		Image heart_image_for_size = new ImageIcon("src/Images/heart.png").getImage();
		double heart_offset = blockWidth/2 - heart_image_for_size.getHeight(null)/2;
		firstLife = new Lives(null);
		secondLife = new Lives(null);
		thirdLife = new Lives(null);
		Image first_life_image = new ImageIcon("src/Images/heart.png").getImage();
		Image second_life_image = new ImageIcon("src/Images/heart.png").getImage();
		Image third_life_image = new ImageIcon("src/Images/heart.png").getImage();
		firstLife.setGameCharacterImage(first_life_image);
		firstLife.setLocation_x((int)(GameConstants.SCREEN_HEIGHT/2 - heart_image_for_size.getHeight(null)*4));
		firstLife.setLocation_y((int)(boardOffset/2-heart_offset));
		secondLife.setGameCharacterImage(second_life_image);
		secondLife.setLocation_x((int)(GameConstants.SCREEN_HEIGHT/2- heart_image_for_size.getHeight(null)));
		secondLife.setLocation_y((int)(boardOffset/2-heart_offset));
		thirdLife.setGameCharacterImage(third_life_image);
		thirdLife.setLocation_x((int)(GameConstants.SCREEN_HEIGHT/2 + heart_image_for_size.getHeight(null)*2));
		thirdLife.setLocation_y((int)(boardOffset/2-heart_offset));
		/*firstLife = new Lives("src/Images/heart.png", (int)GameConstants.SCREEN_HEIGHT/2, 
				(int)boardOffset/2);
		secondLife = new Lives("src/Images/heart.png", (int)(GameConstants.SCREEN_HEIGHT/2 + heart_image_for_size.getHeight(null)), 
				(int)boardOffset/2);
		thirdLife = new Lives("src/Images/heart.png", (int)(GameConstants.SCREEN_HEIGHT/2 + heart_image_for_size.getHeight(null)*2), 
				(int)boardOffset/2);*/
	}


	/**
	 * This function draws the ghosts on the game board
	 * @param g2d
	 * @param ghost
	 */
	private void drawGhost(Graphics2D g2d, Ghosts ghost) {
		g2d.drawImage(ghost.getGhostImage(), ghost.getLocation_y(), ghost.getLocation_x(), this);
	}

	/**
	 * This function draws the pacman on the game board
	 * @param g2d
	 * @param player
	 */
	private void drawPacman(Graphics2D g2d, Pacman player) {
		g2d.drawImage(player.getPacmanImage(), player.getLocation_y(), player.getLocation_x(), this);
	}

	/**
	 * This function draws the power balls on the game board
	 * @param g2d
	 * @param powerBall
	 */
	private void drawPowerBall(Graphics2D g2d, Power_Ball powerBall) {
		if(powerBall.getStatus() == EXISTS) {
		g2d.drawImage(powerBall.getGameCharacterImage(), powerBall.getLocation_y(), 
				powerBall.getLocation_x(), this);
		}
		//if(powerBall.getStatus() == "NotExist"
	}

	private void drawLives(Graphics2D g2d, Lives lives) {
		g2d.drawImage(lives.getGameCharacterImage(), lives.getLocation_y(), 
				lives.getLocation_x(), this);
	}

	/**
	 * This function gets a general position in pixels and returns a position on the array
	 * @param locationX
	 * @param locationY
	 * @return loc_xy_in_map - location on the map (grid location)
	 */
	public int[] locationXYinTheArray(int locationX, int locationY) {
		int []loc_xy_in_map = {-1,-1};
		//double location_y_offset = 1.16;
		if(locationY < boardOffset || locationY > GameConstants.BOARD_WIDTH + boardOffset || 
				locationX <= 0 || locationX > GameConstants.BOARD_HEIGHT) {
			System.out.println("Your not in the borad");
		}
		else if(locationY >= boardOffset && locationY <= (GameConstants.SCREEN_WIDTH - boardOffset) 
				&& locationX > 0 && locationX <= GameConstants.BOARD_HEIGHT) {
			//grid_y:
			loc_xy_in_map[0] = locationX/blockHeight;
			//grid_y:
			loc_xy_in_map[1] = (locationY-boardOffset)/blockWidth;
		}
		return loc_xy_in_map;
	}

	/**
	 * This function listens to the buttons that are pressed on the keyboard 
	 * and sends commands according to each button
	 */
	private void addKeyBoard(){
		//updateScore(SMALL_BALL);
		Image pacman_image_for_size = new ImageIcon("src/Images/pacman_rightGIF.gif").getImage();
		double pacman_offset = blockWidth/2 - pacman_image_for_size.getHeight(null)/2;
		//KeyListener key = new KeyListener();
		//boolean isReleased = false;
		this.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent arg0) {
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				//isReleased = true;
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
				//if(isReleased) {
				switch(arg0.getKeyCode()) {
				case KeyEvent.VK_UP:
					if(isFree(pacman.getGrid_x()-1,pacman.getGrid_y()) == true){
						//direction = UP;
						Image pacman_image_up = new ImageIcon("src/Images/pacman_upGIF.gif").getImage();
						pacman.setDirection(UP);
						pacman.setGrid_x(pacman.getGrid_x()-1);
						pacman.setGrid_y(pacman.getGrid_y());
						pacman.setLocation_x(pacman.getGrid_x()*blockHeight+(int)pacman_offset);
						pacman.setLocation_y(boardOffset+(pacman.getGrid_y()*blockWidth)+(int)pacman_offset);
						pacman.setPacmanImage(pacman_image_up);
						if(isItPbLocatin(pacman.getGrid_x(), pacman.getGrid_y())) {
							System.out.println("Eat PB");
							updateScore(POWER_BALL);
							gameScore.setTimer(timer.timerCountDown1());
							//timer.timerCountDown1();
							
							
						}
						else if(isItSmallBallLocation(pacman.getGrid_x(), pacman.getGrid_y())) {
							System.out.println("Eat Small Ball");
							updateScore(SMALL_BALL);
						}
						map[pacman.getGrid_x()][pacman.getGrid_y()] = "pac";
						map[pacman.getGrid_x()+1][pacman.getGrid_y()] = EMPTY;
						
					}
					break;
				case KeyEvent.VK_DOWN:
					if(isFree(pacman.getGrid_x()+1,pacman.getGrid_y()) == true){
						//direction = DOWN;
						Image pacman_image_down = new ImageIcon("src/Images/pacman_downGIF.gif").getImage();
						pacman.setDirection(DOWN);
						pacman.setGrid_x(pacman.getGrid_x()+1);
						pacman.setGrid_y(pacman.getGrid_y());
						pacman.setLocation_x(pacman.getGrid_x()*blockHeight+(int)pacman_offset);
						pacman.setLocation_y(boardOffset+(pacman.getGrid_y()*blockWidth)+(int)pacman_offset);
						pacman.setPacmanImage(pacman_image_down);
						if(isItPbLocatin(pacman.getGrid_x(), pacman.getGrid_y())) {
							System.out.println("Eat PB");
							gameScore.setTimer(timer.timerCountDown1());
							//timer.timerCountDown1();
						}
						else if(isItSmallBallLocation(pacman.getGrid_x(), pacman.getGrid_y())) {
							System.out.println("Eat Small Ball");
							updateScore(SMALL_BALL);
						}
						//gameScore.updateScore(pacman.getGrid_x(), pacman.getGrid_y());
						map[pacman.getGrid_x()][pacman.getGrid_y()] = "pac";
						map[pacman.getGrid_x()-1][pacman.getGrid_y()] = EMPTY;
					}
					break;
				case KeyEvent.VK_LEFT:
					if(isFree(pacman.getGrid_x(),pacman.getGrid_y()-1) == true){
						//direction = LEFT;
						Image pacman_image_left = new ImageIcon("src/Images/pacman_leftGIF.gif").getImage();
						pacman.setDirection(LEFT);
						pacman.setGrid_x(pacman.getGrid_x());
						pacman.setGrid_y(pacman.getGrid_y()-1);
						pacman.setLocation_x(pacman.getGrid_x()*blockHeight+(int)pacman_offset);
						pacman.setLocation_y(boardOffset+(pacman.getGrid_y()*blockWidth)+(int)pacman_offset);
						pacman.setPacmanImage(pacman_image_left);
						if(isItPbLocatin(pacman.getGrid_x(), pacman.getGrid_y())) {
							System.out.println("Eat PB");
							//HelperForTimer.run();
							//update score
							gameScore.setTimer(timer.timerCountDown1());
							//timer.timerCountDown1();
						}
						else if(isItSmallBallLocation(pacman.getGrid_x(), pacman.getGrid_y())) {
							System.out.println("Eat Small Ball");
							updateScore(SMALL_BALL);
						}
						map[pacman.getGrid_x()][pacman.getGrid_y()] = "pac";
						map[pacman.getGrid_x()][pacman.getGrid_y()+1] = EMPTY;
					}
					break;
				case KeyEvent.VK_RIGHT:
					if(isFree(pacman.getGrid_x(),pacman.getGrid_y()+1) == true){
						//direction = RIGHT;
						Image pacman_image_right = new ImageIcon("src/Images/pacman_rightGIF.gif").getImage();
						pacman.setDirection(RIGHT);
						pacman.setGrid_x(pacman.getGrid_x());
						pacman.setGrid_y(pacman.getGrid_y()+1);
						pacman.setLocation_x(pacman.getGrid_x()*blockHeight+(int)pacman_offset);
						pacman.setLocation_y(boardOffset+(pacman.getGrid_y()*blockWidth)+(int)pacman_offset);
						pacman.setPacmanImage(pacman_image_right);
						if(isItPbLocatin(pacman.getGrid_x(), pacman.getGrid_y())) {
							System.out.println("Eat PB");
							gameScore.setTimer(timer.timerCountDown1());
							//timer.timerCountDown1();
						}
						else if(isItSmallBallLocation(pacman.getGrid_x(), pacman.getGrid_y())) {
							System.out.println("Eat Small Ball");
							updateScore(SMALL_BALL);
						}
						map[pacman.getGrid_x()][pacman.getGrid_y()] = "pac";
						map[pacman.getGrid_x()][pacman.getGrid_y()-1] = EMPTY;
					}
					break;
				default:
					System.out.println("You did not press the correct button");
					//TODO defult and case key == KeyEvent.VK_ESCAPE && timer.isRunning
				}
			}
			//}
		});
		this.setFocusable(true);
		this.requestFocusInWindow();
	}

	//is free means WHITE or pb 	
	private boolean isFree(int x, int y) { 	
		if ((x >= 0 && x <= map.length) && (y >= 0 && y < map.length)) { 			
			if (map[x][y] != BLUE) {
				return true;	
			}
		}
		return false;
	}
	
	//Write here a code for update score
	public int updateScore(String ballType) {
		//int score = 0;
		int small_ball_points = 10;
		int power_ball_points = 50;
		if(ballType == SMALL_BALL) {
			gameScore.setScore(gameScore.getScore() + small_ball_points);
		}
		else if(ballType == POWER_BALL) {
			gameScore.setScore(gameScore.getScore() + power_ball_points);
		}
		System.out.println("Score: " + gameScore.getScore());
		return gameScore.getScore();
	}
	
	/**
	 * This function checks if the Pacman has reached the power ball
	 * @param x
	 * @param y
	 * @return True - if the Pacman got to the power ball
	 */
	private boolean isItPbLocatin(int x, int y) {
		//this.addAncestorListener();
		if(map[x][y] == "pb1") { 
			powerBall_1.setStatus(NOT_EXIST);
			return true;
		}
		if(map[x][y] == "pb2") {
			powerBall_2.setStatus(NOT_EXIST);
			return true;
		}
		if(map[x][y] == "pb3") {
			powerBall_3.setStatus(NOT_EXIST);
			return true;
		}
		if(map[x][y] == "pb4") {
			powerBall_4.setStatus(NOT_EXIST);
			return true;
		}
		return false;
	}

	/**
	 * This function checks if the Pacman has reached the small ball
	 * @param x
	 * @param y
	 * @return True - if the Pacman got to the small ball
	 */
	private boolean isItSmallBallLocation(int x, int y) {
		if(map[x][y] == WHITE) { 
			return true;
		}
		return false;
	}
	

	public void init() {

	}

	private void scorePanel() {
		//gameScore = new GameScore();
		//this.add(gameScore);
		//this.pack();
		GridBagConstraints constraints = new GridBagConstraints( );
		//setLayout(new GridBagLayout( ));
	    //constraints.fill = GridBagConstraints.BOTH;
		gameScore = new GameScore();
		constraints.ipadx = 25;  // add padding
	    constraints.ipady = 25;
		 //constraints.weighty = .5;
	    constraints.gridheight = 2;
	    constraints.weighty = 1.0;
		constraints.anchor = GridBagConstraints.PAGE_END;
		this.add(gameScore, constraints);//e, BorderLayout.PAGE_END
		this.invalidate();
		this.repaint();
		//this.pack();
	}
	
	public TimerCountdown timerCountdown() {
		TimerCountdown timer = new TimerCountdown();
		timer.timerCountDown1();
		return timer;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//this.gameScore.setPreferredSize(new Dimension(100,30));
		//this.add(gameScore, BorderLayout.SOUTH);
		/*this.add(gameScore, BorderLayout.SOUTH);
		this.revalidate();
		this.repaint();*/
		

	}
}