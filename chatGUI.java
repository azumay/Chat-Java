package E01Chat;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.DropMode;

public class chatGUI {

	private JFrame frame;
	private JTextField txtUser;
	private JTextField textField;

	

	/**
	 * Create the application.
	 */
	public chatGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel_top = new JPanel();
		frame.getContentPane().add(panel_top);
		panel_top.setLayout(new GridLayout(2, 3, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_top.add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_top.add(panel_3);
		
		textField = new JTextField();
		panel_3.add(textField);
		textField.setDropMode(DropMode.INSERT);
		textField.setColumns(10);
		
		JButton btnConexion = new JButton("Conectar");
		panel_3.add(btnConexion);
		btnConexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JPanel panel_conexion = new JPanel();
		panel_top.add(panel_conexion);
		
		txtUser = new JTextField();
		panel_conexion.add(txtUser);
		txtUser.setColumns(10);
		
		JPanel panel_chat = new JPanel();
		frame.getContentPane().add(panel_chat);
		panel_chat.setLayout(new GridLayout(2, 2, 0, 0));
		
		Canvas canvas_chat = new Canvas();
		panel_chat.add(canvas_chat);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	

}
