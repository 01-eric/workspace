
public class BitwiseOperators {

	public static void main(String[] args) {
		int bin = 0b11101001;
		int boi = 0b10111011;
		// demonstrates AND in base 2
		System.out.println(Integer.toString(bin, 2) + " & " + Integer.toString(boi, 2) + ": " + Integer.toString(bin & boi, 2));
		// demonstrates AND in base 10
		System.out.println(bin + " & " + boi + ": " + (bin & boi));
		
		/*
		 * Inclusive or: 1-1, 1-0, 0-1 (TT, TF, FT)
		 * Exclusive or: 1-0, 0-1 (TF, FT)
		 */
		
		// demonstrates INCLUSIVE OR in base 2
		System.out.println(Integer.toString(bin, 2) + " | " + Integer.toString(boi, 2) + ": " + Integer.toString(bin | boi, 2));
		// demonstrates INCLUSIVE OR in base 10
		System.out.println(bin + " | " + boi + ": " + (bin | boi));
		// demonstrates EXCLSUIVE OR in base 2
		System.out.println(Integer.toString(bin, 2) + " ^ " + Integer.toString(boi, 2) + ": 0" + Integer.toString(bin ^ boi, 2));
		// demonstrates EXCLUSIVE OR in base 10
		System.out.println(bin + " ^ " + boi + ": " + (bin ^ boi));
		// demonstrates NOT in base 2
		System.out.println("~" + Integer.toString(bin, 2) + ": " + Integer.toString(~bin, 2));
		
		// RIGHT SHIFT in base 2
		System.out.println("R-SHIFT 2 " + Integer.toString(bin, 2) + ": 00" + Integer.toString((bin >> 2), 2));
	}
	
}