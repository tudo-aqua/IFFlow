import tools.aqua.concolic.Verifier;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;

public class Main {
    public static int low;
    private static int high = Tainting.taint(Verifier.nondetInt(), IFSPEC);
    
   public static void main(String[] args){
      secure_if_high_n5_n1();
   }
       
    
    static void secure_if_high_n5_n1() {
        if (high > 0) {
            low = n5(high);
            Tainting.check(low, IFSPEC);
        } else {
            high = -high;
            low = n5(high + low);
            Tainting.check(low, IFSPEC);
        }
        Tainting.stopAnalysis();
    }
    
    
    static int n5(int x) {
        high = 2 * x;
        return 15;
    }
}
