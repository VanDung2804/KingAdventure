package Main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;



public class GamePanel extends JPanel {

	private BufferedImage img;
	private Game game;

	public GamePanel(Game game) {

		this.game = game;
		setPanelSize();
		// importImg();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		game.render(g);
	}

	public void setPanelSize() {
		Dimension size = new Dimension(Game.WIDTH, Game.HEIGHT);
		// setMinimumSize(size);
		setPreferredSize(size);

		// setMaximumSize(size);

	}
	public void updateGame() {
}
	
	public Game getGame() {
		return game;
	}
}
