import java.util.*;

public class Ternary {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		for (int runs = 0; runs < 5; runs++) {
			String[][] circuit = new String[4][3];
			String[] input = scanner.nextLine().split(", ");
			for (int i = 0; i < Integer.parseInt(input[0]); i++) {
				String bin = Integer.toBinaryString(Integer.parseInt(input[i * 2 + 1].substring(1)));
				if (bin.length() != 3) bin = (bin.length() == 1 ? "00" : "0") + bin;
				circuit[input[i * 2 + 1].charAt(0) - 65][0] = bin + " " + input[i * 2 + 2];
			} for (int i = 0; i < circuit[0].length - 1; i++) {
				for (int j = 0; j < circuit.length; j++) {
					if (circuit[j][i] != null) {
						char[] destinations = circuit[j][i].substring(circuit[j][i].indexOf(" ") + 1).toCharArray();
						char output = gate(j, circuit[j][i].substring(0, circuit[j][i].indexOf(" "))) ? '1' : '0';
						for (int k = 0; k < destinations.length; k++) {
							if (circuit[j][i + 1] == null) circuit[j][i + 1] = "";
							circuit[j][i + 1] += output;
						}
					}
				}
			} for (int i = 0; i < circuit.length; i++) {
				for (int j = 0; j < circuit.length; j++) {
					System.out.print(circuit[i][j] + " ");
				} System.out.println();
			}
		} scanner.close();
	}
	
	static boolean gate(int gate, String bin) {
		if (gate == 0) return !(bin.charAt(0) == '1' || bin.charAt(1) == '1' || bin.charAt(2) == '1');
		else if (gate == 1) return !(bin.charAt(0) == '1' && bin.charAt(1) == '1' || bin.charAt(1) == '1' && bin.charAt(2) == '1' || bin.charAt(0) == '1' && bin.charAt(2) == '1');
		else if (gate == 2) return bin.charAt(0) == '1' && bin.charAt(1) == '1' && bin.charAt(2) == '1';
		else return !(bin.charAt(0) == '1' && bin.charAt(1) == '1' && bin.charAt(2) == '1');
	}

}

/* 
4, A7, B, B3, BCD, C5, BCD, D0, CD, C
3, A7, D, B3, D, C5, D, D
*/
