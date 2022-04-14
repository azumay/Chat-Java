package E01Chat;

import java.awt.EventQueue;
import java.awt.Font;

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
import javax.xml.parsers.ParserConfigurationException;

import E01Chat.model.Model;
import E01Chat.model.Usuario;

import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.DropMode;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class chatGUI {

	private JFrame frame;
	private JTextField username;

	private JLabel labelUsuarioOnline;

	private JButton btnDesconexion;

	/* Parte superior CHAT */
	private JPanel panel_top;
	private JPanel panel_2;
	private JPanel panel_login;
	private JPanel panel_4;

	private JPanel panel_online;
	private JTextArea inputText;

	/**
	 * Create the application.
	 */
	public chatGUI() {

		initialize();
		panelChat();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		/*VENTANA PRINCIPAL*/
		frame = new JFrame();
		frame.setTitle("Chat Online");
		frame.setBounds(100, 100, 600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(18, 18, 18));
		frame.getContentPane().setLayout(null);
		
	
		
		

		/* POSICIONAMIENTO AUTO DE LA VENTANA DEL JUEGO */

		Dimension sizeH, sizeW;

		sizeH = Toolkit.getDefaultToolkit().getScreenSize();
		sizeW = this.frame.getSize();

		this.frame.setLocation(((sizeH.width - sizeW.width) / 2), (sizeH.height - sizeW.height) / 2);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 2, 0, 0));

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
		panel_top.setLayout(new GridLayout(5, 1, 0, 0));

		this.panel_login = new JPanel();
		panel_top.add(panel_login);
		panel_login.setLayout(new GridLayout(0, 1, 0, 0));

		username = new JTextField();
		this.panel_login.add(username);
		username.setDropMode(DropMode.INSERT);
		username.setColumns(10);

		JButton btnConectar = new JButton("Conectar");
		this.btnDesconexion = new JButton("Desconectar");

		this.panel_login.add(btnConectar);

		this.panel_login.add(btnDesconexion);

		JLabel label = new JLabel("");
		panel_login.add(label);
		this.btnDesconexion.setVisible(false);

		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Usuario userName = new Usuario();
					userName.setNick(username.getText());

					Model login = new Model();

					login.conectarUsuario(userName);
					sesionIniciada();

				} catch (Exception f) {
					JOptionPane.showMessageDialog(null, f, "Exception detected", JOptionPane.WARNING_MESSAGE);
				}
				btnDesconexion.setVisible(true);

			}
		});

		panel_online = new JPanel();
		panel_top.add(panel_online);
		panel_online.setLayout(new GridLayout(2, 3, 0, 0));

		JLabel lblPersonasOnline = new JLabel("Personas Online");
		panel_online.add(lblPersonasOnline);
		
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane);
	}
	public void panelChat () {
		
		
		
		
        
		
		
	}

	public void sesionIniciada()
			throws ClassNotFoundException, SQLException, IOException, ParserConfigurationException {

		btnDesconexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					Model logout = new Model();

					logout.desconectarUsuario();

					btnDesconexion.setVisible(false);
					labelUsuarioOnline.setVisible(false);

				} catch (Exception f) {
					JOptionPane.showMessageDialog(null, f, "Exception detected", JOptionPane.WARNING_MESSAGE);
				}

			}
		});

		btnDesconexion.setForeground(Color.RED);
		Model model = new Model();

		try {
			JLabel usuarios_online = new JLabel();
			ArrayList<Usuario> usuarios = model.getConnectedUsers();
			for (Usuario usuario : usuarios) {
				this.labelUsuarioOnline = new JLabel(usuario.getNick());
				panel_online.add(labelUsuarioOnline);
			}

		} catch (Exception f) {
			JOptionPane.showMessageDialog(null, f, "Exception detected", JOptionPane.WARNING_MESSAGE);
		}

	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

}
