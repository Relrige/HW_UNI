/*Kulakevych Stas*/

import acm.program.*;
import acm.util.*;

public class Game extends ConsoleProgram {

	public void run() {
		this.setSize(600, 600);
		this.setFont("SeinsSherif-30");
		while (true) {
			int number = rgen.nextInt(1, 100);
			println("");
			println("WELCOME TO Guess GAME");
			println("");
			int i = 0;
			while (true) {
				int guess = readInt("Guess number: ");
				if (guess > 0 && guess < 100) {
					i++;
					if (guess > number)
						println("Less");
					else if (guess < number)
						println("More");
					else {
						println("---------YOU WON------------"+"In "+i+" steps");
						break;
					}
				}
				else
					println("Number is incorrect");
			}
		}
	}
	private RandomGenerator rgen = RandomGenerator.getInstance();
}