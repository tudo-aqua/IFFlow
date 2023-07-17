import tools.aqua.concolic.Verifier;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;

public class Main {
  
    
   public static void main(String[] args){
	   int h = Tainting.taint(Verifier.nondetInt(), IFSPEC);
	   secure_ifl(h);
   }
      

    public static int secure_ifl(int high) {
	int x = 0;
	int y = 0;
	int low = 23;
        //@ loop_invariant 0 <= y && y <= 10;
        //@ determines low, y, (y < 10 ? x : 0) \by \itself;
        //@ assignable low;
        //@ decreases 10 - y;
	while (y < 10) {
	    low = x;
	    if (y == 5) {
		x = high;
		y = 9;
	    }
	    x++;
	    y++;
	}
	Tainting.check(low, IFSPEC);
	Tainting.stopAnalysis();
	return low;
    }
}
