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

import E01Chat.exception.ExceptionChat;
import E01Chat.model.Mensaje;
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
import javax.swing.JTextPane;

public class chatGUI extends JFrame {

	private JFrame frame;
	private JTextField username;
	private boolean conectado;

	private JLabel labelUsuarioOnline;

	private JButton btnDesconexion;
	private JPanel panel_2;
	private JPanel panel_4;
	private JTextArea inputMensaje;
	private JPanel msgArea;

	private JTextPane panel_online;

	private JButton btnEnviar;

	/**
	 * Create the application.
	 */
	public chatGUI() throws ClassNotFoundException, SQLException {

		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		/* VENTANA PRINCIPAL */
		frame = new JFrame();
		frame.setTitle("Chat Online");
		frame.setBounds(100, 100, 700, 665);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(34, 46, 53));
		frame.getContentPane().setLayout(null);

		/* POSICIONAMIENTO AUTO DE LA VENTANA DEL JUEGO */

		Dimension sizeH, sizeW;

		sizeH = Toolkit.getDefaultToolkit().getScreenSize();
		sizeW = this.frame.getSize();

		this.frame.setLocation(((sizeH.width - sizeW.width) / 2), (sizeH.height - sizeW.height) / 2);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
		frame.getContentPane().setLayout(null);

		username = new JTextField();
		username.setBounds(89, 0, 218, 38);
		frame.getContentPane().add(username);
		username.setDropMode(DropMode.INSERT);
		username.setColumns(10);

		JButton btnConectar = new JButton("Conectar");
		btnConectar.setFont(new Font("Dialog", Font.BOLD, 10));
		btnConectar.setForeground(Color.WHITE);
		btnConectar.setBounds(330, 0, 119, 38);
		btnConectar.setBackground(new Color(66, 203, 165));
		frame.getContentPane().add(btnConectar);
		this.btnDesconexion = new JButton("Desconectar");
		btnDesconexion.setFont(new Font("Dialog", Font.BOLD, 10));
		btnDesconexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDesconexion.setBounds(480, 0, 119, 38);
		frame.getContentPane().add(btnDesconexion);

		JLabel lblPersonasOnline = new JLabel("Personas Online");
		lblPersonasOnline.setForeground(Color.WHITE);
		lblPersonasOnline.setBounds(10, 45, 135, 67);
		frame.getContentPane().add(lblPersonasOnline);

		JLabel lblNickname = new JLabel("Nickname:");
		lblNickname.setBounds(12, 11, 99, 27);
		lblNickname.setForeground(new Color(255, 255, 255));
		frame.getContentPane().add(lblNickname);

		msgArea = new JPanel();
		msgArea.setLayout(new GridLayout(10, 1, 5, 5));
		msgArea.setBackground(Color.GRAY);
		msgArea.setVisible(false);
		msgArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		frame.getContentPane().add(msgArea);

		JScrollPane scrollMensaje = new JScrollPane(msgArea);
		scrollMensaje.setBounds(141, 55, 547, 474);
		scrollMensaje.setVisible(false);
		scrollMensaje.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		frame.getContentPane().add(scrollMensaje);

		/*Zona donde agregaremos los usuarios online*/
		panel_online = new JTextPane();
		panel_online.setEditable(false);
		panel_online.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel_online.setBounds(0, 90, 140, 439);
		panel_online.setFont(new Font("Century Gothic", Font.PLAIN, 25));
		panel_online.setForeground(new Color(66, 203, 165));
		frame.getContentPane().add(panel_online);

		btnEnviar = new JButton("Enviar");
		btnEnviar.setBackground(new Color(66, 203, 165));
		btnEnviar.setBounds(141, 530, 99, 67);
		frame.getContentPane().add(btnEnviar);

		inputMensaje = new JTextArea();
		inputMensaje.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		inputMensaje.setWrapStyleWord(true);
		inputMensaje.setLineWrap(true);
		inputMensaje.setForeground(new Color(0, 0, 0));
		inputMensaje.setBackground(new Color(255, 255, 255));
		inputMensaje.setVisible(false);
		inputMensaje.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		JScrollPane scrollPaneText = new JScrollPane(inputMensaje);
		scrollPaneText.setBounds(242, 530, 446, 67);
		frame.getContentPane().add(scrollPaneText);

		this.btnDesconexion.setVisible(false);

		/**
		 * ActionListener para CONECTAR un usuario
		 */

		conectado = false;
		btnConectar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {
					if (username.getText().equals("")) {
						throw new ExceptionChat("Escribe un nickname para conectarte", "E001");
					} else if (username.getText().length() >= 20) {
						throw new ExceptionChat("Máximo 20 caracteres", "E002");
					}
					conectado = true;
					Usuario userName = new Usuario();
					userName.setNick(username.getText());

					Model login = new Model();
					login.conectarUsuario(userName);

					btnDesconexion.setVisible(true);
					inputMensaje.setEditable(true);
					inputMensaje.setEnabled(true);
					inputMensaje.setVisible(true);
					msgArea.setVisible(true);
					scrollMensaje.setVisible(true);

				} catch (Exception f) {
					JOptionPane.showMessageDialog(null, f, "Exception detected", JOptionPane.WARNING_MESSAGE);
				}

			}
		});

		/**
		 * ActionListener para ENVIAR MENSAJE
		 */

		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					if (inputMensaje.getText().equals("")) {
						throw new ExceptionChat("No puedes enviar mensajes sin texto", "E003");
					} else if (inputMensaje.getText().length() >= 30) {
						throw new ExceptionChat("Máximo 30 caracteres por mensaje", "E004");
					}
					Model mdl = new Model();
					Mensaje msg = new Mensaje();
					msg.setMensaje(inputMensaje.getText());
					mdl.enviar(msg);
					inputMensaje.setText(" ");

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Exception detected",
							JOptionPane.WARNING_MESSAGE);
				}
			}

		});

		/**
		 * ActionListener para DESCONECTAR usuario
		 */
		btnDesconexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					conectado = false;
					Model logout = new Model();
					logout.desconectarUsuario();

					btnDesconexion.setVisible(false);
					inputMensaje.setEditable(false);
					inputMensaje.setEnabled(false);
					inputMensaje.setVisible(false);
					msgArea.setVisible(false);
					inputMensaje.setText("");
					btnDesconexion.setForeground(Color.RED);

				} catch (Exception f) {
					JOptionPane.showMessageDialog(null, f, "Exception detected", JOptionPane.WARNING_MESSAGE);
				}

			}
		});

		javax.swing.Timer timer = new javax.swing.Timer(1300, new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent ae) {

				String userName = "";
				try {
					Model model = new Model();
					ArrayList<Usuario> usuarios = model.getConnectedUsers();
					for (int i = 0; i < usuarios.size(); i++) {
						userName += "✅"+usuarios.get(i).getNick() + "\n";
						panel_online.setText(userName);
					}

				} catch (Exception f) {
					JOptionPane.showMessageDialog(null, f, "Exception detected", JOptionPane.WARNING_MESSAGE);
				}

				if (conectado) {

					try {
						// Obetener los mensajes

						Model model = new Model();
						ArrayList<Mensaje> arrayMensajes = model.getMensajes();

						for (Mensaje msg : arrayMensajes) {
							plantillaMensaje message = new plantillaMensaje(msg.getNick(), msg.getMensaje(),
									msg.getHora());
							msgArea.add(message.createMessage());
						}

					} catch (Exception f) {
						JOptionPane.showMessageDialog(null, f, "Exception detected", JOptionPane.WARNING_MESSAGE);
					}
				}
			}

		});
		timer.start();

	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

}
