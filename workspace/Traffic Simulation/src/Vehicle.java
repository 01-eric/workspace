import java.awt.*;

public class Vehicle {
	
	
	/*
	 * fields will be unique to child classes,
	 * default values are all 0 and white color
	 */
	private int x;
	private int y;
	private int width;
	private int height;
	private int speed;
	private Color color = new Color(0xFFFFFF);
	
	public Vehicle(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	
	/*
	 * since all fields will be set by
	 * child classes in their constructor,
	 * paintMe method will be shared by
	 * all child classes.
	 */
	public void paintMe(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}

}
