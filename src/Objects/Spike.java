package Objects;
import Main.Game;
public class Spike extends GameObject {

	public Spike(int x, int y, int objType) {
		super(x, y, objType);
		
		initHitbox(32, 16);
		xDrawOffset = 0;
		yDrawOffset = 32;
		hitbox.y += yDrawOffset;
		
	}

}
