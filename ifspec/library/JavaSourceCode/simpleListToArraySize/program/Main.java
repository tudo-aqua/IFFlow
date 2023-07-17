import tools.aqua.concolic.Verifier;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		int value = Tainting.taint(Verifier.nondetInt(), IFSPEC);
		System.out.println("Running simpleListToArraySize");
		System.out.println("Secret value:   " + value);
		System.out.println("Returned value: " + listArraySizeLeak(value));
	}
	
	/**
	 * Returns the number that was given, by passing
	 * adding elements to a list, converting to an array
	 * and returning its size.
	 * @param h secret value
	 * @return value given
	 */
	public static int listArraySizeLeak(int h) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for(int i = 0; i < h; i++) {
			list.add(42);
		}
		
		Object[] array = list.toArray();

		Tainting.check(array.length, IFSPEC);
		Tainting.stopAnalysis();
		return array.length;
	}
}
