package utilz;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import Main.Game;

public class LoadSave {
//	//public static final String PLAYER_ATLAS = "player_sprites.png";
	public static final String LEVEL_ATLAS = "Terrain (32x32).png";
	public static final String LEVEL_DECOR = "Decorations (32x32).png";
	public static final String CLOSE = "Closiong (46x56).png";
	public static final String THUNG = "Idle.png";
	public static final String MONSTER_THUNG = "Idle (26x30).png";
	public static final String BOON_OFF = "Bomb Off1.png";
	public static final String IDLE_CANNON = "Idle_cannon.png";
	public static final String KING_PING = "Ground (38x28) king_ping.png";
	public static final String PING_CANNON = "Match On (26x18).png";
	public static final String THUNG_PING = "box_king.png";
	public static final String PING_Static = "Idle (34x28).png";
	public static final String PING_BOOM = "Idle (26x26).png";
	public static final String LIVE_BAR = "Live Bar.png";

	

////	public static final String LEVEL_ONE_DATA = "level_one_data_long.png";
////	public static final String MENU_BUTTONS = "button_atlas.png";
////	public static final String MENU_BACKGROUND = "menu_background.png";
////	public static final String PAUSE_BACKGROUND = "pause_menu.png";
////	public static final String SOUND_BUTTONS = "sound_button.png";
////	public static final String URM_BUTTONS = "urm_buttons.png";
////	public static final String VOLUME_BUTTONS = "volume_buttons.png";
////	public static final String MENU_BACKGROUND_IMG = "background_menu.png";
////	public static final String PLAYING_BG_IMG = "playing_bg_img.png";
////	public static final String BIG_CLOUDS = "big_clouds.png";
////	public static final String SMALL_CLOUDS = "small_clouds.png";
//
	public static BufferedImage GetSpriteAtlas(String fileName) {
		BufferedImage img = null;
		InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
		try {
			img = ImageIO.read(is);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return img;
	}
//
//
//
//
////	public static BufferedImage GetSpriteAtlas(String levelAtlas) {
////		// TODO Auto-generated method stub
////		return null;
////	}
//	
//
//
//	public static int[][] GetLevelData() {
//		int[][] lvlData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
//		BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
//
//		for (int j = 0; j < img.getHeight(); j++)
//			for (int i = 0; i < img.getWidth(); i++) {
//				Color color = new Color(img.getRGB(i, j));
//				int value = color.getRed();
//				if (value >= 48)
//					value = 0;
//				lvlData[j][i] = value;
//			}
//		return lvlData;
//
//	}
}
