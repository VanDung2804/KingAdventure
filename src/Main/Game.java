package Main;

import java.awt.Graphics;

import entities.Player;
import levels.LevelManager;

public class Game implements Runnable{
	private GameWinDow gameWindow;
	private GamePanel gamePanel;
	private Thread gameThread;
	private final int FPS_SET = 120;
	private final int UPS_SET = 200;
	private LevelManager levelManager;
	
	public static final int TILES_DEFAULT_SIZE = 32;
	public static final float SCALE = 1.5f;
	public static final int TILES_IN_WIDTH = 26;
	public static final int TILES_IN_HEIGHT = 14;
	public static final int TILES_SIZE = (int) (TILES_DEFAULT_SIZE*SCALE);
	public static final int GAME_WIDTH = TILES_SIZE*TILES_IN_WIDTH;
	public static final int  GAME_HEIGHT = TILES_SIZE*TILES_IN_HEIGHT;
	
	
	private Player player;
	public Game() {
		initClasses();
		gamePanel = new GamePanel(this);//tạo đối tượng để thực hiê
		gameWindow = new GameWinDow(gamePanel);//tao doi tuong để gọi cửa sổ
		gamePanel.requestFocus();//thuc hien ve lai 
		startGameLoop();//vong lap game

	}
	
	private void initClasses() {
		levelManager = new LevelManager(this);
		player = new Player(200, 200, (int) (78 * SCALE), (int) (58 * SCALE));
		player.loadlvlData(levelManager.getCurrentLevel().getLevelData());
	}

	private void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	public void update() {
		player.update();
		levelManager.update();
	}
	
	public void render(Graphics g) {
		levelManager.draw(g);
		player.render(g);
	}

	@Override
	public void run() {
		double timePerFrame = 1000000000.0 / FPS_SET;//thoi gian tren moi khung hinh
		double timePerUpdate = 1000000000.0 / UPS_SET;//thoi gian cap nhat
		///thhoi gian truoc do = thoi gian hien tai
		long previousTime = System.nanoTime();
		int frames = 0;
		int updates = 0;
		long lastCheck = System.currentTimeMillis();
		double deltaU = 0;
		double deltaF = 0;
		while (true) {
			long currentTime = System.nanoTime();
			deltaU += (currentTime - previousTime) / timePerUpdate;
			deltaF += (currentTime - previousTime) / timePerFrame;
			previousTime = currentTime;
			if (deltaU >= 1) {//kiem tra de cap nhat
				update();//goi ham de cap nhat tro choi
				updates++;
				deltaU--;
			}
			if (deltaF >= 1) {
				gamePanel.repaint();
				frames++;
				deltaF--;
			}
			if (System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS: " + frames + " | UPS: " + updates);
				frames = 0;
				updates = 0;
			}
		}

	}
	
	public void windowFocus() {
		player.resetDirBooleans();
	}
	
	public Player getPlayer() {
		return player;
	}
}
