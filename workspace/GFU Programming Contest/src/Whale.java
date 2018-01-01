import java.util.*;

public class Whale {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String[] outputs = new String[Integer.parseInt(scanner.nextLine())];
		for (int i = 0; i < outputs.length; i++) {
			StringBuffer sound = new StringBuffer(scanner.nextLine());
			while (true) {
				int lastIndex = sound.lastIndexOf("X***X");
				if (lastIndex == -1) break;
				sound.replace(lastIndex, lastIndex + 5, "XOOOX");
			} while (true) {
				int lastIndex = sound.lastIndexOf("X**X");
				if (lastIndex == -1) break;
				sound.replace(lastIndex, lastIndex + 4, "XOOX");
			} String[] splitBy3 = sound.toString().split("OOO");
			ArrayList<String> inBetween = new ArrayList<String>();
			for (int j = 0; j < splitBy3.length; j++) {
				String[] temp = splitBy3[j].split("OO");
				for (int k = 0; k < temp.length; k++) inBetween.add(temp[k]);
			} boolean between911 = true;
			inBetween.remove(inBetween.size() - 1);
			if (inBetween.size() > 0) inBetween.remove(0);
			for (int j = 0; j < inBetween.size(); j++) {
				if (inBetween.get(j).length() > 11 || inBetween.get(j).length() < 9) {
					between911 = false;
					break;
				}
			}
			outputs[i] = between911 ? "yes" : "no";
		} for (String s: outputs) System.out.println(s);
		scanner.close();
	}
 
}

/*
3
XXXXXXXXXXX**XXXXXXXXXX**XXXXXXXXXX***XXXXXXXXXXX**XXXXXXXXXX***XXXXXXXXXXX**XXXXXXXXX
XXXXX**XXXXX*XXX
*/
