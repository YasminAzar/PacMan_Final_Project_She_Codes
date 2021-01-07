package Game_Constants_Package;

public final class GameConstants implements Cloneable{

	private GameConstants() {
		// restrict instantiation
	}

	public static final int PACMAN_SPEED = 6; 
	public static final int GHOST_SPEED = 12;
	public static final int GHOST_SLOW_SPEED_FOR_15_SEC = 3;

	public static final int NUMBER_OF_GHOSTS = 4;
	public static final int NUMBER_OF_POWER_BALLS = 4;
	
	public static final int EATING_A_REGULAR_BALL = 10;
	public static final int EATING_A_POWER_BALL = 50;

	public static final int POINTS_FOR_FIRST_GHOST = 200;
	public static final int POINTS_FOR_SECOND_GHOST = 400;
	public static final int POINTS_FOR_THIRD_GHOST = 200;
	public static final int POINTS_FOR_FOURTH_GHOST = 400;

	public static final String GHOST_1_STRING = "gh1";
	public static final String GHOST_2_STRING = "gh2";
	public static final String GHOST_3_STRING = "gh3";
	public static final String GHOST_4_STRING = "gh4";

	//public static final int BLOCK_SIZE = 20; //Block size = step size

	public static final int SCREEN_WIDTH = 700;
	public static final int SCREEN_HEIGHT = 600;

	public static final int BOARD_WIDTH = 500;
	public static final int BOARD_HEIGHT = 500;
	//The amount of squares in the board grid is 15X15
	public static final String BOARD_OPTION_1 [][] = {
			{"1","1","1","1","1","1","1","1","1","1","1","1","1","1","1"},
			{"1","0","0","0","0","0","1","1","1","0","0","0","0","0","1"},
			{"1","0","1","1","0","1","0","0","0","1","0","1","1","0","1"},
			{"1","0","0","0","0","1","1","0","1","1","0","0","0","0","1"},
			{"1","1","1","1","0","0","0","0","0","0","0","1","1","1","1"},
			{"0","0","0","0","0","1","1","0","1","1","0","0","0","0","0"},
			{"0","1","1","0","1","1","1","0","1","1","1","0","1","1","0"},
			{"0","1","1","0","1","1","1","0","1","1","1","0","1","1","0"},
			{"0","1","1","1","1","1","1","0","1","1","1","1","1","1","0"},
			{"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
			{"1","1","1","0","1","0","1","1","1","0","1","0","1","1","1"},
			{"1","0","0","0","1","0","1","1","1","0","1","0","0","0","1"},
			{"1","0","1","0","1","0","1","1","1","0","1","0","1","0","1"},
			{"1","0","0","0","0","0","0","0","0","0","0","0","0","0","1"},
			{"1","1","1","1","1","1","1","1","1","1","1","1","1","1","1"}
	};

	/*public static final int BOARD_OPTION_2 = [1111111111111111111111111,
				  1000001000000000001000001,
				  1011101010110110101011101,
				  1011101010100010101011101,
				  1011101010111110101011101,
				  1011101010000000101011101,
				  1011101010111110101011101,
				  1011101010111110101011101,
				  1001000010001000100001001,
				  1111011111101011111101111,
				  1111000010000000100001111,
				  1111011010110110101101111,
				  0000011000100010001100001,
				  1111011010111110101101111,
				  1111000010000000100001111,
				  1111011111101011111101111,
				  1011000010001000100001101,
				  1011011010111110101101101,
				  1011011000111110001101101,
				  1000011010000000101100001,
				  1011011010111110101101101,
				  1011011010100010101101101,
				  1011011010110110101101101,
				  1000011000000000001100001,
				  1111111111111111111111111];

	public static final int BOARD_OPTION_3 = [0000100001001001000010000,
				  0101111011101011101111010,
				  1100000000000000000000011,
				  0101111011011101101111010,
				  0001111011011101101111000,
				  0101111011011101101111010,
				  1101111011011101101111011,
				  1100010000001000000100011,
				  0101010111101011110101010,
				  0000010011001001100100000,
				  1111010111101011110101111,
				  1111010111101011110101111,
				  1111010111101011110101111,
				  0000010011001001100100000,
				  0111010111101011110101110,
				  0111010000001000000101110,
				  0111011111111111111101110,
				  0000000001000001000000000,
				  0100101101001001011010010,
				  1110101101001001011010111,
				  0100101101011101011010010,
				  0001101101001001011011000,
				  0011101101101011011011100,
				  0111101100000000011011110,
				  1111101111111111111011111]*/

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
