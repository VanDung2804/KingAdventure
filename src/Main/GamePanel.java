package Main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Inputs.MouseInputs;
import Inputs.MouseInputs;
import Inputs.KeyboardInputs;

import static Utilz.Constants.PlayerConstants.*;
import static Utilz.Constants.Directions.*;
public class GamePanel extends JPanel{

	private MouseInputs mouseInputs;
	private float xDelta = 100, yDelta = 100;
	//private BufferedImage[] idleAni;
	private BufferedImage img;
	private BufferedImage[][] animation;
	private int aniTick,aniIndex,aniSpeed = 50;
	private int playerAction = IDLE ;
	private int playerDir = -1;
	private boolean moving = false;
	 
	public GamePanel() {
		mouseInputs = new MouseInputs(this);
		importImg();
		setPanelSize();
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
		loadAnimation();
	}


	
	private void loadAnimation() {
		//so phan tu cua mang
		animation = new BufferedImage[9][10];//so luong sprite có trong ảnh
		for(int j=0;j<animation.length;j++) {
		for(int i=0;i<animation[j].length;i++) {
			//hang j,cot i
			//chieu cao 58,chieu rong 78
			animation[j][i] = img.getSubimage(i*78,j*57, 78, 57);//đọc số lượng ảnh bất đâù từ 0
		}
		}
	}

	private void importImg() {
		InputStream is = getClass().getResourceAsStream("/king.png");

		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void setPanelSize() {
		Dimension size = new Dimension(1280, 800);
		setMinimumSize(size);//kich thuoc toi thieu
		setPreferredSize(size);
		setMaximumSize(size);//toi da

	}

//	public void changeXDelta(int value) {
//		this.xDelta += value;
//	}
//
//	public void changeYDelta(int value) {
//		this.yDelta += value;
//	}
//
//	public void setRectPos(int x, int y) {
//		this.xDelta = x;
//		this.yDelta = y;
//	}
	
	//set huong
	public void setDirection(int direction) {
		this.playerDir = direction;
		moving = true;
	}
	
	public void setMoving(boolean moving) {
		this.moving = moving;
	}
	
	
	private void updateAnimationTick() {
		aniTick++;
		if(aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
		        if(aniIndex >= GetSpriteAmount(playerAction)) 
			       aniIndex = 0;
		}
	}
	
	private void setAnimation() {
		if(moving) 
			playerAction = RUNNING;
		else 
			playerAction = IDLE;
		
	}
	

	private void updatePos() {
		if(moving) {
			switch(playerDir) {
			case LEFT:
				xDelta -= 1;
				break;
			case UP:
				yDelta -= 1;
				break;
			case RIGHT:
				xDelta += 1;
				break;
			case DOWN:
				yDelta += 1;
				break;
			}
		}
		
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		updateAnimationTick();
		//toa do diem bat dau 78 ngang,58 doc
		setAnimation();
		updatePos();
		g.drawImage(animation[playerAction][aniIndex], (int) xDelta, (int) yDelta, 256, 160, null);
		repaint();//ve lai
	}


	


	
}
