package modelon.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.SelectEvent;


import domain.*;

import businessLogic.*;
import dataAccess.*;


public class QueryQuestionBean {

	private Date fecha;
	private Event even;
	private Question quest;
	private BLFacade appFacadeInterface;
	
	private static List<Event> eventos=new ArrayList<Event>();
	private static List<Question> quests=new ArrayList<Question>();
	
	public QueryQuestionBean() {
		 appFacadeInterface=new BLFacadeImplementation(new DataAccess(true));
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
		 System.out.println("El tipo del evento: "+even.getEventNumber()+"/"+even.getDescription());
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
		 FacesContext.getCurrentInstance().addMessage("miForm: mensajes",
		 new FacesMessage("Fecha escogida: "+event.getObject()));
		 
		 
		 this.setEventos(appFacadeInterface.getEvents((Date)event.getObject()));
	
	}
	public static Event getObject(String even) {
		 for (Event t: eventos){
		 if (even.equals(t.getDescription()))
		 return t;}
		 return null;
		}

	public void onEventSelect(SelectEvent event) {
		this.even=(Event)event.getObject();
		FacesContext.getCurrentInstance().addMessage("miForm:escogido",
		 new FacesMessage("Evento escogido : Nº"+even.getEventNumber()+"["+even.getDescription()+"]"));
		this.setQuests(even.getQuestions());
		}


}
