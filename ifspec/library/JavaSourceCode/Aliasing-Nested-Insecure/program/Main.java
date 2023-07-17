import tools.aqua.concolic.Verifier;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;
public class Main {

    static class A {
        B b;

        A(B b) {
            this.b = b;
        }
    }

    static class B {
        int val;

        B(int val) {
            this.val = val;
        }
    }

    static int secret = Tainting.taint(Verifier.nondetInt(), IFSPEC);

    public static void main(String[] args) {
        B b = new B(1);
        A a = new A(b);

        b.val = secret;

        Tainting.check(a.b.val, IFSPEC);
        Tainting.stopAnalysis();

        System.out.println(a.b.val);
    }
}
