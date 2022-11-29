package modelon.bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.SelectEvent;

import configuration.UtilDate;
import domain.Event;

public class QueryQuestionBean {

	private Date fecha;
	private Event even;
	private static List<Event> eventos=new ArrayList<Event>();
	
	public QueryQuestionBean() { 
		   Calendar today = Calendar.getInstance();	   
		   int month=today.get(Calendar.MONTH);
		   month+=1;
		   int year=today.get(Calendar.YEAR);
		   if (month==12) { month=0; year+=1;}  
			eventos.add(new Event(1, "Cádiz-Rayo Vallecano", UtilDate.newDate(year,month,17)));
			eventos.add(new Event(2, "Real Betis-Levante", UtilDate.newDate(year,month,17)));
			eventos.add(new Event(3, "Real Sociedad-Real Madrid", UtilDate.newDate(year,month,17)));
			eventos.add(new Event(4, "Atlético de Madrid-Athletic de Bilbao", UtilDate.newDate(year,month,1)));
			eventos.add(new Event(5, "Elche-Barcelona", UtilDate.newDate(year,month,1)));
			for (Event e :eventos) {
				System.out.println(e.toString());
			}
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
		 FacesContext.getCurrentInstance().addMessage(null,
		 new FacesMessage("Fecha escogida: "+event.getObject()));
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
