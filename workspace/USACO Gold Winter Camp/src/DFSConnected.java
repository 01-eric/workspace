import java.util.*;

public class DFSConnected {
	
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
		} TreeSet<Integer> connectedNodes = new TreeSet<Integer>();
		dfs(graph, scanner.nextInt(), connectedNodes);
		System.out.println(Arrays.toString(connectedNodes.toArray()));
		scanner.close();
	} 
	
	static void dfs(TreeMap<Integer, TreeSet<Integer>> graph, int currentNode, TreeSet<Integer> connectedNodes) {
		connectedNodes.add(currentNode);
		for (int adjacentNode : graph.get(currentNode)) if (!connectedNodes.contains(adjacentNode)) dfs(graph, adjacentNode, connectedNodes);
	}

}

/*
Given number of edges, then N lines representing two connected vertices (undirected)
Then a final line is the source node, find all nodes reachable from source node
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
9

Output; [5, 6, 7, 8, 9, 15, 20]

*/