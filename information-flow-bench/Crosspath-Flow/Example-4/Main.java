import tools.aqua.concolic.Verifier;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;

public class Main {
    private static int i;
    
    public static void main(String[] argv) {
	i = Tainting.taint(Verifier.nondetInt(), IFSPEC);
	int config = 10;
	int[] a = new int[config];
	for(int i = 0; i < config; i++) {
	    a[i] = i;
	}
	a[config/2] = i;
	int y = 0;
	int z = 0;
	for(int j = 0; j < config; j++){
	}
	if(y == 0) {
	    z = 1;
	}
	    
	    

	Tainting.check(z, IFSPEC);
        Tainting.stopAnalysis();
    }
}
