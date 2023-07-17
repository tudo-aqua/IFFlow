import tools.aqua.concolic.Verifier;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;

public class Main {

    private static String secret = Tainting.taint(Verifier.nondetString(), IFSPEC);

    static class A {
        static String stored;

        static {
            stored = secret;
            Tainting.check("initialized", IFSPEC);
            Tainting.stopAnalysis();
            System.out.println("initialized");
        }

        int add(int a, int b) {
            return a + b;
        }
    }

    public static void main(String[] args) {
        A a = new A();
        a.add(1, 2);
    }

}
