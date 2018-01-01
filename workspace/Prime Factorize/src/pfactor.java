import java.util.*;
public class pfactor {

	public pfactor() {
		Scanner scanner = new Scanner(System.in);
		long n = Long.parseLong(scanner.nextLine());
		String factorization = "";
		while (!isPrime(n)) {
			long m = firstPFactor(n);
			factorization += m + " * ";
			n = n / m;
		} factorization += n;
		System.out.println(factorization.contains(" * ") ? factorization : "Cannot be factored");
		scanner.close();
	}
	
	public static void main(String[] args) {
		new pfactor();
	}
	
	public boolean isPrime(long num) {
		for (long i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0) {
				return false;
			}
		} return true;
	}
	
	public long firstPFactor(long num) {
		for (long i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0) {
				return i;
			}
		} return 1;
	}

}
