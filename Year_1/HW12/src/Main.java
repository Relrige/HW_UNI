/*
kulakevych Stas
 */

import acm.program.*;

public class Main extends ConsoleProgram {
	public boolean j = true;
	public boolean check=true;

	public void run() {
		this.setSize(500, 500);
		this.setFont("SeinsSherif-25");
		while (j) {
			String str = readLine("Enter str: ");
			check(str);

		}
	}

	private void check(String str) {
		String res="";
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) != ' ' && check) {
				if ((str.charAt(i) >= 'A' && str.charAt(i) <= 'Z')) {
					for (int j = 0; j+i <str.length(); j++) {
						if (str.charAt(i + j) != ' ')
							res += str.charAt(i+j);
						else
							break;
						
					}
					check=false;
					res+=' ';
				}
				else
					check=false;
			} 
			else if (str.charAt(i) == ' ') {
				println("Check: " +check);
				check = true;
			}
		}
		print(res+"\n");
		check = true;
		
	}
}