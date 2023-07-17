import java.util.Random;

import tools.aqua.concolic.Verifier;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;

class Main {
    public static int f(int h, int l, int[] a)
    {
        for(int i=0;i < h;i++) {
            l += a[i];
		}
        Tainting.check(l, IFSPEC);
        Tainting.stopAnalysis();
        return l;
    }
    public static void main(String args[]){
        int h =  Tainting.taint(Verifier.nondetInt(), IFSPEC);
        int l = Verifier.nondetInt();
        int[] arr = randIntArray(h);
        f(h,l,arr);
    }

	/** Helper method to obtain an array of random
	 * 	integers with the given length
	 **/
	static int[] randIntArray(int length) {
		int[] ret = new int[length];
		for (int i = 0; i < length; i++) {
			ret[i] = 42; // Constant in original benchmark
		}
		return ret;
	}
}
