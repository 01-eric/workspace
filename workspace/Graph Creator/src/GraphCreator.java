/*
 * Graph Creator v 1.2
 * Eric Fan
 * 5/22/17
 * Draw your own graph on this program's user interface
 * and be able to find the least costly path which traverses
 * all nodes.
 */

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GraphCreator implements ActionListener, MouseListener {
	
	
	// building user interface
	JFrame frame = new JFrame();
	GraphPanel panel = new GraphPanel();
	JButton nodeButton = new JButton("Node");
	JButton edgeButton = new JButton("Edge");
	JTextField labels = new JTextField("A");
	JTextField firstNode = new JTextField("A");
	JTextField secondNode = new JTextField("B");
	JTextField salesmanStart = new JTextField("A");
	JButton findPath = new JButton("Cheapest Path");
	JButton connect = new JButton("Test For Connection");
	Container south = new Container();
	Container north = new Container();
	final int NODE_CREATE = 0;
	final int EDGE_FIRST = 1;
	final int EDGE_SECOND = 2;
	int state = NODE_CREATE;
	Node first = null;
	ArrayList<Integer> pathLengths = new ArrayList<Integer>();
	ArrayList<ArrayList<Node>> paths = new ArrayList<ArrayList<Node>>();

	public GraphCreator() {
		frame.setVisible(true);
		frame.setSize(new Dimension(1200, 800));
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(panel, BorderLayout.CENTER);
		south.setLayout(new GridLayout(1, 3));
		south.add(nodeButton);
		south.add(edgeButton);
		south.add(labels);
		north.setLayout(new GridLayout(1, 5));
		north.add(firstNode);
		north.add(secondNode);
		north.add(connect);
		north.add(salesmanStart);
		north.add(findPath);
		findPath.addActionListener(this);
		connect.addActionListener(this);
		frame.add(south, BorderLayout.SOUTH);
		frame.add(north, BorderLayout.NORTH);
		nodeButton.addActionListener(this);
		nodeButton.setBackground(new Color(0xF6FF67));
		edgeButton.addActionListener(this);
		edgeButton.setBackground(Color.WHITE);
		panel.addMouseListener(this);
	}

	public static void main(String[] args) {
		new GraphCreator();
	}

	public void mouseClicked(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {
		if (state == NODE_CREATE) panel.addNode(new Node(e.getX(), e.getY(), labels.getText()));
		else if (state == EDGE_FIRST) {
			Node n = panel.getNode(e.getX(), e.getY());
			if (n != null) {
				first = n;
				state = EDGE_SECOND;
				first.setColor(new Color(0xFF0000));
			}
		} else if (state == EDGE_SECOND) {
			Node n = panel.getNode(e.getX(), e.getY());
			if (n != null && !n.equals(first)) {
				boolean invalid = false;
				for (int i = 0; i < labels.getText().length(); i++) invalid = invalid || !Character.isDigit(labels.getText().charAt(i));
				if (!invalid) {
					first.setColor(Color.BLACK);
					state = EDGE_FIRST;
					panel.addEdge(new Edge(first, n, labels.getText()));
				} else JOptionPane.showMessageDialog(frame, "Edges must be numerical values.");
			}
		} panel.repaint();
	}
	
	/*
	 * node and edge buttons act like radio buttons
	 * only one of them can be selected and they don't
	 * do anything except determine what other clicks do.
	 * if connect is pressed, a queue is used to determine
	 * whether two nodes are connected. the cheapest path
	 * button finds the cheapest path to traverse the graph.
	 * of course, if the connect nodes button shows false
	 * for anything, traversing would not be possible.
	 */
	public void actionPerformed(ActionEvent e) {
		// put unhighlight outside so node unhighlights too
		panel.unhighlight();
		panel.repaint();
		if (e.getSource().equals(nodeButton)) {
			nodeButton.setBackground(new Color(0xF6FF67));
			edgeButton.setBackground(Color.WHITE);
			state = NODE_CREATE;
		} else if (e.getSource().equals(edgeButton)) {
			edgeButton.setBackground(new Color(0xF6FF67));
			nodeButton.setBackground(Color.WHITE);
			state = EDGE_FIRST;
		} else if (e.getSource().equals(connect)) {
			if (!panel.nodeExists(firstNode.getText())) JOptionPane.showMessageDialog(frame, "First Node Does Not Exist!");
			else if (!panel.nodeExists(secondNode.getText())) JOptionPane.showMessageDialog(frame, "Second Node Does Not Exist!");
			else {
				Queue queue = new Queue();
				// path will keep track of the current "path"
				ArrayList<String> path = new ArrayList<String>();
				path.add(panel.getNode(firstNode.getText()).getId());
				ArrayList<String> adjacents = panel.getConnectedLabels(firstNode.getText());
				for (String s: adjacents) queue.enqueue(s);
				/*
				 * duplicates in the queue don't matter
				 * because their adjacent nodes won't
				 * be added to the list of nodes.
				 */
				while (!queue.isEmpty()) {
					String currentNode = queue.dequeue();
					if (!path.contains(currentNode)) path.add(currentNode);
					adjacents = panel.getConnectedLabels(currentNode);
					for (String s: adjacents) if (!path.contains(s)) queue.enqueue(s);
				} if (path.contains(secondNode.getText())) JOptionPane.showMessageDialog(frame, "Nodes " + firstNode.getText() + " & " + secondNode.getText() + " are connected!");
				else JOptionPane.showMessageDialog(frame, "Nodes " + firstNode.getText() + " & " + secondNode.getText() + " are not connected.");
			}
		} else if (e.getSource().equals(findPath)) {
			// clear the lists from last run
			paths.clear();
			pathLengths.clear();
			// paths.contains(Node n) doesn't work???
			boolean[] visited = new boolean[panel.getNodeCount()];
			travel(panel.getNode(salesmanStart.getText()), new ArrayList<Node>(), 0, visited);
			if (pathLengths.size() > 0) {
				int index = 0;
				int min = Integer.MAX_VALUE;
				for (int i = 0; i < paths.size(); i++) {
					if (pathLengths.get(i) < min) {
						index = i;
						min = pathLengths.get(i);
					}
				} StringBuilder path = new StringBuilder();
				for (int i = 0; i < paths.get(index).size(); i++) path.append(paths.get(index).get(i).getId()).append("-");
				path.deleteCharAt(path.length() - 1);
				JOptionPane.showMessageDialog(frame, "The least costly path is " + path + " with the cost of " + min);
			} else JOptionPane.showMessageDialog(frame, "Not all nodes can be visited.");
		}
	}
	
	/*
	 * define recursive method
	 */
	public void travel(Node n, ArrayList<Node> path, int total, boolean[] visited) {
		// each call of this function adds its node to the path
		path.add(n);
		visited[panel.getNodeIndex(n.getId())] = true;
		// if the path length is 4 return this method
		if (panel.getNodeCount() == path.size()) {
			ArrayList<Node> pathCopy = new ArrayList<Node>();
			for (Node node: path) pathCopy.add(node);
			paths.add(pathCopy);
			pathLengths.add(total);
			return;
		} else { // otherwise call this method on the next adjacent node
			for (Edge e: panel.edgeList) {
				if (e.getOppositeEnd(n) != null) {
					if (!visited[panel.getNodeIndex(e.getOppositeEnd(n).getId())]) {
						travel(e.getOppositeEnd(n), path, total + Integer.parseInt(e.getLabel()), visited);
						path.remove(path.size() - 1);
						visited[panel.getNodeIndex(e.getOppositeEnd(n).getId())] = false;
					}
				}
			}
		}
	}

}
