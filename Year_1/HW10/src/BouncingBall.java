

import acm.program.*;
import acm.graphics.*;

	public class BouncingBall extends GraphicsProgram {
		
		/** Розмір (діаметр) м'ячика */
		private static final int DIAM_BALL = 30;
		
		/** Іеличина Y швидкість збільшується враховуючи гравітацію*/
		private static final double GRAVITY = 3;
		
		/** Затримка анімації між кроками */
		private static final int DELAY = 50;
		
		/** Початкові значення X і Y розташування м'ячика */
		private static final double X_START = DIAM_BALL / 2;
		private static final double Y_START = 100;

		/** X швидкість */
		private static final double X_VEL = 5;

		/** Y Швидкість зменшується після удару */
		private static final double BOUNCE_REDUCE = 0.9;

		/** Початкові значення X і Y швидкостей */
		private double xVel = X_VEL;
		private double yVel = 0.0;

		/* private instance variable */
		private GOval ball;
		
		public void run() {
			setup();
			//Програма закінчується коли м'ячик викочується за праву межу екрану
			while (ball.getX() < getWidth()) {
				moveBall();
				checkForCollision();
				pause(DELAY);
			}
		}

		/** Створюємо і розміщуємо м'ячик */
		private void setup() {
			ball = new GOval(X_START, Y_START, DIAM_BALL, DIAM_BALL);
			ball.setFilled(true);
			add(ball);
		}

		/** Оновлення і переміщення м'ячика */
		private void moveBall() {
			//Збільшення швидкості з урахуванням гравітації
			yVel += GRAVITY;
			ball.move(xVel,yVel);
		}

		/** Якщо м'ячик наштовхується на пол, оновлення швидкостей і 
		 * місця розташування*/
		private void checkForCollision() {
			// визначаємо чи не перетнув м'ячик межу полу
			if (ball.getY() > getHeight() - DIAM_BALL) {
				// змінюємо швидкість Y щоб заставити м'ячик рухатися в гору
				yVel = -yVel * BOUNCE_REDUCE;
			
				double diff = ball.getY() - (getHeight() - DIAM_BALL);
				ball.move(0, -2 * diff);
			}
		}
}
