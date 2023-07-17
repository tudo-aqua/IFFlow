import tools.aqua.concolic.Verifier;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;

public class Main {

	public static int doIt(int h){
		// Assign the high variable h to the most significant half of the long variable x
		long x = h*256*256*256*256; // 4bytes to the left
		
		// Fill the least-significant part of x with random garbage
		x+= (Verifier.nondetInt());
		
		
		// Assign x to the low variable l
		// Here is where the "magic" happens
		// The casting form long to int drops the four most significant bytes, which contain the secret
		int l = (int) x;
		
		return l;
	}
	
	public static void main(String[] args) {	
		// Assign first input parameter to the high variable
		int h = Verifier.nondetInt();
		Tainting.taint(h, IFSPEC);
		// Do very important math
		int l = doIt(h);
		Tainting.check(l, IFSPEC);
		Tainting.stopAnalysis();
	}
}
