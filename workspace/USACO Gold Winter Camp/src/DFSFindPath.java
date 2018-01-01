import java.util.*;

public class DFSFindPath {
	
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
		} dfs(graph, scanner.nextInt(), scanner.nextInt(), new TreeMap<Integer, Integer>(), new TreeSet<Integer>());
	} 
	
	static void dfs(TreeMap<Integer, TreeSet<Integer>> graph, int currentNode, int endingNode, TreeMap<Integer, Integer> edgeTo, TreeSet<Integer> connectedNodes) {
		connectedNodes.add(currentNode);
		if (currentNode == endingNode) { // if path DNE nothing is printed
			while (edgeTo.get(currentNode) != null) {
				System.out.print(currentNode + " ");
				currentNode = edgeTo.get(currentNode);
			} System.out.println(currentNode);
		} else {
			for (int adjacentNode : graph.get(currentNode)) {
				if (!connectedNodes.contains(adjacentNode)) {
					edgeTo.put(adjacentNode, currentNode);
					dfs(graph, adjacentNode, endingNode, edgeTo, connectedNodes);
				}
			}
		}
	}

}

/*
Given number of edges, then N lines representing two connected vertices (undirected)
Then a final line is the two vertices to find a path between.
12
1 2
2 4
3 4
4 1
2 3
1 3
5 6
5 7
6 8
6 9
7 15
7 20
1 4

Output: [1, 2, 3, 4]

*/