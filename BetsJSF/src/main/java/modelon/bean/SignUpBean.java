package modelon.bean;

import java.util.ArrayList;


public class SignUpBean {
	private String nombre;
	private String password;
	private String numTarjeta;
	private String correoElec;
	private ArrayList<String> lista = new ArrayList<String>();
	
	
	
	public SignUpBean() {
		lista.add("Admin");
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNumTarjeta() {
		return numTarjeta;
	}
	public void setNumTarjeta(String numTarjeta) {
		this.numTarjeta = numTarjeta;
	}
	public String getCorreoElec() {
		return correoElec;
	}
	public void setCorreoElec(String correoElec) {
		this.correoElec = correoElec;
	}
	
	public ArrayList<String> getLista() {
		return lista;
	}
	public void setLista(ArrayList<String> lista) {
		this.lista = lista;
	}
	public String comprobar() {
		if(!lista.isEmpty()) {
			if(!lista.contains(nombre)) {
				lista.add(nombre);
				return "OkSignUp";
			}else {
				return "FailSignUp";
			}
		}
		lista.add(nombre);
		return "OkSignUp";
	}
	
}
