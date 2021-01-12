package Board_Package;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.stream.IntStream;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Game_Constants_Package.GameConstants;
import Game_Objects.Ghosts;
import Game_Objects.Pacman;
import Game_Objects.Power_Ball;
import Log_Package.PacmanLog;

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
	Ghosts redGhost, blueGhost, pinkGhost, orangeGhost;
	Pacman pacman;
	Power_Ball powerBall_1, powerBall_2, powerBall_3, powerBall_4;
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
	String UP = "U";
	String DOWN = "D";
	String LEFT = "L";
	String RIGHT = "R";
	ArrayList<Junction> junctionArrList = new ArrayList<Junction>();
	Junction junc = new Junction();

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
		locationBallX = calcLocationBall(blockWidth)[0];
		//System.out.println("locationBallX = " + locationBallX);
		locationBallY = calcLocationBall(blockHeight)[1];
		//System.out.println("locationBallY = " + locationBallY);
		createJunction();
		createPowerBalls();
		//locInArray = locationXYinTheArray(34,95);
		//System.out.print("loc_xy_in_map[0] = " + locInArray[0]+", ");
		//System.out.println("loc_xy_in_map[1] = " + locInArray[1]);

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

	private void createJunction() {
		ArrayList<Junction> junctionsArrList = new ArrayList<Junction>();
		String BLUE = "1";
		String WHITE = "0";
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
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
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
					g2d.fillOval(j*blockWidth+boardOffset+(int)(blockWidth*0.4), i*blockHeight+(int)(blockWidth*0.4), (int)(blockWidth*0.25), (int)(blockHeight*0.25));
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
		//double offset_ghost_pacman_w = 1.05;
		//double offset_ghost_pacman_h = 1.015;
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
		drawPacman(g2, pacman);
		drawPowerBall(g2, powerBall_1);
		drawPowerBall(g2, powerBall_2);
		drawPowerBall(g2, powerBall_3);
		drawPowerBall(g2, powerBall_4);
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
		map[pacmanLoc[0]][pacmanLoc[1]] = "pac";
		pbLoc1 = locationXYinTheArray(powerBall_1.getLocation_x(),powerBall_1.getLocation_y());
		map[pbLoc1[0]][pbLoc1[1]] = "pb1";
		pbLoc2 = locationXYinTheArray(powerBall_2.getLocation_x(),powerBall_2.getLocation_y());
		map[pbLoc2[0]][pbLoc2[1]] = "pb2";
		pbLoc3 = locationXYinTheArray(powerBall_3.getLocation_x(),powerBall_3.getLocation_y());
		map[pbLoc3[0]][pbLoc3[1]] = "pb3";
		pbLoc4 = locationXYinTheArray(powerBall_4.getLocation_x(),powerBall_4.getLocation_y());
		map[pbLoc4[0]][pbLoc4[1]] = "pb4";
		printMap();
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
		//loationBallX = (int) (boardOffset+boardOffset*0.13+blockWidth);
		locationBallX = blockWidth+boardOffset+(int)(blockWidth*0.4);
		//loationBallY = blockHeight+blockHeight/2;
		locationBallY = (int) (blockHeight*1.39);
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
		int min_i = gameBoard.length-1;
		int max_i = 0;
		int min_j_first_row = gameBoard.length-1;
		int max_j_first_row = 0;
		int min_j_last_row = gameBoard.length-1;
		int max_j_last_row = 0;
		int [] index_pb = new int[6];
		for (int i = 0; i < gameBoard.length; i++) {
			for (int j = 0; j < gameBoard.length; j++) {
				if(gameBoard[i][j] == "0") {
					if(map[i][j] == "0") {
						//Saves the indexes to the top balls
						if(i < min_i) {
							min_i = i;
							index_pb[0] = i;
							if(j < min_j_first_row) {
								index_pb[1] = j;
								max_j_first_row = j;
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
		}
		return index_pb;
	}

	/**
	 * This function calls the ghosts to enter the game
	 */
	public void callGhosts() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		redGhost = new Ghosts(null);
		blueGhost = new Ghosts(null);
		pinkGhost = new Ghosts(null);
		orangeGhost = new Ghosts(null);
		Image red_ghost_image = new ImageIcon("src/Images/redGhostGIF.gif").getImage();
		Image blue_ghost_image = new ImageIcon("src/Images/blueGhostGIF.gif").getImage();
		Image pink_ghost_image = new ImageIcon("src/Images/pinkGhostGIF.gif").getImage();
		Image orange_ghost_image = new ImageIcon("src/Images/orangeGhostGIF.gif").getImage();
		double ghost_offset = blockWidth/2 - red_ghost_image.getHeight(null)/2;
		redGhost.setGameCharacterImage(red_ghost_image);
		redGhost.setGrid_x(randEmptyRow);
		redGhost.setGrid_y(firstIndexInEmptyRow);
		redGhost.setLocation_x(redGhost.getGrid_x()*blockHeight + (int)ghost_offset);
		redGhost.setLocation_y((int) (redGhost.getGrid_y()*blockWidth/**offsetGhostPacman_w)*/+blockWidth-ghost_offset));
		redGhost.setDirection(LEFT);
		System.out.println("redGhost grid_x: " + redGhost.getGrid_x() + " y "+redGhost.getGrid_y());
		System.out.println("redGhost location_x: " + redGhost.getLocation_x() + " y "+redGhost.getLocation_y());
		blueGhost.setGameCharacterImage(blue_ghost_image);
		blueGhost.setGrid_x(randEmptyRow);
		blueGhost.setGrid_y(firstIndexInEmptyRow+1);
		blueGhost.setLocation_x(blueGhost.getGrid_x()*blockHeight + (int)ghost_offset);
		blueGhost.setLocation_y((int) (blueGhost.getGrid_y()*blockWidth/**offsetGhostPacman_w)*/+blockWidth-ghost_offset));
		blueGhost.setDirection(LEFT);
		System.out.println("blueGhost grid_x: " + blueGhost.getGrid_x() + " y "+blueGhost.getGrid_y());
		System.out.println("blueGhost location_x: " + blueGhost.getLocation_x() + " y "+blueGhost.getLocation_y());
		pinkGhost.setGameCharacterImage(pink_ghost_image);
		pinkGhost.setGrid_x(randEmptyRow);
		pinkGhost.setGrid_y(firstIndexInEmptyRow+2);
		pinkGhost.setLocation_x(pinkGhost.getGrid_x()*blockHeight + (int)ghost_offset);
		pinkGhost.setLocation_y((int) (pinkGhost.getGrid_y()*blockWidth/**offsetGhostPacman_w)*/+blockWidth-ghost_offset));
		pinkGhost.setDirection(RIGHT);
		System.out.println("pinkGhost grid_x: " + pinkGhost.getGrid_x() + " y "+pinkGhost.getGrid_y());
		System.out.println("pinkGhost location_x: " + pinkGhost.getLocation_x() + " y "+pinkGhost.getLocation_y());
		orangeGhost.setGameCharacterImage(orange_ghost_image);
		orangeGhost.setGrid_x(randEmptyRow);
		orangeGhost.setGrid_y(firstIndexInEmptyRow+3);
		orangeGhost.setLocation_x(orangeGhost.getGrid_x()*blockHeight + (int)ghost_offset);
		orangeGhost.setLocation_y((int) (orangeGhost.getGrid_y()*blockWidth/**offsetGhostPacman_w)*/+blockWidth-ghost_offset));
		orangeGhost.setDirection(RIGHT);
		System.out.println("orangeGhost grid_x: " + orangeGhost.getGrid_x() + " y "+orangeGhost.getGrid_y());
		System.out.println("orangeGhost location_x: " + orangeGhost.getLocation_x() + " y "+orangeGhost.getLocation_y());
	}

	/**
	 * This function calls the pacman to enter the game
	 */
	public void callPacman() {
		pacman = new Pacman(null);
		Image pacman_image = new ImageIcon("src/Images/pacman_rightGIF.gif").getImage();
		double pacman_offset = blockWidth/2 - pacman_image.getHeight(null)/2;
		pacman.setGameCharacterImage(pacman_image);
		//(int)(firstIndexInEmptyRow*blockWidth*offset_ghost_pacman_w)+blockWidth*5+boardOffset, (int)(randEmptyRow*blockHeight*offset_ghost_pacman_h))
		pacman.setGrid_x(randEmptyRow);
		pacman.setGrid_y(firstIndexInEmptyRow+5);
		pacman.setLocation_x(pacman.getGrid_x()*blockHeight + (int)pacman_offset);
		pacman.setLocation_y((int)(pacman.getGrid_y()*blockWidth/**offsetGhostPacman_w)*/+blockWidth-pacman_offset));
		System.out.println("pacman grid_x: " + pacman.getGrid_x() + " y "+pacman.getGrid_y());
		System.out.println("pacman location_x: " + pacman.getLocation_x() + " y "+pacman.getLocation_y());
	}

	/**
	 * This function calls the power balls to enter the game
	 */
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
		Image power_ball_1_image = new ImageIcon("src/Images/powerball.png").getImage();
		Image power_ball_2_image = new ImageIcon("src/Images/powerball.png").getImage();
		Image power_ball_3_image = new ImageIcon("src/Images/powerball.png").getImage();
		Image power_ball_4_image = new ImageIcon("src/Images/powerball.png").getImage();
		double offsetPowerBall_w_h = blockWidth/2 - power_ball_1_image.getHeight(null)/2;;
		powerBall_1.setGameCharacterImage(power_ball_1_image);
		powerBall_1.setGrid_x(pbIndex2);
		powerBall_1.setGrid_y(pbIndex1);
		powerBall_1.setLocation_x(powerBall_1.getGrid_x()*blockHeight + (int)offsetPowerBall_w_h);
		powerBall_1.setLocation_y((int)(powerBall_1.getGrid_y()*blockWidth+boardOffset+offsetPowerBall_w_h));
		powerBall_2.setGameCharacterImage(power_ball_2_image);
		powerBall_2.setGrid_x(pbIndex3);
		powerBall_2.setGrid_y(pbIndex1);
		powerBall_2.setLocation_x(powerBall_2.getGrid_x()*blockHeight + (int)offsetPowerBall_w_h);
		powerBall_2.setLocation_y((int)(powerBall_2.getGrid_y()*blockWidth+boardOffset+offsetPowerBall_w_h));
		powerBall_3.setGameCharacterImage(power_ball_3_image);
		powerBall_3.setGrid_x(pbIndex5);
		powerBall_3.setGrid_y(pbIndex4);
		powerBall_3.setLocation_x(powerBall_3.getGrid_x()*blockHeight + (int)offsetPowerBall_w_h);
		powerBall_3.setLocation_y((int)(powerBall_3.getGrid_y()*blockWidth+boardOffset+offsetPowerBall_w_h));
		powerBall_4.setGameCharacterImage(power_ball_4_image);
		powerBall_4.setGrid_x(pbIndex6);
		powerBall_4.setGrid_y(pbIndex4);
		powerBall_4.setLocation_x(powerBall_4.getGrid_x()*blockHeight + (int)offsetPowerBall_w_h);
		powerBall_4.setLocation_y((int)(powerBall_4.getGrid_y()*blockWidth+boardOffset+offsetPowerBall_w_h));
	}

	/**
	 * This function draws the ghosts on the game board
	 * @param g2d
	 * @param ghost
	 */
	private void drawGhost(Graphics2D g2d, Ghosts ghost) {
		g2d.drawImage(ghost.getGameCharacterImage(), ghost.getLocation_y(), ghost.getLocation_x(), this);
	}

	/**
	 * This function draws the pacman on the game board
	 * @param g2d
	 * @param player
	 */
	private void drawPacman(Graphics2D g2d, Pacman player) {
		g2d.drawImage(player.getGameCharacterImage(), player.getLocation_y(), player.getLocation_x(), this);
	}

	/**
	 * This function draws the power balls on the game board
	 * @param g2d
	 * @param powerBall
	 */
	private void drawPowerBall(Graphics2D g2d, Power_Ball powerBall) {
		//double offsetPowerBall_w_h = 0.2;
		//int x_index_pb = powerBall.getGrid_x();
		//int y_index_pb = powerBall.getGrid_y();
		//powerBall.setLocation_x(x_index_pb*blockWidth+(int)(blockWidth*offsetPowerBall_w_h)+boardOffset);
		//powerBall.setLocation_y(y_index_pb*blockHeight+(int)(blockHeight*offsetPowerBall_w_h));
		g2d.drawImage(powerBall.getGameCharacterImage(), powerBall.getLocation_y(), powerBall.getLocation_x(), this);
	}

	/**
	 * This function gets a general position in pixels and returns a position on the array
	 * @param locationX
	 * @param locationY
	 * @return loc_xy_in_map - location on the map (grid location)
	 */
	public int[] locationXYinTheArray(int locationX, int locationY) {
		int []loc_xy_in_map = {-1,-1};
		if(locationY >= boardOffset && locationY <= (GameConstants.SCREEN_WIDTH - boardOffset) && locationX > 0 && locationX <= GameConstants.BOARD_HEIGHT) {
			for (int i = 1; i <= map.length; i++) {
				for (int k = 1; k <= map.length; k++) {
					if(locationX <= blockHeight) {
						loc_xy_in_map[0] = 0;
						//System.out.print("loc_xy_in_map[1] = " + loc_xy_in_map[0] +", ");
					}
					else if(locationX > blockHeight*i && locationX < blockHeight+33*i) {
						loc_xy_in_map[0] = i;
					}
					if(locationY == boardOffset || locationY <= boardOffset+33) {
						loc_xy_in_map[1] = 0;
						//System.out.print("loc_xy_in_map[0] = " + loc_xy_in_map[0]+", ");
					}
					else if(locationY > boardOffset+33*k && locationY <= boardOffset+33*k*2) {
						loc_xy_in_map[1] = k;
						//System.out.print("loc_xy_in_map[0] = " + loc_xy_in_map[0] +", ");
					}

					//System.out.print("loc_xy_in_map[0] = " + loc_xy_in_map[0]+", ");
					//System.out.println("loc_xy_in_map[1] = " + loc_xy_in_map[0] +", ");
				}
			}

		}
		else
			System.out.println("Your not in the borad");
		return loc_xy_in_map;
	}

	public void init() {}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}
}