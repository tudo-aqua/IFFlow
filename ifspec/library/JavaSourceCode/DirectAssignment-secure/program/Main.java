import tools.aqua.concolic.Verifier;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;

class Main {

    public static void main (String [] args) {
        int h = Verifier.nondetInt();
        Tainting.taint(h, IFSPEC);
        leakyMethod(h);
    }

	public static int leakyMethod(int high) {
		Tainting.check(0, IFSPEC);
        Tainting.stopAnalysis();
        return 0;
	}
}
