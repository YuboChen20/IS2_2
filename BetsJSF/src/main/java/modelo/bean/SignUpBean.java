package modelo.bean;

import java.util.ArrayList;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import modelo.dominio.Usuario;


public class SignUpBean {
	private String nombre;
	private String password;
	private String numTarjeta;
	private String correoElec;
	private BLFacade appFacadeInterface;
	private String mg;
	
	public SignUpBean() {
		appFacadeInterface=new BLFacadeImplementation(DataAccess.getInstance());
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


	public String getMg() {
		return mg;
	}

	public void setMg(String mg) {
		this.mg = mg;
	}

	public String comprobar() {
		Usuario us = this.appFacadeInterface.AddUser(nombre, password, numTarjeta, correoElec);
		if(us!=null) {
			this.vaciar();
			return "OkSignUp";
		}
		this.setMg("Usuario ya existente");
		return " ";
	}
	public String salir() {
		this.vaciar();
		return "OkSignUp";
	}
	public void vaciar() {
		this.setNombre("");
		this.setPassword("");
		this.setNumTarjeta("");
		this.setCorreoElec("");
		this.setMg("");
	}
	
}
