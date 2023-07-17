import tools.aqua.concolic.Verifier;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;

class Main {
    private static String a = "in";
    private static String b = "secure";
    private static String c = "i";
    private static String d = "nse";
    private static String e = "cure";
    public static boolean foo(boolean h) {
      // Write h to the jvm string pool
      if(h)
        (a + b).intern();

      // Read back the value of h from the string pool
      String s = (c + d + e);
      Tainting.check(s.intern() != s, IFSPEC);
      Tainting.stopAnalysis();
      return (s.intern() != s);
    }

    public static void main (String [] args) {
        boolean h = Verifier.nondetBoolean();
        h = Tainting.taint(h, IFSPEC);
        foo(h);
    }

    /** Helper method to obtain a random boolean */
    static boolean randBool() {
        return System.currentTimeMillis() % 2 == 0;
    }
    
    /** Helper methot to obtain a random integer */
    static int randInt() {
      return (int) System.currentTimeMillis();
    }

}
