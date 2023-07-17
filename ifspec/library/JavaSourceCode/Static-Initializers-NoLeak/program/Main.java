import tools.aqua.concolic.Verifier;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;

public class Main {

	static String l = "Foo";
	static String h = Tainting.taint(Verifier.nondetString(), IFSPEC);
	static String x = "Foo";
	
	
	static class A {
		static int f=17;
		static {
			l=x;
			System.out.println("Ainit");
		}
	}
	
	static class B {
		static {
			x=h;
			System.out.println("Binit");
		}
	}
	
	static void f(Object a, Object b) {}

	public static void main(String[] args) {
		f(A.f,new B());
		/*int x = A.f;
		new B();*/
		Tainting.check(l, IFSPEC);
		Tainting.stopAnalysis();
		System.out.println(l);
	}

}
