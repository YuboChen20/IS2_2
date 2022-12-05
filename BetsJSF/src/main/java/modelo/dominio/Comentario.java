package modelo.dominio;

public class Comentario {
	private Integer comtNumber;
	private String text;
	private Event evento;
	private Usuario user;
	private String date;
	
	
	public Comentario(Integer comtNumber, String text, Event evento, Usuario user, String date) {
		super();
		this.comtNumber = comtNumber;
		this.text = text;
		this.evento = evento;
		this.user = user;
		this.date = date;
	}
	
	public Integer getComtNumber() {
		return comtNumber;
	}
	public void setComtNumber(Integer comtNumber) {
		this.comtNumber = comtNumber;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Event getEvento() {
		return evento;
	}
	public void setEvento(Event evento) {
		this.evento = evento;
	}
	public Usuario getUser() {
		return user;
	}
	public void setUser(Usuario user) {
		this.user = user;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return user.getUserName()+": "+text;
	}
	
	
	
}
