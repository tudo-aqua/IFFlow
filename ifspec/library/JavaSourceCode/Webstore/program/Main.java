import tools.aqua.concolic.Verifier;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;

public class Main {
    public int low;
    private int high;

    private static int h = Verifier.nondetInt();
    private static int l;

    private static int[] transaction;
  
    
   public static void main(String[] args) {
       Tainting.taint(h, IFSPEC);
       Tainting.check(buyProduct(l,h), IFSPEC);
       Tainting.stopAnalysis();
   }

       
   /*Customer buys the product and wants to pay with the given credit card number. 
     Afterwards, the store returns the bought product*/ 
   public static int buyProduct(int prod, int cc) {
      transaction = new int[2];
      transaction[0] = prod;
      transaction[1] = cc;

      return transaction[0];
   }    
}
