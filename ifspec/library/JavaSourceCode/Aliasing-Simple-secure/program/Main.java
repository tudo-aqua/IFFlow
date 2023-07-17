import tools.aqua.concolic.Verifier;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;
class Main {

    static class A { 
        int i; 
    }

    static void set(A v1, A v2, int h) {
        v1.i = h;
    }

    static int getNumber() {return Tainting.taint(Verifier.nondetInt(), IFSPEC);}

    static int test(int i){
    	A v1 = new A();
        A v2 = new A();        
        set (v1, v2, i);
        Tainting.check(v2.i, IFSPEC);
        Tainting.stopAnalysis();
        return v2.i;
    }

    public static void main (String args[]) throws Exception {
        test(getNumber());
    }
}
