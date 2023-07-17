import tools.aqua.concolic.Verifier;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;
import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args) {
		int value = Tainting.taint(Verifier.nondetInt(), IFSPEC);
		System.out.println("Running simpleListSize");
		System.out.println("Secret value:   " + value);
		System.out.println("Returned value: " + listSizeLeak(value));
	}

	/**
	 * Returns the number that was given, by passing
	 * adding elements to a list and returning its size.
	 * @param h secret value
	 * @return value given
	 */
	public static int listSizeLeak(int h) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for(int i = 0; i < h; i++) {
			list.add(42);
		}
		Tainting.check(list.size(), IFSPEC);
		Tainting.stopAnalysis();
		return list.size();
	}
}
