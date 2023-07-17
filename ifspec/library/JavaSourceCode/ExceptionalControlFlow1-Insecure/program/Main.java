import tools.aqua.concolic.Verifier;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;

public class Main {

    private static class T extends Exception {}

    /** Main test method parameter is the secret, return value is public */
    static boolean foo(boolean h) {
      try {
        if (h) {throw new T();}
      } catch (T t){
          return true;
      }
      return false;
    }

    public static void main (String [] args) {
        boolean h = Verifier.nondetBoolean();
        h = Tainting.taint(h, IFSPEC);
        boolean sink = foo(h);
        Tainting.check(sink, IFSPEC);
        Tainting.stopAnalysis();
    }
}
