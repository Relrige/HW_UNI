/*
kulakevych Stas
 */

import acm.graphics.GCompound;

import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GObject;
import acm.graphics.GOval;

import acm.graphics.GRect;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GKletka extends GCompound implements MouseListener {

	public int state = 0;
	private GRect box;

	public static boolean isWon = false;
	public static int counter = 0;
	boolean full = false;
	private int x;
	private int y;
	private int width;
	private int height;
	public static boolean xWon = false;
	public static boolean oWon = false;
	public static boolean playVSbot = false;
	public static boolean AiTurn = false;

	public GKletka(int x, int y, int width, int height) {
		addMouseListener(this);
		this.x = x;
		this.width=width;
		this.height=height;
		this.y = y;
		box = new GRect(x, y, width, height);
		add(box);
		state = 0;
	}

	@Override
	public void mouseClicked(MouseEvent mouseEvent) {
		
		if (!playVSbot) {
			if (full == false && isWon == false) {
				if (counter % 2 == 0) {
					drawX(getElementAt(mouseEvent.getX(), mouseEvent.getY()));
				} else {
					draw0(getElementAt(mouseEvent.getX(), mouseEvent.getY()));
				}
				full = true;
				counter++;
			}
		} else {
			if (!AiTurn) {
				
				if(Main.playForX==true)
				{
				if (full == false && isWon == false) {
					if (counter % 2 == 0) {
						drawX(getElementAt(mouseEvent.getX(), mouseEvent.getY()));
					} else {
						draw0(getElementAt(mouseEvent.getX(), mouseEvent.getY()));
					}
					full = true;
					counter++;
					AiTurn=true;
				}
				Main.field();
				}
				else{
					if (full == false && isWon == false) {
						if (counter % 2 == 0) {
							drawX(getElementAt(mouseEvent.getX(), mouseEvent.getY()));
						} else {
							draw0(getElementAt(mouseEvent.getX(), mouseEvent.getY()));
						}
						full = true;
						counter++;
						AiTurn=true;
					}
					
					Main.field();
					}
				}
					
			}
		}

	

	public void updateColor() {
		isWon = true;
		if (state == 1) {
			GLine line = new GLine(x, y, x + width, y
					+ height);
			line.setColor(Color.GREEN);
			GLine line2 = new GLine(x, y + width,
					x + height, y);
			line2.setColor(Color.GREEN);
			GRect rect = new GRect(x, y, width, height);
			rect.setColor(Color.GREEN);
			add(rect);
			// setLocation(this.getX(), this.getY());
			add(line);
			add(line2);
			state = 1;
			xWon=true;
		} else if (state == -1) {
			GOval oval = new GOval(x, y, width, height);
			// GRect rect = new GRect(x, y, this.getWidth(), this.getHeight());
			// rect.setColor(Color.GREEN);
			// add(rect);
			oval.setColor(Color.GREEN);
			add(oval);
			state = -1;
			oWon=true;
		}

	}

	public void drawX(GObject object) {
		GLine line = new GLine(x, y, x + object.getWidth(), y
				+ object.getHeight());
		GLine line2 = new GLine(x, y + object.getWidth(),
				x + object.getWidth(), y);
		GRect rect = new GRect(x, y, object.getWidth(), object.getHeight());
		add(line);
		add(line2);
		add(rect);
		// setLocation(object.getX(), object.getY());
		this.state = 1;
		this.full = true;
	}

	public void draw0(GObject object) {
		GOval oval = new GOval(x, y, object.getWidth(), object.getHeight());
		// GRect rect = new GRect(object.getX(), object.getY(),
		// object.getWidth(), object.getHeight());
		// add(rect);

		add(oval);
		state = -1;
		this.full = true;

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public static void Clear() {
		// isWon=false;
	}


	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return state;
	}
}