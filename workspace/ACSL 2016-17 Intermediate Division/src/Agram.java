import java.util.*;
public class Agram {
	
	Scanner scanner = new Scanner(System.in);
	ArrayList<String> sameSuite = new ArrayList<String>();
	
	public Agram() {
		String output;
		for (int runs = 0; runs < 5; runs++) {
			String[] cards = scanner.nextLine().split(", ");
			if (hasSuite(cards)) {
				output = sameSuite.get(0);
				boolean hasHigher = false;
				for (int index = 0; index < sameSuite.size(); index++) {
					hasHigher = hasHigher || Math.max(rank(cards[0]), rank(sameSuite.get(index))) > rank(cards[0]);
				} if (hasHigher) {
					for (int index = sameSuite.size() - 1; index >= 0; index--) {
						if (rank(sameSuite.get(index)) < rank(cards[0])) {
							sameSuite.remove(index);
						}
					} output = sameSuite.get(0);
					for (int index = 1; index < sameSuite.size(); index++) {
						if (rank(sameSuite.get(index)) < rank(output)) { 
							output = sameSuite.get(index);
						}
					}
				} else {
					for (int index = 1; index < sameSuite.size(); index++) {
						if (rank(sameSuite.get(index)) < rank(output)) {
							output = sameSuite.get(index);
						}
					}
				} System.out.println(output);
			} else {
				output = cards[1];
				for (int index = 2; index < cards.length; index++) {
					if (rank(cards[index]) < rank(output)) {
						output = cards[index];
					}
				} System.out.println(output);
			} sameSuite.clear();
		} scanner.close();
	}
	
	public static void main(String[] args) {
		new Agram();
	}
	
	public boolean hasSuite(String[] array) {
		char suite = array[0].charAt(1);
		boolean b = false;
		for (int index = 1; index < array.length; index++) {
			if (array[index].contains(Character.toString(suite))) {
				sameSuite.add(array[index]);
				b = true;
			}
		} return b;
	}
	
	public int rank(String card) {
		if (card.charAt(0) == 'A') {
			return 1;
		} else if (card.charAt(0) == 'K') {
			return 13;
		} else if (card.charAt(0) == 'Q') {
			return 12;
		} else if (card.charAt(0) == 'J') {
			return 11;
		} else if (card.charAt(0) == 'T') {
			return 10;
		} return Integer.parseInt(card.substring(0, 1));
	}
}