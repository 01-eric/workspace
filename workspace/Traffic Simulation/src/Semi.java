import java.awt.*;

public class Semi extends Vehicle {
	
	// inherits all fields from Vehicle
	
	final static int WIDTH = 100;
	final static int HEIGHT = 40;
	final static int SPEED = 5;
	final static Color COLOR = new Color(0x0000FF);
	
	/*
	 * calls super constructor
	 * but defines superclass's
	 * fields using its own constants
	 */
	public Semi(int x, int y) {
		super(x, y);
		setWidth(WIDTH);
		setHeight(HEIGHT);
		setSpeed(SPEED);
		setColor(COLOR);
	}

}
