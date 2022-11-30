package modelon.dominio;

public class Usuario {
	private String userName;
	private String password;
	private String cardCode;
	private String correo;
	private boolean admin;
	private double dinero;
	
	
	
	
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
