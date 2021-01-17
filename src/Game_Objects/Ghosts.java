package Game_Objects;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Ghosts extends GameCharacter {
	
	private Image ghostImage;
	
	public Ghosts(String[] args) {
		super(args);
	}
	
	public Ghosts(String image_string, int grid_x, int grid_y, int location_x, int location_y, String direction) {
		super(null);
		this.ghostImage = new ImageIcon(image_string).getImage();
		this.setGrid_x(grid_x);
		this.setGrid_y(grid_y);
		this.setLocation_x(location_x);
		this.setLocation_y(location_y);
		this.setDirection(direction);
	}
	public Image getGhostImage() {
		return ghostImage;
	}

	public void setGhostImage(Image image) {
		this.ghostImage = image;
	}
}
