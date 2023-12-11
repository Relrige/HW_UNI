/*
 kulakevych Stas
 */

import acm.program.*;

public class Enum2 extends ConsoleProgram {
	public boolean error=false;
	public void run() {
		this.setSize(500, 500);
		this.setFont("SeinsSherif-25");
		while (true) {
			error=false;
			String dayNumber = readLine("Enter numb for weekday");
			
			for (Weekday color : Weekday.values()) {
				if (color.toString().equals(dayNumber)) {
					error=false;
					break;
				} 
				else
					error=true;
			}
			System.out.println(error);
			if(!error)
				println("Наступний день тижня: "
					+ Weekday.valueOf(dayNumber).getNext());

			println("To end enter 0");
			double i = readDouble();
			if (i == 0) {
				println("THE END");
				return;
			}
		}

	}

	public enum Weekday {
		MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

		public Weekday getNext() {
			if (this.ordinal() + 1 >= 7)
				return Weekday.values()[0];
			else
				return (this.ordinal() + 1 < (Weekday.values().length)) ? Weekday
						.values()[this.ordinal() + 1] : null;
		}

	}
}
