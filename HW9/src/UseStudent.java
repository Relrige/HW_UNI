
import acm.program.*;

public class UseStudent extends ConsoleProgram{

	public void run(){
		setSize(500,500);
		setFont("Times New Roman-28");
		
		Student petro = new Student();
		Student ivan = new Student("Ivan","Petrovuch");
		Student vika = new Student("Viktoria","Vasulina",12,70,1,"FI");
		
		println(petro.toString());
		println(ivan.toString());
		println(vika.toString());
		vika.changeCourse();
		ivan.updateAverageMark(80);
		println(ivan.toString());
		println(vika.toString());
	}

	
}
