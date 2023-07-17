import tools.aqua.concolic.Verifier;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;

public class Main {

    static int foo(int h) {
        int y = id(h);
        int x = 0;
        return id(x);
    }

    static int id(int x) {
      return x;
    }

    public static void main (String [] args) {
        int h = Verifier.nondetInt();
        h = Tainting.taint(h, IFSPEC);
        int x = foo(h);
	Tainting.check(x, IFSPEC);
        Tainting.stopAnalysis();

    }

    /** Helper method to obtain a random boolean */
    static boolean randBool() {
        return true;
    }
}
