package Game_Objects;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Ghosts extends GameCharacter {
	
	private Image ghostImage;
	private int grid_x;
	private int grid_y;
	private int location_x;
	private int location_y;
	
	public Ghosts(String[] args) {
		super(args);
		//Image image;
		//int location_x;
		//int location_y;
		//String direction;
	}
	public Ghosts(Image image, int grid_x, int grid_y, int location_x, int location_y, String direction) {
		super(null);
		this.ghostImage = new ImageIcon().getImage();
		this.grid_x = grid_x;
		this.grid_y = grid_y;
		this.location_x = location_x;
		this.location_y = location_y;
		this.direction = direction;
	}
	public Image getGhostImage() {
		return ghostImage;
	}

	public void setGhostImage(Image image) {
		this.ghostImage = image;
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
