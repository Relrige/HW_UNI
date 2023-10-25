/*
kulakevych Stas
 */

import java.math.*;
import acm.program.*;

public class Add extends ConsoleProgram {

	public void run() {
		this.setSize(500, 500);
		this.setFont("SeinsSherif-20");
		while (true) {
			println("Choose program");
			println("1 - Fibonachi");
			println("0 - end");

			int choose = readInt();

			switch (choose) {
			case 1:
				println("Enter number to count to Fibonachi");
				int numbToCountTo = readInt();
				int i = 0;
				rFibonachi(numbToCountTo, 0, 1, i);

				break;
			}
			if(choose==0)
				return;
		}

	}

	private int Fibonachi(int a, int firstNumb, int secondNumb) {
		if (a < 0)
			return 1;
		int nextNumb = 0;
		for (int i = 0; i < a; i++) {
			println(i + " " + firstNumb);
			nextNumb = firstNumb + secondNumb;
			firstNumb = secondNumb;
			secondNumb = nextNumb;
		}

		return 0;
	}

	private int rFibonachi(int numbToCountTo, int b, int c, int i) {
		if (numbToCountTo < 0)
			return 1;

		int res = b;
		if (res > numbToCountTo)
			return 0;
		println(i + " " + res);
		res = b + c;
		return res + rFibonachi(numbToCountTo, c, res, ++i);
	}

}
