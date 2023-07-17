import tools.aqua.concolic.Verifier;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;

public class Main {
	public Main() {
		System.out.println("Please enter two private integers a and b.");
		System.out.println("We will return a/b in integer precision");
		System.out.print("Enter one private integer: ");
		int i1 = Verifier.nondetInt();
		System.out.print("Enter an other private integer: ");
		int i2 = Tainting.taint(Verifier.nondetInt(), IFSPEC);
		int r = i1/i2;
		String result = i1 + " / " + i2 + " = " + r;
		System.out.println(" " + result);
		System.out.println("Your query was saved in our secure database,"
				+ " such that we can use it for marketing issues.");
		System.out.println("We guarantee that your query will not be saved publicly on the disk.");
		this.writeToDB(result);
	}
	
	private void writeToDB(String entry) {
		// saves the new entry to a secure DB for logging of user inputs
		System.out.println("writeToDB:" + entry);
	}
	
	public static void writeToDisk(String err) {
		// saves the error into a public log file, such that technicians can solve the problem.
		System.out.println("writeToDisk:" + err);
	}

	public static void main(String... args) {
		try	{
			new Main();
		} catch (Exception e) {
			writeToDisk(e.toString());
			Tainting.check(e.toString(), IFSPEC);
			Tainting.stopAnalysis();
		}
	}
}
