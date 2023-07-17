import tools.aqua.concolic.Verifier;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;
import java.util.Random;

public class Main {
	private static int secret = Tainting.taint(Verifier.nondetInt(), IFSPEC);
	
	public static void main(String[] args) {
	    Verifier.assume(secret >= 0);
	    int output = secret;
	    
	    Random random = new Random();
	    boolean b = Verifier.nondetBoolean();
	    int randomInt = Verifier.nondetInt();
	    if(b) {
		// output += random.nextInt(Integer.MAX_VALUE - secret);
		Verifier.assume(randomInt < Integer.MAX_VALUE - secret);
		output += randomInt;
	    } else {
		//output -= random.nextInt(-(Integer.MIN_VALUE + secret));
		Verifier.assume(randomInt < -(Integer.MIN_VALUE + secret));
		output -= randomInt;
	    }
	    Tainting.check(output, IFSPEC);
	    Tainting.stopAnalysis();
	    System.out.println(Integer.toString(output));
	}
}
