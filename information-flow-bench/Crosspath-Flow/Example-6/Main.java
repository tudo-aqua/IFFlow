import tools.aqua.concolic.Tainting;
import tools.aqua.concolic.Verifier;

import static tools.aqua.concolic.Tainting.IFSPEC;

public class Main {
    static class A {
	public int inner = 0;
    }
    private static int i;
    public static void main(String[] argv) {
	i = Tainting.taint(Verifier.nondetInt(),IFSPEC);
	int source = i;
	A a = new A();
	A b = a;
	int z = 0;

	if(source == 0) {
	    z = 1;
	}

	b.inner = 1;

	Tainting.check(a.inner, IFSPEC);
	
    }
}
