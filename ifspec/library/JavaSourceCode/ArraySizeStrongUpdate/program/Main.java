import tools.aqua.concolic.Verifier;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;

public class Main {

    static int secret = Tainting.taint(Verifier.nondetInt(), IFSPEC);

    public static void main(String[] args) {
        int[] a = new int[secret];
        a = new int[5];

        Tainting.check(a.length, IFSPEC);
        Tainting.stopAnalysis();

        System.out.println(a.length);
    }
}
