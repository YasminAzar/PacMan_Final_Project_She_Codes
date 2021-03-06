package Board_Package;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
//import java.util.Timer;
//import java.util.TimerTask;
import java.util.stream.IntStream;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import Game_Constants_Package.GameConstants;
import Game_Objects.Ghosts;
import Game_Objects.Lives;
import Game_Objects.Pacman;
import Game_Objects.Power_Ball;
import Log_Package.PacmanLog;
import Score_Package.GameScore;

public class Board extends JPanel implements ActionListener{

	public BufferedImage redGhostBI, blueGhostBI, pinkGhostBI, orangeGhostBI;
	public int boardOffset;
	public int blockWidth;
	public int blockHeight;
	public int locationBallX;
	public int locationBallY;
	public int randEmptyRow;
	public int firstIndexInEmptyRow;
	public int firstPBlocationX, firstPBlocationY;
	public int pbIndex1, pbIndex2, pbIndex3, pbIndex4, pbIndex5,pbIndex6;
	public int boardWidth, boardHeight;
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
	int timerCounter;
	int ghost_offset;
	int countOfGhostEatPacman = 0;
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
	//TimerCountdown timer = new TimerCountdown();

	public 	Board() {
		boardWidth = (int)(GameConstants.SCREEN_WIDTH * GameConstants.BOARD_PERCENT);
		boardHeight = (int)(GameConstants.SCREEN_HEIGHT * GameConstants.BOARD_PERCENT);
		blockWidth = calcBlockSize(map.length, boardWidth,boardHeight)[0];
		System.out.println("block width: " + blockWidth);
		blockHeight = calcBlockSize(map.length, boardWidth,boardHeight)[1];
		System.out.println("block height: " + blockHeight);
		boardOffset = (GameConstants.SCREEN_WIDTH - boardWidth)/2;
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
		//updateGhost();
		updateGhost(redGhost);
		drawGhost(g2, redGhost);
		updateGhost(blueGhost);
		drawGhost(g2, blueGhost);
		updateGhost(pinkGhost);
		drawGhost(g2, pinkGhost);
		updateGhost(orangeGhost);
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

		/*redGhostLoc = locationXYinTheArray(redGhost.getLocation_x(),redGhost.getLocation_y());
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
		}*/
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
							first_col_index = k - 4;
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
		ghost_offset =(int)( blockWidth/2 - image_for_size.getHeight(null)/2);
		System.out.println("image size: " + image_for_size.getHeight(null));
		redGhost = new Ghosts("src/Images/redGhostGIF.gif", randEmptyRow, firstIndexInEmptyRow, 
				randEmptyRow*blockHeight+ghost_offset, 
				(int)(firstIndexInEmptyRow*blockWidth+/*2*blockWidth+*/ghost_offset+boardOffset), LEFT, "rg");
		map[redGhost.getGrid_x()][redGhost.getGrid_y()] = "rg";//����� ��� ����

		System.out.println("redGhost grid_x: " + redGhost.getGrid_x() + " y "+redGhost.getGrid_y());
		System.out.println("redGhost location_x: " + redGhost.getLocation_x() + " y "+redGhost.getLocation_y());
		blueGhost = new Ghosts("src/Images/blueGhostGIF.gif", randEmptyRow, firstIndexInEmptyRow+1, 
				randEmptyRow*blockHeight+ghost_offset, 
				(int)((firstIndexInEmptyRow+1)*blockWidth+/*2*blockWidth+*/ghost_offset+boardOffset), LEFT, "bg");
		map[blueGhost.getGrid_x()][blueGhost.getGrid_y()] = "bg";
		
		System.out.println("blueGhost grid_x: " + blueGhost.getGrid_x() + " y "+blueGhost.getGrid_y());
		System.out.println("blueGhost location_x: " + blueGhost.getLocation_x() + " y "+blueGhost.getLocation_y());
		pinkGhost = new Ghosts("src/Images/pinkGhostGIF.gif", randEmptyRow, firstIndexInEmptyRow+5, 
				randEmptyRow*blockHeight+ghost_offset, 
				(int)((firstIndexInEmptyRow+5)*blockWidth+/*2*blockWidth+*/ghost_offset+boardOffset), RIGHT, "pg");
		map[pinkGhost.getGrid_x()][pinkGhost.getGrid_y()] = "pg";
		
		System.out.println("pinkGhost grid_x: " + pinkGhost.getGrid_x() + " y "+pinkGhost.getGrid_y());
		System.out.println("pinkGhost location_x: " + pinkGhost.getLocation_x() + " y "+pinkGhost.getLocation_y());
		orangeGhost = new Ghosts("src/Images/orangeGhostGIF.gif", randEmptyRow, firstIndexInEmptyRow+6, 
				randEmptyRow*blockHeight+ghost_offset, 
				(int)((firstIndexInEmptyRow+6)*blockWidth+/*2*blockWidth+*/ghost_offset+boardOffset), RIGHT,"og");
		map[orangeGhost.getGrid_x()][orangeGhost.getGrid_y()] = "og";
		
		System.out.println("orangeGhost grid_x: " + orangeGhost.getGrid_x() + " y "+orangeGhost.getGrid_y());
		System.out.println("orangeGhost location_x: " + orangeGhost.getLocation_x() + " y "+orangeGhost.getLocation_y());
	}

	/**
	 * This function calls the pacman to enter the game
	 */
	public void callPacman() {
		Image pacman_image_for_size = new ImageIcon("src/Images/pacman_rightGIF.gif").getImage();
		double pacman_offset = blockWidth/2 - pacman_image_for_size.getHeight(null)/2;
		pacman = new Pacman("src/Images/pacman_rightGIF.gif", randEmptyRow, firstIndexInEmptyRow+3,
				randEmptyRow*blockHeight + (int)pacman_offset,
				(int)((firstIndexInEmptyRow+3)*blockWidth+/*2*blockWidth+*/pacman_offset+boardOffset),
				RIGHT);
		pacman.setNameOnMap("pac");
		map[pacman.getGrid_x()][pacman.getGrid_y()] = pacman.getNameOnMap();
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
		powerBall_1.setNameOnMap("pb1");
		map[powerBall_1.getGrid_x()][powerBall_1.getGrid_y()] = powerBall_1.getNameOnMap();
		powerBall_2.setGameCharacterImage(power_ball_2_image);
		powerBall_2.setGrid_x(pbIndex1);
		powerBall_2.setGrid_y(pbIndex3);
		powerBall_2.setLocation_x(powerBall_2.getGrid_x()*blockHeight + (int)offsetPowerBall_w_h);
		powerBall_2.setLocation_y((int)(powerBall_2.getGrid_y()*blockWidth+boardOffset+offsetPowerBall_w_h));
		powerBall_2.setStatus(EXISTS);
		powerBall_2.setNameOnMap("pb2");
		map[powerBall_2.getGrid_x()][powerBall_2.getGrid_y()] = powerBall_2.getNameOnMap();
		powerBall_3.setGameCharacterImage(power_ball_3_image);
		powerBall_3.setGrid_x(pbIndex4);
		powerBall_3.setGrid_y(pbIndex5);
		powerBall_3.setLocation_x(powerBall_3.getGrid_x()*blockHeight + (int)offsetPowerBall_w_h);
		powerBall_3.setLocation_y((int)(powerBall_3.getGrid_y()*blockWidth+boardOffset+offsetPowerBall_w_h));
		powerBall_3.setStatus(EXISTS);
		powerBall_3.setNameOnMap("pb3");
		map[powerBall_3.getGrid_x()][powerBall_3.getGrid_y()] = powerBall_3.getNameOnMap();
		powerBall_4.setGameCharacterImage(power_ball_4_image);
		powerBall_4.setGrid_x(pbIndex4);
		powerBall_4.setGrid_y(pbIndex6);
		powerBall_4.setLocation_x(powerBall_4.getGrid_x()*blockHeight + (int)offsetPowerBall_w_h);
		powerBall_4.setLocation_y((int)(powerBall_4.getGrid_y()*blockWidth+boardOffset+offsetPowerBall_w_h));
		powerBall_4.setStatus(EXISTS);
		powerBall_4.setNameOnMap("pb4");
		map[powerBall_4.getGrid_x()][powerBall_4.getGrid_y()] = powerBall_4.getNameOnMap();
	}

	public void callLives() {
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
		firstLife.setStatus(EXISTS);
		secondLife.setGameCharacterImage(second_life_image);
		secondLife.setLocation_x((int)(GameConstants.SCREEN_HEIGHT/2- heart_image_for_size.getHeight(null)));
		secondLife.setLocation_y((int)(boardOffset/2-heart_offset));
		secondLife.setStatus(EXISTS);
		thirdLife.setGameCharacterImage(third_life_image);
		thirdLife.setLocation_x((int)(GameConstants.SCREEN_HEIGHT/2 + heart_image_for_size.getHeight(null)*2));
		thirdLife.setLocation_y((int)(boardOffset/2-heart_offset));
		thirdLife.setStatus(EXISTS);
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

			//loc_xy_in_map[0] = locationX/blockHeight;
			//grid_y:
			//loc_xy_in_map[1] = (locationY-boardOffset)/blockWidth;
			//I try work like this for not workinf with locationXYinTheArray 
			/*powerBall.setGrid_x(powerBall.getLocation_x()/blockHeight);
			powerBall.setGrid_y((powerBall.getLocation_y()-boardOffset)/blockWidth);*/
			//powerBall = locationXYinTheArray(powerBall_1.getLocation_x(),powerBall_1.getLocation_y());
			//map[powerBall.getGrid_x()][powerBall.getGrid_y()] = "pb1";
		}
	}

	private void drawLives(Graphics2D g2d, Lives lives) {
		if(lives.getStatus() == EXISTS) {
			g2d.drawImage(lives.getGameCharacterImage(), lives.getLocation_y(), 
					lives.getLocation_x(), this);
		}
	}

	/**
	 * This function gets a general position in pixels and returns a position on the array
	 * @param locationX
	 * @param locationY
	 * @return loc_xy_in_map - location on the map (grid location)
	 */
	public int[] locationXYinTheArray(int locationX, int locationY) {
		int []loc_xy_in_map = {-1,-1};
		boolean pass = sanityCheck(locationX, locationY);
		//double location_y_offset = 1.16;
		/*if(locationY < boardOffset || locationY > GameConstants.BOARD_WIDTH + boardOffset || 
				locationX <= 0 || locationX > GameConstants.BOARD_HEIGHT) {
			System.out.println("Your not in the borad");
		}
		else*/ if(locationY >= boardOffset && locationY <= (GameConstants.SCREEN_WIDTH - boardOffset) 
				&& locationX > 0 && locationX <= boardHeight) {
			//grid_x:
			loc_xy_in_map[0] = locationX/blockHeight;
			//grid_y:
			loc_xy_in_map[1] = (locationY-boardOffset)/blockWidth;
		}
		return loc_xy_in_map;
	}

	// in bounds
	// x > size/2 x < grid_size-space
	// y > board_offset+space y < grid_size-space
	private boolean sanityCheck(int x, int y) {
		int image_size = redGhost.getGhostImage().getWidth(null);
		double ghost_offset = (int)blockWidth/2 - image_size/2;
		if ((y >= boardOffset+ghost_offset-1) && (y <= boardOffset+blockWidth*map.length-ghost_offset)) {
			if  ((x >= ghost_offset) && (x <= blockWidth*map.length-ghost_offset)) {
				System.out.println("OK " + " x " + x + " y " + y);
				return true;
			}
		}
		System.out.println("NOT OK " + " x " + x + " y " + y);
		return false;
	}

	/**
	 * This function listens to the buttons that are pressed on the keyboard 
	 * and sends commands according to each button
	 */
	private void addKeyBoard(){
		Image pacman_image_for_size = new ImageIcon("src/Images/pacman_rightGIF.gif").getImage();
		double pacman_offset = blockWidth/2 - pacman_image_for_size.getHeight(null)/2;
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
						map[pacman.getGrid_x()][pacman.getGrid_y()] = EMPTY;
						pacman.setGrid_x(pacman.getGrid_x()-1);
						pacman.setGrid_y(pacman.getGrid_y());
						pacman.setLocation_x(pacman.getGrid_x()*blockHeight+(int)pacman_offset);
						pacman.setLocation_y(boardOffset+(pacman.getGrid_y()*blockWidth)+(int)pacman_offset);
						pacman.setPacmanImage(pacman_image_up);
						checkPowerBallTimer();
						if(isItSmallBallLocation(pacman.getGrid_x(), pacman.getGrid_y())) {
							System.out.println("Eat Small Ball");
							updateScore(SMALL_BALL);
						}

						map[pacman.getGrid_x()][pacman.getGrid_y()] = "pac";


					}
					break;
				case KeyEvent.VK_DOWN:
					if(isFree(pacman.getGrid_x()+1,pacman.getGrid_y()) == true){
						//direction = DOWN;
						Image pacman_image_down = new ImageIcon("src/Images/pacman_downGIF.gif").getImage();
						pacman.setDirection(DOWN);
						map[pacman.getGrid_x()][pacman.getGrid_y()] = EMPTY;
						pacman.setGrid_x(pacman.getGrid_x()+1);
						pacman.setGrid_y(pacman.getGrid_y());
						pacman.setLocation_x(pacman.getGrid_x()*blockHeight+(int)pacman_offset);
						pacman.setLocation_y(boardOffset+(pacman.getGrid_y()*blockWidth)+(int)pacman_offset);
						pacman.setPacmanImage(pacman_image_down);
						checkPowerBallTimer();
						if(isItSmallBallLocation(pacman.getGrid_x(), pacman.getGrid_y())) {
							System.out.println("Eat Small Ball");
							updateScore(SMALL_BALL);
						}
						//gameScore.updateScore(pacman.getGrid_x(), pacman.getGrid_y());
						map[pacman.getGrid_x()][pacman.getGrid_y()] = "pac";

					}
					break;
				case KeyEvent.VK_LEFT:
					if(isFree(pacman.getGrid_x(),pacman.getGrid_y()-1) == true){
						//direction = LEFT;
						Image pacman_image_left = new ImageIcon("src/Images/pacman_leftGIF.gif").getImage();
						pacman.setDirection(LEFT);
						map[pacman.getGrid_x()][pacman.getGrid_y()] = EMPTY;
						pacman.setGrid_x(pacman.getGrid_x());
						pacman.setGrid_y(pacman.getGrid_y()-1);
						pacman.setLocation_x(pacman.getGrid_x()*blockHeight+(int)pacman_offset);
						pacman.setLocation_y(boardOffset+(pacman.getGrid_y()*blockWidth)+(int)pacman_offset);
						pacman.setPacmanImage(pacman_image_left);
						checkPowerBallTimer();
						if(isItSmallBallLocation(pacman.getGrid_x(), pacman.getGrid_y())) {
							System.out.println("Eat Small Ball");
							updateScore(SMALL_BALL);
						}
						map[pacman.getGrid_x()][pacman.getGrid_y()] = "pac";


					}
					break;
				case KeyEvent.VK_RIGHT:
					if(isFree(pacman.getGrid_x(),pacman.getGrid_y()+1) == true){
						//direction = RIGHT;
						Image pacman_image_right = new ImageIcon("src/Images/pacman_rightGIF.gif").getImage();
						pacman.setDirection(RIGHT);
						map[pacman.getGrid_x()][pacman.getGrid_y()] = EMPTY;
						pacman.setGrid_x(pacman.getGrid_x());
						pacman.setGrid_y(pacman.getGrid_y()+1);
						pacman.setLocation_x(pacman.getGrid_x()*blockHeight+(int)pacman_offset);
						pacman.setLocation_y(boardOffset+(pacman.getGrid_y()*blockWidth)+(int)pacman_offset);
						pacman.setPacmanImage(pacman_image_right);
						checkPowerBallTimer();
						if(isItSmallBallLocation(pacman.getGrid_x(), pacman.getGrid_y())) {
							System.out.println("Eat Small Ball");
							updateScore(SMALL_BALL);
						}
						map[pacman.getGrid_x()][pacman.getGrid_y()] = "pac";

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
			if (/*map[x][y] != BLUE*/ !map[x][y].equals(BLUE)) {
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
		if(/*map[x][y] == WHITE*/ map[x][y].equals(WHITE) ) { 
			return true;
		}
		return false;
	}


	public void init() {

	}

	private void scorePanel() {
		GridBagConstraints constraints = new GridBagConstraints( );
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
	}

	public TimerCountdown timerCountdown() {
		TimerCountdown timer = new TimerCountdown();
		timer.timerCountDown1();
		return timer;
	}

	private void checkPowerBallTimer() {
		final int second = 1000;
		if(isItPbLocatin(pacman.getGrid_x(), pacman.getGrid_y())) {
			System.out.println("Eat PB");
			timerCounter = GameConstants.GHOST_IN_ACTIVE_TIME;
			ActionListener task = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent evt) {
					if(timerCounter < 0) {
						Timer t = (Timer)evt.getSource();
						t.stop();
					}
					else {
						gameScore.setPowerBallCounter(timerCounter);
						timerCounter -= 1;
					}		
				}
			};
			Timer timer = new Timer(second, task);
			timer.start();
			updateScore(POWER_BALL);
			//gameScore.setTimer(timer.timerCountDown1());
			//timer.timerCountDown1();		
		}			
	}
	//check if this is the location of the pacman
	public boolean isItPacmanLocation(int x, int y) {
		if(map[x][y].equals("pac")) { 
			pacman.setStatus(NOT_EXIST);
			return true;
		}
		return false;

	}
	//check if the ghost eat the pacman
	public void isGhostEatPacman(Ghosts currentGhost) {
		if(isItPacmanLocation(currentGhost.getGrid_x(), currentGhost.getGrid_y())){
			System.out.println("The Ghost eat the pacman");
			countOfGhostEatPacman++;
			switch(countOfGhostEatPacman) {
			case 1:
				firstLife.setStatus(NOT_EXIST);
				break;
			case 2:
				secondLife.setStatus(NOT_EXIST);
				break;
			case 3:
				thirdLife.setStatus(NOT_EXIST);
				break;
			}


		}

	}

	//Fix and continue this function
	public void updateGhost(Ghosts current) {
		//boolean in_game = false;
		//Ghosts arr_ghost [] = {redGhost,blueGhost, pinkGhost, orangeGhost};
		int number_of_steps = blockWidth;
		//for(int i = 0; i < arr_ghost.length; i++) {
		//Ghosts current = arr_ghost[i];
		boolean pass = sanityCheck(current.getLocation_x(), current.getLocation_y());
		int prev_grid_x = current.getGrid_x();
		int prev_grid_y = current.getGrid_y();
		String name_at_the_next = " ";
		System.out.println("moveCounter of " + current.getNameOnMap() + ": " + current.getMoveCounter());
		if(current.getMoveCounter() < number_of_steps) {
			map[current.getGrid_x()][current.getGrid_y()] = current.getNameOnMap();
			current.setMoveCounter(current.getMoveCounter()+1);
			
			//per direction update location
			if(current.getDirection().equals(UP) ) {
				current.setLocation_x(current.getLocation_x()-1);
			}
			else if(current.getDirection().equals(DOWN)) {
				current.setLocation_x(current.getLocation_x()+1);
			}
			else if(current.getDirection().equals(RIGHT)) {
				current.setLocation_y(current.getLocation_y()+1);
			}
			else if(current.getDirection().equals(LEFT)) {
				current.setLocation_y(current.getLocation_y()-1);
			}
			//checking to changing map location and grid
			if(current.getMoveCounter() == (int)(number_of_steps/2)) {
				//map[pacman.getGrid_x()][pacman.getGrid_y()] = EMPTY;
				//pacman.setGrid_x(pacman.getGrid_x()-1);
				//pacman.setGrid_y(pacman.getGrid_y());
				//save white balls and power balls if they was exist
				if(current.getDirection().equals(UP) && !map[current.getGrid_x()-1][current.getGrid_y()].equals(BLUE) ) {
					
					/*if(map[current.getGrid_x()-1][current.getGrid_y()].equals(WHITE)) {
						map[current.getGrid_x()][current.getGrid_y()] = WHITE;
					}*/
					current.setGrid_x(current.getGrid_x()-1);
				}
				else if(current.getDirection().equals(DOWN) && !map[current.getGrid_x()+1][current.getGrid_y()].equals(BLUE)) {
					
					/*if(map[current.getGrid_x()][current.getGrid_y()].equals(WHITE)) {
						map[current.getGrid_x()][current.getGrid_y()] = WHITE;
					}*/
					current.setGrid_x(current.getGrid_x()+1);
				}
				else if(current.getDirection().equals(RIGHT) && !map[current.getGrid_x()][current.getGrid_y()+1].equals(BLUE)) {
					
					/*if(map[current.getGrid_x()][current.getGrid_y()].equals(WHITE)) {
						map[current.getGrid_x()][current.getGrid_y()] = WHITE;
					}*/
					current.setGrid_y(current.getGrid_y()+1);
				}
				else if(current.getDirection().equals(LEFT) && !map[current.getGrid_x()][current.getGrid_y()-1].equals(BLUE)) {
					
					/*if(map[current.getGrid_x()][current.getGrid_y()].equals(WHITE)) {
						map[current.getGrid_x()][current.getGrid_y()] = WHITE;
					}*/
					current.setGrid_y(current.getGrid_y()-1);
					//current.setGrid_y((current.getGrid_y()*blockWidth+blockWidth+ghost_offset)-1);
				}
				//current.setGrid_x(locationXYinTheArray(current.getLocation_x(), current.getLocation_y())[0]);
				//current.setGrid_y(locationXYinTheArray(current.getLocation_x(), current.getLocation_y())[1]);
				//changeGhostsLocation(current, current.getLocation_x(), current.getLocation_y(), current.getGhostImage());
				//if there was small ball in this location-

				//check whey
				/*if(map[current.getGrid_x()][current.getGrid_y()].equals(WHITE)) {
					map[prev_grid_x][prev_grid_y] = WHITE;
				}*/
				/*if(map[current.getGrid_x()][current.getGrid_y()].equals(EMPTY)) {
					map[prev_grid_x][prev_grid_y] = EMPTY;
				}
				if(map[current.getGrid_x()][current.getGrid_y()].equals(WHITE)) {
					map[prev_grid_x][prev_grid_y] = WHITE;
				}

				if(map[current.getGrid_x()][current.getGrid_y()].equals("pac")) {
					map[prev_grid_x][prev_grid_y] = "pac";
				}*/
				if(map[current.getGrid_x()][current.getGrid_y()].equals(EMPTY)) {
					map[prev_grid_x][prev_grid_y] = EMPTY;
				}
				else
				map[prev_grid_x][prev_grid_y] = WHITE;
				isGhostEatPacman(current);
			}
		}
		else if(current.getMoveCounter() >= number_of_steps) {
			
			/*redGhost = new Ghosts("src/Images/redGhostGIF.gif", randEmptyRow, firstIndexInEmptyRow, 
						randEmptyRow*blockHeight+(int)ghost_offset, 
						(int)(firstIndexInEmptyRow*blockWidth+blockWidth-ghost_offset), LEFT, "rg");*/
			//make sure we are in the right position
			int x = current.getGrid_x()*blockHeight+ghost_offset;
			int y = current.getGrid_y()*blockWidth+/*2*blockWidth+*/ghost_offset+boardOffset;
			current.setLocation_x(x);
			current.setLocation_y(y);
			
			current.setMoveCounter(0);
			//lock for possible direction
			current.setDirection(getGhostDirection(current.getGrid_x(),current.getGrid_y(), current.getDirection()));
			
			map[current.getGrid_x()][current.getGrid_y()] = current.getNameOnMap();
			
			//current.setDirection(getGhostDirection(x,y, current.getDirection()));
			if(current.getDirection().equals(UP)) {
				current.setDirection(UP);
				//changeGhostsLocation(current, current.getLocation_x(), current.getLocation_y()-1, current.getGhostImage());
			}
			else if(current.getDirection().equals(DOWN) ) {
				current.setDirection(DOWN);
				//changeGhostsLocation(current, current.getLocation_x()+1, current.getLocation_y(), current.getGhostImage());
			}
			else if(current.getDirection().equals(RIGHT) ) {
				current.setDirection(RIGHT);
				//changeGhostsLocation(current, current.getLocation_x(), current.getLocation_y()+1, current.getGhostImage());
			}
			else if(current.getDirection().equals(LEFT) ) {
				current.setDirection(LEFT);
				//changeGhostsLocation(current, current.getLocation_x(), current.getLocation_y()-1, current.getGhostImage());
			}
			
			current.setMoveCounter(current.getMoveCounter()+1);
		}
		pass = sanityCheck(current.getLocation_x(), current.getLocation_y());
		//}

		/*if(in_game == false) {
			redGhost.setLocation_x(redGhost.getLocation_x());
			redGhost.setLocation_y(redGhost.getLocation_y()-1);//At first it goes left
			redGhost.setGrid_x(locationXYinTheArray(redGhost.getLocation_x(), redGhost.getLocation_y())[0]);
			redGhost.setGrid_y(locationXYinTheArray(redGhost.getLocation_x(), redGhost.getLocation_y())[1]);
			redGhost.setGhostImage(redGhost.getGhostImage());
			blueGhost.setLocation_x(blueGhost.getLocation_x());
			blueGhost.setLocation_y(blueGhost.getLocation_y()-1);//At first it goes left
			blueGhost.setGrid_x(locationXYinTheArray(blueGhost.getLocation_x(), blueGhost.getLocation_y())[0]);
			blueGhost.setGrid_y(locationXYinTheArray(blueGhost.getLocation_x(), blueGhost.getLocation_y())[1]);
			blueGhost.setGhostImage(blueGhost.getGhostImage());
			pinkGhost.setLocation_x(pinkGhost.getLocation_x());
			pinkGhost.setLocation_y(pinkGhost.getLocation_y()+1);//At first it goes right
			pinkGhost.setGrid_x(locationXYinTheArray(pinkGhost.getLocation_x(), pinkGhost.getLocation_y())[0]);
			pinkGhost.setGrid_y(locationXYinTheArray(pinkGhost.getLocation_x(), pinkGhost.getLocation_y())[1]);
			pinkGhost.setGhostImage(pinkGhost.getGhostImage());
			orangeGhost.setLocation_x(orangeGhost.getLocation_x());
			orangeGhost.setLocation_y(orangeGhost.getLocation_y()+1);//At first it goes right
			orangeGhost.setGrid_x(locationXYinTheArray(orangeGhost.getLocation_x(), orangeGhost.getLocation_y())[0]);
			orangeGhost.setGrid_y(locationXYinTheArray(orangeGhost.getLocation_x(), orangeGhost.getLocation_y())[1]);
			orangeGhost.setGhostImage(orangeGhost.getGhostImage());
			in_game = true;
		}
		else {
			if(junc.getnumOfOption() == 2 || junc.getnumOfOption() == 3) {
			int randDirection = (int) (Math.random()*junc.getnumOfOption());
				//if(junc.getDirection().charAt(randDirection) ==  UP) {
					//changeGhostsDirection

				//}

			}		
		}*/
	}

	private String getGhostDirection(int x, int y, String prev_direction) {

		direction = "";
		int index_to_remove;
		char char_to_remove;
		if(x-1 >= 0 && /*map[x-1][y] != BLUE*/ !map[x-1][y].equals(BLUE)) {
			direction = direction.concat(UP);
		}
		if(x+1 < map.length && /*map[x+1][y] !=BLUE*/ !map[x+1][y].equals(BLUE)) {
			direction = direction.concat(DOWN);
		}
		if(y-1 >= 0 && /*map[x][y-1] != BLUE*/ !map[x][y-1].equals(BLUE)) {
			direction = direction.concat(LEFT);
		}
		if(y+1 < map.length && /*map[x][y+1] != BLUE*/ !map[x][y+1].equals(BLUE)) {
			direction = direction.concat(RIGHT);
		}
		//check if I need to removw the direction I fame from
		if(prev_direction.equals(LEFT)) {
			index_to_remove = direction.indexOf(RIGHT);
			char_to_remove = direction.charAt(index_to_remove);
			//char [] charDirections = direction.toCharArray();
			//charDirections
			//char [] charUpdated = charDirections.
			direction = direction.replace(char_to_remove, ' ');
			System.out.println("x=" + x +" y=" + y + " = " + direction);	
		}
		if(prev_direction.equals(RIGHT)) {
			index_to_remove = direction.indexOf(LEFT);
			char_to_remove = direction.charAt(index_to_remove);
			direction = direction.replace(char_to_remove, ' ');
			System.out.println("x=" + x +" y=" + y + " = " + direction);
		}
		if(prev_direction.equals(UP)) {
			index_to_remove = direction.indexOf(DOWN);
			char_to_remove = direction.charAt(index_to_remove);
			direction = direction.replace(char_to_remove, ' ');
			System.out.println("x=" + x +" y=" + y + " = " + direction);
		}
		if(prev_direction.equals(DOWN)) {
			index_to_remove = direction.indexOf(UP);
			char_to_remove = direction.charAt(index_to_remove);
			direction = direction.replace(char_to_remove, ' ');
			System.out.println("x=" + x +" y=" + y + " = " + direction);
		}
		if(direction.length() == 0) {
			System.out.println("no direction");
		}
		//TODO check if there is ghost at this direction near to it- if there is- go to another direction
		//if(String.valueOf(direction.charAt(0)).equals(' ')) {
		if(direction.charAt(0) == ' '){
			direction =String.valueOf(direction.charAt(1));
		}
		else {
			direction =String.valueOf(direction.charAt(0));
		}
		return direction;
	}

	/*private String getGhostDirection(int x, int y, String prev_direction) {
		//String directions="";
		direction = "";
		if (map[x][y] != BLUE && x >= 0 && y >= 0 && x <= map.length && y <= map.length) {
			//if there is 3 options
			if(x>0 && y>0 && x<map.length-1 && y<map.length-1 && 
					map[x-1][y] != BLUE && map[x+1][y] != BLUE && map[x][y-1] != BLUE &&
					map[x][y+1] != BLUE) {
				if(prev_direction.equals(LEFT)) {
					direction = direction.concat(LEFT+UP+DOWN);
				}
				else if(prev_direction.equals(RIGHT)) {
					direction = direction.concat(RIGHT+UP+DOWN);
				}
				else if(prev_direction.equals(UP)) {
					direction = direction.concat(UP+RIGHT+LEFT);
				}
				else if(prev_direction.equals(DOWN)) {
					direction = direction.concat(DOWN+RIGHT+LEFT);
				}
			}
			//if there is 2 options
			else if((y == 0 && x>0 && x<map.length-1 && map[x-1][y] != BLUE && map[x+1][y] != BLUE && map[x][y+1] != BLUE)) {
				if(prev_direction.equals(LEFT)) {
					direction = direction.concat(UP+DOWN);
				}
				else if(prev_direction.equals(UP)) {
					direction = direction.concat(UP+RIGHT);
				}
				else if(prev_direction.equals(DOWN)) {
					direction = direction.concat(DOWN+RIGHT);
				}
			}
			else if(y == map.length-1 && x>0 && x<map.length-1 && map[x-1][y] != BLUE && map[x+1][y] != BLUE && map[x][y-1] != BLUE) {
				if(prev_direction.equals(RIGHT)) {
					direction = direction.concat(UP+DOWN);
				}
				else if(prev_direction.equals(UP)) {
					direction = direction.concat(UP+LEFT);
				}
				else if(prev_direction.equals(DOWN)) {
					direction = direction.concat(DOWN+LEFT);
				}
			}
			else if(x == 0 && y>0 && y<map.length-1 && map[x][y-1] != BLUE && map[x][y+1] != BLUE && map[x+1][y] != BLUE) {
				if(prev_direction.equals(LEFT)) {
					direction = direction.concat(LEFT+DOWN);
				}
				else if(prev_direction.equals(RIGHT)) {
					direction = direction.concat(RIGHT+DOWN);
				}
				else if(prev_direction.equals(UP)) {
					direction = direction.concat(RIGHT+LEFT);
				}
			}
			else if(x == map.length-1 && y>0 && y<map.length-1 && map[x][y-1] != BLUE && map[x][y+1] != BLUE && map[x-1][y] != BLUE) {
				if(prev_direction.equals(LEFT)) {
					direction = direction.concat(LEFT+UP);
				}
				else if(prev_direction.equals(RIGHT)) {
					direction = direction.concat(RIGHT+UP);
				}
				else if(prev_direction.equals(DOWN)) {
					direction = direction.concat(RIGHT+LEFT);
				}
			}
			else if(map[x][y-1] == BLUE && x>0 && x<map.length-1 && map[x-1][y] != BLUE && map[x+1][y] != BLUE && map[x][y+1] != BLUE) {
				if(prev_direction.equals(LEFT)) {
					direction = direction.concat(UP+DOWN);
				}
				else if(prev_direction.equals(UP)) {
					direction = direction.concat(UP+RIGHT);
				}
				else if(prev_direction.equals(DOWN)) {
					direction = direction.concat(DOWN+RIGHT);
				}
			}
			else if(map[x][y+1] == BLUE && x>0 && x<map.length-1 && map[x-1][y] != BLUE && map[x+1][y] != BLUE && map[x][y-1] != BLUE) {
				if(prev_direction.equals(RIGHT)) {
					direction = direction.concat(UP+DOWN);
				}
				else if(prev_direction.equals(UP)) {
					direction = direction.concat(UP+LEFT);
				}
				else if(prev_direction.equals(DOWN)) {
					direction = direction.concat(DOWN+LEFT);
				}
			}
			else if(map[x-1][y] == BLUE && y>0 && y<map.length-1 && map[x][y-1] != BLUE && map[x][y+1] != BLUE && map[x+1][y] != BLUE) {
				if(prev_direction.equals(LEFT)) {
					direction = direction.concat(LEFT+DOWN);
				}
				else if(prev_direction.equals(RIGHT)) {
					direction = direction.concat(RIGHT+DOWN);
				}
				else if(prev_direction.equals(UP)) {
					direction = direction.concat(RIGHT+LEFT);
				}
			}
			else if(map[x+1][y] == BLUE && y>0 && y<map.length-1 && map[x][y-1] != BLUE && map[x][y+1] != BLUE && map[x-1][y] != BLUE){
				if(prev_direction.equals(LEFT)) {
					direction = direction.concat(LEFT+UP);
				}
				else if(prev_direction.equals(RIGHT)) {
					direction = direction.concat(RIGHT+UP);
				}
				else if(prev_direction.equals(DOWN)) {
					direction = direction.concat(RIGHT+LEFT);
				}
			}
			//if there is 1 option
			else if(x+1 >= 0 && y+1 < map.length-1 && map[x+1][y] != BLUE && map[x][y+1] != BLUE) {
				//if(map[x+1][y] != BLUE && map[x][y+1] != BLUE) {
					if(prev_direction.equals(LEFT)) {
						direction = direction.concat(DOWN);
					}
					else {
						direction = direction.concat(RIGHT);
					}
				//}
			}
			//check left-down corner
			else if(x-1 >= 0 && y+1 < map.length-1 && map[x-1][y] != BLUE && map[x][y+1] != BLUE) {
				//if() {
					if(prev_direction.equals(LEFT)) {
						direction = direction.concat(UP);
					}
					else {
						direction = direction.concat(RIGHT);
					}
				//}
			}
			//check right-down corner
			else if(x-1 >= 0 && y-1 >= 0 && map[x-1][y] != BLUE && map[x][y-1] != BLUE) {
				//if() {
					if(prev_direction.equals(RIGHT)) {
						direction = direction.concat(UP);
					}
					else {
						direction = direction.concat(LEFT);
					}
				//}
			}
			//check right-up corner
			else if(x+1 <= map.length-1 && y-1 >= 0 && map[x+1][y] != BLUE && map[x][y-1] != BLUE) {
				//if() {
					if(prev_direction.equals(UP)) {
						direction = direction.concat(LEFT);
					}
					else {
						direction = direction.concat(DOWN);
					}
				//}
			}
			//between two walls above and below
			else if(x-1 >= 0 && x+1 <= map.length-1 && map[x-1][y] == BLUE && map[x+1][y] == BLUE) {
				//if(map[x-1][y] == BLUE && map[x+1][y] == BLUE) {
					direction = prev_direction;
				//}
			}
			//between two walls right and left
			else if(y-1 >= 0 && y+1 <= map.length-1 && map[x][y-1] == BLUE && map[x][y+1] == BLUE) {
				//if(map[x][y-1] == BLUE && map[x][y+1] == BLUE) {
					direction = prev_direction;
				//}
			}
			//edge on the left and wall on the right
			else if(y == 0 && map[x][y+1] == BLUE) {
				direction = prev_direction;
			}
			//edge on the right and wall on the left
			else if(y == map.length-1 && map[x][y-1] == BLUE) {
				direction = prev_direction;
			}
			//edge on top and wall on bottom
			else if(x == 0 && map[x+1][y] == BLUE) {
				direction = prev_direction;
			}
			//edge on bottom and wall on top
			else if(x == map.length-1 && map[x-1][y] == BLUE) {
				direction = prev_direction;
			}
			// up
			// make sure not zero
			else if (x-1 >= 0) { // top row
				if (map[x-1][y] == WHITE) {
					direction = direction.concat(UP);
				}
			}
			// down
			// make sure not bottom
			else if (x+1 <= map.length-1) {
				if (map[x+1][y] == WHITE) {
					direction = direction.concat(DOWN);
				}
			}
			// left
			// make sure not zero
			else if (y-1 >= 0) {
				if (map[x][y-1] == WHITE) {
					direction = direction.concat(LEFT);
				}
			}
			// right
			// make sure not right
			else if (y+1 <= map.length-1) {
				if (map[x][y+1] == WHITE) {
					direction = direction.concat(RIGHT);
				}
			}
		}
		// check value
		System.out.println(" Gridx " + x + " Gridy " + y + " directions " + direction);
		if (direction.length() == 0) {
			System.out.println(" no no!! ");
		}
		if(direction.length() < 2) {
			direction =prev_direction;
		}

		if(direction.length() == 2) {
			direction =prev_direction;
		}
		else if(direction.length() >= 3) {
			if(String.valueOf(direction.charAt(0)).equals(UP) && prev_direction.equals(DOWN)) {
				direction =String.valueOf(direction.charAt(1));
			}
			else if(String.valueOf(direction.charAt(0)).equals(DOWN) && prev_direction.equals(UP)) {
				direction =String.valueOf(direction.charAt(1));
			}
			else if(String.valueOf(direction.charAt(0)).equals(RIGHT) && prev_direction.equals(LEFT)) {
				direction =String.valueOf(direction.charAt(1));
			}
			else if(String.valueOf(direction.charAt(0)).equals(LEFT) && prev_direction.equals(RIGHT)) {
				direction =String.valueOf(direction.charAt(1));
			}
			else {
				direction =String.valueOf(direction.charAt(0));
			}
		}
		System.out.println("New direction: " + direction);
		return direction;
	}*/

	/*private String getGhostDirection(int x, int y, String prev_direction) {
		String direction = "";
		//2 directions options
		if()

		//edge on the left and wall on the right
		if(y == 0 && map[x][y+1] == BLUE) {
			direction = prev_direction;
		}
		//edge on the right and wall on the left
		if(y == map.length-1 && map[x][y-1] == BLUE) {
			direction = prev_direction;
		}
		//edge on top and wall on bottom
		if(x == 0 && map[x+1][y] == BLUE) {
			direction = prev_direction;
		}
		//edge on bottom and wall on top
		if(x == map.length-1 && map[x-1][y] == BLUE) {
			direction = prev_direction;
		}
		//check left-up corner
		if(x+1 <= map.length-1 && y+1 < map.length-1) {
			if(map[x+1][y] != BLUE && map[x][y+1] != BLUE) {
				if(prev_direction.equals(LEFT)) {
					direction = direction.concat(DOWN);
				}
				else {
					direction = direction.concat(RIGHT);
				}
			}
		}
		//check left-down corner
		if(x-1 >= 0 && y+1 < map.length-1) {
			if(map[x-1][y] != BLUE && map[x][y+1] != BLUE) {
				if(prev_direction.equals(LEFT)) {
					direction = direction.concat(UP);
				}
				else {
					direction = direction.concat(RIGHT);
				}
			}
		}
		//check right-down corner
		if(x-1 >= 0 && y-1 >= 0) {
			if(map[x-1][y] != BLUE && map[x][y-1] != BLUE) {
				if(prev_direction.equals(RIGHT)) {
					direction = direction.concat(UP);
				}
				else {
					direction = direction.concat(LEFT);
				}
			}
		}
		//check right-up corner
		if(x+1 <= map.length-1 && y-1 >= 0) {
			if(map[x+1][y] != BLUE && map[x][y-1] != BLUE) {
				if(prev_direction.equals(UP)) {
					direction = direction.concat(LEFT);
				}
				else {
					direction = direction.concat(DOWN);
				}
			}
		}
		//edge on the left and wall on the right
		if(y == 0 && map[x][y+1] == BLUE) {
			direction = prev_direction;
		}
		//edge on the right and wall on the left
		if(y == map.length-1 && map[x][y-1] == BLUE) {
			direction = prev_direction;
		}
		//edge on top and wall on bottom
		if(x == 0 && map[x+1][y] == BLUE) {
			direction = prev_direction;
		}
		//edge on bottom and wall on top
		if(x == map.length-1 && map[x-1][y] == BLUE) {
			direction = prev_direction;
		}
		//check up
		if(x-1 >= 0) {
			if(map[x-1][y] != BLUE) {
				direction = direction.concat(UP);
			}
		}
		//check down
		if(x+1 <= map.length-1) {
			if(map[x+1][y] != BLUE) {
				direction = direction.concat(DOWN);
			}
		}
		//check right
		if(y+1 < map.length-1) {
			if(map[x][y+1] != BLUE) {
				direction = direction.concat(RIGHT);
			}
		}
		//check left
		if(y-1 >= 0) {
			if(map[x][y-1] != BLUE) {
				direction = direction.concat(LEFT);
			}
		}

		// check value
		System.out.println(" x " + x + " y " + y + " directions " + direction);
		//if (direction.length() > 0) {
		//direction =String.valueOf(direction.charAt(0));
		if(direction.length() < 2) {
			direction =prev_direction;
		}

		if(direction.length() == 2) {
			direction =prev_direction;
		}
		else if(direction.length() >= 3) {
			if(String.valueOf(direction.charAt(0)).equals(UP) && prev_direction.equals(DOWN)) {
				direction =String.valueOf(direction.charAt(1));
			}
			else if(String.valueOf(direction.charAt(0)).equals(DOWN) && prev_direction.equals(UP)) {
				direction =String.valueOf(direction.charAt(1));
			}
			else if(String.valueOf(direction.charAt(0)).equals(RIGHT) && prev_direction.equals(LEFT)) {
				direction =String.valueOf(direction.charAt(1));
			}
			else if(String.valueOf(direction.charAt(0)).equals(LEFT) && prev_direction.equals(RIGHT)) {
				direction =String.valueOf(direction.charAt(1));
			}
			else {
				direction =String.valueOf(direction.charAt(0));
			}
		}


		/*if(direction == "UD" || direction == "DU" || direction == "RL" || direction == "LR") {
				direction = prev_direction;

			}*/

	/*else {
				direction =String.valueOf(direction.charAt(0));
			}


		//}
		System.out.println("New direction: " + direction);
		//System.out.println(direction.equalsIgnoreCase(RIGHT));
		return direction;
	}*/

	private void changeGhostsLocation(Ghosts ghost, int location_x, int location_y, Image image) {
		ghost.setLocation_x(location_x);
		ghost.setLocation_y(location_y);
		ghost.setGrid_x(locationXYinTheArray(location_x, location_y)[0]);
		ghost.setGrid_y(locationXYinTheArray(location_x, location_y)[1]);
		ghost.setGameCharacterImage(image);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}
}