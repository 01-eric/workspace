import java.awt.Color;

public class SUV extends Vehicle {
	
	// inherits all fields from Vehicle
	
	final static int WIDTH = 60;
	final static int HEIGHT = 30;
	final static int SPEED = 8;
	final static Color COLOR = new Color(0x00FF00);
	
	/*
	 * calls super constructor
	 * but defines superclass's
	 * fields using its own constants
	 */
	public SUV(int x, int y) {
		super(x, y);
		setWidth(WIDTH);
		setHeight(HEIGHT);
		setSpeed(SPEED);
		setColor(COLOR);
	}

}