package modelo.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.event.SelectEvent;


import modelo.dominio.Comentario;
import modelo.dominio.Usuario;
import modelo.dominio.Event;
import modelo.dominio.Question;
import businessLogic.*;
import configuration.UtilDate;
import dataAccess.*;


public class QueryQuestionBean {
	private Date fecha;
	private Event even;
	private Question quest;
	private BLFacade appFacadeInterface;
	private String text;
	private String ult;
	private LoginBean loginSession;
	private String userName;
	private String mensaje;
	
	private static List<Event> eventos=new ArrayList<Event>();
	private static List<Question> quests=new ArrayList<Question>();
	private static List<Comentario> chat = new ArrayList<Comentario>();
	
	public QueryQuestionBean() {
		 appFacadeInterface=new BLFacadeImplementation(DataAccess.getInstance());
	}
	
	
	
	
	public LoginBean getLoginSession() {
		return loginSession;
	}




	public void setLoginSession(LoginBean loginSession) {
		this.loginSession = loginSession;
	}




	public String getUserName() {
		return loginSession.getNombre();
	}




	public void setUserName(String userName) {
		this.userName = userName;
	}




	public Question getQuest() {
		return quest;
	}

	public void setQuest(Question quest) {
		this.quest = quest;
	}

	public List<Question> getQuests() {
		return quests;
	}

	public void setQuests(List<Question> quests) {
		this.quests = quests;
	}
	
	
	public Event getEven() {
		 return even;
	}
	public void setEven(Event even) {
		 this.even = even;
	}
	public List<Event> getEventos() {
		 return eventos;
	}
	public void setEventos(List<Event> eventos) {
		 this.eventos = eventos;
	}


	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha; 
	}
	public void onDateSelect(SelectEvent event) {
		this.even=null;
		this.quests=null;
		 
		 this.setEventos(appFacadeInterface.getEvents((Date)event.getObject()));
	
	}
	public static Event getObject(String even) {
		 for (Event t: eventos){
		 if (even.equals(t.getDescription()))
		 return t;}
		 return null;
		}

	public void onEventSelect(SelectEvent event) {
		this.setMensaje("");
		this.even=(Event)event.getObject();
		chat=appFacadeInterface.getComentarios(even);
		System.out.println(chat.isEmpty());
		this.rellenarChat(chat);

		this.setQuests(even.getQuestions());
		}
	
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
	public List<Comentario> getChat() {
		return chat;
	}

	public void setChat(List<Comentario> chat) {
		this.chat = chat;
	}


	public String getUlt() {
		return ult;
	}

	public void setUlt(String ult) {
		this.ult = ult;
	}

	public void sendComment() {
	//System.out.println(this.getUserName());
		FacesContext ectx= FacesContext.getCurrentInstance();
		HttpServletRequest request=(HttpServletRequest) ectx.getExternalContext().getRequest();
		HttpSession httpSession = request.getSession();
		Usuario user = (Usuario) httpSession.getAttribute("Usuario");
		System.out.println(user.getUserName());
		if(even==null) {
			this.setMensaje("No se ha escogido ningún Evento al que añadir el comentario");
		}else {
			this.setMensaje("");
			System.out.println(even);
			Comentario com = new Comentario(text,even, user);
			Comentario com1= appFacadeInterface.createComentario(com);
			Event ev = com1.getEvent();
			this.setText("");
			System.out.println(com1);
			chat=appFacadeInterface.getComentarios(ev);
			this.rellenarChat(chat);
		}
	
}

public String rellenarChat(List<Comentario> comentarios) {
	this.setUlt("");
	for(Comentario com:comentarios) {
		if(this.getUlt().compareTo("")==0) {
			this.setUlt(com.toString());
		}else {
			this.setUlt(this.getUlt()+"\n"+com.toString());
			
		}
	}
	return ult;
}




public String getMensaje() {
	return mensaje;
}




public void setMensaje(String mensaje) {
	this.mensaje = mensaje;
}

public String salir() {
	return "OkSalir";
}
}
