package modelo.dominio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Usuario {
	@Id
	private String userName;
	private String password;
	private String cardCode;
	private String correo;
	private boolean admin;
	private double dinero;
	@OneToMany(targetEntity=Comentario.class,mappedBy="usuario",cascade=CascadeType.ALL, fetch=FetchType.EAGER) 
	private List<Comentario> comentarios=new ArrayList<Comentario>();
	

	
	public Usuario() {
		
	}
	
	public Usuario(String userName, String password, String cardCode, String correo, boolean admin) {
		super();
		this.userName = userName;
		this.password = password;
		this.cardCode = cardCode;
		this.correo = correo;
		this.admin = admin;
		this.dinero= 50;
	}
	public String getUserName() {
		return userName;
	}
	public Comentario addComentario(Comentario com)  {
        comentarios.add(com);
        return com;
	}
	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCardCode() {
		return cardCode;
	}
	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public double getDinero() {
		return dinero;
	}
	public void setDinero(double dinero) {
		this.dinero = dinero;
	}

	
}
