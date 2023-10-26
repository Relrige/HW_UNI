package ua.com.glybovets.java1_1.lecture8;

import acm.program.*;
import acm.util.*;

public class SimpleRandom extends ConsoleProgram{
	
	public void run(){
		int ri = rgen.nextInt(1,6);
		println ("Випадкове число "+ri);
	}
	
	/*Private instance variable*/
	private RandomGenerator rgen = RandomGenerator.getInstance();

}
