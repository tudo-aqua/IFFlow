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
				this.invalidTries=0;
			} else {
				this.loggedIn = false;
				this.invalidTries++;
			}
		} else {
			Tainting.check("No more password tries allowed", IFSPEC);
			Tainting.stopAnalysis();
			/* System.out.println("No more password tries allowed"); */
		}
	}
}
