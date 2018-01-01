import java.util.*;

public class Traffic {
	
	static Node[][] map = new Node[6][6];
	
	static {
		for (int i = 0; i < map.length; i++) for (int j = 0; j < map[0].length; j++) map[i][j] = new Node(Integer.toString(i) + (j));
		for (int i = 0; i < map.length; i++) { // horizontal roads
			if (i % 2 == 0) for (int j = 0; j < map[0].length - 1; j++) map[i][j].addAdjacentNode(map[i][j + 1], 1);
			else for (int j = 1; j < map[0].length; j++) map[i][j].addAdjacentNode(map[i][j - 1], 1);
		} for (int j = 0; j < map[0].length; j++) { // vertical roads
			if (j % 2 == 0) for (int i = 0; i < map.length - 1; i++) map[i][j].addAdjacentNode(map[i + 1][j], 1);
			else for (int i = 1; i < map.length; i++) map[i][j].addAdjacentNode(map[i - 1][j], 1);
		} for (int i = 0; i < map[0].length - 2; i++) { // long diagonal
			map[map.length - 1 - i][i].addAdjacentNode(map[map.length - 2 - i][i + 1], 1.4);
			map[map.length - 2 - i][i + 1].addAdjacentNode(map[map.length - 1 - i][i], 1.4);
		} for (int i = 1; i < map.length - 2; i++) { // medium diagonal
			map[i][i].addAdjacentNode(map[i + 1][i + 1], 1.4);
			map[i + 1][i + 1].addAdjacentNode(map[i][i], 1.4);
		} for (int i = 0; i < 2; i++) { // short diagonal
			map[i + 1][i].addAdjacentNode(map[i + 2][i + 1], 1.4);
			map[i + 2][i + 1].addAdjacentNode(map[i + 1][i], 1.4);
		}
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		for (int runs = 0; runs < 10; runs++) {
			String[] input = scanner.nextLine().split(", ");
			labelDistances(Integer.parseInt(input[2]), Integer.parseInt(input[3]), 0);
		}
	}
	
	static void labelDistances(int x, int y, double distance) {
		map[x][y].setDistance(distance);
		System.out.println(x + Integer.toString(y) + ": " + distance);
		for (Edge e: map[x][y].getAdjacentEdges()) {
			if (e.getEndpoint().getDistance() == 0) {
				labelDistances(e.getEndpoint().getID().charAt(0) - 48, e.getEndpoint().getID().charAt(1) - 48, distance + e.getLength());
			}
		}
		
	}

}

class Node {
	
	private String id;
	private LinkedList<Edge> adjacentEdges = new LinkedList<Edge>();
	private double distance;
	
	Node(String id) {
		this.id = id;
	}
	
	void addAdjacentNode(Node adjacentNode, double length) {
		adjacentEdges.add(new Edge(this, adjacentNode, length));
	}
	
	LinkedList<Edge> getAdjacentEdges() {
		return adjacentEdges;
	}
	
	String getID() {
		return id;
	}
	
	double getDistance() {
		return distance;
	}
	
	void setDistance(double distance) {
		this.distance = distance;
	}
	
}

class Edge {
	
	private double length;
	private Node start, end;
	
	Edge(Node start, Node end, double length) {
		this.start = start;
		this.end = end;
		this.length = length;
	}
	
	double getLength() {
		return length;
	}
	
	Node getEndpoint() {
		return end;
	}
	
}
