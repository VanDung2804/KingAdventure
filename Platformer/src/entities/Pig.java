package entities;
import static utilz.Constants.EnemyConstants.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;
import static utilz.Constants.Directions.*;

import main.Game;

public class Pig extends Enemy {
	public Pig(float x, float y) {
		super(x, y, PIG_WIDTH, PIG_HEIGHT, PIG);

	}


}
