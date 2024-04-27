/*
 * Програма призначена для демонстрації цикла While
 * Програма знаходить суму чисел, що вводить користувач
 */


import java.awt.Color;
import java.math.*;
import acm.graphics.GOval;
import acm.program.*;

public class Add extends ConsoleProgram{

	
	
	public void run(){
		this.setSize(500,700);
		this.setFont("SeinsSherif-20");
		while (true) {
			println("Enter number to choose function");
		println("0) exit");
		println("1)  y=Max(a,b,c,d)");
		println("2)  y=x^4");
		println("3)  y=ax2+bx+c");
		int choose = readInt("Choose function: ");
		switch(choose){
			case(0):
				break;
			case(1):
			int a = readInt("Enter a: ");
			int b = readInt("Enter b: ");
			int c = readInt("Enter c: ");
			int d = readInt("Enter d: ");
			println("Max= "+Max(a,b,c,d));
				break;
			case(2):
				int x = readInt("Enter x: ");
				println("x^4= "+x4(x));
				break;
			case(3):
			a = readInt("Enter a: ");
			b = readInt("Enter b: ");
			c = readInt("Enter c: ");
			x = readInt("Enter x: ");
			println(Quadric(a,b,c,x));
				break;
		
		}
		
		int e = readInt ("Enter 9 to try again: ");
		if(e!=9)
		{
			break;
		}
		}
	}
	private int Max(int a, int b, int c, int d){
		int max=a;
		if(max<b)
			max=b;
		if(max<c)
			max=c;
		if(max<d)
			max=d;
		
		return max;
	}
	private double x4(int x){		
		return Math.pow(x, 4);
	}
	private double Quadric(int a,int b,int c,int x){		
		
		
		return a*Math.pow(x, 2)+b*x+c;
	}
}
