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
		String securityAnswer = (str >= '0' && str <= '9') ? " ����� �� '0' �� '9'"
				: " �� ����� �� '0' �� '9'";
		println(securityAnswer);
		securityAnswer = (str >= 'a' && str <= 'z') ? " char �� 'a' �� 'z'"
				: " NE char �� 'a' �� 'z'";
		println(securityAnswer);
		securityAnswer = (str >= 'A' && str <= 'Z') ? " char �� 'A' �� 'Z'"
				: " NE char �� 'A' �� 'Z'";
		println(securityAnswer);
	}
}