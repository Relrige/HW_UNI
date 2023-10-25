/*
 * Kulakevych Stas
 */

import java.awt.Color;

import acm.program.*;
import acm.graphics.*;

public class Robot extends GraphicsProgram {

	private static final int HEIGHT = 500;
	private static final int WIGHT = 500;
	private static final double HEAD_WIDTH = 0.6*WIGHT;
	private static final double HEAD_HEIGHT = 0.6*HEIGHT;
	private static final double EYE_RADIUS = 0.3*HEAD_WIDTH;
	private static final double MOUTH_WIDTH = 0.3*HEAD_WIDTH;
	private static final double MOUTH_HEIGHT = 0.1*HEAD_HEIGHT;
	

	public void run() {
		this.setSize(WIGHT, HEIGHT);
		double vidstupWidth = (WIGHT - HEAD_WIDTH) / 2;
		double vidstupHEIGHT = (HEIGHT - HEAD_HEIGHT) / 2;
		drawHead(vidstupWidth, vidstupHEIGHT);
		drawEyes(vidstupWidth, vidstupHEIGHT);
		drawMouth(vidstupWidth, vidstupHEIGHT);
	}

	public void drawHead(double x, double y) {
		GRect rect = new GRect(x, y,HEAD_WIDTH , HEAD_HEIGHT);
		rect.setFilled(true);
		rect.setColor(Color.GRAY);
		add(rect);
	}
	public void drawMouth(double x, double y) {
		GRect rect = new GRect(WIGHT/2-MOUTH_WIDTH/2,HEIGHT/2+MOUTH_HEIGHT,MOUTH_WIDTH , MOUTH_HEIGHT);
		rect.setFilled(true);
		rect.setColor(Color.PINK);
		add(rect);
	}
	public void drawEyes(double x, double y) {
		GOval oval = new GOval(WIGHT/2-HEAD_WIDTH/6-EYE_RADIUS,HEIGHT/2-EYE_RADIUS,EYE_RADIUS,EYE_RADIUS);
	      oval.setFilled(true);
	      oval.setColor(Color.GREEN);
	      add(oval);
	      GOval oval2 = new GOval(WIGHT/2+HEAD_WIDTH/6,(HEIGHT/2)-EYE_RADIUS,EYE_RADIUS,EYE_RADIUS);
	      oval2.setFilled(true);
	      oval2.setColor(Color.GREEN);
	      add(oval2);
	}

}
