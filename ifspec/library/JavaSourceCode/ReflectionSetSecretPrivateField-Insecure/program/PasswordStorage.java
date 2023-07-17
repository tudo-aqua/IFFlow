import tools.aqua.concolic.Verifier;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;

public class PasswordStorage {

    private String password = Tainting.taint(Verifier.nondetString(), IFSPEC);
    private String version="4.2";

    public String getPassword(String auth) {
        if (auth.equals("secretHash")) {
            return password;
        }
        else {
            return "";
        }
    }

}
