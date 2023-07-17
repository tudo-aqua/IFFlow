import tools.aqua.concolic.Verifier;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;

public class Main {

    static class A {
        int val;

        A(int val) {
            this.val = val;
        }
    }

    static int secret = Tainting.taint(Verifier.nondetInt(), IFSPEC);

    public static void main(String[] arg) {
        A a = new A(secret);
        A b = new A(5);
        A c = b;

        b = a;

        a.val = 2;
        Tainting.check(c.val, IFSPEC);
        Tainting.stopAnalysis();
        System.out.println(c.val);
    }
}
