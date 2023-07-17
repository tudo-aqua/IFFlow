import tools.aqua.concolic.Verifier;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;

class Main {
    
		public static void main(String args[]) {
            int h = Verifier.nondetInt();
            Tainting.taint(h, IFSPEC);
            Tainting.check(computeSecretly(h), IFSPEC);
            Tainting.stopAnalysis();
    }
    
		// compare the secret input to 0, set the return value to some
    // intermediate value, but set the return value to 5 eventually
    private static int computeSecretly(int h) {
        int a = 42;

        if (h > 0) {
            a  = 5;
        } else {
            a = 3;
        }

        if (h <= 0) {
            a = 5;
        }

        return a;
    }
}

