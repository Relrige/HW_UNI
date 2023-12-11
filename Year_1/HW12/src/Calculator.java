/*
 kulakevych Stas
 */

import acm.program.*;

public class Calculator extends ConsoleProgram {

	public void run() {
		this.setSize(500, 500);
		this.setFont("SeinsSherif-25");
		while (true) {
			int num1 = readInt("Введіть перше число: ");
			int num2 = readInt("Введіть друге число: ");
			String str = readLine("Enter Dia");
			if (str.length() == 1) {
				calc(num1, num2, str.charAt(0));
			}

			println("To end enter 0");
			double i = readDouble();
			if (i == 0) {
				println("\nTHE END");
				return;
			}
		}

	}

	private void calc(int num, int num2, char dia) {
		if (dia == '+') {
			print("Додавання: " + (num + num2) + "\n");
		} else if (dia == '-') {
			print("Віднімання: " + (num - num2) + "\n");
		} else if (dia == '/') {
			if (num2 != 0)
				print("Ділення: " +((double)num / (double)num2) + "\n");
			else
				print("Ділення на нуль неможливо\n");
		} else if (dia == '*') {
			print("Множення: " + (num * num2) + "\n");

		}

	}

}
