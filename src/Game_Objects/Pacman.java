package Game_Objects;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Pacman extends GameCharacter{

	private Image pacmanImage;

	public Pacman(String[] args) {
		super(args);
		// TODO Auto-generated constructor stub
	}
	public Pacman(String image_string, int grid_x, int grid_y, int location_x, int location_y, String direction) {
		super(null);
		this.pacmanImage = new ImageIcon(image_string).getImage();
		this.setGrid_x(grid_x);
		this.setGrid_y(grid_y);
		this.setLocation_x(location_x);
		this.setLocation_y(location_y);;
		this.setDirection(direction);
	}
	public Image getPacmanImage() {
		return pacmanImage;
	}

	public void setPacmanImage(Image image) {
		this.pacmanImage = image;
	}

}
