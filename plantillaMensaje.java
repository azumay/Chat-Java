package E01Chat;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;


import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class plantillaMensaje {
	private String user;
	private String message;
	private String date;
	
	
	public plantillaMensaje(String user, String message, String date) {
		this.user=user;
		this.message=message;
		this.date=date;
	}

	public JPanel createMessage() {

		JLabel jlabelUser = new JLabel(" "+this.user, SwingConstants.LEFT);
		JLabel jlabelMensaje = new JLabel(" "+this.message+" ", SwingConstants.CENTER);
		JLabel jlabelHora = new JLabel(this.date+" ✔️✔️ ", SwingConstants.RIGHT);
		JPanel jpanel = new JPanel(new GridLayout(3, 1, 8, 8));
		
		jpanel.setBorder(BorderFactory.createLineBorder(new Color(52,61,70)));
		jpanel.add(jlabelUser);
		jpanel.add(jlabelMensaje);
		jpanel.add(jlabelHora);
		
		jpanel.setBackground(new Color(79,79,79));
		jlabelUser.setForeground(new Color(4, 207, 68));
		jlabelMensaje.setForeground(new Color(255,255,255));
		jlabelHora.setForeground(new Color(255,255,255));
		
		jlabelUser.setFont(new Font("Century Gothic", Font.ITALIC, 12));
		jlabelMensaje.setFont(new Font("Century Gothic", Font.BOLD, 20));
		jlabelHora.setFont(new Font("Century Gothic", Font.ITALIC, 10));
		
		return jpanel;
	}
}


