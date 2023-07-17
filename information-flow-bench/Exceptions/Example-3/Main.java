import tools.aqua.concolic.Tainting;
import tools.aqua.concolic.Verifier;

import static tools.aqua.concolic.Tainting.IFSPEC;


public class Main {
    private static int i;
  public static void main(String[] args) {
      i = Verifier.nondetInt();
      i = Tainting.taint(i, IFSPEC);
      int source = i;
      int a = 0;
      try {
	  int x = 5/source;
	  a = 1;
      }
      catch(Exception e) {
	  
      }
      int sink = 1;
      Tainting.check(sink, 1);
  }

}
