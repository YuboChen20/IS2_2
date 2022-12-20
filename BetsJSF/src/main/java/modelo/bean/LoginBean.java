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
	
	/**
	 * Comprobar que la cuenta sea la correcta, si el usuario no existe, la contraseña es incorrecta, saltara el error, si no, confirmara su existencia.
	 * @return string que determina su existencia.
	 */
	public String comprobar() {
		Usuario user = appFacadeInterface.getUser(nombre);
		guardarUsuario(user);
		
		if(user!=null) {
			if(user.getPassword().equals(password)) {
				this.vaciar();
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
	
	/**
	 * Guardar el usuario en caso de confirmar su existencia en la sesion, para luego poder acceder a el via sesion.
	 * @param user, usuario a guardar
	 */
	public void guardarUsuario(Usuario user) {
		FacesContext ectx= FacesContext.getCurrentInstance();
		HttpServletRequest request=(HttpServletRequest) ectx.getExternalContext().getRequest();
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("Usuario", user);
	} 
	
	public BLFacade getAppFacadeInterface() {
		return appFacadeInterface;
	}
	public void setAppFacadeInterface(BLFacade appFacadeInterface) {
		this.appFacadeInterface = appFacadeInterface;
	}
	
	/**
	 * Boton para pasar a la pagina de registrarse
	 * @return string para confirmarlo
	 */
	public String registrarse() {
		this.vaciar();
		return "SignUpOk";
	}
	/**
	 * Funcion que vacia los espacios disponibles para rellenar
	 */
	public void vaciar() {
		this.setNombre("");
		this.setPassword("");
		this.setMessag("");
	}
  
  
}
