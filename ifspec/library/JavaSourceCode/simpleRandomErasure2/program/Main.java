import tools.aqua.concolic.Verifier;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;
import java.util.Random;

public class Main {
	private static int secret = Tainting.taint(Verifier.nondetInt(), IFSPEC);
	
	public static void main(String[] args) {
		int output = secret;
		
		int random = Verifier.nondetInt();
		output += random - secret;
		Tainting.check(output, IFSPEC);
		Tainting.stopAnalysis();
		System.out.println(Integer.toString(output));
	}
}
