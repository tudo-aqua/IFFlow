import tools.aqua.concolic.Verifier;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;
import java.lang.reflect.Field;


public class Main {
	private static boolean secret = Tainting.taint(Verifier.nondetBoolean(), IFSPEC);
    private static boolean pub = true;
	
	public static void main(String args[]) throws Exception {
		SecureSafe superSecureSafe = new SecureSafe();
		
		Field passwordField = SecureSafe.class.getDeclaredField("password");
		passwordField.setAccessible(pub);
		try {
			passwordField.get(superSecureSafe).toString();
			Tainting.check(false, IFSPEC);
			Tainting.stopAnalysis();
			System.out.println("The boolean was false");
		} catch(Exception e) {
			Tainting.check(true, IFSPEC);
			Tainting.stopAnalysis();
			System.out.println("The boolean was true");
		}
	}
}
