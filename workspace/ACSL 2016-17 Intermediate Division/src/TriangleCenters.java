import java.util.*;

public class TriangleCenters {
	
	private static Point[] vertices = new Point[3];
	private static double[] sideLen;
	private static Line[] sides;
	
	public static void main(String[] args) {
		System.out.println(new Line(new Point(1, 0), new Point(2, 0)).getYInt());
		Scanner scanner = new Scanner(System.in);
		for (int running = 0; running < 2; running++) {
			String[] input = scanner.nextLine().split(", ");
			for (int i = 0; i < vertices.length; i++) vertices[i] = new Point(Double.parseDouble(input[i * 2]), Double.parseDouble(input[i * 2 + 1]));
			sideLen = new double[]{distance(vertices[1], vertices[2]), distance(vertices[0], vertices[2]), distance(vertices[0], vertices[1])};
			sides = new Line[]{new Line(vertices[1], vertices[2]), new Line(vertices[0], vertices[2]), new Line(vertices[0], vertices[1])};
			System.out.printf("CENTROID, " + centroid() + "\nINCENTER, " + incenter() + "\nCIRCUMCENTER, " + circumcenter() + "\nORTHOCENTER, " + orthocenter() + "\nAREA, %.2f\n", area());
		} scanner.close();
	}
	
	public static double distance(Point p1, Point p2) {
		return Math.sqrt(Math.pow(p1.getY() - p2.getY(), 2) + Math.pow(p1.getX() - p2.getX(), 2));
	}
	
	public static Point midpoint(Point p1, Point p2) {
		return new Point((p1.getX() + p2.getX()) / 2, (p1.getY() + p2.getY()) / 2);
	}
	
	public static Point intersection(Line f1, Line f2) {
		return new Point((f2.getYInt() - f1.getYInt()) / (f1.getSlope() - f2.getSlope()), f1.getSlope() * (f2.getYInt() - f1.getYInt()) / (f1.getSlope() - f2.getSlope()) + f1.getYInt());
	}
	
	public static Point circumcenter() {
		return intersection(new Line(-1 / sides[0].getSlope(), midpoint(vertices[1], vertices[2])), new Line(-1 / sides[1].getSlope(), midpoint(vertices[0], vertices[2])));
	}
	
	public static Point incenter() {
		return new Point((vertices[0].getX() * sideLen[0] + vertices[1].getX() * sideLen[1] + vertices[2].getX() * sideLen[2]) / (sideLen[0] + sideLen[1] + sideLen[2]), 
		(vertices[0].getY() * sideLen[0] + vertices[1].getY() * sideLen[1] + vertices[2].getY() * sideLen[2]) / (sideLen[0] + sideLen[1] + sideLen[2]));
	}
	
	public static Point orthocenter() {
		return intersection(new Line(-1 / sides[0].getSlope(), vertices[0]), new Line(-1 / sides[1].getSlope(), vertices[1]));
	}
	
	public static Point centroid() {
		return intersection(new Line(vertices[0], midpoint(vertices[1], vertices[2])), new Line(vertices[1], midpoint(vertices[0], vertices[2])));
	}
	
	public static double area() {
		double s = (sideLen[0] + sideLen[1] + sideLen[2]) / 2;
		return Math.sqrt(s * (s - sideLen[0]) * (s - sideLen[1]) * (s - sideLen[2]));
	}

}

class Point {
	
	private double x;
	private double y;
	
	Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	Point() {
		this(0, 0);
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public String toString() {
		return String.format("%.2f, %.2f", x, y);
	}
	
}

class Line {
	
	private double slope;
	private double yIntercept;
	
	Line(double slope, double yIntercept) {
		this.slope = slope;
		this.yIntercept = yIntercept;
	}
	
	Line(Point p1, Point p2) {
		slope = (p1.getY() - p2.getY()) / (p1.getX() - p2.getX());
		yIntercept = p1.getY() - slope * p1.getX();
	}
	
	Line(double slope, Point p) {
		this(slope, p.getY() - slope * p.getX());
	}
	
	public double getSlope() {
		return slope;
	}
	
	public double getYInt() {
		return yIntercept;
	}
	
}
