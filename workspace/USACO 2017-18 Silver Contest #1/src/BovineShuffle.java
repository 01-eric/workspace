import java.util.*;
import java.io.*;

public class BovineShuffle {

	public static void main(String[] args) throws IOException {
		// Scanner scanner = new Scanner(new File("shuffle.in"));
		PrintWriter out = new PrintWriter(new FileWriter("shuffle.out"));
		Scanner scanner = new Scanner(System.in);
		int[] graph = new int[scanner.nextInt() - 1];
		boolean[] partOfCycle = new boolean [graph.length];
		for (int i = 0; i < graph.length; i++) graph[i] = scanner.nextInt();
		
	}
	
	static void findCycle(int index, int[] graph, boolean[] partOfCycle, ArrayList<Integer> traversed) {
		traversed.add(index);
		if (traversed.contains(graph[index])) {
			for (int i = 0; i )
		} else if (!partOfCycle[graph[index]]) findCycle(graph[index], graph, partOfCycle, traversed);
	}
	
}

/*
4
3 2 1 2

8
8 5 5 5 6 2 8 7
*/