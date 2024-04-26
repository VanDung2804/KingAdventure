package Entities;

import static Utilz.Constants.PlayerConstants.*;
import static Utilz.HelpMethods.*;
import Utilz.LoadSave;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import GameStates.Playing;
import Main.Game;

public class Player extends Entity{
	
	private BufferedImage[][] animation;
	
	private int aniTick,aniIndex,aniSpeed = 25;
	private int heartIndex;
	private int flipX = 0, flipW = 1;
	private int[][] lvlData;
	private int playerAction = IDLE ;
	private boolean left, up,right,down,jump;
	private boolean moving = false, attacking;
	
	private float playerSpeed = 1.5f;
	private float xDrawOffset = 28 * Game.SCALE;
	private float yDrawOffset = 19 *Game.SCALE;

	private float airSpeed = 0f;
	private float gravity = 0.02f * Game.SCALE;
	private float jumpSpeed = -2.25f * Game.SCALE;
	private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
	private boolean inAir = false;
	
	//Status Bar UI
	private BufferedImage statusBarImg;
	private BufferedImage[] heart;

	
	private int statusBarWidth = (int) (99 * Game.SCALE);
	private int statusBarHeight = (int) (51 * Game.SCALE);
	private int statusBarX = (int) (40 * Game.SCALE);
	private int statusBarY = (int) (30 * Game.SCALE);

	private int healthBarWidth = (int) (150 * Game.SCALE);
	private int healthBarHeight = (int) (4 * Game.SCALE);
	private int healthBarXStart = (int) (57 * Game.SCALE);
	private int healthBarYStart = (int) (45 * Game.SCALE);

	private int tileY = 0;

	private int maxHealth = 3;
	private int currentHealth = maxHealth;
	private int healthWidth = healthBarWidth;
	
	//Attack Box
	private Rectangle2D.Float attackBox;
	
	private boolean attackChecked;
	private Playing playing;
	
	public Player(float x, float y, int width ,int height, Playing playing) {
		super(x, y, width, height);
		this.playing = playing;
		loadAnimation();
		initHitbox(x, y, (int)35, (int) 37);
		initAttackBox();
	}
	
	public void update() {

		if (currentHealth <= 0) {
			playing.setGameOver(true);
			return;
		}
		updateAttackBox();
		updatePos();
		if (moving)
			checkPotionTouched();

		if (attacking)
			checkAttack();
		updateAnimationTick();
		updateHeartTick();
		setAnimation();
	}

	public void render(Graphics g) {
		g.drawImage(animation[playerAction][aniIndex], (int) x + flipX, (int) y, 128 * flipW, 80, null);
//		drawHitbox(g);
//		drawAttackBox(g);
		drawUI(g);
	}
	
	public void setSpawn(Point spawn) {
		System.out.println(spawn.x);
		System.out.println(spawn.y);
		this.x = spawn.x;
		this.y = spawn.y;
		hitbox.x = x;
		hitbox.y = y;
	}
	
		private void initAttackBox() {
		attackBox = new Rectangle2D.Float(x, y, (int)(44* Game.SCALE), (int)(32 * Game.SCALE));
	}

	private void updateAttackBox() {
		if(right) {
			attackBox.x = hitbox.x + hitbox.width;
		}
		else if(left) {
			attackBox.x = hitbox.x  - (int)attackBox.width;
		}
		attackBox.y = hitbox.y;
	}

	private void drawAttackBox(Graphics g) {
		g.setColor(Color.red);
		g.drawRect((int)attackBox.x, (int)attackBox.y, (int)attackBox.width, (int)attackBox.height);
	}
	
	
	private void checkPotionTouched() {
		playing.checkPotionTouched(hitbox);
	}
	
	public void changeHealth(int value) {
		currentHealth += value;

		if (currentHealth <= 0)
			currentHealth = 0;
		else if (currentHealth >= maxHealth)
			currentHealth = maxHealth;
	}
	
	private void drawUI(Graphics g) {
		g.drawImage(statusBarImg, statusBarX, statusBarY,  statusBarWidth, statusBarHeight, null);
		switch(currentHealth) {
		case 1:
			g.drawImage(heart[heartIndex], healthBarXStart, healthBarYStart, (int)(27 * Game.SCALE), (int)(21 * Game.SCALE), null);
			break;
		case 2:
			g.drawImage(heart[heartIndex], healthBarXStart, healthBarYStart, (int)(27 * Game.SCALE), (int)(21 * Game.SCALE), null);
			g.drawImage(heart[heartIndex], healthBarXStart + 20, healthBarYStart, (int)(27 * Game.SCALE), (int)(21 * Game.SCALE), null);
			break;
		case 3:
			g.drawImage(heart[heartIndex], healthBarXStart, healthBarYStart, (int)(27 * Game.SCALE), (int)(21 * Game.SCALE), null);
			g.drawImage(heart[heartIndex], healthBarXStart + 20, healthBarYStart, (int)(27 * Game.SCALE), (int)(21 * Game.SCALE), null);
			g.drawImage(heart[heartIndex], healthBarXStart + 40, healthBarYStart, (int)(27 * Game.SCALE), (int)(21 * Game.SCALE), null);
			break;
		}
		
	}

	private void updateAnimationTick() {
		aniTick++;
		if (aniTick >= aniSpeed) { 
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= GetSpriteAmount(playerAction)) {
				attacking = false;
				attackChecked = false;
				aniIndex = 0;
			}
		}
	}
	
	private void updateHeartTick() {
		aniTick++;
		if (aniTick >= aniSpeed) { 
			aniTick = 0;
			heartIndex++;
			if (heartIndex >= 8) 
				heartIndex = 0;
		}
	}
	
	private void checkAttack() {
		if (attackChecked || aniIndex != 1)
			return;
		attackChecked = true;
		playing.checkEnemyHit(attackBox);
		playing.checkObjectHit(attackBox);

	}
	
	private void setAnimation() {
		int startAni = playerAction;

		if (moving)
			playerAction = RUN;
		else
			playerAction = IDLE;

		if (inAir) {
			if (airSpeed < 0)
				playerAction = JUMP;
			else
				playerAction = FALL;
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
	
	public void loadLvlData(int[][] lvlData) {
		this.lvlData = lvlData;
		if (!IsEntityOnFloor(hitbox, lvlData))
			inAir = true;
	}

	private void updatePos() {
		moving = false;
		
	    if (jump && !inAir) {
	        jump();
	    }

	    float xSpeed = 0;

	    if (left) {
	    	flipX = (int)(width * 1.34);
	    	flipW = -1;
	        xSpeed -= playerSpeed;
	        moving = true;
	    }
	    if (right) {
	    	flipX = 0;
	    	flipW = 1;
	        xSpeed += playerSpeed;
	        moving = true;
	    }
	    if (!inAir && !IsEntityOnFloor(hitbox, lvlData)) {
	        inAir = true;
	    }
	    if (inAir) {
	        if (CanMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvlData)) {
	            hitbox.y += airSpeed;
	            y += airSpeed;
	            airSpeed += gravity;
	            updateXPos(xSpeed);
	        } 
	        else {
	            hitbox.y = GetEntityYPosUnderRoofOrAboveFloor(hitbox, airSpeed);
	            if (airSpeed > 0) {
	                resetInAir();
	            } 
	             else {
	                airSpeed = fallSpeedAfterCollision;
	            }
	            updateXPos(xSpeed);
	        }
	    } 
	    else updateXPos(xSpeed);
	    hitbox.x = x + xDrawOffset;
	    hitbox.y = y + yDrawOffset;
	}

	private void resetInAir() {
		inAir = false;
		airSpeed = 0;
	}
	
	private void updateXPos(float xSpeed) {
	    if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)) {
	        hitbox.x += xSpeed;
	        x += xSpeed;
	    } else {
	        hitbox.x = GetEntityXPosNextToWall(hitbox, xSpeed);
	    }
	}


	private void loadAnimation() {
		BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.Player);
		BufferedImage heartSprite = LoadSave.GetSpriteAtlas(LoadSave.HEART);
		
		animation = new BufferedImage[10][11];
		heart = new BufferedImage[8];
		
		for(int j=0;j<animation.length;j++) 
			for(int i=0;i<animation[j].length;i++) 
				animation[j][i] = img.getSubimage(i*78,j*58, 78, 58);
		
		for(int i=0;i<heart.length;i++) 
			heart[i] = heartSprite.getSubimage(i*18, 0, 18, 14);
		
		 statusBarImg = LoadSave.GetSpriteAtlas(LoadSave.STATUS_BAR);
		 
	}
	
	public void resetDirBooleans() {
		left = false;
		right = false;
		down = false;
		up = false;
	}
	
	private void jump() {
		if (inAir)
			return;
		inAir = true;
		airSpeed = jumpSpeed;

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
	
	public void setJump(boolean jump) {
		this.jump = jump;
	}

	public void resetAll() {
		resetDirBooleans();
		inAir = false;
		attacking = false;
		moving = false;
		playerAction = IDLE;
		currentHealth = maxHealth;

		hitbox.x = x;
		hitbox.y = y;

		if (!IsEntityOnFloor(hitbox, lvlData))
			inAir = true;
	}
	public int getTileY() {
		return tileY;
	}
}