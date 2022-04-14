package E01Chat;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Timestamp;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import E01Chat.model.Mensaje;
import E01Chat.model.Usuario;

public class plantillaMensaje {
	private String user;
	private String message;
	private Timestamp date;
	
	/**
	 * Constructor per obtindre les dades necesaries per crear un missatge
	 * @param user
	 * @param message
	 * @param date
	 */
	public plantillaMensaje(String user, String message, Timestamp date) {
		this.user=user;
		this.message=message;
		this.date=date;
	}

	/**
	 * Metode per crear un missatge a partir de les dades que 'shan definit al iniciar la Clase
	 * @return el panell amb el missatge contruit
	 */
	public JPanel createMessage() {

		JLabel jlabel = new JLabel(" "+this.user, SwingConstants.LEFT);
		JLabel jlabel2 = new JLabel(" "+this.message+" ", SwingConstants.CENTER);
		JLabel jlabel3 = new JLabel(this.date+" ", SwingConstants.RIGHT);
		JPanel jpanel = new JPanel(new GridLayout(3, 1, 8, 8));
		jpanel.setBorder(BorderFactory.createLineBorder(new Color(52,61,70)));
		jpanel.add(jlabel);
		jpanel.add(jlabel2);
		jpanel.add(jlabel3);
		jpanel.setBackground(new Color(79,79,79));
		jlabel.setForeground(new Color(255,255,255));
		jlabel2.setForeground(new Color(255,255,255));
		jlabel3.setForeground(new Color(255,255,255));
		jlabel.setFont(new Font("Century Gothic", Font.ITALIC, 12));
		jlabel2.setFont(new Font("Century Gothic", Font.BOLD, 20));
		jlabel3.setFont(new Font("Century Gothic", Font.ITALIC, 10));
		
		return jpanel;
	}
}


