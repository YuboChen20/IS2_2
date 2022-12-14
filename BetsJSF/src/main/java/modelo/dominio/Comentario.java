package modelo.dominio;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Comentario {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer comtNumber;
	private String text;
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Event evento;
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private Usuario user;
	private String date;
	
	public Comentario() {}
	
	/*public Comentario(String text, Event evento, String date) {
		super();
		//this.comtNumber = comtNumber;
		this.text = text;
		this.evento = evento;
		//this.user = user;
		this.date = date;
	}*/
	
	public Comentario(String text, Event evento, Usuario user, String date) {
		super();
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
