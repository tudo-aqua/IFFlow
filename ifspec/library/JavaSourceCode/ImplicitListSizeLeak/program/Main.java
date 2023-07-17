import tools.aqua.concolic.Verifier;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;

import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args) {
		int value = Verifier.nondetInt();
		value = Tainting.taint(value, IFSPEC);
		int sink = Main.listSizeLeak(value);
		Tainting.check(sink, IFSPEC);
		Tainting.stopAnalysis();
		/* System.out.println("Running simpleListSize");
		System.out.println("Secret value:   " + value);
		System.out.println("Returned value: " + Main.listSizeLeak(value)); */
	}

	public static int listSizeLeak(int h) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		int r = 0;

		for(int i = 0; i < h; i++) {
			list.add(42);
		}

		if (list.size() < 10) {
			r = 1;
		}
		
		return r;
	}
}
