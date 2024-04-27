/*
 * Програма призначена для демонстрації цикла While
 * Програма знаходить суму чисел, що вводить користувач
 * в цій версії уникається повторення команд
 */


import acm.program.*;

public class Add2 extends ConsoleProgram{

	private static final int SENTINEL = 0; //число яке означає кінець введення
	
	public void run(){
		
		int total = 0;
		
		while (true){
			int val = readInt("Введіть число:");
			if (val == SENTINEL) break;
			total += val;
			
		}
		println("Сума = " + total);
	}
	
}
