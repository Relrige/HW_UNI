import java.awt.Color;
//kulakevych Stas
import acm.graphics.*;
import acm.program.*;

public class House extends GraphicsProgram{

	public void run(){
		this.setSize(700, 700);
		GRect rect1 = new GRect(10,10,50,50);
		add(rect1);
		
		GRect grass = new GRect(0,400,700,400);
		grass.setFilled(true);
		grass.setColor(Color.GREEN);
		add(grass);
		
		GRect Sky = new GRect(0,0,700,400);
		Sky.setFilled(true);
		Sky.setColor(Color.CYAN);
		add(Sky);
		
		GRect wall = new GRect(150,430,400,300);
		wall.setFilled(true);
		wall.setColor(Color.RED);
		add(wall);
		
		
		GPolygon roof = new GPolygon();
		roof.addVertex(0,0);
		roof.addVertex(100,-100);
		roof.addVertex(200,0);
		roof.setColor(Color.BLACK);
		roof.setFilled(true);
		roof.scale(2.0,2.0);
		add(roof,150,430);
		
		
		GOval cloud1 = new GOval(100,45,170,100);
		cloud1.setFilled(true);
		cloud1.setFillColor(Color.WHITE);
		cloud1.setColor(Color.WHITE);
		add(cloud1);
		GOval cloud2 = new GOval(130,65,170,100);
		cloud2.setFilled(true);
		cloud2.setColor(Color.WHITE);
		
		add(cloud2);
		GOval cloud3 = new GOval(70,65,170,100);
		cloud3.setFilled(true);
		cloud3.setColor(Color.WHITE);
		cloud3.setFillColor(Color.WHITE);
		add(cloud3);
		
		
		GRect dumohid = new GRect(430,330,40,60);
		dumohid.setFilled(true);
		dumohid.setColor(Color.GRAY);
		add(dumohid);
		
		GRect door = new GRect(200,560,100,160);
		door.setFilled(true);
		door.setColor(Color.PINK);
		add(door);
		
		GRect window = new GRect(380,500,90,90);
		window.setFilled(true);
		window.setColor(Color.BLACK);
		window.setFillColor(Color.WHITE);
		add(window);
		
		
		GRect trunk = new GRect(30,150);
		trunk.setColor(Color.darkGray);
		trunk.setFilled(true);
		this.add(trunk,50,500);
		
		GOval leaves = new GOval(120,100);
		leaves.setColor(Color.ORANGE);
		leaves.setFilled(true);
		this.add(leaves,0,450);
		
		GLabel label = new GLabel("It's my new house", 570, 675);
		label.setFont("sansserif-16");
		label.setColor(Color.RED);
		add(label);
		
		GRect window1 = new GRect(9,90);
		window1.setColor(Color.BLACK);
		window1.setFilled(true);
		this.add(window1,421,500);
		GRect window2 = new GRect(90,9);
		window2.setColor(Color.BLACK);
		window2.setFilled(true);
		this.add(window2,380,540);
		
		
		GRect parkan1 = new GRect(13,90);
		parkan1.setColor(Color.MAGENTA);
		parkan1.setFilled(true);
		this.add(parkan1,580,450);
		
		GRect parkan2 = new GRect(13,90);
		parkan2.setColor(Color.MAGENTA);
		parkan2.setFilled(true);
		this.add(parkan2,660,450);
		GRect parkan3 = new GRect(125,13);
		parkan3.setColor(Color.MAGENTA);
		parkan3.setFilled(true);
		this.add(parkan3,560,470);
		GRect parkan4 = new GRect(125,13);
		parkan4.setColor(Color.MAGENTA);
		parkan4.setFilled(true);
		this.add(parkan4,560,510);
		
		
	}
}
