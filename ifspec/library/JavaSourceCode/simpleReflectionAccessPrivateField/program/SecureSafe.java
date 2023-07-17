import tools.aqua.concolic.Verifier;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;

public class SecureSafe {

	private String password = Tainting.taint(Verifier.nondetString(), IFSPEC);
	
}
