package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gamestates.Playing;
import utilz.LoadSave;
import static utilz.Constants.EnemyConstants.*;

public class EnemyManager {
	private Playing playing;
	private BufferedImage[][]pigArr;
	private ArrayList<Pig> pigs = new ArrayList<>();

	public EnemyManager(Playing playing) {
		this.playing = playing;
		loadEnemyImgs();
		addEnemies();
	}

	private void addEnemies() {
		pigs = LoadSave.GetPig();
		System.out.println("size of crabs: " + pigs.size());
	}

	public void update() {
		for (Pig c : pigs)
			c.update();
	}

	public void draw(Graphics g, int xLvlOffset) {
		drawCrabs(g, xLvlOffset);
	}

	private void drawCrabs(Graphics g, int xLvlOffset) {
		for (Pig c : pigs)
			g.drawImage(pigArr[c.getEnemyState()][c.getAniIndex()], (int) c.getHitbox().x - xLvlOffset, (int) c.getHitbox().y, PIG_WIDTH, PIG_HEIGHT, null);

	}

	private void loadEnemyImgs() {
		pigArr = new BufferedImage[8][11];
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.PIG_SPRITE);
		for (int j = 0; j < pigArr.length; j++)
			for (int i = 0; i < pigArr[j].length; i++)
				pigArr[j][i] = temp.getSubimage(i * 34, j * 28, 34,28);
	}


}