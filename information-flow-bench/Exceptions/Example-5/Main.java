import tools.aqua.concolic.Tainting;
import tools.aqua.concolic.Verifier;

import static tools.aqua.concolic.Tainting.IFSPEC;

public class Main {
    private static int i;
    public static void main(String[] argv) {
	i = Tainting.taint(Verifier.nondetInt(),IFSPEC);
	int source1 = i;
	int source2 = Verifier.nondetInt();
	int config = 10;
	int[] arr = new int[config];
	int z = 1;
	int sink = 0;
	for(int i = 0; i < config; i++) {
	    arr[i] = 2*i;
	}
	// source1 == 0 => Exception => z is modified
	try {
	    int x = 1/source1;
	}
	catch(Exception e) {
	    z = 0;
	}

	// z == 1, i.e. not modified => Exception => sink is modified
	try {
	    arr[source2*z] = 1000;
	}
	catch(Exception e){
	    sink = 1;
	}

	// The second exception is only thrown if the first was not thrown.
	Tainting.check(sink,IFSPEC);
    }
	
  
}
