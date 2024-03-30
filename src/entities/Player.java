package entities;

import static Utilz.Constants.Directions.*;
import static Utilz.Constants.PlayerConstants.*;
import static Utilz.HelpMethods.CanMoveHere;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import Main.Game;
import Utilz.LoadSave;

public class Player extends Entity{
	
	private BufferedImage[][] animation;
	private int aniTick,aniIndex,aniSpeed = 25;
	private int playerAction = IDLE ;
	private boolean left, up,right,down;
	private boolean moving = false,attacking;
	private float playerSpeed = 2.0f;
	private int[][] lvlData;
	private float xDrawOffset = 21 * Game.SCALE;
	private float yDrawOffset = 4 *Game.SCALE;

	public Player(float x, float y,int width,int height) {
		super(x, y, width, height);
		loadAnimation();
		initHitbox(x, y, 20*Game.SCALE, 28*Game.SCALE);
	}
	
	public void update() {
		updatePos();

		updateAnimationTick();
		setAnimation();
		
	}
	
	public void render(Graphics g) {
		
		g.drawImage(animation[playerAction][aniIndex], (int)( hitbox.x -xDrawOffset), (int)( hitbox.y - xDrawOffset), width, height, null);
		drawHitbox(g);

	}
	private void updateAnimationTick() {
		aniTick++;
		if(aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
		        if(aniIndex >= GetSpriteAmount(playerAction)) { 
			       aniIndex = 0;
			       attacking = false;	
		        }
		}
	}
	
	private void setAnimation() {
		
		int startAni = playerAction;
		if(moving) 
			playerAction = RUNNING;
		else 
			playerAction = IDLE;
		
		if(attacking)
			playerAction = ATTACK;
		
		if(startAni != playerAction)
			resetAniTick();
	}
	

	private void resetAniTick() {
		aniTick = 0;
		aniIndex = 0;
		
	}

	private void updatePos() {
		moving = false;
		
		if(!left && !right && !up && !down)
			return;
		float xSpeed = 0, ySpeed = 0;
		if (left && !right) 
			xSpeed =- playerSpeed;
		 else if (right && !left) 
			xSpeed = playerSpeed;
		if (up && !down) 
			ySpeed = -playerSpeed;
		 else if (down && !up) 
			ySpeed = playerSpeed;
//		
//		
//		if(CanMoveHere(x+xSpeed, y+ySpeed, width, height, lvlData)) {
//			this.x += xSpeed;
//			this.y += ySpeed;
//			moving = true;
//		}
		
		

		
		if(CanMoveHere(hitbox.x+xSpeed, hitbox.y+ySpeed, hitbox.width, hitbox.height, lvlData)) {
			hitbox.x += xSpeed;
			hitbox.y += ySpeed;
			moving = true;
		}
			
	}
	

	private void loadAnimation() {
		
			BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
			animation = new BufferedImage[9][10];
			for(int j=0;j<animation.length;j++) 
			for(int i=0;i<animation[j].length;i++) 
			animation[j][i] = img.getSubimage(i*78,j*57, 78, 57);
	}
	
	
	
	
	
	public void resetDirBooleans() {
		left = false;
		right = false;
		down = false;
		up = false;
	}
	
	

	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public void loadlvlData(int[][] lvlData) {
		this.lvlData = lvlData;
		
	}

}
