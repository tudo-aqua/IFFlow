import tools.aqua.concolic.Verifier;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;

public class PasswordManager {
	private String password = Tainting.taint(Verifier.nondetString(), IFSPEC);
	private int invalidTries = 0;
	private int maximumTries = 10;
	private boolean loggedIn = false;
	
	public void tryLogin(String tryedPassword) {
		if(this.invalidTries < this.maximumTries) {
			if(this.password.equals(tryedPassword)) {
				this.loggedIn = true;
			} else {
				this.loggedIn = false;
				this.invalidTries++;
			}
		}
		Tainting.check("Login Attempt Completed", IFSPEC);
		Tainting.stopAnalysis();
		System.out.println("Login Attempt Completed");
	}
}

