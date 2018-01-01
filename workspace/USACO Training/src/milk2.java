/*
 ID: eric.111
 LANG: JAVA
 TASK: milk2
 */

import java.io.*;
import java.util.*;

public class milk2 {

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(new File("milk2.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));
		// Scanner scanner = new Scanner(System.in);
		boolean[] milking = new boolean[1000000];
		int numberFarmers = Integer.parseInt(scanner.nextLine());
		int maxFin = 0;
		int minStart = milking.length;
		for (int i = 0; i < numberFarmers; i++) {
			String[] startFin = scanner.nextLine().split(" ");
			int fin = Integer.parseInt(startFin[1]); 
			int start = Integer.parseInt(startFin[0]);
			for (int j = start; j < fin; j++) milking[j] = true;
			maxFin = Math.max(maxFin, fin);
			minStart = Math.min(minStart, start);
		} int countMilking = 0, countIdle = 0, maxMilking = 0, maxIdle = 0;
		for (int i = minStart; i <= maxFin; i++) {
			if (i == maxFin) {
				maxMilking = Math.max(countMilking, maxMilking);
			} else if (milking[i]) {
				countMilking++;
				maxIdle = Math.max(countIdle, maxIdle);
				countIdle = 0;
			} else {
				countIdle++;
				maxMilking = Math.max(countMilking, maxMilking);
				countMilking = 0;
			}
		} out.println(maxMilking + " " + maxIdle);
		out.close();
		scanner.close();
	}

}
