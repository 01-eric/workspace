import java.awt.Color;

public class SportsCar extends Vehicle {
	
	// inherits all fields from Vehicle
	
	final static int WIDTH = 40;
	final static int HEIGHT = 20;
	final static int SPEED = 12;
	final static Color COLOR = new Color(0xFF0000);
	
	/*
	 * calls super constructor
	 * but defines superclass's
	 * fields using its own constants
	 */
	public SportsCar(int x, int y) {
		super(x, y);
		setWidth(WIDTH);
		setHeight(HEIGHT);
		setSpeed(SPEED);
		setColor(COLOR);
	}

}