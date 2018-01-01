/*
 ID: eric.111
 LANG: JAVA
 TASK: test
 */

import java.io.*;
import java.util.*;

public class test {
	
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(new File("test.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("test.out")));
		String[] input = scanner.nextLine().split(" ");
		out.println(Integer.parseInt(input[0]) + Integer.parseInt(input[1]));
		out.close();
		scanner.close();
	}

}
