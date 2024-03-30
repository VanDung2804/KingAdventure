package Main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;


public class GameWinDow{
	private JFrame jframe;
	public GameWinDow(GamePanel gamePanel) {
		jframe = new JFrame();
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setLocationRelativeTo(null);
        jframe.add(gamePanel);
        jframe.pack();
        jframe.setResizable(false);
        jframe.setVisible(true);
        jframe.addWindowFocusListener(new WindowFocusListener() {
			
			@Override
			
			
			
			public void windowLostFocus(WindowEvent e) {
				gamePanel.getGame().windowFocus();
				
			}
			
			@Override
			public void windowGainedFocus(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}

}
