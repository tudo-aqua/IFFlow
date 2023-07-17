import tools.aqua.concolic.Verifier;

import java.util.*;
import java.io.*;
import java.lang.*;
import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;

class Main {
	static int countAlphanumeric(String passwd) {
        int count = 0;
        for (int i = 0; i < passwd.length(); i++) 
            if(Character.isDigit(passwd.charAt(i)) || Character.isLetter(passwd.charAt(i)))
                count++;

        return count; 
	}
	
	static int countCaps(String passwd) {
        int count = 0;
        for (int i = 0; i < passwd.length(); i++)
            if (passwd.charAt(i) >= 'A' && passwd.charAt(i) <= 'Z')
                count++;
        return count;
	}

	static int passwordstrength(String passwd) {
		int strength = 0; 

		if (countAlphanumeric(passwd) < 3)
			strength = 1;
		
		if (countCaps(passwd) < 3)
			strength = 2; 
		else
			strength = 3; 

		return strength;
	}

	public static void main (String args[]) {
        String passwd = Tainting.taint(Verifier.nondetString(), IFSPEC);
		int strength  = passwordstrength(passwd);
		Tainting.check(strength, IFSPEC);
		Tainting.stopAnalysis();
		System.out.println(strength);
	} 
}
