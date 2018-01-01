
public class StringBuilderBuffer {
	
	public static void main(String[] args) {
		
		// commonly we concatenate Strings like this
		
		String str = "";
		str += "My name is Joe.";
		int age = 17;
		str += " I am " + age + " years old.";
		System.out.println(str);
		
		/*
		 * This is easy to write, but it is inefficient. Strings
		 * are essentially an unchangeable object where once you
		 * declare it as an empty String, it will stay an empty
		 * String forever. What the computer is really doing when
		 * using += is creating a new String with the old String
		 * and the String being added and assigning the new String
		 * to the original variable name.
		 */
		
		/*
		 * StringBuffers and StringBuilders are dynamic and not size
		 * limited making them more efficient than Strings.
		 */
		
		StringBuilder sb = new StringBuilder("Hello world.");
		sb.append(" ").append(str);
		System.out.println(sb);
		
		int num = 252;
		boolean isPalindrome;
		isPalindrome = Integer.toString(num).equals(new StringBuilder(Integer.toString(num)).reverse().toString());
		System.out.println(isPalindrome);
		
		StringBuilder temp = new StringBuilder("Insert here");
		System.out.println(temp.insert(temp.lastIndexOf(" "), " :D"));
		
		// replace all groups of 0's length 2 with *s
		String input = "0011100000001110001111001111100110010011011100100100001110001010001100";
		StringBuilder k = new StringBuilder(input);
		while (k.lastIndexOf("1001") != -1) k.replace(k.lastIndexOf("1001"), k.lastIndexOf("1001") + 4, "1**1");
		if (k.subSequence(0, 3).equals("001")) k.replace(0, 2, "**");
		if (k.subSequence(k.length() - 3, k.length()).equals("100")) k.replace(k.length() - 2, k.length(), "**");
		System.out.println(k);
		
		// String Formatting /////////////////////////////////////////////
		
		System.out.println("\tTab");
		System.out.println(new StringBuffer("New\nLine"));
		
		/*
		 *  use formatting commands with System.out.printf() and String.format()
		 *  %f - float, %d - integer, %s - String
		 *  numbers after the % give the replaced value a width. Negative values left align.
		 *  decimal values after %f tell how many decimal places to print the float as.
		 *  use %% for to print out single percent sign, use \\ to output single backslash
		 */
		
		for (int i = 0; i < 150; i += 20) System.out.printf("%3d: %s\n", i, "here is some text.");
		for (int i = 0; i < 20; i++) System.out.printf("%5.2f\n", Math.random() * 20);
		
		
	}

}
