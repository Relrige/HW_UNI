
import acm.program.*;

public class UseStudent extends ConsoleProgram{

	public void run(){
		setSize(500,500);
		setFont("Times New Roman-28");
		
		Student petro = new Student();
		Student ivan = new Student("Ivan","Petrovuch");
		Student vika = new Student("Viktoria","Vasulina",12,70,1,"FI");
		StudentKMA student3 = new StudentKMA();
		StudentKMA nastya = new StudentKMA("Nastya","Kalina",20,78,3,"FI");
		
		
		println(petro.toString());
		println(ivan.toString());
		println(vika.toString());
		vika.changeCourse();
		ivan.updateAverageMark(80);
		println(ivan.toString());
		println(vika.toString());
		println(student3.toString());
		
		println(nastya.toString());
		nastya.updateOppKnowledge(100);
		println(nastya.toString());
	}

	
}
