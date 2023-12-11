/*
 kulakevych Stas
 */

import acm.program.*;

public class Enum extends ConsoleProgram {

	public void run() {
		this.setSize(500, 500);
		this.setFont("SeinsSherif-25");
		while (true) {
			int dayNumber = readInt("Enter numb for weekday");
			if (dayNumber >= 1 && dayNumber <= 7) {
				
				Weekday weekday = Weekday.values()[dayNumber - 1];				
				println("���� �����: " + weekday.getDisplayName());
				
			} else {
				println("������� ����������� ����� ��� �����. ���� �����, ������ ����� �� 1 �� 7.");
			}
			println("To end enter 0");
			double i = readDouble();
			if (i == 0) {
				println("THE END");
				return;
			}
		}
		
	}

	public enum Weekday {
		MONDAY("��������"), TUESDAY("³������"), WEDNESDAY("������"),
		THURSDAY("������"), FRIDAY("�'������"), SATURDAY("������"),
		SUNDAY("�����");

		private final String displayName;

		Weekday(String displayName) {
			this.displayName = displayName;
		}
		public String getNext() {     
			return (this.ordinal()+1 < (Weekday.values().length))? Weekday.values()[this.ordinal() + 1].getDisplayName(): null;   
		} 

		public String getDisplayName() {
			return displayName;
		}
	}
}
