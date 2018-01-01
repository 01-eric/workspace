import java.util.*;

public class PrimsMST {
	
	public static void main(String[] args) {
		// input of a connected graph
		Scanner scanner = new Scanner(System.in);
		int edges = scanner.nextInt(), vertices = scanner.nextInt();
		int[][] adjMatrix = new int[vertices][vertices], mstMatrix = new int[vertices][vertices]; // cells will contain cost of path from dim1 to dim2
		for (int i = 0; i < edges; i++) {
			int vertex1 = scanner.nextInt(), vertex2 = scanner.nextInt(), cost = scanner.nextInt();
			adjMatrix[vertex1][vertex2] = cost;
			adjMatrix[vertex2][vertex1] = cost;
		} scanner.close();
		
		// store in an array the lowest cost connection from any point already in MST
		// and from which vertex provides that cheapest connection
		int[] lowestCost = new int[vertices + 1] /*extra value on end for min node selection*/, lowestCostVertex = new int[vertices + 1];
		TreeSet<Integer> visitedNodes = new TreeSet<Integer>();
		for (int i = 0; i < lowestCost.length; i++) {
			lowestCost[i] = Integer.MAX_VALUE; // everything starts with infinite cost except 0 (first vertex to add to MST)
			lowestCostVertex[i] = -1; // -1 is flag value saying no path from existing MST to lowestCostVertex[i]
		} lowestCost[0] = 0;
		
		// use the algorithm to greedily pick all paths one by one
		while (visitedNodes.size() < vertices) {
			int selectedNode = lowestCost.length - 1; // loop through all vertices select lowest cost node, lowestCost.length - 1 (extra value) will always be infinite so never selected
			for (int i = selectedNode; i >= 0; i--) if (!visitedNodes.contains(i) && lowestCost[i] < lowestCost[selectedNode]) selectedNode = i;
			visitedNodes.add(selectedNode); // add the minimum cost node
			if (lowestCostVertex[selectedNode] != -1) { // if not special flag value then add new edge to mstMatrix
				mstMatrix[selectedNode][lowestCostVertex[selectedNode]] = lowestCost[selectedNode];
				mstMatrix[lowestCostVertex[selectedNode]][selectedNode] = lowestCost[selectedNode];
			} for (int i = 0; i < vertices; i++) { // now that MST is modified, if new vertex provides cheaper route update minimum cost
				if (!visitedNodes.contains(i) && adjMatrix[selectedNode][i] != 0 /*path must exist from new node*/ && adjMatrix[selectedNode][i] < lowestCost[i]) {
					lowestCost[i] = adjMatrix[selectedNode][i];
					lowestCostVertex[i] = selectedNode;
				}
			}
		}
		
		// print matrix
		for (int i = 0; i < mstMatrix.length; i++) {
			for (int j = 0; j < mstMatrix[0].length; j++) System.out.print((mstMatrix[i][j] < 10 ? "0" : "") + mstMatrix[i][j] + " ");
			System.out.println();
		}
	}

}

/*
for undirected graph return adjacency list of MST given that
graph is connected given N edges and V vertices in format (vertex1, vertex2, cost)
16 9
1 3 29
3 6 52
5 1 32
5 7 28
7 2 34
2 6 40
7 1 19
3 2 17
4 7 27
0 7 16
0 2 26
5 4 35
0 4 38
0 6 58
5 8 10
4 8 10

Output: 
00 00 26 00 00 00 00 16 00 
00 00 00 00 00 00 00 19 00 
26 00 00 17 00 00 40 00 00 
00 00 17 00 00 00 00 00 00 
00 00 00 00 00 00 00 27 10 
00 00 00 00 00 00 00 00 10 
00 00 40 00 00 00 00 00 00 
16 19 00 00 27 00 00 00 00 
00 00 00 00 10 10 00 00 00
 */
