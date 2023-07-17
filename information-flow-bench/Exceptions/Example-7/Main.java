import tools.aqua.concolic.Tainting;
import tools.aqua.concolic.Verifier;

import static tools.aqua.concolic.Tainting.IFSPEC;

public class Main {
    private static int i;
    public static void main(String[] argv) {
	i = Tainting.taint(Verifier.nondetInt(),IFSPEC);
	int source = i;
	int x = 1;
	int sink = 1;
	
	try {
	    int y = 1/source;
	}
	catch(Exception e) {
	    x = 0;
	}
	try {
	    int y = 1/x;
	}
	catch(Exception e) {
	    sink = 0;
	}

	Tainting.check(sink, IFSPEC);
	
    }
}
