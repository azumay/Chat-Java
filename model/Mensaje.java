package E01Chat.model;

import java.sql.Timestamp;

public class Mensaje {

	int id;
	String nick;
	String mensaje;
	Timestamp hora;
	
	public Mensaje() {
		
	}

	public int getId() {
		return id;
	}

	public Timestamp getHora() {
		return hora;
	}

	public void setHora(Timestamp hora) {
		this.hora = hora;
	}

	public void setId(int id) {
		this.id = id;
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
