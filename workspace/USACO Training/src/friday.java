/*
 ID: eric.111
 LANG: JAVA
 TASK: friday
 */

import java.io.*;

public class friday {
	
	static int DOW = 6; // Jan. 13th, 1900 is Saturday
	static int[] occurences = new int[7];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("friday.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("friday.out")));
		// Scanner scanner = new Scanner(System.in);
		int years = Integer.parseInt(br.readLine());
		for (int year = 1900; year < 1900 + years; year++) {
			countMonth(31); // January
			countMonth(year % 400 == 0 || year % 100 != 0 && year % 4 == 0 ? 29 : 28); // February
			countMonth(31); // March
			countMonth(30); // April
			countMonth(31); // May
			countMonth(30); // June
			countMonth(31); // July
			countMonth(31); // August
			countMonth(30); // September
			countMonth(31); // October
			countMonth(30); // November
			countMonth(31); // December
		} out.print(occurences[6]);
		for (int i = 0; i < occurences.length - 1; i++)
			out.print(" " + occurences[i]);
		out.println();
		out.close();
		br.close();
	}
	
	public static void countMonth(int days) {
		occurences[DOW]++;
		DOW = (DOW + (days % 7)) % 7;
		/*
		 * reasoning behind the formula ((DOW + (days % 7)) % 7:
		 * currently, the DOW represents the 13th of the month.
		 * the next month's 13th is N days away, N being the number
		 * of days in the month. This will cause a N % 7 shift in
		 * the DOW until the next 13th, for example, in a 31 day month
		 * if the 13th is on Friday (5) then next month's 13th will be 
		 * 31 % 7 = 3 days ahead in the week than this month, so we add
		 * DOW + (N % 7) to get the next 13th. However, in a case like
		 * this 5 + 3 = 8th day of the week? So we take DOW + (N % 7) and
		 * apply % 7 to it again: ((DOW + (days % 7)) % 7, in this case
		 * (5 + (31 % 7)) % 7 = 1 (Monday)
		 */
	}

}
