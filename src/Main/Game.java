package Main;

import java.awt.Graphics;

import javax.swing.JPanel;

import Level.LevelManger;

public class Game implements Runnable {

	private GameWindow gameWindow;
	private GamePanel gamepanel;
	private LevelManger levelmanger;

	public final static int TILES_DEFAULT_SIZE = 32;
	public final static float SCALE = 2f;//1.5
	public final static int TILES_IN_WIDTH = 26;
	public final static int TILES_IN_HEIGHT = 14;
	public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
	public final static int WIDTH = TILES_SIZE * TILES_IN_WIDTH;
	public final static int HEIGHT = TILES_IN_HEIGHT * TILES_SIZE;
	

	public Game() {
		initClasses();
		gamepanel = new GamePanel(this);
		gameWindow = new GameWindow(gamepanel);

	}

	private void initClasses() {
		levelmanger = new LevelManger(this);
	}

//@Override
//public void run() {
//	// TODO Auto-generated method stub
//	
//}
	public void update() {
		levelmanger.update();
	}

	public void render(Graphics g) {
		//levelmanger.draw(g);
		levelmanger.draw1(g);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
