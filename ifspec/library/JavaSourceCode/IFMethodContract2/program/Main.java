import tools.aqua.concolic.Verifier;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;

public class Main {
    public static int low;
    private static int high;
  
    
   public static void main(String[] args){
       int h = Tainting.taint(Verifier.nondetInt(), IFSPEC);
       Tainting.check(insecure_if_high_n1(h), IFSPEC);
       Tainting.stopAnalysis();
   }
       
    
    static int insecure_if_high_n1(int high) {
		int low;
        if (high > 0) {
            low = n5(high);
        } else {
            low = 7;
        }
        low = n1(high);
		return low;
    }

    static int n1(int x){
		return 27;
    }
    
    static int n5(int x) {
        return 15;
    }
    
    
}
