/*
 ID: eric.111
 LANG: JAVA
 TASK: ride
 */

import java.io.*;
import java.util.*;

public class ride {

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(new File("ride.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ride.out")));
		char[] comet = scanner.nextLine().toCharArray();
		char[] group = scanner.nextLine().toCharArray();
		int cometnumber = 1;
		int groupnumber = 1;
		for (int i = 0; i < comet.length; i++) {
			cometnumber = cometnumber * ((int)comet[i] - 64);
		} for (int i = 0; i < group.length; i++) {
			groupnumber = groupnumber * ((int)group[i] - 64);
		} if (cometnumber % 47 == groupnumber % 47) {
			out.println("GO");
		} else {
			out.println("STAY");
		} scanner.close();
		out.close();
	}

}
