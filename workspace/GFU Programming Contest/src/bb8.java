
public class bb8 {

	public static void main(String[] args) {
		int[] bb8 = {2 ,4, 4, 2, 4, 6, 6, 4};
		for (int i = 0; i < bb8.length; i++) {
			for (int j = 0; j < (8 - bb8[i]) / 2; j++) {
				System.out.print(" ");
			} for (int k = 0; k < bb8[i]; k++) {
				System.out.print(".");
			} System.out.println();
		}
	}

}
