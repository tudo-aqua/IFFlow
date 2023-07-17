import tools.aqua.concolic.Verifier;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;
public class Main {

    static class A {
        int val;

        A(int val) {
            this.val = val;
        }

        void update(int val) {
            this.val = val;
        }
    }

    static int secret = Tainting.taint(Verifier.nondetInt(), IFSPEC);

    public static void main(String[] args) {
        A a = new A(1);
        A b = new A(1);
        A c = b;

        doUpdate(a, secret);
        Tainting.check(c.val, IFSPEC);
        Tainting.stopAnalysis();
        System.out.println(c.val);
    }

    static void doUpdate(A a, int val) {
        a.update(val);
    }
}
