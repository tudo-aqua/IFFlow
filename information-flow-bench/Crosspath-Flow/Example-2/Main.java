import tools.aqua.concolic.Verifier;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;

public class Main {
    private static int i;
    
    public static void main(String[] argv) {
	i = Tainting.taint(Verifier.nondetInt(), IFSPEC);
	int y = 0;
	int z = 0;
	int x = 0;
	if(i == 0) {
	    z = 1;
	} else {
	}
	if(z == 0) {
	    x = 1;
	} else {
	}

	Tainting.check(y, IFSPEC);
        Tainting.stopAnalysis();
    }
}
