
package modelo.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;

import dataAccess.DataAccess;
import modelo.dominio.Event;
import modelo.dominio.Question;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

public class CreateQuestionBean {
	private String question;
	private double betMinimum;
	private Event ev;
	private BLFacade appFacadeInterface;
	private Question q;
	private String mensaje;
	
	
	private Date fecha;
	private static List<Event> eventos=new ArrayList<Event>();
	private static List<Question> questions=new ArrayList<Question>();
	
	public CreateQuestionBean() {

		appFacadeInterface=new BLFacadeImplementation(new DataAccess(false));
		this.mensaje="";
		
	}
	
	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public  List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		CreateQuestionBean.questions = questions;
	}

	public Question getQ() {
		return q;
	}

	public void setQ(Question q) {
		this.q = q;
	}

	
	
	
	
	public List<Question> getQuests() {
		return questions;
	}

	public void setQuests(List<Question> questions) {
		this.questions = questions;
	}
	
	public Event getEv() {
		return ev;
	}

	public void setEv(Event ev) {
		this.ev = ev;
	}


	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public double getBetMinimum() {
		return betMinimum;
	}

	public void setBetMinimum(double betMinimum) {
		this.betMinimum = betMinimum;
	}
	
	public void create() {
		
		if(this.question!="" && this.betMinimum>(float) 1 && this.ev!=null) {
			try {
				Question q= appFacadeInterface.createQuestion(ev,question,(float)betMinimum);
				this.questions.add(q);
				this.mensaje="";
			} catch (EventFinished e) {
				this.mensaje="Evento ya finalizado, no se puede crear pregunta";
			} catch (QuestionAlreadyExist e) {
				this.mensaje="Esa pregunta ya existe para el evento";
			}

		}
		
	
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha; 
	}
	public void onDateSelect(SelectEvent event) {
		 this.ev=null;
		 this.questions=null;
		 
		 this.setEventos(appFacadeInterface.getEvents((Date)event.getObject()));
	
	}
	public List<Event> getEventos() {
		return eventos;
	}

	public void setEventos(List<Event> eventos) {
		CreateQuestionBean.eventos = eventos;
	}

	public static Event getObject(String even) {
		 for (Event t: eventos){
		 if (even.equals(t.getDescription()))
		 return t;}
		 return null;
		}

	public void onEventSelect(SelectEvent event) {
		this.ev=(Event)event.getObject();
		FacesContext.getCurrentInstance().addMessage("miForm:escogido",
		 new FacesMessage("Evento escogido : Nº"+ev.getEventNumber()+"["+ev.getDescription()+"]"));
		this.setQuests(ev.getQuestions());
		}

	
}





