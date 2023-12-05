import stanford.karel.*;

public class PR4First extends SuperKarel {

	public void run() {
		if (frontIsBlocked() && leftIsClear()) {
			turnLeft();
			while (frontIsClear() && facingNorth()) {
				goThrough();
				goBack();
				findExit();
			}
			turnRight();
		}
		while (frontIsClear() && facingEast()) {
			goThrough();
			goBack();
			findExit();
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
		while (leftIsBlocked() && frontIsClear()) {
			move();
		}
		if (leftIsClear()) {
			turnLeft();
			move();
			turnLeft();
			while (frontIsClear()) {
				move();
			}
			turnAround();
		}
	}
}
