import java.util.*;

public class Catalog {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String[] alphabetical = new String[Integer.parseInt(scanner.nextLine())];
		ArrayList<String> numerical = new ArrayList<String>();
		int[] nums = new int[alphabetical.length];
		for (int i = 0; i < alphabetical.length; i++) {
			alphabetical[i] = scanner.nextLine();
			numerical.add(alphabetical[i]);
			String[] temp = alphabetical[i].split(" ");
			nums[i] = Integer.parseInt(temp[temp.length - 1]);
		} Arrays.sort(alphabetical);
		Arrays.sort(nums);
		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < numerical.size(); j++) {
				String[] temp = numerical.get(j).split(" ");
				if (nums[i] == Integer.parseInt(temp[temp.length - 1])) {
					System.out.println(numerical.get(j));
					numerical.remove(j);
				}
			}
		} System.out.println();
		for (String s: alphabetical) System.out.println(s);
		scanner.close();
	}

}
