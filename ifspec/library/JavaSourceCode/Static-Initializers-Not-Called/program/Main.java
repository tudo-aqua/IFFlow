import tools.aqua.concolic.Verifier;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;

public class Main {

    private static String secret = Tainting.taint(Verifier.nondetString(), IFSPEC);

    static class A {
        static {
            Tainting.check(secret, IFSPEC);
            Tainting.stopAnalysis();
            System.out.println(secret);
        }
    }

    public static void main(String[] args) {
        System.out.println("nothing here.");
    }
}
