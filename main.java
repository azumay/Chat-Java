package E01Chat;

import javax.swing.JOptionPane;
import java.awt.EventQueue;
import E01Chat.chatGUI;

public class main {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					chatGUI window = new chatGUI();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
