package Objects;
import Main.Game;

public class Diamon extends GameObject {

	private float hoverOffset;
	private int maxHoverOffset, hoverDir = 1;

	public Diamon(int x, int y, int objType) {
		super(x, y, objType);
		doAnimation = true;

		initHitbox(14, 10);

		xDrawOffset = (int) (5 * Game.SCALE);
		yDrawOffset = (int) (1 * Game.SCALE);

		maxHoverOffset = (int) (5 * Game.SCALE);

		hitbox.y += yDrawOffset + (int) (Game.SCALE *5);
		hitbox.x += xDrawOffset - 15;
	}

	public void update() {
		updateAnimationTick();
		updateHover();
	}

	private void updateHover() {
		hoverOffset += (0.075f * Game.SCALE * hoverDir);

		if (hoverOffset >= maxHoverOffset)
			hoverDir = -1;
		else if (hoverOffset < 0)
			hoverDir = 1;

		hitbox.y = y + hoverOffset;
	}
}
