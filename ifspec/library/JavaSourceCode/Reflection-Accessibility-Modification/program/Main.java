import tools.aqua.concolic.Verifier;

import java.lang.reflect.Field;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;

public class Main {
	private static boolean secret = Tainting.taint(Verifier.nondetBoolean(), IFSPEC);
	
	public static void main(String args[]) throws Exception {
		SecureSafe superSecureSafe = new SecureSafe();
		
		Field passwordField = SecureSafe.class.getDeclaredField("password");
		passwordField.setAccessible(secret);
		boolean sink = false;
		try {
			passwordField.get(superSecureSafe).toString();
			sink = false;
		} catch(Exception e) {
			sink = true;
		}
		Tainting.check(sink, IFSPEC);
		Tainting.stopAnalysis();
	}
}
