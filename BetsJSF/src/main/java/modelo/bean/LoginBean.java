package modelo.bean;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import modelo.dominio.Usuario;

public class LoginBean {
	private String nombre;
	private String password;
	private BLFacade appFacadeInterface;
	private String messag;

	public LoginBean() {
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
	public String getMessag() {
		return messag;
	}
	public void setMessag(String messag) {
		this.messag = messag;
	}
	public String comprobar() {
		Usuario user = appFacadeInterface.getUser(nombre);
		FacesContext ectx= FacesContext.getCurrentInstance();
		HttpServletRequest request=(HttpServletRequest) ectx.getExternalContext().getRequest();
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("Usuario", user);
		if(user!=null) {
			if(user.getPassword().equals(password)) {
				 if(user.isAdmin()){
					 return "okAdmin";
				 }
				 else {
					 return "okUser";
				 }
			}
			this.setMessag("Contraseña incorrecta");
			return "";
		}
		this.setMessag("Error el usuario no Existe");
		return "";	 
	} 
	
	public BLFacade getAppFacadeInterface() {
		return appFacadeInterface;
	}
	public void setAppFacadeInterface(BLFacade appFacadeInterface) {
		this.appFacadeInterface = appFacadeInterface;
	}
	public String registrarse() {
		return "SignUpOk";
	} 
  
  
}
