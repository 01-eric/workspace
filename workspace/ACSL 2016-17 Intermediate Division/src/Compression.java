import java.util.*;

public class Compression {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		for (int runs = 0; runs < 5; runs++) {
			StringBuilder input = new StringBuilder(scanner.nextLine().toUpperCase());
			String[] split = input.toString().split(" ");
			ArrayList<String> al = new ArrayList<String>();
			for (String s: split) {
				StringBuilder sb = new StringBuilder(s);
				for (int i = 0; i < sb.length(); i++) if (!Character.isLetter(sb.charAt(i))) sb.deleteCharAt(i--);
				al.add(sb.toString());
			} for (int i = 0; i < al.size(); i++) for (int j = 0; j < i; j++) if (al.get(i).equals(al.get(j))) al.remove(i--);
			for (int i = 0; i < al.size(); i++) while (input.indexOf(al.get(i)) != -1) input.replace(input.indexOf(al.get(i)), input.indexOf(al.get(i)) + al.get(i).length(), Integer.toString(i + 1));
			System.out.println(input);
			scanner.close();
		}
	}
	
}
