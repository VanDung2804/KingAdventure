package Levels;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Entities.Piggy;
import Objects.Cannon;
import Objects.Diamon;
import Objects.GameContainer;
import Objects.Spike;
import Utilz.HelpMethods;

import static Utilz.HelpMethods.GetLevelData;
import static Utilz.HelpMethods.GetPigs;
import static Utilz.HelpMethods.GetPlayerSpawn;


public class Level {

	private BufferedImage img;
	private int[][] lvlData;
	private ArrayList<Piggy> pigs;
	private Point playerSpawn;
	private ArrayList<Diamon> diamons;
	private ArrayList<GameContainer> containers;
	private ArrayList<Spike> spikes;
	private ArrayList<Cannon> cannons;

	public Level(BufferedImage img) {
		this.img = img;
		createLevelData();
		createEnemies(LevelManager.lvlIndex);;
		calcPlayerSpawn(LevelManager.lvlIndex);
		createDiamons(LevelManager.lvlIndex);
		createContainers(LevelManager.lvlIndex);
		createSpikes(LevelManager.lvlIndex);
		createCannons(LevelManager.lvlIndex);

	}
	private void createCannons(int lvlIndex) {
		cannons = HelpMethods.GetCannon(lvlIndex);
	}
	private void createSpikes(int lvlIndex) {
		spikes = HelpMethods.GetSpikes(lvlIndex);
	}
	public void createContainers( int lvIndex) {
		containers = HelpMethods.GetContainers(lvIndex);
	}

	public void createDiamons(int lvlIndex  ) {
		diamons = HelpMethods.GetDiamons(lvlIndex);
	}
	
	public void calcPlayerSpawn(int lvlIndex) {
		playerSpawn = GetPlayerSpawn(lvlIndex);
	}
	
	public void createEnemies(int lvlIndex) {
		pigs = GetPigs(lvlIndex);
	}
	
	public ArrayList<Piggy> getPigs() {
		return pigs;
	}

	private void createLevelData() {
		lvlData = GetLevelData(img);
	}

	public Level(int[][] lvlData) {
		this.lvlData = lvlData;
	}

	public int getSpriteIndex(int x, int y) {
		return lvlData[y][x];
	}

	public int[][] getLevelData() {
		return lvlData;
	}

	public Point getPlayerSpawn() {
		
		return playerSpawn;
	}
	
	public ArrayList<Diamon> getPotions() {
		return diamons;
	}

	public ArrayList<GameContainer> getContainers() {
		return containers;
	}
	
	public ArrayList<Spike> getSpikes() {
		return spikes;
	}
	
	public ArrayList<Cannon> getCannon() {
		return cannons;
	}
}  