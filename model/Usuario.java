package E01Chat.model;

import java.time.LocalDateTime;

public class Usuario {

	private String nick;
	private LocalDateTime fecha;

	public Usuario(String nick, LocalDateTime fecha) {
		this.nick = nick;
		this.fecha = fecha;

	}

	public Usuario() {

	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public Usuario(String pNick) {

		this.nick = pNick;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

}
