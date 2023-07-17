import tools.aqua.concolic.Verifier;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;

public class Main {

    static private int secret = Tainting.taint(Verifier.nondetInt(), IFSPEC);

    public static void main(String[] args) {

        int[] arr = new int[5];
        arr[0] = secret;
        int sink = 0;

        

        if (arr[0] == secret) {
            sink = 1;
        } else {
            sink = 1;
        }

        Tainting.check(sink, IFSPEC);
        Tainting.stopAnalysis();

    }

}
