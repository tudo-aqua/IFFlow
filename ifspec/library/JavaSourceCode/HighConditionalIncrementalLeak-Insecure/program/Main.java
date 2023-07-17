import tools.aqua.concolic.Verifier;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;

class Main {
    public static void main(String args[]){
			int h = Verifier.nondetInt();
			int l = 1;
			h = Tainting.taint(h, IFSPEC);
			int sink = f(h,l);
			Tainting.check(sink, IFSPEC);
			Tainting.stopAnalysis();
			//System.out.println(f(h, l));
    }

    public static int f(int h, int l)
    {
			while (h>0){
				h--;
				l++;
			}
			return l;
    }
}
