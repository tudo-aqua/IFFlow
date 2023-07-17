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

    static private int secret = Tainting.taint(Verifier.nondetInt(), IFSPEC);

    public static void main(String[] args) {
        A a = new A(1);
        A b = a;

        if (secret == 42) {
            a.val = 2;
        } else {
            a.val = 2;
        }
        Tainting.check(b.val, IFSPEC);
        Tainting.stopAnalysis();
        System.out.println(b.val);
    }
}
