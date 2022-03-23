package E01Chat;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Canvas;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import E01Chat.Controller.Chat;
import E01Chat.Model.BaseDades;

import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.DropMode;
import java.awt.Color;
import java.awt.Dimension;

public class chatGUI {

	private JFrame frame;
	private JTextField username;


	/* Parte superior CHAT */
	private JPanel panel_top;
	private JPanel panel_2;
	private JPanel panel_login;
	private JPanel panel_4;

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
		this.frame.setBounds(100, 100, 600, 600);
		this.frame.setTitle("Chat Online");
		
		/* POSICIONAMIENTO AUTO DE LA VENTANA DEL JUEGO */

		Dimension sizeH, sizeW;

		sizeH = Toolkit.getDefaultToolkit().getScreenSize();
		sizeW = this.frame.getSize();

		this.frame.setLocation(((sizeH.width - sizeW.width) / 2), (sizeH.height - sizeW.height) / 2);
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(2, 1, 0, 0));

		// Generar el menu superior
				JMenuBar barra = new JMenuBar();
				JMenu programa = new JMenu("Menu");
				JMenuItem acerca = new JMenuItem("Acerca de...");
				JMenuItem salir = new JMenuItem("Salir");

				this.frame.setJMenuBar(barra);
				barra.add(programa);
				programa.add(acerca);
				programa.add(salir);
				programa.addSeparator();
				programa.setMnemonic('P');
		
		/* ACIONES DEL MENU */

				salir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});

				acerca.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(null, "Chat creado creado por Xavi Yamuza", "Acerca de...",
								JOptionPane.INFORMATION_MESSAGE);
					}
				});
		
		this.panel_top = new JPanel();
		frame.getContentPane().add(panel_top);
		panel_top.setLayout(new GridLayout(1, 3, 0, 0));

		JPanel panel_2 = new JPanel();
		panel_top.add(panel_2);

		this.panel_login = new JPanel();
		panel_top.add(panel_login);

		username = new JTextField();
		this.panel_login.add(username);
		username.setDropMode(DropMode.INSERT);
		username.setColumns(10);

		JButton btnConectar = new JButton("Conectar");
		panel_login.add(btnConectar);

		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String usuario = username.getText();
					Chat prueba = new Chat();

					prueba.conectarUsuario(usuario);

				} catch (Exception f) {
					JOptionPane.showMessageDialog(null, f, "Exception detected", JOptionPane.WARNING_MESSAGE);
				}
				sesionIniciada();
			}
		});

		JPanel panel_conexion = new JPanel();
		panel_top.add(panel_conexion);
		panel_conexion.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel_chat = new JPanel();
		frame.getContentPane().add(panel_chat);
		panel_chat.setLayout(new GridLayout(2, 2, 0, 0));

		Canvas canvas_chat = new Canvas();
		panel_chat.add(canvas_chat);
	}

	public void sesionIniciada() {
		JButton btnDesconexion = new JButton("Desconectar");
		btnDesconexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					Chat prueba = new Chat();

					prueba.desconectarUsuario();

					btnDesconexion.setVisible(false);

				} catch (Exception f) {
					JOptionPane.showMessageDialog(null, f, "Exception detected", JOptionPane.WARNING_MESSAGE);
				}

			}
		});

		btnDesconexion.setForeground(Color.RED);
		this.panel_login.add(btnDesconexion);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

}
