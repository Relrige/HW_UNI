import java.awt.Color;
//kulakevych Stas
import acm.graphics.*;
import acm.program.*;

public class Sun extends GraphicsProgram{

	final int numberOfRays=301;

	public void run() {
		this.setSize(400, 400);
        int radius=50;
        int centerX=200;
        int centerY=200;
        
        //draw sun
        GOval sun = new GOval(centerX-radius,centerY-radius,2*radius,2*radius);
        sun.setFilled(true);
        sun.setColor(Color.YELLOW);
        add(sun);
        
        double angelBetweenRays= 365.0/numberOfRays;
        for(int i =0;i<numberOfRays;i++)
        {
        	double angle = Math.toRadians(angelBetweenRays*i);
        	int x1=centerX;
        	int y1=centerY;
        	int x2=(int)(centerX+2*radius*Math.cos(angle));
        	int y2=(int)(centerX+2*radius*Math.sin(angle));
        	GLine ray = new GLine(x1,y1,x2,y2);
        	ray.setColor(Color.GREEN);
        	add(ray);
        }
    }

}
