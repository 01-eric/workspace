import java.io.*;
import java.util.*;

public class Paint {
	public static void main(String[] args) throws IOException {
		File text = new File("paint.in");
		Scanner scanner = new Scanner(text);
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("paint.out")));
		String[] ab = scanner.nextLine().split(" ");
		String[] cd = scanner.nextLine().split(" ");
		int[] abcd = {Integer.parseInt(ab[0]), Integer.parseInt(ab[1]), Integer.parseInt(cd[0]), Integer.parseInt(cd[1])};
		if (Math.max(abcd[0], abcd[1]) < Math.min(abcd[2], abcd[3]) || Math.max(abcd[2], abcd[3]) < Math.min(abcd[0], abcd[1])) {
			out.println(Math.abs(abcd[0] - abcd[1]) + Math.abs(abcd[2] - abcd[3]));
		} else {
			out.println(Math.max(abcd[0], Math.max(abcd[1], Math.max(abcd[2], abcd[3]))) - Math.min(abcd[0], Math.min(abcd[1], Math.min(abcd[2], abcd[3]))));
		} scanner.close();
		out.close();
	}
}
