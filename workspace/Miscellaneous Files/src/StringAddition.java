import java.io.*;

public class StringAddition {
	
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new FileReader("2^200000.txt"));
			StringBuilder pow = new StringBuilder(br.readLine());
			for (int i = (int)Math.pow(10, 5) * 2; i < 250000; i++) {
				System.out.println(i);
				pow = add(new StringBuilder(pow), new StringBuilder(pow));
			} PrintWriter pw = new PrintWriter(new File("output.txt"));
			for (char c: pow.toString().toCharArray()) pw.write(c);
			pw.close();
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static StringBuilder add(StringBuilder sb1, StringBuilder sb2) {
		for (int i = Math.min(sb1.length(), sb2.length()); i <= Math.max(sb1.length(), sb2.length()); i++) {
			if (sb1.length() <= sb2.length()) sb1.insert(0, "0");
			else if (sb1.length() >= sb2.length()) sb2.insert(0, "0");
		} StringBuilder toReturn = new StringBuilder(sb1.length());
		for (int i = toReturn.capacity() - 1; i >= 0; i--) {
			toReturn.insert(0, Integer.toString((sb1.codePointAt(i) + sb2.codePointAt(i) - 96) % 10));
			if (sb1.codePointAt(i) + sb2.codePointAt(i) - 96 > 10) incrementDigit(sb1, i - 1);
		} return toReturn.charAt(0) != '0' ? toReturn : toReturn.deleteCharAt(0);
	}
	
	private static StringBuilder incrementDigit(StringBuilder sb, int index) {
		if (sb.charAt(index) == '9') {
			sb.replace(index, index + 1, "0");
			incrementDigit(sb, index - 1);
		} else sb.replace(index, index + 1, Character.toString((char)(sb.codePointAt(index) + 1)));
		return sb;
	}

}
