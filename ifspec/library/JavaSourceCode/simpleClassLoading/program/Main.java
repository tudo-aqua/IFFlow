import tools.aqua.concolic.Verifier;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;
import java.lang.reflect.Method;

public class Main {
	private static boolean secret = Tainting.taint(Verifier.nondetBoolean(), IFSPEC);

	public static void main(String[] args) throws Exception {
		// Inspired by
		// http://stackoverflow.com/questions/482633/in-java-is-it-possible-to-know-whether-a-class-has-already-been-loaded
		Method m = ClassLoader.class.getDeclaredMethod("findLoadedClass", new Class[] { String.class });
		m.setAccessible(true);
		ClassLoader cl = ClassLoader.getSystemClassLoader();

		if (secret) {
			new A();
		} else {
			new B();
		}

		Object test = m.invoke(cl, "simpleClassLoading.simpleClassLoading$A");
		boolean reconstructed = test != null;
		Tainting.check(reconstructed, IFSPEC);
		Tainting.stopAnalysis();
		System.out.println("Reconstruction was " + reconstructed);
	}

	static class A {
	}

	static class B {
	}
}
