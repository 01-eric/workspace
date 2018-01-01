import java.util.*;

// SPT provides shortest path from source node to any node
public class DijkstraSPT {
	
	public static void main(String[] args) {
		// read in input of connected graph
		Scanner scanner = new Scanner(System.in);
		int edges = scanner.nextInt(), vertices = scanner.nextInt();
		int[][] adjMatrix = new int[vertices][vertices], sptMatrix = new int[vertices][vertices]; // each cell has path cost
		for (int i = 0; i < edges; i++) {
			int vertex1 = scanner.nextInt(), vertex2 = scanner.nextInt(), cost = scanner.nextInt();
			adjMatrix[vertex1][vertex2] = cost;
			adjMatrix[vertex2][vertex1] = cost;
		} scanner.close();
		
		// store in an array the lowest cost connection from any point already in SPT
		// points added later to SPT must give new points a cumulative total of cost
		// unlike MST; and from which vertex gives that connection
		int[] lowestCost = new int[vertices + 1] /*extra value on end for min node selection*/, lowestCostVertex = new int[vertices + 1];
		TreeSet<Integer> visitedNodes = new TreeSet<Integer>();
		for (int i = 0; i < lowestCost.length; i++) {
			lowestCost[i] = Integer.MAX_VALUE;
			lowestCostVertex[i] = -1; // -1 is flag value saying no path from existing SPT to lowestCostVertex[i]
		} lowestCost[0] = 0; // everything starts with infinite cost except 0 (SOURCE node to add to SPT)
		
		// greedy algorithm selecting locally optimal path
		while (visitedNodes.size() < vertices) { // if looking for specific path we can break once path found
			int selectedNode = lowestCost.length - 1; // loop through all vertices select lowest cost node, lowestCost.length - 1 (extra value) will always be infinite so never selected
			for (int i = selectedNode; i >= 0; i--) if (!visitedNodes.contains(i) && lowestCost[i] < lowestCost[selectedNode]) selectedNode = i;
			visitedNodes.add(selectedNode); // add the minimum cost node
			if (lowestCostVertex[selectedNode] != -1) { // if not special flag value then add new edge to mstMatrix
				// different from MST, copy distance from original graph adjMatrix instead of totalCost bc totalCost from lowestCost[] will be of total path
				sptMatrix[selectedNode][lowestCostVertex[selectedNode]] = adjMatrix[selectedNode][lowestCostVertex[selectedNode]];
				sptMatrix[lowestCostVertex[selectedNode]][selectedNode] = adjMatrix[lowestCostVertex[selectedNode]][selectedNode];
			} for (int i = 0; i < vertices; i++) { // now that SPT is modified, if new vertex provides cheaper route update minimum cost
				if (!visitedNodes.contains(i) && adjMatrix[selectedNode][i] != 0 /*path must exist from new node*/ && adjMatrix[selectedNode][i] + lowestCost[selectedNode] < lowestCost[i]) {
					lowestCost[i] = adjMatrix[selectedNode][i] + lowestCost[selectedNode]; // notable difference from MST: new cost is not just copied from adjMatrix but cumulative
					lowestCostVertex[i] = selectedNode;
				}
			}
		}
		
		// print matrix
		for (int i = 0; i < sptMatrix.length; i++) {
			for (int j = 0; j < sptMatrix[0].length; j++) System.out.print((sptMatrix[i][j] < 10 ? "0" : "") + sptMatrix[i][j] + " ");
			System.out.println();
		}
	}

}
