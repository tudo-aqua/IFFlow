import tools.aqua.concolic.Verifier;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;

class A {}
class B extends A {}
class C extends A {}

public class Main {
	static boolean secret = Tainting.taint(Verifier.nondetBoolean(), IFSPEC);
	
	public static void main(String[] args) {
		Tainting.check(test(), IFSPEC);
		Tainting.stopAnalysis();
	}


        public static boolean test(){
                A obj;
		
		if(secret) {
			obj = new B();
		} else {
			obj = new C();
		}
		
		boolean reconstructed = true;
		
		try{
			A test = ((B) obj);
		} catch(Exception e){
			reconstructed = false;
		} finally {
			return reconstructed;
		}
        }

     
}
