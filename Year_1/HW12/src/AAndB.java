/*
 kulakevych Stas
 */

import acm.program.*;

public class AAndB extends ConsoleProgram {

	public void run() {
		this.setSize(500, 500);
		this.setFont("SeinsSherif-25");
		while (true) {
			int num1 = readInt("¬вед≥ть перше число: ");
			calc(num1);
		
		}

	}

	private void calc(int num) {
		if(num<=3)
			return;
		int a=1;
		int b=1;
		while(true)
		{
			int sum=(5*a-3*b);
			if(sum==num)
			{
				println("A-"+a+" B- "+b);
				break;
			}
			else if(sum<num){
				a++;
			}
			else
				b++;
		}
	}

}
