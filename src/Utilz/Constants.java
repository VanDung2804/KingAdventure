package Utilz;

public class Constants {
	public static class Directions {
		public static final int LEFT = 0;
		public static final int UP = 1;
		public static final int RIGHT = 2;
		public static final int DOWN = 3;
	}

	public static class PlayerConstants {
		public static final int ATTACK = 0;
		public static final int DEAD = 1;
		public static final int DOOR_IN = 2;
		public static final int DOOR_OUT = 3;
		public static final int JUMP = 4;
		public static final int GORUND = 5;
		public static final int IDLE = 6;
		public static final int RUNNING = 7;
//hang doc 0 //hang
		public static int GetSpriteAmount(int player_action) {
			switch (player_action) {
			case RUNNING:
				return 8;
			case IDLE:
				return 10;
	
			case JUMP:
				return 2;
			case ATTACK:
				return 3;
			case GORUND: 
				return 1;
			default:
				return 1;
			}
		}
	}

}
