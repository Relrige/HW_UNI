import stanford.karel.*;

public class PR4Second extends SuperKarel {

	public void run() {
		if(beepersPresent())
		{
			pickBeeper();
		}
		if (leftIsClear()) {
			turnLeft();
			while (frontIsClear() && facingNorth()) {
				goThrough();
				goBack();
				findExit();
			}
		} else {
			while (frontIsClear() && facingEast()) {
				goThrough();
				goBack();
				findExit();
			}
		}

		if (facingNorth()) {
			turnRight();
		}
	}

	private void goThrough() {
		
		while (frontIsClear()) {
			if (beepersPresent()) {
				pickBeeper();
			}
			move();
		}
		if (beepersPresent()) {
			pickBeeper();
		}
	}

	private void goBack() {
		turnAround();
		while (frontIsClear()) {
			move();
		}
		turnAround();
	}

	private void findExit() {
		while (rightIsBlocked() && frontIsClear()) {
			move();
		}
		if (rightIsClear()) {
			turnRight();
			move();
			turnRight();
			while (frontIsClear()) {
				move();
			}
			turnAround();
		}
	}
}
