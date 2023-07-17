import tools.aqua.concolic.Verifier;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;

public class Main {

    static int secret = Tainting.taint(Verifier.nondetInt(), IFSPEC);

    public static void main(String[] args) {
        int[] arr = new int[secret];

        for (int i=0; i<Integer.MAX_VALUE; i++) {
            try {
                int j=arr[i];
            } catch (Exception e) {
                Tainting.check(i, IFSPEC);
                Tainting.stopAnalysis();

                System.out.println(i);
                System.exit(0);
            }
        }
    }
}
