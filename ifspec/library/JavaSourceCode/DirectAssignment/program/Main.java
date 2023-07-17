import tools.aqua.concolic.Verifier;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;

class Main {

    public static void main (String [] args) {
        int h = Verifier.nondetInt();
        h = Tainting.taint(h, IFSPEC);
        int sink = leakyMethod(h);
        Tainting.check(sink, IFSPEC);
        Tainting.stopAnalysis();
    }

	public static int leakyMethod(int high) {
        return high;
	}
}
