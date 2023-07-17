import tools.aqua.concolic.Verifier;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;

public class Main {
	
	public static int high = Tainting.taint(Verifier.nondetInt(), IFSPEC);
	public static int low = 1;
	


	public static void main(String[] args) {
		Tainting.check(test(high,low), IFSPEC);
		Tainting.stopAnalysis();
	}

        public static int test(int h, int l){
		A a1 = new A(l);
		A a2 = new A(h);

		return a1.doPrint();
        }

}
