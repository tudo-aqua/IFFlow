import tools.aqua.concolic.Verifier;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;

public class Main {

	static int h = Verifier.nondetInt();

	static int l = Verifier.nondetInt();
	
	static int lsink, hsink;
	
	public static void f()
	{
		if(l == 1)
			set((long) h);
		else
			set(h);
	}
	
	public static void set(long a) { lsink = (int) a;}
	public static void set(int a) { hsink = a;}
	
	
	public static void main(String[] args) {
		h = Tainting.taint(h, IFSPEC);

		f();

		Tainting.check(lsink, IFSPEC);
		Tainting.stopAnalysis();
	}
}
