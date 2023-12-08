/*
kulakevych Stas
 */

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.program.GraphicsProgram;

import java.awt.Color;
import java.awt.event.MouseEvent;

public class Main extends GraphicsProgram {
	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;

	static GField field;
	private int count = 0;
	public boolean newGame = true;
	RestartButton restartButton;

	public static boolean playForX = false;

	public void run() {
		field = new GField(this);
		this.setSize(WIDTH, HEIGHT);
		addMouseListeners();

		GLabel label = new GLabel("Player VS Player");
		label.setFont("SansSherif-" + 30);
		label.setLocation(WIDTH / 2 - 100, HEIGHT / 2);
		add(label);
		GLabel label2 = new GLabel("Player VS AI");
		label2.setFont("SansSherif-" + 30);
		label2.setLocation(WIDTH / 2 - 100, HEIGHT / 2 - 100);
		add(label2);

	}

	@Override
	public void mouseClicked(MouseEvent mouseEvent) {
		if (count == 0) {
			if(getElementAt(mouseEvent.getX(),mouseEvent.getY()) instanceof GLabel){
			GLabel label = (GLabel) getElementAt(mouseEvent.getX(),
					mouseEvent.getY());
			if (label.getLabel().matches("Player VS Player") && newGame == true) {
				removeAll();
				newGame = false;
				setUp();
			}

			else if (label.getLabel().matches("Player VS AI")
					&& newGame == true) {
				removeAll();
				GLabel label3 = new GLabel("Play for x");
				label3.setFont("SansSherif-" + 30);
				label3.setLocation(WIDTH / 2 - 100, HEIGHT / 2);
				add(label3);
				GLabel label2 = new GLabel("Play for o");
				label2.setFont("SansSherif-" + 30);
				label2.setLocation(WIDTH / 2 - 100, HEIGHT / 2 - 100);
				add(label2);
				GKletka.playVSbot = true;
				newGame = false;
				// setUp();
			}
			count++;
			return;
			}
		}
		if (count == 1 && GKletka.playVSbot == true) {
			GLabel label = (GLabel) getElementAt(mouseEvent.getX(),
					mouseEvent.getY());
			if (label.getLabel().matches("Play for x")) {
				removeAll();
				newGame = false;
				playForX = true;
				setUp();
			}
			if (label.getLabel().matches("Play for o")) {
				removeAll();
				newGame = false;
				setUp();
				field.AITurn();
				
			}
			count++;
		}
		if (GKletka.isWon == true) {
//			if(getElementAt(mouseEvent.getX(), mouseEvent.getY()) instanceof RestartButton){
				RestartButton label = (RestartButton) getElementAt(mouseEvent.getX(), mouseEvent.getY());
			if (label.getColor() == Color.GRAY) {
				clearCanvas();
				newGame = true;
				GKletka.isWon = false;
				count = 0;
				GKletka.playVSbot = false;
				GKletka.counter = 0;
				GKletka.oWon=false;
				GKletka.xWon=false;
				GKletka.AiTurn=false;
				playForX=false;
				add(ACMMethods.writeText("Player VS AI", this.getWidth() / 2,
						(int) (getHeight() / 2) - 100, 30));
				add(ACMMethods.writeText("Player VS Player",
						this.getWidth() / 2, (int) (getHeight() / 2), 30));
			}
			System.out.println("won");
			return;
		}
		
		field.check();
	}

	public static void field() {
		field.check();
		if (GKletka.isWon == false && GKletka.AiTurn) {
			field.AITurn();
		}
	}

	private void clearCanvas() {
		while (getElementCount() > 0) {
			GObject obj = getElement(0);
			remove(obj);
		}
	}

	public void newGame() {
		results();
		setUp();
	}

	public void results() {

		RestartButton restartButton = new RestartButton(100, 470, 300, 100, 32);
		add(restartButton);
	}

	public void setUp() {

		field.init();
	}

}