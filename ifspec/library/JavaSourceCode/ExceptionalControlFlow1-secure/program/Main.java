import tools.aqua.concolic.Verifier;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;

public class Main {

    private static class T extends Exception {}

    /** Main test method parameter is the secret, return value is public */
    static boolean foo(boolean h) {
      boolean x = false;
      try {
        if (h) {throw new T();}
      } catch (T t){
        //Nothing happening here
      }
      x = true;
      return x;
    }

    public static void main (String [] args) {
        boolean h = Verifier.nondetBoolean();
        Tainting.taint(h, IFSPEC);
        Tainting.check(foo(h), IFSPEC);
        Tainting.stopAnalysis();
    }
}
