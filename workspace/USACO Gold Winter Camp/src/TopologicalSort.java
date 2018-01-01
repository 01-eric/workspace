import java.util.*;

public class TopologicalSort {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int numEdges = scanner.nextInt();
		TreeMap<Integer, TreeSet<Integer>> graph = new TreeMap<Integer, TreeSet<Integer>>();
		for (int i = 0; i < numEdges; i++) {
			int vertex1 = scanner.nextInt(), vertex2 = scanner.nextInt();
			if (!graph.containsKey(vertex1)) graph.put(vertex1, new TreeSet<Integer>());
			graph.get(vertex1).add(vertex2);
		} if (!cycleExists(graph)) System.out.println(Arrays.toString(topologicalSort(graph)));
		else System.out.println("Error: graph is not acyclic");
	}
	
	public static boolean cycleExists(TreeMap<Integer, TreeSet<Integer>> graph) {
		TreeSet<Integer> visitedNodes = new TreeSet<Integer>();
		for (Map.Entry<Integer, TreeSet<Integer>> entry : graph.entrySet()) if (!visitedNodes.contains(entry.getKey()) && cycleAtNode(graph, entry.getKey(), new TreeSet<Integer>(), visitedNodes)) return true;
		return false;
		
	}

	private static boolean cycleAtNode(TreeMap<Integer, TreeSet<Integer>> graph, int currentNode, TreeSet<Integer> inCurrentPath, TreeSet<Integer> visitedNodes) {
		visitedNodes.add(currentNode);
		boolean hasCycle = false;
		if (graph.containsKey(currentNode)) {
			inCurrentPath.add(currentNode);
			for (int adjacentNode : graph.get(currentNode)) {
				if (inCurrentPath.contains(adjacentNode)) return true; // if found back edge return true, all higher function calls will return true as well
				else if (!visitedNodes.contains(adjacentNode)) hasCycle = hasCycle || cycleAtNode(graph, adjacentNode, inCurrentPath, visitedNodes);
			} inCurrentPath.remove(currentNode); // if returning to previous function call w/o finding cycle, current node is no longer in path
		} return hasCycle; // returns false if none of children produce cycles
	}
	
	public static int[] topologicalSort(TreeMap<Integer, TreeSet<Integer>> graph) {
		ArrayList<Integer> stack = new ArrayList<Integer>();
		TreeSet<Integer> visitedNodes = new TreeSet<Integer>();
		for (Map.Entry<Integer, TreeSet<Integer>> entry : graph.entrySet()) if (!visitedNodes.contains(entry.getKey())) topologicalDFS(graph, entry.getKey(), stack, visitedNodes);
		int[] toReturn = new int[stack.size()]; // reverse order of stack
		for (int i = 0; i < toReturn.length; i++) toReturn[i] = stack.get(stack.size() - 1 - i);
		return toReturn;
	}
	
	private static void topologicalDFS(TreeMap<Integer, TreeSet<Integer>> graph, int currentNode, ArrayList<Integer> order, TreeSet<Integer> visitedNodes) {
		visitedNodes.add(currentNode);
		if (graph.containsKey(currentNode)) for (int adjacentNode : graph.get(currentNode)) if (!visitedNodes.contains(adjacentNode)) topologicalDFS(graph, adjacentNode, order, visitedNodes);
		order.add(currentNode); // nodes with outdegree of zero are added first
	}
	
}

/*
16
0 5
0 1
2 0
2 3
0 6
3 5
5 4
4 20
6 4
7 6
8 7
6 9
9 10
9 11
9 12
11 12

Output: [8, 7, 2, 3, 0, 6, 9, 11, 12, 10, 5, 4, 20, 1]
		

15
0 5
0 1
2 0
2 3
0 6
3 5
5 4
6 4
7 6
8 7
6 9
9 10
11 9
9 12
12 11

Output: cycle

17
0 5
0 1
2 0
2 3
0 6
3 5
5 4
6 4
7 6
8 7
6 9
9 10
9 11
9 12
11 12
13 14 
14 13

Output: cycle
 */
