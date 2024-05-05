/*
kulakevych Stas
 */

import acm.program.*;

public class SymbolCheck extends ConsoleProgram {
	public boolean j = true;

	public void run() {
		this.setSize(500, 500);
		this.setFont("SeinsSherif-25");
		while (j) {
			String str = readLine("Enter char");
			if (str.length()==1) {
				check(str.charAt(0));
			}
		}
	}

	private void check(char str) {
		String securityAnswer = (str >= '0' && str <= '9') ? " öèôğà â³ä '0' äî '9'"
				: " ÍÅ öèôğà â³ä '0' äî '9'";
		println(securityAnswer);
		securityAnswer = (str >= 'a' && str <= 'z') ? " char â³ä 'a' äî 'z'"
				: " NE char â³ä 'a' äî 'z'";
		println(securityAnswer);
		securityAnswer = (str >= 'A' && str <= 'Z') ? " char â³ä 'A' äî 'Z'"
				: " NE char â³ä 'A' äî 'Z'";
		println(securityAnswer);
	}
}