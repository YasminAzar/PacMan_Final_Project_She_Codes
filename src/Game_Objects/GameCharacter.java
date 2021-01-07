package Game_Objects;

import java.awt.Image;

import javax.swing.ImageIcon;

import Log_Package.PacmanLog;

public class GameCharacter {
	ImageIcon gameCharacterIcon;
	private Image gameCharacterImage;
	private int location_x;
	private int location_y;
	private int grid_x;
	private int grid_y;
	String status;
	boolean isPlayer;
	
	public GameCharacter(String[] args) {
		PacmanLog.log("GameCharacter: ", "Start");
		
	}
	
	public void move() {}

	public Image getGameCharacterImage() {
		return gameCharacterImage;
	}

	public void setGameCharacterImage(Image image) {
		this.gameCharacterImage = image;
	}

	public int getGrid_x() {
		return grid_x;
	}

	public void setGrid_x(int grid_x) {
		this.grid_x = grid_x;
	}

	public int getGrid_y() {
		return grid_y;
	}

	public void setGrid_y(int grid_y) {
		this.grid_y = grid_y;
	}

	public int getLocation_x() {
		return location_x;
	}

	public void setLocation_x(int location_x) {
		this.location_x = location_x;
	}

	public int getLocation_y() {
		return location_y;
	}

	public void setLocation_y(int location_y) {
		this.location_y = location_y;
	}
	
	
}
