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
	@ManyToOne(targetEntity=Event.class,fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
	private Event event;
	@ManyToOne(targetEntity=Usuario.class,fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Usuario usuario;

	public Comentario() {}
	
	/*public Comentario(String text, Event evento, String date) {
		super();
		//this.comtNumber = comtNumber;
		this.text = text;
		this.evento = evento;
		//this.user = user;
		this.date = date;
	}*/
	
	public Comentario(String text, Event evento, Usuario user) {
		
		this.text = text;
		this.event = evento;
		this.usuario = user;

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
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event evento) {
		this.event = evento;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario user) {
		this.usuario = user;
	}


	@Override
	public String toString() {
		return usuario.getUserName()+": "+text;
	}
	
	
	
}
