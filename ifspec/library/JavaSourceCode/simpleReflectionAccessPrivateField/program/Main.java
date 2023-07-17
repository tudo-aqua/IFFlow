import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;

public class Main {
	public static void main(String[] args) throws IOException, InterruptedException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		SecureSafe superSecureSafe = new SecureSafe();
		Tainting.check(Main.hackSafe(superSecureSafe), IFSPEC);
		Tainting.stopAnalysis();
		System.out.println(Main.hackSafe(superSecureSafe));
	}
	
	public static String hackSafe(SecureSafe easyToHackSafe) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		Field insecurePasswordField = SecureSafe.class.getDeclaredField("password");
		insecurePasswordField.setAccessible(true);
		return insecurePasswordField.get(easyToHackSafe).toString();
	}
}
