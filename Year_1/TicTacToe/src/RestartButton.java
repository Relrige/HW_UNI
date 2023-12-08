import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GRect;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RestartButton extends GCompound implements MouseListener{
    private static final String RESTART_TEXT = "Restart";
    private static final Color backgroundColor = Color.GRAY;
    private static final String fontFamily = "Calibri";
    private static final int fontStyle = Font.PLAIN;
    private GRect box;
    private GLabel label;

    /**
     * Default restart button
     * @param x top left corner x
     * @param y top left corner y
     * @param width button width
     * @param height button height
     * @param fontSize font size of a text
     */
    public RestartButton(int x, int y, int width, int height, int fontSize){
        setLocation(x, y);
        box = new GRect(0, 0, width, height);
        box.setFillColor(backgroundColor);
        box.setFilled(true);
        add(box);
        label = new GLabel(RESTART_TEXT);
        label.setFont(new Font(fontFamily, fontStyle, fontSize));
        label.setLocation((width - label.getWidth())/2, (height + label.getHeight())/2 - label.getDescent());
        add(this.label);
    }

    public Color getColor()
    {
    	return Color.GRAY;
    }
	@Override
	public void mouseClicked(MouseEvent arg0) {
		 //s
		
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

