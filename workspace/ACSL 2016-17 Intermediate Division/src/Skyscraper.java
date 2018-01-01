import java.util.*;

public class Skyscraper {
	
	private Scanner scanner = new Scanner(System.in);
	private int[][] board = new int[6][6];
	private int filledRowIndex = -1;
	private ArrayList<Integer> availableRows = new ArrayList<Integer>();
	private ArrayList<int[][]> pos = new ArrayList<int[][]>((int)Math.pow(6, 4));
	private ArrayList<String> permutations = new ArrayList<String>();

	public Skyscraper() {
		fillBoard();
		for (int i = 1; i < board.length - 1; i++) if (i != filledRowIndex) availableRows.add(i);
		String[][] combinations = {arrange(board[filledRowIndex][1]), arrange(board[filledRowIndex][2]), arrange(board[filledRowIndex][3]), arrange(board[filledRowIndex][4])};
		for (int i = 0; i < combinations[0].length; i++) {
			for (int j = 0; j < combinations[0].length; j++) {
				for (int k = 0; k < combinations[0].length; k++) {
					for (int l = 0; l < combinations[0].length; l++) {
						pos.add(onepos(i, j, k, l, combinations));
					}
				}
			}
		} int pointer = 0;
		scan: while (pos.size() > 1) {
			for (int rows = 1; rows < pos.get(pointer).length - 1; rows++) {
				StringBuilder sb = new StringBuilder().append(pos.get(pointer)[rows][1]).append(pos.get(pointer)[rows][2]).append(pos.get(pointer)[rows][3]).append(pos.get(pointer)[rows][4]);
				if (containsDuplicateChar(sb.toString()) || visibleScrapers(sb.toString()) != pos.get(pointer)[rows][0] || visibleScrapers(sb.reverse().toString()) != pos.get(pointer)[rows][5]) {
					pos.remove(pointer);
					continue scan;
				}
			} for (int columns = 1; columns < pos.get(pointer)[0].length - 1; columns++) {
				StringBuilder sb = new StringBuilder().append(pos.get(pointer)[1][columns]).append(pos.get(pointer)[2][columns]).append(pos.get(pointer)[3][columns]).append(pos.get(pointer)[4][columns]);
				if (visibleScrapers(sb.toString()) != pos.get(pointer)[0][columns] || visibleScrapers(sb.reverse().toString()) != pos.get(pointer)[5][columns]) {
					pos.remove(pointer);
					continue scan;
				}
			} if (++pointer > 1) {
				System.out.println("This input produces more than one arrangement.");
				break;
			}
		} for (int inputLines = 0; inputLines < 5; inputLines++) {
			int input = Integer.parseInt(scanner.nextLine());
			System.out.println(pos.get(0)[input / 10][input % 10]);
		} scanner.close();
	}

	public static void main(String[] args) {
		new Skyscraper();
	}
	
	private void fillBoard() {
		String[] data = scanner.nextLine().split(", ");
		for (int i = 1; i < board[0].length - 1; i++) {
			board[0][i] = Integer.parseInt(data[0].substring(i - 1, i));
			board[board.length - 1][i] = Integer.parseInt(data[data.length - 1].substring(i - 1, i));
		} for (int i = 1; i < board.length - 1; i++) {
			if (data[i].length() == 6) {
				for (int j = 1; j < board[0].length - 1; j++) board[i][j] = Integer.parseInt(data[i].substring(j, j + 1));
				filledRowIndex = i;
			} board[i][0] = Integer.parseInt(data[i].substring(0, 1));
			board[i][board[0].length - 1] = Integer.parseInt(data[i].substring(data[i].length() - 1));
		}
	}
	
	private String[] arrange(int exclude) {
		permutations.clear();
		StringBuilder sb = new StringBuilder("1234");
		permutation("", sb.deleteCharAt(sb.lastIndexOf(Integer.toString(exclude))).toString());
		String[] output = new String[permutations.size()];
		for (int i = 0; i < output.length; i++) output[i] = permutations.get(i);
		return output;
	}
	
	private void permutation(String prefix, String str) {
		if (str.isEmpty()) permutations.add(prefix);
		else for (int i = 0; i < str.length(); i++) permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1));
	}
	
	private int[][] onepos(int i, int j, int k, int l, String[][] combinations) {
		int[] ijkl = {i, j, k, l};
		int[][] output = new int[board.length][board[0].length];
		for (int rows = 0; rows < output.length; rows++) {
			for (int columns = 0; columns < output[0].length; columns++) {
				if (rows != 0 && rows != output.length - 1 && rows != filledRowIndex && columns != 0 && columns != output[0].length - 1) {
					output[rows][columns] = combinations[columns - 1][ijkl[columns - 1]].charAt(availableRows.indexOf(rows)) - 48;
				} else output[rows][columns] = board[rows][columns];
			}
		} return output;
	}
	
	private boolean containsDuplicateChar(String str) {
		for (int i = 0; i < str.length(); i++) if (new StringBuilder(str).deleteCharAt(i).toString().contains(str.substring(i, i + 1))) return true;
		return false;
	}
	
	private int visibleScrapers(String str) {
		int max = Integer.MIN_VALUE;
		int visCount = 0;
		for (int i = 0; i < str.length(); i++) {
			if (Integer.parseInt(str.substring(i, i + 1)) > max) {
				visCount++;
				max = Integer.parseInt(str.substring(i, i + 1));
			}
		} return visCount;
	}

}

/* 
3221, 41, 22, 14, 231422, 2313
14
34
23
32
21

 */
// 3221, 412341, 22, 14, 22, 2313
// 3221, 41, 224132, 14, 22, 2313
// 3221, 41, 22, 143214, 22, 2313
// 4221, 313241, 22, 22, 13, 1224
// 4221, 31, 221432, 22, 13, 1224
// 4221, 31, 22, 234122, 13, 1224
// 4221, 31, 22, 22, 142313, 1224
// 1323, 142313, 22, 31, 22, 2132
// 1323, 13, 221432, 31, 22, 2132
// 1323, 13, 22, 313241, 22, 2132
// 1323, 13, 22, 31, 234122, 2132
// 2124, 234213, 32, 12, 31, 2421
// 2124, 23, 313422, 12, 31, 2421
// 2124, 23, 32, 142132, 31, 2421
// 2124, 23, 32, 12, 321341, 2421
// 3212, 22, 22, 142313, 31, 2231
// 2312, 22, 13, 234122, 31, 3231
// 2241, 232141, 12, 23, 32, 2214
// 1224, 13, 231422, 22, 31, 3231