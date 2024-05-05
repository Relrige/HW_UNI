import acm.program.*;

public class UseMethods extends ConsoleProgram {

	public void run() {
		setSize(500, 500);
		setFont("Times New Roman-28");
		Methods methods = new Methods();
		String s = "";
		int n = 0;
		while (true) {
			this.getConsole().clear();
			int choice = readInt("Input task number from 1 to 16: ");
			println();
			if (choice == 0)
				choice = -1;
			try {
				switch (choice) {
				case 0:
					return;
				case 1:
					println("print name and surname 10 times:");
					String name = readLine("Input name: ");
					String surName = readLine("Input surname: ");
					println();
					methods.printNames(this,name, surName);
					break;
				case 2:
					println("Output squares of first 10 positive numbers: ");
					println();
					methods.squareTen(this);
					break;
				case 3:
					println("Output squares of first 5 positive unpaired numbers: ");
					println();
					methods.square5(this);
					break;
				case 4:
					while (true) {
						println("Calculate sum of n first positive numbers");
						n = readInt("Input n: ");
						while (n <= 0) {
							println("Input positive number");
							n = readInt();
						}
						println();
						methods.sumN(this,n);
						println("To end enter 0");
						println("Any other to continue");
						double i = readDouble();
						if (i == 0) {
							println("THE END");
							break;
						}
					}
					break;
				case 5:
					while (true) {
						println("Calculate sum of {1, 3, 5... n}");
						n = readInt("Input n: ");
						while (n <= 0) {
							println("Input positive number");
							n = readInt();
						}
						println();
						methods.sumOdd(this,n);
						println("To end enter 0");
						println("Any other to continue");
						double i = readDouble();
						if (i == 0) {
							println("THE END");
							break;
						}
					}
					break;
				case 6:
					while (true) {
						println("Calculate sum of {1, 1/2, 1/3...1/n}");
						n = readInt("Input n: ");
						while (n <= 0) {
							println("Input positive number");
							n = readInt();
						}
						println();
						methods.sum1n(this,n);
						println("To end enter 0");
						println("Any other to continue");
						double i = readDouble();
						if (i == 0) {
							println("THE END");
							break;
						}
					}
					break;
				case 7:
					println("Output pow(2,10)");
					println();
					methods.square2(this);
					break;
				case 8:
					while (true) {
					println("Calculate n!");
					n = readInt("Input n: ");
					while (n < 0) {
						println("Input positive number");
						n = readInt();
					}
					println();
					methods.factorial(this,n);
					println("To end enter 0");
					println("Any other to continue");
					double i = readDouble();
					if (i == 0) {
						println("THE END");
						break;
					}
				}
					break;
				case 9:
					while (true) {
					println("Calculate y=-2.4x^2+5x-3");
					println();
					
					methods.function(this);
					println("To end enter 0");
					println("Any other to continue");
					double i = readDouble();
					if (i == 0) {
						println("THE END");
						break;
					}
				}
					break;
				case 10:
					while (true) {
					println("Generate 10 numbers from 1 to 10 and output sum and average");
					println();
					methods.randomAvg(this);
					println("To end enter 0");
					println("Any other to continue");
					double i = readDouble();
					if (i == 0) {
						println("THE END");
						break;
					}
				}
					break;
				case 11:
					while(true){
					println("Output cost of apples, where 1kg. cost n");
					n = readInt("Input n: ");
					while (n <= 0) {
						println("Input positive number");
						n = readInt();
					}
					println();
					methods.price(this,n);
					println("To end enter 0");
					println("Any other to continue");
					double i = readDouble();
					if (i == 0) {
						println("THE END");
						break;
					}
				}
					break;
				case 12:
					while(true){
					println("Math check of +/- from 1 to 100");
					println();
					methods.rndMath(this);
					println("To end enter 0");
					println("Any other to continue");
					double i = readDouble();
					if (i == 0) {
						println("THE END");
						break;
					}
				}
					break;
				case 13:
					while(true){
					println("Calculate sum and average of next numbers");
					println();
					methods.sumEntered(this);
					println("To end enter 0");
					println("Any other to continue");
					double i = readDouble();
					if (i == 0) {
						println("THE END");
						break;
					}
				}
					break;
				case 14:
					while(true){
					println("Output max number");
					println();
					methods.max(this);
					println("To end enter 0");
					println("Any other to continue");
					double i = readDouble();
					if (i == 0) {
						println("THE END");
						break;
					}
				}
					break;
				case 15:
					while(true){
					println("Check n for simple number");
					n = readInt("Input n: ");
					while (n <= 0) {
						println("Input positive number");
						n = readInt();
					}
					println();
					if (methods.Task15(this,n))
						println("n is simple");
					else
						println("n isn't  simple");
					println("To end enter 0");
					println("Any other to continue");
					double i = readDouble();
					if (i == 0) {
						println("THE END");
						break;
					}
				}
					break;
				case 16:
					while(true){
					println("Guess game. You have 7 attempts");
					println();
					methods.Task16(this);
					println("To end enter 0");
					println("Any other to continue");
					double i = readDouble();
					if (i == 0) {
						println("THE END");
						break;
					}
				}
					break;
				default:
					s = "Input right number: ";
					println(s);
				}
			} finally {
				if (s.matches("")) {
					println();
					choice = readInt("input 0 for stop, any num for continue: ");
					if (choice == 0)
						return;
				} else {
					s = "";
				}
			}

		}

	}
}