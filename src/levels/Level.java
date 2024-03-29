package levels;



public class Level {
	private int[][] lvData;
	
	public Level(int[][] lvlData) {
		this.lvData = lvlData;
	}

	public int getSpriteIndex(int x,int y) {
		return lvData[y][x];
	}
	

}
