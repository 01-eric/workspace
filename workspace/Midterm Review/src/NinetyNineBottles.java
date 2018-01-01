public class NinetyNineBottles {
	public static void main(String[] args) {
		for (int i = 99; i > 0; i--) {
			if (i != 1) System.out.println(i + " bottles of pop on the wall\n" + i + " bottles of pop"); 
			else System.out.println(i + " bottle of pop on the wall\n" + i + " bottle of pop"); 
			if (i != 2) System.out.println("Take one down, pass it around\n" + (i - 1) + " bottle of pop on the wall\n");
			else System.out.println("Take one down, pass it around\n" + (i - 1) + " bottle of pop on the wall\n");
		}
	}
}
