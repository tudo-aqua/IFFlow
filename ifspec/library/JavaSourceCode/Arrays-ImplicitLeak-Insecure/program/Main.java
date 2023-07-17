import tools.aqua.concolic.Verifier;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;

public class Main {

    static private int secret = Tainting.taint(Verifier.nondetInt(), IFSPEC);

    public static void main(String[] args) {

        int[] arr = new int[5];
        arr[0] = secret;

        Tainting.check(arr[0], IFSPEC);
        Tainting.stopAnalysis();

        if (arr[0] == 42) {
            System.out.println("Found");
        }

    }

}
