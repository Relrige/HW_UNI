/*Kulakevych Stas*/

import acm.program.*;
import acm.util.*;

public class RollDice extends ConsoleProgram {

	private static final int NUM_SIDES = 6;

	public void run() {
		this.setSize(500, 500);
		this.setFont("SeinsSherif-30");
		while (true) {
			println("WELCOME TO ROLLDICE GAME");
			println("");
			int numDice = readInt("Кількість гральних кубиків для гравця: ");
			if (numDice < 1)
				continue;
			int numSide = readInt("Кількість граней кубиків: ");
			if (numSide <= 1)
				continue;

			println("Choose program");
			println("1 - Number of steps");
			println("2 - Continue play");
			println("0 - end");
			int choose = readInt();
			switch (choose) {
			case 1:
				println("Enter number of steps");
				int numbToCountTo = readInt();
				int now = 0;
				int now2 = 0;
				int total = 0;
				int total2 = 0;
				for (int i = 0; i < numbToCountTo; i++) {
					now = rollDice(numDice, numSide);
					now2 = rollDice(numDice, numSide);
					println("-------------THrow "+i+"---------");
					println("First player current points - " + now+". Second player- "+now2);
					//println("Second player current points - " + now2);
					total += now;
					total2 += now2;
					println("First player total points - " + total+". Second player- "+total2);
					//println("Second player total points - " + total2);
					if (total > total2)
						println("----First player won!");
					else if(total < total2)
						println("----Second player won!");
					else
						println("----Draw!");
				}

				break;
			case 2:
				println("You choosed continue play");
				now = 0;
				now2 = 0;
				total = 0;
				total2 = 0;
				while (true) {
					now = rollDice(numDice, numSide);
					now2 = rollDice(numDice, numSide);
					println("First player current points - " + now);
					println("Second player current points - " + now2);
					total += now;
					total2 += now2;
					println("First player total points - " + total);
					println("Second player total points - " + total2);
					println(" ");
					println("Press 0 to end, any other to continue");
					int end = readInt();
					if(end!=0)
						continue;
					else
						break;
				}
				if (total > total2)
					println("First player won");
				else
					println("Second player won");
				break;
			}

		}

	}

	/* Returns the total of rolling numDice dice */
	private int rollDice(int numDice, int numSide) {
		int total = 0;
		for (int i = 0; i < numDice; i++) {
			total += rgen.nextInt(1, numSide);
		}
		return total;
	}

	private RandomGenerator rgen = RandomGenerator.getInstance();

}