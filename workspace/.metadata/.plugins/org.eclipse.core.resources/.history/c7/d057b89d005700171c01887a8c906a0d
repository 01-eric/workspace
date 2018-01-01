import java.io.*;
import java.util.*;

public class RandomSelectionSort {
	
	public static void main(String[] args) {
		try {
			PrintWriter pw = new PrintWriter(new File("in.txt"));
			for (int i = 0; i < 100; i++) pw.write(Double.toString(Math.random() * 100) + (i == 99 ? "" : ","));
			pw.close();
			BufferedReader br = new BufferedReader(new FileReader(new File("in.txt")));
			String[] s = br.readLine().split(",");
			br.close();
			double[] nums = new double[s.length];
			for (int i = 0; i < nums.length; i++) nums[i] = Double.parseDouble(s[i]);
			for (int j = 0; j < nums.length; j++) {
				double min = Double.MAX_VALUE;
				int minIndex = -1;
				for (int i = j; i < nums.length; i++) {
					if (nums[i] < min) {
						min = nums[i];
						minIndex = i;
					}
				} double temp = nums[minIndex];
				nums[minIndex] = nums[j];
				nums[j] = temp;
			} System.out.println(Arrays.toString(nums));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
