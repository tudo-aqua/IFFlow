import tools.aqua.concolic.Tainting;
import tools.aqua.concolic.Verifier;

import static tools.aqua.concolic.Tainting.IFSPEC;

public class Main {

    private static int i;
    public static void main(String[] args) {
	i = Verifier.nondetInt();
	i = Tainting.taint(i, IFSPEC);
	int x = i;
	int u = 2;
	int z = 2;
	try {
	    bar(x);
	    u=1;
	}
	catch(Exception e) {
	    
	}

	if (u==1) {
	    z=1;
	}
	Tainting.check(z, IFSPEC);
    }

    private static void bar(int x) {
	if (x==0) {
	    int y=1/x;
	}
    }
}
