/*
kulakevych Stas
 */

import java.math.*;
import acm.program.*;

public class Towers extends ConsoleProgram {

	public static final int discsFrom = 1;
	public static final int AnotherOne = 3;

	public void run() {
		this.setSize(500, 500);
		this.setFont("SeinsSherif-20");
		while (true) {
			println("Choose number of discs");
			int a = readInt();
			if(a>0){
			println("Choose tower to put discs on");
			int discsTo = readInt();

			towers(a, discsFrom, discsTo, AnotherOne);
			if (!(discsFrom < 1 || discsFrom > 3 || AnotherOne < 1
					|| AnotherOne > 3 || discsTo < 1 || discsTo > 3 || discsFrom == discsTo))
				println("Number of steps= " + (Math.pow(2, a) - 1));
			else
				println("Wrong numbers");
			}
			else
				println("Wrong numbers");
		}

	}

	private void towers(int h, int discsFrom, int discsTo, int c) {
		if (discsFrom < 1 || discsFrom > 3 || c < 1 || c > 3 || discsTo < 1
				|| discsTo > 3 || discsFrom == discsTo)
			return;
		if (c == discsTo)
			c = 2;
		if (h == 1) {
			System.out.println("Перемістити диск 1 з голки " + discsFrom + "->"
					+ discsTo);
			return;
		}
		towers(h - 1, discsFrom, c, discsTo);
		System.out.println("Перемістити диск " + h + " з голки " + discsFrom
				+ "->" + discsTo);
		towers(h - 1, c, discsTo, discsFrom);
	}
}
