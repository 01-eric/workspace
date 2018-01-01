/*
 ID: eric.111
 LANG: JAVA
 TASK: beads
 */
import java.io.*;
import java.util.*;

public class beads {
	
	char[] beads;

	public beads() throws IOException {
		Scanner scanner = new Scanner(new File("beads.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("beads.out")));
		// Scanner scanner = new Scanner(System.in);
		int uselessinputnumber = Integer.parseInt(scanner.nextLine());
		beads = scanner.nextLine().toCharArray();
		int max = breakNecklace(0);
		for (int i = 1; i < uselessinputnumber; i++) {
			max = Math.max(max, breakNecklace(i));
		} out.println(max);
		out.close();
		scanner.close();
		
	}

	public static void main(String[] args) throws IOException {
		new beads();
	}
	
	public int breakNecklace(int index) {
		boolean[] checked = new boolean[beads.length];
		int count = 0;
		char rightbead = firstRightNonWhiteBead(index);
		char leftbead = firstLeftNonWhiteBead(index);
		for (int i = index + 1; i < index + 1 + beads.length; i++) {
			int k = i >= beads.length ? i - beads.length : i;
			if (!(beads[k] == 'w' || beads[k] == rightbead)) {
				break;
			} count++;
			checked[k] = true;
		} for (int i = index; i > index - beads.length; i--) {
			int k = i < 0 ? i + beads.length : i;
			if (!(beads[k] == 'w' || beads[k] == leftbead) || checked[k]) {
				break;
			} count++;
		} return count;
	}
	
	public char firstRightNonWhiteBead(int index) {
		for (int i = index + 1; i < index + 1 + beads.length; i++) {
			int k = i >= beads.length ? i - beads.length : i;
			if (beads[k] != 'w') {
				return beads[k];
			}
		} return 'w';
	}
	
	public char firstLeftNonWhiteBead(int index) {
		for (int i = index; i > index - beads.length; i--) {
			int k = i < 0 ? i + beads.length : i;
			if (beads[k] != 'w') {
				return beads[k];
			}
		} return 'w';
	}

}
