import tools.aqua.concolic.Tainting;
import tools.aqua.concolic.Verifier;
import static tools.aqua.concolic.Tainting.IFSPEC;

class Main {
	public static boolean leakyMethod(boolean high) {
		boolean ret;
		ret = (high && true);
		return ret;
	}
	public static void main(String arg[]){
		boolean a = Tainting.taint(Verifier.nondetBoolean(), IFSPEC);
		Tainting.check(leakyMethod(a), IFSPEC);
		Tainting.stopAnalysis();
	}
}
