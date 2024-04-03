package entities;

import static Utilz.Constants.Directions.*;
import static Utilz.Constants.PlayerConstants.*;
import static Utilz.HelpMethods.*;

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
	private boolean left, up,right,down,jump;//giu nut de thuc hien hanh dong
	private boolean moving = false,attacking;
	private float playerSpeed = 2.0f;
	private int[][] lvlData;
	private float xDrawOffset = 21 * Game.SCALE;
	private float yDrawOffset = 4 *Game.SCALE;
	
//	//Jumoing/ Gravity
//	private float airSpeed = 0f;//luu tru toc do khi nhay len nhay xuong//toc do bay
//	private float gravity = 0.004f* Game.SCALE; //trong luc khi dieu chinh se nhay cao hon
//	private float jumpSpeed = -2.25f *Game.SCALE;//toc do nhay
//	private float fallSpeedAfterCollision = 0.5f*Game.SCALE;//toc do roi sau va cham
//	private boolean inAir = false;
	// Jumping / Gravity
		private float airSpeed = 0f;
		private float gravity = 0.04f * Game.SCALE;
		private float jumpSpeed = -2.25f * Game.SCALE;
		private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
		private boolean inAir = false;
	
	
	
	public Player(float x, float y,int width,int height) {
		super(x, y, width, height);
		loadAnimation();
		initHitbox(x, y, 20*Game.SCALE, 27*Game.SCALE);
	}
	
	public void update() {
		updatePos();

		updateAnimationTick();
		setAnimation();
		
	}
	
	public void render(Graphics g) {
		
		g.drawImage(animation[playerAction][aniIndex], (int)( hitbox.x -xDrawOffset), (int)( hitbox.y - xDrawOffset), width, height, null);
//		drawHitbox(g);

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

		if (moving)
			playerAction = RUNNING;
		else
			playerAction = IDLE;

		if (inAir) {
			if (airSpeed < 0)
				playerAction = JUMP;
			else
				playerAction = RUNNING;
		}

		if (attacking)
			playerAction = ATTACK;

		if (startAni != playerAction)
			resetAniTick();
	}

	

	private void resetAniTick() {
		aniTick = 0;
		aniIndex = 0;
		
	}


	private void updatePos() {
		moving = false;

		if (jump)
			jump();
		if (!left && !right && !inAir)
			return;

		float xSpeed = 0;

		if (left)
			xSpeed -= playerSpeed;
		if (right)
			xSpeed += playerSpeed;

		if (!inAir)
			if (!IsEntityOnFloor(hitbox, lvlData))
				inAir = true;

		if (inAir) {
			if (CanMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvlData)) {
				hitbox.y += airSpeed;
				airSpeed += gravity;
				updateXPos(xSpeed);
			} else {
				hitbox.y = GetEntityYPosUnderRoofOrAboveFloor(hitbox, airSpeed);
				if (airSpeed > 0)
					resetInAir();
				else
					airSpeed = fallSpeedAfterCollision;
				updateXPos(xSpeed);
			}

		} else
			updateXPos(xSpeed);
		moving = true;
	}

	private void jump() {
		if (inAir)
			return;
		inAir = true;
		airSpeed = jumpSpeed;

	}

	private void resetInAir() {
		inAir = false;
		airSpeed = 0;
		
	}

	private void updateXPos(float xSpeed) {
		if(CanMoveHere(hitbox.x+xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)) {
			
			// co nghia la di chuyen den day dc
		hitbox.x += xSpeed;
		//hitbox.y += ySpeed;
		moving = true;
	}else {
		hitbox.x= GetEntityXPosNextToWall(hitbox,xSpeed);
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
		if(!IsEntityOnFloor(hitbox, lvlData))
			inAir = true;
	}
	public void setJump(boolean jump) {
		this.jump = jump;
	}

}
