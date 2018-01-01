/*
 * TRIANGLE SOLVER v. 1.0
 * Solve a triangle without having to
 * do the math yourself!
 * created by Eric Fan
 * 3/16/2017
 */

import java.util.*;

public class TriangleSolver {
	
	static Scanner scanner = new Scanner(System.in);

	public TriangleSolver() {
		System.out.print("Solve By: SSS, SAS, ASS, AAS, or ASA: ");
		String arrangement = scanner.nextLine();
		System.out.print("Enter data: ");
		String[] nums = scanner.nextLine().split(", ");
		Triangle tri = new Triangle(Double.parseDouble(nums[0]), Double.parseDouble(nums[1]), Double.parseDouble(nums[2]), arrangement);
		System.out.println(tri.getData());
	}

	public static void main(String[] args) {
		boolean running = true;
		while (running) {
			new TriangleSolver();
			System.out.print("Run Again? Y/N: ");
			running = scanner.nextLine().toUpperCase().equals("Y");
		}
	}

}
