import tools.aqua.concolic.Tainting;
import tools.aqua.concolic.Verifier;
import static tools.aqua.concolic.Tainting.IFSPEC;

public class Main {
    public static int foo(int h) {
        int [] a = new int [2];
        a[0] = h;
        return a[1];
    }

    public static void main (String[] args) {
        int h = Tainting.taint(Verifier.nondetInt(), IFSPEC);
        Tainting.check(foo(h), IFSPEC);
        Tainting.stopAnalysis();
    }
}
