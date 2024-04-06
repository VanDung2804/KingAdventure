package ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import gamestates.Gamestate;
import gamestates.Playing;
import Main.Game;
import utilz.Constants;
import utilz.LoadSave;
//import static utilz.Constants.UI.PauseButtons.*;
//import static utilz.Constants.UI.URMButtons.*;
//import static utilz.Constants.UI.VolumeButtons.*;

public class PauseOverlay {

	private Playing playing;
	private BufferedImage backgroundImg;
	private int bgX, bgY, bgW, bgH;
//	private SoundButton musicButton, sfxButton;
//	private UrmButton menuB, replayB, unpauseB;
//	private VolumeButton volumeButton;

	public PauseOverlay(Playing playing) {
		this.playing = playing;
//	loadBackground();
//		createSoundButtons();
//		createUrmButtons();
//		createVolumeButton();

	}

//	private void createVolumeButton() {
//		int vX = (int) (309 * Game.SCALE);
//		int vY = (int) (278 * Game.SCALE);
//		volumeButton = new VolumeButton(vX, vY, SLIDER_WIDTH, VOLUME_HEIGHT);
//	}
//
//	private void createUrmButtons() {
//		int menuX = (int) (313 * Game.SCALE);
//		int replayX = (int) (387 * Game.SCALE);
//		int unpauseX = (int) (462 * Game.SCALE);
//		int bY = (int) (325 * Game.SCALE);
//
//		menuB = new UrmButton(menuX, bY, URM_SIZE, URM_SIZE, 2);
//		replayB = new UrmButton(replayX, bY, URM_SIZE, URM_SIZE, 1);
//		unpauseB = new UrmButton(unpauseX, bY, URM_SIZE, URM_SIZE, 0);
//
//	}
//
//	private void createSoundButtons() {
//		int soundX = (int) (450 * Game.SCALE);
//		int musicY = (int) (140 * Game.SCALE);
//		int sfxY = (int) (186 * Game.SCALE);
//		musicButton = new SoundButton(soundX, musicY, SOUND_SIZE, SOUND_SIZE);
//		sfxButton = new SoundButton(soundX, sfxY, SOUND_SIZE, SOUND_SIZE);
//
//	}

//	private void loadBackground() {
//		backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.PAUSE_BACKGROUND);
//		bgW = (int) (backgroundImg.getWidth() * Game.SCALE);
//		bgH = (int) (backgroundImg.getHeight() * Game.SCALE);
//		bgX = Game.GAME_WIDTH / 2 - bgW / 2;
//		bgY = (int) (25 * Game.SCALE);
//
//	}

	public void update() {
	}
		

	public void draw(Graphics g) {
		
	}

	public void mouseDragged(MouseEvent e) {
		

	}

	public void mousePressed(MouseEvent e) {
	
	}

	public void mouseReleased(MouseEvent e) {
		

	}

	public void mouseMoved(MouseEvent e) {
		
	}

//	private boolean isIn(MouseEvent e, PauseButton b) {
//		return b.getBounds().contains(e.getX(), e.getY());
//	}

}