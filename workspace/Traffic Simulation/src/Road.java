import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Road extends JPanel {
	
	/*
	 * static constants for all 
	 * instances of Road
	 */
	final static int LANE_HEIGHT = 120;
	final static int LENGTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	
	/*
	 * private fields can be accessed through
	 * public methods
	 */
	private ArrayList<Vehicle> cars = new ArrayList<Vehicle>();
	private int carCount = 0;
	
	public Road() {
		super(); // why do we need this
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // ?
		g.setColor(new Color(0x000000));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(new Color(0xFFFFFF));
		for (int a = 0; a < 3; a++) for (int b = 0; b < getWidth(); b += 50) g.fillRect(b, LANE_HEIGHT * (1 + a) - 2, 30, 5);
		for (Vehicle vehicle: cars) vehicle.paintMe(g);
	}
	
	/*
	 * adds a car to the cars ArrayList,
	 * can include any number of cars
	 * in the parameter area
	 */
	public void addCar(Vehicle... v) {
		for (Vehicle vehicle: v) cars.add(vehicle);
	}

	/*
	 * each step will only take place if
	 * it will not collide into any other cars by
	 * comparing its would-be x and y position to all
	 * the other existing cars. if a car goes off 
	 * the side it will wrap around to zero if 
	 * it won't hit anything. if a collision will occur,
	 * cars will switch to the nearest open lane if possible.
	 */
	public void step() {
		for (Vehicle vehicle: cars) {
			if (!collision(vehicle.getX() + vehicle.getSpeed(), vehicle.getY(), vehicle)) {
				if (vehicle.getX() > LENGTH && !collision(0, vehicle.getY(), vehicle)) {
					vehicle.setX(0);
					carCount++;
				} else vehicle.setX(vehicle.getX() + vehicle.getSpeed());
			} else if (vehicle.getY() > LANE_HEIGHT && !collision(vehicle.getX(), vehicle.getY() - LANE_HEIGHT, vehicle)) vehicle.setY(vehicle.getY() - LANE_HEIGHT);
			else if (vehicle.getY() < LANE_HEIGHT * 3 && !collision(vehicle.getX(), vehicle.getY() + LANE_HEIGHT, vehicle)) vehicle.setY(vehicle.getY() + LANE_HEIGHT);
		}
	}

	/*
	 * checks to see if vehicle v
	 * will collide with anything
	 * if it moves to the parameters
	 * x and y
	 */
	public boolean collision(int x, int y, Vehicle v) {
		for (Vehicle vc: cars) if (Math.abs(y - vc.getY()) <= 60 && !vc.equals(v) && x < vc.getX() + vc.getWidth() && x + v.getWidth() > vc.getX()) return true;
		return false;
	}
	
	public int getCarCount() {
		return carCount;
	}
	
	public void resetCarCount() {
		carCount = 0;
	}
	
}
