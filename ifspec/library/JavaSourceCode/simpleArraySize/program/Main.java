import tools.aqua.concolic.Verifier;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;

public class Main {
	
	public static void main(String[] args) {
		int value = Tainting.taint(Verifier.nondetInt(), IFSPEC);
		Main.arraySizeLeak(value);
	}

	/**
	 * Returns the number that was given, by passing
	 * it trough an array size.
	 * @param h secret value
	 * @return value given
	 */
	public static int arraySizeLeak(int h) {
		int[] array = new int[h];
		Tainting.check(array.length, IFSPEC);
		Tainting.stopAnalysis();
		return array.length;
	}
}
