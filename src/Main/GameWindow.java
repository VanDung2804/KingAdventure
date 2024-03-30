package Main;

import java.awt.Color;

import javax.swing.JFrame;

public class GameWindow {
	private JFrame jframe;

	public GameWindow(GamePanel gamepanel) {
		jframe = new JFrame();
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setResizable(false);
		jframe.add(gamepanel);
		jframe.setBackground(Color.black);
		jframe.setLocationRelativeTo(null);
		jframe.pack();
		jframe.setVisible(true);

	}
}
