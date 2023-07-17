import tools.aqua.concolic.Verifier;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;

public class Main {

    private static String secret = Tainting.taint(Verifier.nondetString(), IFSPEC);
    private static String[] vals = {"a", "b", "c"};

    static class A {
        static {
            vals[0] = secret;
        }
    
        void leak() {
            String l = vals[0];
            Tainting.check(l, IFSPEC);
            Tainting.stopAnalysis();
            /* System.out.println(vals[0]); */
        }
    }

    public static void main(String[] args) {
        A a = new A();
        a.leak();
    }

}
