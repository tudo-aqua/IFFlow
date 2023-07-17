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
	    // This if-Block has no functional purpose.
	    // javac registers exception handlers at the first instruction that can throw an exception.
	    // There without the first if, javac registers the handler after x's assignment and the anaylsis returns the correct result by accident.
	    if(source != 0){
		int y = 1/source;
	    }
	    x = 3;
	    if(source == 0) {
		int y = 1/source;
	    }
	}
	catch(Exception e) {
	}

	sink = x;

	Tainting.check(sink, IFSPEC);
	
    }
}
