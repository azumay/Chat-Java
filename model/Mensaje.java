package E01Chat.model;

import java.sql.Timestamp;

public class Mensaje {

	String nick;
	String mensaje;
	String hora;
	
	public Mensaje(String nick, String mensaje, String hora) {
		
		this.nick = nick;
		this.mensaje = mensaje;
		this.hora = hora;
		
	}
	public Mensaje() {
		
		
	}

	

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	
	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
}
