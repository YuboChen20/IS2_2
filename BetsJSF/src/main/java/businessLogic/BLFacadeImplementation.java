package businessLogic;

import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import dataAccess.DataAccess;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import modelo.dominio.Comentario;
import modelo.dominio.Event;
import modelo.dominio.Question;
import modelo.dominio.Usuario;

/**
 * It implements the business logic as a web service.
 */

public class BLFacadeImplementation  implements BLFacade {
	public DataAccess dbManager;

	
    public BLFacadeImplementation(DataAccess da)  {
		
		dbManager=da;		
	}
	

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */

   public Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist{
	   
	    //The minimum bed must be greater than 0
		Question qry=null;
		
	    
		if(new Date().compareTo(event.getEventDate())>0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
				
		
		 qry=dbManager.createQuestion(event,question,betMinimum);		

		
		return qry;
   };
	
	/**
	 * This method invokes the data access to retrieve the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
  
	public List<Event> getEvents(Date date)  {	
		List<Event>  events=dbManager.getEvents(date);
		return events;
	}

    
	/**
	 * This method invokes the data access to retrieve the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	public List<Date> getEventsMonth(Date date) {
	
		List<Date> dates=dbManager.getEventsMonth(date);

		return dates;
	}
	
	
	/**
	 * Conseguir usuario de la base de datos
	 * @param name, nombre del usuario buscado
	 * @return usuario buscado
	 */
	public Usuario getUser(String name){
		return this.dbManager.getUser(name);
	}
	
	/**
	 * Crear un usuario y guardarlo
	 * @param name, nombre del usuario
	 * @param pass, contraseña del usuario
	 * @param card, tarjeta de credito
	 * @param correo, el correo del usuario
	 * @return usuario creado
	 */
	public Usuario AddUser(String name,String pass,String card,String correo) {
		return this.dbManager.AddUser(name, pass, card, correo);
	}
	
	/**
	 * Crear un comentario y guardarlo
	 * @param com, comentario a crear y guardar
	 * @return comentario creado
	 */
	public Comentario createComentario(Comentario com){
		   
	    //The minimum bed must be greater than 0
		Comentario coment=null;
		
	    
		//if(new Date().compareTo(event.getEventDate())>0)
			//throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
				
		
		 coment=dbManager.createComentario(com);		

		
		return coment;
 };

/**
 * Consigue todos los comentarios de un evento
 * @param evento, el evento que corresponde
 * @return coleccion de comentarios
 */
 public List<Comentario> getComentarios(Event evento){
	 List<Comentario> lista = null;
	 lista=dbManager.getComentarios(evento);
	 return lista;
 }

	/**
	 * This method invokes the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	

	 public void initializeBD() {   
		dbManager.initializeDB();

	}


}

