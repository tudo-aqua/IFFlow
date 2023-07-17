import tools.aqua.concolic.Verifier;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;

public class Main {
	private static boolean secret = Tainting.taint(Verifier.nondetBoolean(), IFSPEC);
	
	public static void main(String[] args) {

		Tainting.check(test(), IFSPEC);
		Tainting.stopAnalysis();
	}

        public static int test(){
                int value;
		
		if(secret) {
			value = 1;
		} else {
			value = 1;
		}
		
		return value;        
        }
	
}
