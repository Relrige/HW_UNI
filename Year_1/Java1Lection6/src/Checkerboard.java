/*
 * малюємо шахову дошку
 */


import acm.graphics.*;
import acm.program.*;

public class Checkerboard extends GraphicsProgram{
	
	/* Кількість стрічок*/
	private static final int NROWS = 8;
	
	/* Кількість стовбчиків*/
	private static final int NCOLUMNS = 8;
	
	public void run(){
		int sqSize = getHeight()/NROWS;
		for (int i = 0; i<NROWS; i++){
			for (int j = 0; j<NCOLUMNS; j++){
				int x = j*sqSize;
				int y = i*sqSize;
				GRect sq = new GRect(x,y,sqSize,sqSize);
				sq.setFilled(((i+j)%2)!=0);
				add(sq);
			}
		}
	}
	
}
