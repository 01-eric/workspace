import java.util.*;

class Point {
	
	double x;
	double y;
	/*
	 * Constructors are special methods that are automatically run with the creation of a new object or class.
	 * Constructors are useful in the sense that when creating a class you can pass through pieces of data by
	 * using a constructor. Without a constructor, you would have to set the class's data manually, by either
	 * creating "set" methods or doing, in this case, Point.x = someNumber or Point.y = someNumber. You can have
	 * multiple constructors as long as the parameters are different and the system will decide which constructor
	 * to use based on what parameters are entered when creating a new instance of the object.
	 */
	public Point() {
	} // this empty constructor is allowing an instance of Point to be created without any parameters.
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Point(String coordinates) {
		String[] input = coordinates.split(",");
		x = Double.parseDouble(input[0].substring(1));
		y = Double.parseDouble(input[1].substring(0, input[1].length() - 1));
	}
	
	void printLocation() {
		System.out.println("(" + x + "," + y + ")");
	}
	
}

public class Constructors {
	
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		// when I create a Point without using the constructor, I have to manually set its values.
		Point p = new Point();
		p.x = 2;
		p.y = 7;
		// but constructors allow me to save time by automatically setting it for me.
		Point q = new Point(14.25,3.5);
		System.out.print("Enter the coordinates for a point in the form (x,y): ");
		// constructors easily allow user input and other variables control the class's data.
		Point r = new Point(scanner.nextLine());
		p.printLocation();
		q.printLocation();
		r.printLocation();
		System.out.println("Slope between point p and point r: " + slope(p,r));
		System.out.print("Enter number of points to create: ");
		Point[] points = new Point[Integer.parseInt(scanner.nextLine())];
		for (int i = 0; i < points.length; i++) {
			points[i] = new Point(Math.random() * 100, Math.random() * 100);
			points[i].printLocation();
		}
	}
	
	static double slope(Point p1, Point p2) {
		return (p2.y - p1.y) / (p2.x - p1.x);
	}

}
