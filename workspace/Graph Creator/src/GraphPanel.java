import java.awt.*;
import java.util.*;
import javax.swing.*;

public class GraphPanel extends JPanel {
	
	ArrayList<Node> nodeList = new ArrayList<Node>();
	ArrayList<Edge> edgeList = new ArrayList<Edge>();
	ArrayList<ArrayList<Boolean>> adjacency = new ArrayList<ArrayList<Boolean>>();
	
	public GraphPanel() {
		super();
	}

	/*
	 * repaints the interface every time something
	 * is updated. highlighted nodes are created
	 * by setting the Graphics color.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Node n: nodeList) {
			g.setColor(n.getColor());
			g.drawOval(n.getX() - Node.RADIUS / 2, n.getY() - Node.RADIUS / 2, Node.RADIUS, Node.RADIUS);
			g.drawString(n.getId(), n.getX() - (int)(((double)n.getId().length() / 2) * 6), n.getY() + 3);
			g.setColor(Color.BLACK);
		} for (Edge e: edgeList) {
			g.drawLine(e.getFirst().getX(), e.getFirst().getY(), e.getSecond().getX(), e.getSecond().getY());
			int x1 = e.getFirst().getX(), y1 = e.getFirst().getY(), x2 = e.getSecond().getX(), y2 = e.getSecond().getY();
			g.drawString(e.getLabel(), (x1 + x2) / 2, (y1 + y2) / 2); // using midpoint formula
		}
	}
	
	// returns all string representations of nodes adjacent to the parameter
	public ArrayList<String> getConnectedLabels(String label) {
		ArrayList<String> toReturn = new ArrayList<String>();
		int b = getNodeIndex(label);
		for (int i = 0; i < adjacency.size(); i++) if (adjacency.get(b).get(i) && !nodeList.get(i).getId().equals(label)) toReturn.add(nodeList.get(i).getId());
		return toReturn;
	}
	
	// adds a new node to the nodeList
	public void addNode(Node n) {
		nodeList.add(n);
		adjacency.add(new ArrayList<Boolean>());
		for (int i = 0; i < adjacency.size() - 1; i++) adjacency.get(i).add(false);
		for (int i = 0; i < adjacency.size(); i++) adjacency.get(adjacency.size() - 1).add(false);
	}
	
	// adds a new edge to the edgeList
	public void addEdge(Edge e) {
		edgeList.add(e);
		int firstIndex = -1, secondIndex = -1;
		for (int i = 0; i < nodeList.size(); i++) {
			if (nodeList.get(i).equals(e.getFirst())) firstIndex = i;
			if (nodeList.get(i).equals(e.getSecond())) secondIndex = i;
		} adjacency.get(firstIndex).set(secondIndex, true);
		adjacency.get(secondIndex).set(firstIndex, true);
	}
	
	/*
	 * these methods find a node based on either their x-y position
	 * or their name.
	 */
	public Node getNode(int x, int y) {
		for (Node n: nodeList) if (Math.sqrt(Math.pow(x - n.getX(), 2) + Math.pow(y - n.getY(), 2)) < Node.RADIUS) return n;
		return null;
	}
	
	public Node getNode(String s) {
		for (Node n: nodeList) if (n.getId().equals(s)) return n;
		return null;
	}
	
	/*
	 * finds the index of a node in nodeList given its
	 * string representation
	 */
	public int getNodeIndex(String s) {
		for (int i = 0; i < nodeList.size(); i++) if (s.equals(nodeList.get(i).getId())) return i;
		return -1;
	}
	
	// determines whether or not a node exists
	public boolean nodeExists(String s) {
		for (Node n: nodeList) if (n.getId().equals(s)) return true;
		return false;
	}

	// sets all nodes to black
	public void unhighlight() {
		for (Node n: nodeList) n.setColor(Color.BLACK);
	}
	
	// returns the amount of nodes
	public int getNodeCount() {
		return nodeList.size();
	}

}

class Node {
	
	private int x;
	private int y;
	private String id;
	private Color color = Color.BLACK;
	static final int RADIUS = 40;
	
	Node(int x, int y, String id) {
		this.setX(x);
		this.setY(y);
		this.setId(id);
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public void setColor(Color color) {
		this.color = color;
	} 
	
	public Color getColor() {
		return color;
	}

}

class Edge {
	
	private Node first;
	private Node second;
	private String label;
	
	Edge(Node first, Node second, String label) {
		this.setFirst(first);
		this.setSecond(second);
		this.setLabel(label);
	}
	
	public Node getFirst() {
		return first;
	}
	
	public void setFirst(Node first) {
		this.first = first;
	}
	
	public Node getSecond() {
		return second;
	}
	
	public void setSecond(Node second) {
		this.second = second;
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public Node getOppositeEnd(Node n) {
		if (first.equals(n)) return second;
		else if (second.equals(n)) return first;
		return null;
	}
	
}


/*
 * A data structure which maintains the
 * order of the data.
 */
class Queue {
	
	private ArrayList<String> queue = new ArrayList<String>();
	
	public void enqueue(String s) {
		queue.add(s);
	}
	
	public String dequeue() {
		String s = queue.get(0);
		queue.remove(0);
		return s;
	}
	
	public boolean isEmpty() {
		return queue.isEmpty();
	}
	
}
