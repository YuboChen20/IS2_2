package modelon.bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.SelectEvent;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.*;
import gui.MainGUI;
import businessLogic.*;
import dataAccess.*;


public class QueryQuestionBean {

	private Date fecha;
	private Event even;
	private static List<Event> eventos=new ArrayList<Event>();
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
		 FacesContext.getCurrentInstance().addMessage(null,
		 new FacesMessage("Fecha escogida: "+event.getObject()));
		 BLFacade appFacadeInterface=new BLFacadeImplementation(new DataAccess(true));
		 this.setEventos(appFacadeInterface.getEvents((Date)event.getObject()));
		 System.out.println("---------------");
		 System.out.println(eventos.toString());
	}
	public static Event getObject(String even) {
		 for (Event t: eventos){
		 if (even.equals(t.getDescription()))
		 return t;}
		 return null;
		}
	public void listener(AjaxBehaviorEvent event) {
		 FacesContext.getCurrentInstance().addMessage(null,
		 new FacesMessage("El tipo del usuario:"+even.getEventNumber()+"/"+even.getDescription()));
		}
	public void onEventSelect(SelectEvent event) {
		this.even=(Event)event.getObject();
		FacesContext.getCurrentInstance().addMessage("miForm:mensajes",
		 new FacesMessage("El tipo del usuario (tabla):"+even.getEventNumber()+"/"+even.getDescription()));
		}


}
