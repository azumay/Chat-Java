package E01Chat.Model;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class BaseDades {

	private String servidor;
	private String bbdd;
	private String user;
	private String password;
	private Connection conexion;

	public BaseDades() {
		this.servidor = "jdbc:mysql://localhost:3306/";
		this.bbdd = "chat";
		this.user = "appuser";
		this.password = "TiC.123456";
	}
	
	public Connection toConnect () {
		try {
		
		this.conexion = DriverManager.getConnection(this.servidor + this.bbdd, this.user, this.password);
		
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e, "Exception detected", JOptionPane.WARNING_MESSAGE);
		}
		return conexion;
	}
}
