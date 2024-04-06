package GameStates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import Main.Game;
import Ui.PauseOverlay;											//PauseMenu
import Utilz.LoadSave;
import Ui.PauseOverlay;
import Entities.Enemy;
import Entities.EnemyManager;
import Entities.Player;
import Levels.LevelManager;


public class Playing extends State implements StateMethods{
	
	private Player player;
	private EnemyManager enemyManager;
	private LevelManager levelManager;
	private PauseOverlay pauseOverlay;
	private boolean paused = false;
	
	public Playing(Game game) {
		super(game);
		initClasses();

	}
	
	public void initClasses() {
		levelManager = new LevelManager(game);
		player = new Player(150, 270, 78, 57);
		player.loadLvlData(levelManager.getCurrentLevel().getLevelData());
		enemyManager = new EnemyManager(this);
		pauseOverlay = new PauseOverlay(this);						//PauseMenu
	}
	
	@Override
	public void update() {
		if(!paused) {
			player.update();
			enemyManager.update(levelManager.getCurrentLevel().getLevelData(), player);
		}
		else 
			pauseOverlay.update();
		
		
	}

	@Override
	public void draw(Graphics g) {
	
			enemyManager.draw(g);
			player.render(g);
			if(paused) {
				g.setColor(new Color(0,0,0,100));
			    g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
				pauseOverlay.draw(g);	
			
			}
			
		
			
	}

	@Override
	public void mouseClicked(MouseEvent e) {
//		System.out.println("Clicked");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (paused)
			pauseOverlay.mousePressed(e);
		else ;
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (paused)
			pauseOverlay.mouseReleased(e);
		else ;
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (paused)
			pauseOverlay.mouseMoved(e);
		else ;
		
	}

	public void unpauseGame() {
		paused = false;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_ESCAPE:
			paused = !paused;
			break;
		case KeyEvent.VK_A:
			player.setLeft(true);;
			break;
		case KeyEvent.VK_D:
			player.setRight(true);
			break;
//		case KeyEvent.VK_W:
//			player.setUp(true);
//			break;
//		case KeyEvent.VK_S:
//			player.setDown(true);
//			break;
		case KeyEvent.VK_J:
			player.setAttacking(true);
			break;
		case KeyEvent.VK_SPACE:
			player.setJump(true);

			break;

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			player.setLeft(false);
			break;
		case KeyEvent.VK_D:
			player.setRight(false);
			break;
//		case KeyEvent.VK_W:
//			player.setUp(false);
//			break;
//		case KeyEvent.VK_S:
//			player.setDown(false);
//			break;
//		case KeyEvent.VK_J:
//			player.setAttacking(false);
//			break;
		case KeyEvent.VK_SPACE:
			player.setJump(false);

			break;
		}
		
	}
	
	public Player getPlayer() {
		return player;
	}
	
}
