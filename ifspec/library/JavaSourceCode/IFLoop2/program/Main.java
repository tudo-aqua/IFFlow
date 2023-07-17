import tools.aqua.concolic.Verifier;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;

public class Main {
    public static int low;
    private static int high = Verifier.nondetInt();


   public static void main(String[] args){
      insecure_ifl();
   }
      

    public static void insecure_ifl() {
        high = Tainting.taint(high, IFSPEC);
	int x = 0;
	int y = 0;
	while (y < 10) {
	    print(x);
	    if (y == 5) {
		x = high;
	    }
	    x++;
	    y++;
	}
    Tainting.check(low, IFSPEC);
    Tainting.stopAnalysis();
    }

    public static void print(int x) {
            low = x;
    }
}
