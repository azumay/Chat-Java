package E01Chat.Controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import E01Chat.Model.BaseDades;

public class Chat {

	private Connection conexion;
	
	
	public Chat () {
		
		BaseDades setConexion = new BaseDades();
		this.conexion = setConexion.toConnect();
	}
	
	
	public void conectarUsuario(String user) throws ClassNotFoundException, SQLException {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			CallableStatement stmt = conexion.prepareCall("{call connect(?)}");
			stmt.setString(1, user);
			ResultSet result = stmt.executeQuery();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Exception detected", JOptionPane.WARNING_MESSAGE);
		}

	}
	public void desconectarUsuario() throws ClassNotFoundException, SQLException {

		try {
			
			CallableStatement accion = conexion.prepareCall("{call disconnect}");
			ResultSet resultCall = accion.executeQuery();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Exception detected", JOptionPane.WARNING_MESSAGE);
		}

	}
}
