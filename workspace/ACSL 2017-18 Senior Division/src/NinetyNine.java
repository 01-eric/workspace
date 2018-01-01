import java.util.*;

public class NinetyNine {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String[] init = scanner.nextLine().split(", "); // initialize hand
		
		// 5 loops
		for (int runs = 0; runs < 5; runs++) {
			ArrayList<String> p1hand = new ArrayList<String>(5), p2hand = new ArrayList<String>(5);
			for (int i = 0; i < init.length / 2; i++) p1hand.add(init[i]); // player 1's hand
			for (int i = init.length / 2; i < init.length; i++) p2hand.add(init[i]); // player 2's hand
			String[] input = scanner.nextLine().split(", "); // initial score and draw pile
			int score = Integer.parseInt(input[0]); // initial score
			
			for (int i = 1; Math.min(p1hand.size(), p2hand.size()) > 0; i++) { // go through cards to play
				sortByValue(p1hand); // sort to find median
				if (crossBorder(score, getPoints(p1hand.get(p1hand.size() / 2), score))) score += 5; // is there bonus?
				score += getPoints(p1hand.remove(p1hand.size() / 2), score); // play median card
				if (i < input.length) p1hand.add(input[i++]); // draw
				if (score > 99) {
					System.out.println(score + ", Player #2"); // opposite player?
					break;
				}
				
				sortByValue(p2hand); // repeat above process for player 2
				if (crossBorder(score, getPoints(p2hand.get(p2hand.size() / 2), score))) score += 5;
				score += getPoints(p2hand.remove(p2hand.size() / 2), score); // play median card
				if (i < input.length) p2hand.add(input[i]); // draw
				if (score > 99) {
					System.out.println(score + ", Player #1");
					break;
				}
			}
		}
	}
	
	static int getValue(String card) {
		switch (card) {
			case "T": return 10;
			case "J": return 11;
			case "Q": return 12;
			case "K": return 13;
			case "A": return 14;
			default: return card.charAt(0) - 48;
		}
	}
	
	static int getPoints(String card, int score) {
		switch (card) { // no break needed because return breaks
			case "7": return score > 92 ? 1 : getValue(card);
			case "9": return 0;
			case "T": return -getValue(card);
			default: return getValue(card);
		}
	}
	
	static void sortByValue(ArrayList<String> hand) {
		for (int i = 1; i < hand.size(); i++) {
			for (int j = i; j > 0 && getValue(hand.get(j - 1)) > getValue(hand.get(j)); j--) {
				String temp = new String(hand.get(j - 1)); // idk if strings set by reference so just in case
				hand.set(j - 1, hand.get(j));
				hand.set(j, temp);
			}
		}
	}
	
	static boolean crossBorder(int score, int toAdd) {
		return score <= 33 && score + toAdd >= 34 || score + toAdd <= 33 && score >= 34 || 
		score <= 55 && score + toAdd >= 56 || score + toAdd <= 55 && score >= 56 ||
		score <= 77 && score + toAdd >= 78 || score + toAdd <= 77 && score >= 78;
	}
	
}

/*
8, 9, Q, 6, 7, K, A, 5, 9, 8
75, J, 7, Q, T, A, 6, 2, 3, 4, 5
50, 7, K, T, 8, 3, Q, 9, 7, 2, 3
63, 3, 6, 8, T, 7, 7, T, 3, 5, 8
79, A, 9, 7, T, A, 9, T, A, 6, 4
50, A, T, Q, A, T, K, J, T, A, 7

99, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7
92, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7
100, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2
 */
