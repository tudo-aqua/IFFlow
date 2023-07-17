import tools.aqua.concolic.Verifier;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;
import java.lang.ArithmeticException;

public class Main {
	public static int divide(int l, int h){
		int z = 0;
		try{
			z = l / h;
		}catch(ArithmeticException e){
			Tainting.check(h, IFSPEC);
			Tainting.stopAnalysis();
			System.out.println(h + " is not defined");
		}
		return z;
	}
	public static void main(String[] args) {
		int h = Verifier.nondetInt();
		h = Tainting.taint(h, IFSPEC);
		divide(Verifier.nondetInt(), h);
	}
}
