import tools.aqua.concolic.Verifier;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;

class Main {
    static int g(int a) throws Exception { 
        if (a < 0)
            throw new Exception();
        return 1;
    }
    static int f(int a) {
        int x;
        try {
            x = g(a);
            x++;
        }
        catch(Exception e) {
            x = 0;
        }
        return x;
    }
    public static void main(String args[]) throws Exception {
        int h = Tainting.taint(Verifier.nondetInt(), IFSPEC);
        Tainting.check(f(h), IFSPEC);
        Tainting.stopAnalysis();
    }
};
