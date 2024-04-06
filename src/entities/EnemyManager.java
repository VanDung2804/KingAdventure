package entities;

//import java.awt.Graphics;
//import java.awt.image.BufferedImage;
//import java.util.ArrayList;
//
//import gamestates.Playing;
//import utilz.LoadSave;
//import static utilz.Constants.EnemyConstants.*;

public class EnemyManager {

//	private Playing playing;
//	private BufferedImage[][] pingArr;
//	private ArrayList<Ping> ping = new ArrayList<>();
//
//	public EnemyManager(Playing playing) {
//		this.playing = playing;
//		loadEnemyImgs();
////		addEnemies();
//	}
//
////	private void addEnemies() {
////		ping= LoadSave.GetPing();
////		System.out.println("size of ping: " + ping.size());
////	}
//
//	public void update() {
//		for (Ping c : ping)
//			c.update();
//	}
//
//	public void draw(Graphics g, int xLvlOffset) {
//		drawCrabs(g, xLvlOffset);
//	}
//
//	private void drawCrabs(Graphics g, int xLvlOffset) {
//		for (Ping c : ping)
//			g.drawImage(pingArr[c.getEnemyState()][c.getAniIndex()], (int) c.getHitbox().x - xLvlOffset, (int) c.getHitbox().y, CRABBY_WIDTH, CRABBY_HEIGHT, null);
//
//	}
//
//	private void loadEnemyImgs() {
//		pingArr = new BufferedImage[5][9];
//		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.CRABBY_SPRITE);
//		for (int j = 0; j < pingArr.length; j++)
//			for (int i = 0; i < pingArr[j].length; i++)
//				pingArr[j][i] = temp.getSubimage(i * CRABBY_WIDTH_DEFAULT, j * CRABBY_HEIGHT_DEFAULT, CRABBY_WIDTH_DEFAULT, CRABBY_HEIGHT_DEFAULT);
//	}
}
