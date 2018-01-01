import java.util.*;

public class BFSShortestPath {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int numEdges = scanner.nextInt();
		TreeMap<Integer, TreeSet<Integer>> graph = new TreeMap<Integer, TreeSet<Integer>>();
		for (int i = 0; i < numEdges; i++) {
			int vertex1 = scanner.nextInt(), vertex2 = scanner.nextInt();
			if (!graph.containsKey(vertex1)) graph.put(vertex1, new TreeSet<Integer>());
			if (!graph.containsKey(vertex2)) graph.put(vertex2, new TreeSet<Integer>());
			graph.get(vertex1).add(vertex2);
			graph.get(vertex2).add(vertex1);
		} shortestPath(graph, scanner.nextInt(), scanner.nextInt());
		
	}
	
	static void shortestPath(TreeMap<Integer, TreeSet<Integer>> graph, int currentNode, int end) {
		TreeSet<Integer> connectedNodes = new TreeSet<Integer>();
		TreeMap<Integer, Integer> edgeTo = new TreeMap<Integer, Integer>();
		ArrayList<Integer> queue = new ArrayList<Integer>(); // treat as queue
		connectedNodes.add(currentNode); // visited first node
		queue.add(currentNode); // queue to visit children of first node
		while (!queue.isEmpty() && queue.get(0) != end) { // unless all connected nodes visited queue should not be empty
			currentNode = queue.remove(0); // pull from queue
			for (int adjacentNode : graph.get(currentNode)) { // visit each of the children by adding to queue so levels are in order
				if (!connectedNodes.contains(adjacentNode)) {
					edgeTo.put(adjacentNode, currentNode);
					connectedNodes.add(adjacentNode);
					queue.add(adjacentNode);
				}
			}
		} // for (Map.Entry<Integer, Integer> entry : edgeTo.entrySet()) System.out.println(entry.getKey() + " " + entry.getValue());
		while (edgeTo.get(end) != null) {
			System.out.print(end + " ");
			end = edgeTo.get(end);
		} System.out.println(end);
	}

}

/*
Given number of edges, then N lines representing two connected vertices (undirected)
Then a final line is the two vertices to find the shortest path between.
12
1 2
2 3
3 4
4 1
2 4
1 3
5 6
5 7
6 8
6 9
7 15
7 20
1 4

Output: [1, 4]

*/