/*
 ID: eric.111
 LANG: JAVA
 TASK: namenum
 */

import java.io.*;
import java.util.*;

public class namenum {

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(new File("namenum.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));
		// Scanner scanner = new Scanner(System.in);
		String nameCode = scanner.nextLine();
		// the encoding described by the problem
		char[][] serialCode = new char[8][3];
		int temp = 65;
		for (int i = 0; i < serialCode.length; i++) {
			for (int j = 0; j < serialCode[0].length; j++) {
				serialCode[i][j] = (char)temp;
				temp = temp == 80 ? temp + 2 : temp + 1;
			}
		} // read names from dict.txt and filter them out, faster than generating combinations and testing
		BufferedReader br = new BufferedReader(new FileReader("dict.txt"));
		ArrayList<String> acceptedNames = new ArrayList<String>();
		while (true) {
			String str = br.readLine();
			if (str == null) break;
			boolean[] matchLetter = new boolean[nameCode.length()];
			boolean allMatch = true;
			if (str.length() == nameCode.length()) for (int i = 0; i < matchLetter.length; i++) matchLetter[i] = str.charAt(i) == serialCode[nameCode.charAt(i) - 50][0] || 
			str.charAt(i) == serialCode[nameCode.charAt(i) - 50][1] || str.charAt(i) == serialCode[nameCode.charAt(i) - 50][2];
			for (boolean b: matchLetter) {
				if (!b) {
					allMatch = false;
					break;
				}
			} if (allMatch) acceptedNames.add(str);
		} if (acceptedNames.size() == 0) out.println("NONE");
		else for (String s: acceptedNames) out.println(s);
		br.close();
		scanner.close();
		out.close();
	}
	
}