package E01Chat.model;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;

public class Model {

	private String servidor;
	private String bbdd;
	private String user;
	private String password;
	private Connection conexion;

	public Model() throws SQLException, IOException, ParserConfigurationException, ClassNotFoundException {
		this.servidor = "jdbc:mysql://localhost:3306/";
		this.bbdd = "chat";
		this.user = "appuser";
		this.password = "TiC.123456";
		this.conexion = toConnect();
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
	
	//Zona de metodos
	
	public void conectarUsuario(Usuario user) throws ClassNotFoundException, SQLException {

		try {
			CallableStatement stmt = this.conexion.prepareCall("{call connect(?)}");
			stmt.setString(1, user.getNick());
			ResultSet result = stmt.executeQuery();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Exception detected", JOptionPane.WARNING_MESSAGE);
		}

	}
	
	public void desconectarUsuario() throws ClassNotFoundException, SQLException {

		try {
			
			CallableStatement accion = this.conexion.prepareCall("{call disconnect}");
			ResultSet resultCall = accion.executeQuery();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Exception detected", JOptionPane.WARNING_MESSAGE);
		}

	}
	
	public ArrayList<Usuario> getConnectedUsers () throws SQLException {
        ResultSet getUsersOnline = this.conexion.createStatement().executeQuery("call getConnectedUsers()");
        ArrayList<Usuario> users = new ArrayList<>();

        while (getUsersOnline.next()) {
        	Usuario user = new Usuario();
            user.setNick(getUsersOnline.getString("nick"));
            //user.setDate_con(getUsersOnline.getObject("date_con", LocalDateTime.class));
            users.add(user);
        }

        getUsersOnline.close();
        return users;
    }
	
	
	
	public void enviar(Mensaje msg) throws SQLException {
        CallableStatement stmt = this.conexion.prepareCall("call send(?)");
        stmt.setString(1, msg.getMensaje());
        stmt.execute();
        stmt.close();
    }
	
	public ArrayList<Mensaje> getMensajes () throws SQLException {
        ResultSet result = this.conexion.createStatement().executeQuery("call getMessages()");
        ArrayList<Mensaje> men = new ArrayList<>();

        while (result.next()) {
        	Mensaje mensajes = new Mensaje();
        	mensajes.setNick(result.getString("nick"));
        	mensajes.setMensaje(result.getString("mensaje"));
        	mensajes.setHora(result.getTimestamp("hora"));
        	men.add(mensajes);
        }

        result.close();
        return men;
    }
	
}
