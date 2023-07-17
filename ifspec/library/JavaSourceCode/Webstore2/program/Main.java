import tools.aqua.concolic.Verifier;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;

public class Main {
    public int low;
    private int high;

    private static int h;
    private static int l = Verifier.nondetInt();

    private int[] transaction;
  
    
   public static void main(String[] args){

      Main webstore = new Main();
      Tainting.check(webstore.seePreview(l), IFSPEC);
      Tainting.stopAnalysis();
      webstore.seePrime(l);
   }

   private VideoSet[] vids;

   public Video seePreview(int i) {
     if (vids != null && 0 <= i && i < vids.length) {
       return vids[i].vFree;
     } else {
       return null;
     }
   }

  public Video seePrime(int i) {
     if (vids != null && 0 <= i && i < vids.length) {
       return vids[i].vPrime;
     } else {
       return null;
     }
   }
          

   public static class VideoSet {
     public Video vFree;
     public Video vPrime;
       {
           Tainting.taint(vPrime, IFSPEC);
       }
   }

   public static class Video{}
}
