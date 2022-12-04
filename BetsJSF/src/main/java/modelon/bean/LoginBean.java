package modelon.bean;

import java.util.Locale;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import configuration.ConfigXML;
import dataAccess.DataAccess;

public class LoginBean {
	private String nombre;
	private String password;

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

	public String comprobar() {
	 if(nombre.equals("Admin")){
	 return "okAdmin";
	 }
	 else {
	 return "okUser";
	 }
	 } 
  
  
}
