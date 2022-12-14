package businessLogic;

import java.util.Vector;
import java.util.Date;
import java.util.List;


import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import modelo.dominio.Comentario;
import modelo.dominio.Event;
import modelo.dominio.Question;
import modelo.dominio.Usuario;

/**
 * Interface that specifies the business logic.
 */

public interface BLFacade  {
	  

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
	public Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist;
	
	
	/**
	 * This method retrieves the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public List<Event> getEvents(Date date);
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	public List<Date> getEventsMonth(Date date);
	
	/**
	 * This method calls the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeBD();


	/**
	 * Conseguir usuario de la base de datos
	 * @param name, nombre del usuario buscado
	 * @return usuario buscado
	 */
	public Usuario getUser(String nombre);
	
	/**
	 * Crear un usuario y guardarlo
	 * @param name, nombre del usuario
	 * @param pass, contraseņa del usuario
	 * @param card, tarjeta de credito
	 * @param correo, el correo del usuario
	 * @return usuario creado
	 */
	public Usuario AddUser(String name,String pass,String card,String correo);

	/**
	 * Crear un comentario y guardarlo
	 * @param com, comentario a crear y guardar
	 * @return comentario creado
	 */
	public Comentario createComentario(Comentario com);
	
	/**
	 * Consigue todos los comentarios de un evento
	 * @param evento, el evento que corresponde
	 * @return coleccion de comentarios
	 */
	public List<Comentario> getComentarios(Event evento);
}
