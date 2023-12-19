//Kulakevych Stanislav exam keral
import stanford.karel.SuperKarel;

public class Task1 extends SuperKarel {
	public void run() {
		turnLeft();
		findMidOfLabirint();

	}

	private void findMidOfLabirint(){
		while(frontIsClear()){
			moveToTheWall();
			if(rightIsClear())
				turnRight();
		}
		putAllBeepers();
	}
	private void putAllBeepers()
	{
		while(beepersInBag())
		{
			putBeeper();
		}
	}
	private void moveToTheWall()
	{
		tryPickBeeper();
		while(frontIsClear())
		{
			move();
			tryPickBeeper();
		}
	}
	private void tryPickBeeper()
	{
		while(beepersPresent())
		{
			pickBeeper();
		}
	}
}

