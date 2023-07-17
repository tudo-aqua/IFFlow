import tools.aqua.concolic.Verifier;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;

class Main {
	public static void main(String args[]){
		int h = Verifier.nondetInt();
		h = Tainting.taint(h, IFSPEC);
		int sink = f(h, Verifier.nondetInt());
		Tainting.check(sink, IFSPEC);
		Tainting.stopAnalysis();
	}

	public static int f(int h, int l)
	{
		l = h;
		return l;
	}
}
