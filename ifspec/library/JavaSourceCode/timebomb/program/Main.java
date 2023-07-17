import tools.aqua.concolic.Verifier;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;

public class Main {
	public static void main(String[] args) {
		int value = Tainting.taint(Verifier.nondetInt(), IFSPEC);
    noLeak(value);
	}

  static long inThePast =  1456223086265L; // 23 Feb. 2016 11:24
	public static int noLeak(int h) {
    long curr = System.currentTimeMillis() ;
    if (curr < inThePast) {
        Tainting.check(h, IFSPEC);
        Tainting.stopAnalysis();
      return h;
    }
    Tainting.check(0, IFSPEC);
    Tainting.stopAnalysis();
    return 0;
  }
}
