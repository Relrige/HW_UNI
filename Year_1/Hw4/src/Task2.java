/*
Kulakevych Stas
 */

import stanford.karel.*;

public class Task2 extends SuperKarel {
	public void run() {
		put1Beepers();
		while (frontIsClear()) {
			moveBeeperCloser();
		}
		detectBeeper2();
		if(facingWest())
		{
			turnAround();
		}
		
	}
    // Method to put one beeper in the starting position
	private void put1Beepers() {
		if (frontIsClear()) {
			putBeeper();
			while (frontIsClear()) {
				move();
			}
			turnAround();
			putBeeper();
		}
		else
			putBeeper();
	}
	// method to move beeper closer to center
	private void moveBeeperCloser() {
		if (frontIsClear()) {
			move();
		}
		while (noBeepersPresent() && frontIsClear()) {
			move();
		}
		if (beepersPresent()) {
			pickBeeper();
			turnAround();
			move();
			if (noBeepersPresent()) {
				putBeeper();
			}
		}
	}
	//method to stay on beeper int the center
	private void detectBeeper2() {
		turnAround();
		while (noBeepersPresent()) {
			if (frontIsClear()) {
				move();
			}
		}
	}

}
