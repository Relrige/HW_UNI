/*
kulakevych Stas
 */

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import acm.graphics.GCompound;
import acm.graphics.GLabel;

import acm.graphics.GRect;
import acm.graphics.GRectangle;

public class GField extends GCompound implements MouseListener {

	GKletka TopLeft;
	GKletka TopMid;
	GKletka TopRight;
	GKletka CenterLeft;
	GKletka CenterMid;
	GKletka CenterRight;
	GKletka BotLeft;
	GKletka BotMid;
	GKletka BotRight;
	Main canvas;

	int widht = 150;

	static int isCrossWon = 0;

	public GField(Main canvas) {
		this.canvas = canvas;
	}

	public void init() {
		addMouseListener(this);
		TopLeft = new GKletka(0, 0, widht, widht);
		TopMid = new GKletka(widht, 0, widht, widht);
		TopRight = new GKletka(widht * 2, 0 * 2, widht, widht);
		CenterLeft = new GKletka(0, widht, widht, widht);
		CenterMid = new GKletka(widht, widht, widht, widht);
		CenterRight = new GKletka(widht * 2, widht, widht, widht);
		BotLeft = new GKletka(0, widht * 2, widht, widht);
		BotMid = new GKletka(widht, widht * 2, widht, widht);
		BotRight = new GKletka(widht * 2, widht * 2, widht, widht);
		canvas.add(TopLeft);
		canvas.add(TopMid);
		canvas.add(TopRight);
		canvas.add(CenterLeft);
		canvas.add(CenterMid);
		canvas.add(CenterRight);
		canvas.add(BotLeft);
		canvas.add(BotMid);
		canvas.add(BotRight);
	}

	@Override
	public GRectangle getBounds() {
		// TODO Auto-generated method stub
		GLabel label = new GLabel("" + TopLeft.getState() + " " + BotMid.state);
		label.setFont("SansSherif-" + 30);
		label.setLocation(100, 100);
		canvas.add(label);
		return null;
	}
	
	public void AITurn(){
		GKletka.counter++;
		GKletka.AiTurn=false;
		while(true)
		{
			int move=(int) (Math.random()*9);
			if(move==1)
			{
				if(!TopLeft.full)
				{
					if(!canvas.playForX)
						TopLeft.drawX(TopLeft);
					else
						TopLeft.draw0(TopLeft);
					break;
				}
			}
			if(move==2)
			{
				if(!TopMid.full)
				{
					if(!canvas.playForX)
						TopMid.drawX(TopMid);
					else
						TopMid.draw0(TopMid);
					break;
				}
			}
			if(move==3)
			{
				if(!TopRight.full)
				{
					if(!canvas.playForX)
						TopRight.drawX(TopRight);
					else
						TopRight.draw0(TopRight);
					break;
				}
			}
			if(move==4)
			{
				if(!CenterLeft.full)
				{
					if(!canvas.playForX)
						CenterLeft.drawX(CenterLeft);
					else
						CenterLeft.draw0(CenterLeft);
					break;
				}
			}
			if(move==5)
			{
				if(!CenterMid.full)
				{
					if(!canvas.playForX)
						CenterMid.drawX(CenterMid);
					else
						CenterMid.draw0(CenterMid);
					break;
				}
			}
			if(move==6)
			{
				if(!CenterRight.full)
				{
					if(!canvas.playForX)
						CenterRight.drawX(CenterRight);
					else
						CenterRight.draw0(CenterRight);
					break;
				}
			}
			if(move==7)
			{
				if(!BotLeft.full)
				{
					if(!canvas.playForX)
						BotLeft.drawX(BotLeft);
					else
						BotLeft.draw0(BotLeft);
					break;
				}
			}
			if(move==8)
			{
				if(!BotMid.full)
				{
					if(!canvas.playForX)
						BotMid.drawX(BotMid);
					else
						BotMid.draw0(BotMid);
					break;
				}
			}
			if(move==9)
			{
				if(!BotRight.full)
				{
					if(!canvas.playForX)
						BotRight.drawX(BotRight);
					else
						BotRight.draw0(BotRight);
					break;
				}
			}
			
		}
	}
	public void check() {
		if (GKletka.counter < 4)
			return;
		if ((TopLeft.state + TopMid.state + TopRight.state) == -3
				|| (TopLeft.state + TopMid.state + TopRight.state) == 3) {
			TopLeft.updateColor();
			TopMid.updateColor();
			TopRight.updateColor();

		} else if ((CenterLeft.state + CenterMid.state + CenterRight.state) == -3
				|| (CenterLeft.state + CenterMid.state + CenterRight.state) == 3) {
			CenterLeft.updateColor();
			CenterMid.updateColor();
			CenterRight.updateColor();
		} else if ((BotLeft.state + BotMid.state + BotRight.state) == -3
				|| (BotLeft.state + BotMid.state + BotRight.state) == 3) {
			BotLeft.updateColor();
			BotMid.updateColor();
			BotRight.updateColor();
		} else if ((TopLeft.state + CenterLeft.state + BotLeft.state) == -3
				|| (TopLeft.state + CenterLeft.state + BotLeft.state) == 3) {
			TopLeft.updateColor();
			CenterLeft.updateColor();
			BotLeft.updateColor();
		} else if ((TopMid.state + CenterMid.state + BotMid.state) == -3
				|| (TopMid.state + CenterMid.state + BotMid.state) == 3) {
			TopMid.updateColor();
			CenterMid.updateColor();
			BotMid.updateColor();
		} else if ((TopRight.state + CenterRight.state + BotRight.state) == -3
				|| (TopRight.state + CenterRight.state + BotRight.state) == 3) {
			TopRight.updateColor();
			CenterRight.updateColor();
			BotRight.updateColor();
		}

		else if ((BotLeft.state + CenterMid.state + TopRight.state) == -3
				|| (BotLeft.state + CenterMid.state + TopRight.state) == 3) {
			BotLeft.updateColor();
			CenterMid.updateColor();
			TopRight.updateColor();
		} else if ((TopLeft.state + CenterMid.state + BotRight.state) == -3
				|| (TopLeft.state + CenterMid.state + BotRight.state) == 3) {
			TopLeft.updateColor();
			CenterMid.updateColor();
			BotRight.updateColor();
		}
		
		if (GKletka.isWon == true || GKletka.counter >=9) {
			if(GKletka.xWon==true)
			{
				GLabel label = new GLabel("X won");
				label.setFont("SansSherif-" + 30);
				label.setLocation(canvas.getWidth() / 2-100, canvas.getHeight() / 2-80);
				canvas.add(label);
			}
			else if(GKletka.oWon==true)
			{
				GLabel label = new GLabel("O won");
				label.setFont("SansSherif-" + 30);
				label.setLocation(canvas.getWidth() / 2-100, canvas.getHeight() / 2-80);
				canvas.add(label);
			}
			else{
				GLabel label = new GLabel("DRAW");
				label.setFont("SansSherif-" + 30);
				label.setLocation(canvas.getWidth() / 2-100, canvas.getHeight() / 2-80);
				canvas.add(label);
				GKletka.isWon=true;
			}
//			 else if (GKletka.counter % 2 == 1) {
//				GLabel label = new GLabel("X won");
//				label.setFont("SansSherif-" + 30);
//				label.setLocation(canvas.getWidth() / 2-100, canvas.getHeight() / 2-80);
//				canvas.add(label);
//			}
//				else if (GKletka.counter % 2 == 0) {
//				GLabel label = new GLabel("O won");
//				label.setFont("SansSherif-" + 30);
//				label.setLocation(canvas.getWidth() / 2-100, canvas.getHeight() / 2-80);
//				canvas.add(label);
//			}
			removeMouseListener(this);
			canvas.newGame();
		}
	}

	@Override
	public void paint(Graphics arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent mouseEvent) {

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

}