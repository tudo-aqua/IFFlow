import tools.aqua.concolic.Verifier;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;

public class Main {

	static String l = "Foo";
	static String h = Tainting.taint(Verifier.nondetString(), IFSPEC);

	static String x = "Foo";
	static {
		x = h;
	}

	public static void main(String[] args) {
		l=x;
		Tainting.check(l, IFSPEC);
		Tainting.stopAnalysis();
		/* System.out.println(l); */
	}

}
