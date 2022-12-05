package dataAccess;

import java.io.File;
import java.util.ArrayList;
//hello
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import configuration.UtilDate;
import modelo.dominio.*;

import exceptions.QuestionAlreadyExist;
import modelo.HibernateUtil;
import org.hibernate.Session; 


/**
 * It implements the data access to the objectDb database
 */
public class DataAccess {
	
	private static DataAccess instance;
	
	private DataAccess() {
		 initializeDB();
	}
	
	public static DataAccess getInstance() {
		if (instance==null) instance=  new DataAccess();
		return instance;
	}

	
	
	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeDB(){
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction(); 
		try {

			
		   Calendar today = Calendar.getInstance();
		   
		   int month=today.get(Calendar.MONTH);
		   month+=1;
		   int year=today.get(Calendar.YEAR);
		   if (month==12) { month=0; year+=1;}  
	    
			Event ev1=new Event(1, "Atlético-Athletic", UtilDate.newDate(year,month,17));
			Event ev2=new Event(2, "Eibar-Barcelona", UtilDate.newDate(year,month,17));
			Event ev3=new Event(3, "Getafe-Celta", UtilDate.newDate(year,month,17));
			Event ev4=new Event(4, "Alavés-Deportivo", UtilDate.newDate(year,month,17));
			Event ev5=new Event(5, "Español-Villareal", UtilDate.newDate(year,month,17));
			Event ev6=new Event(6, "Las Palmas-Sevilla", UtilDate.newDate(year,month,17));
			Event ev7=new Event(7, "Malaga-Valencia", UtilDate.newDate(year,month,17));
			Event ev8=new Event(8, "Girona-Leganés", UtilDate.newDate(year,month,17));
			Event ev9=new Event(9, "Real Sociedad-Levante", UtilDate.newDate(year,month,17));
			Event ev10=new Event(10, "Betis-Real Madrid", UtilDate.newDate(year,month,17));

			Event ev11=new Event(11, "Atletico-Athletic", UtilDate.newDate(year,month,1));
			Event ev12=new Event(12, "Eibar-Barcelona", UtilDate.newDate(year,month,1));
			Event ev13=new Event(13, "Getafe-Celta", UtilDate.newDate(year,month,1));
			Event ev14=new Event(14, "Alavés-Deportivo", UtilDate.newDate(year,month,1));
			Event ev15=new Event(15, "Español-Villareal", UtilDate.newDate(year,month,1));
			Event ev16=new Event(16, "Las Palmas-Sevilla", UtilDate.newDate(year,month,1));
			

			Event ev17=new Event(17, "Málaga-Valencia", UtilDate.newDate(year,month,28));
			Event ev18=new Event(18, "Girona-Leganés", UtilDate.newDate(year,month,28));
			Event ev19=new Event(19, "Real Sociedad-Levante", UtilDate.newDate(year,month,28));
			Event ev20=new Event(20, "Betis-Real Madrid", UtilDate.newDate(year,month,28));
			
			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;
					
		
			q1=ev1.addQuestion("¿Quién ganará el partido?",1);
			q2=ev1.addQuestion("¿Quién meterá el primer gol?",2);
			q3=ev11.addQuestion("¿Quién ganará el partido?",1);
			q4=ev11.addQuestion("¿Cuántos goles se marcarán?",2);
			q5=ev17.addQuestion("¿Quién ganará el partido?",1);
			q6=ev17.addQuestion("¿Habrá goles en la primera parte?",2);
			
			
			
			Usuario us = new Usuario("Yubo","123",null,null,true);
			Usuario us2 = new Usuario("Silvia","123",null,null,true);
			Usuario us3 = new Usuario("Carlos","123",null,null,true);
			Usuario us4 = new Usuario("User","123","123456789012","usuariomasguapo@gmail.com",false);
			
			session.persist(us);
			session.persist(us2);
			session.persist(us3);
			session.persist(us4);
			
			session.save(q1);
			session.save(q2);
			session.save(q3);
			session.save(q4);
			session.save(q5);
			session.save(q6);
	
	        
			session.save(ev1);
			session.save(ev2);
			session.save(ev3);
			session.save(ev4);
			session.save(ev5);
			session.save(ev6);
			session.save(ev7);
			session.save(ev8);
			session.save(ev9);
			session.save(ev10);
			session.save(ev11);
			session.save(ev12);
			session.save(ev13);
			session.save(ev14);
			session.save(ev15);
			session.save(ev16);
			session.save(ev17);
			session.save(ev18);
			session.save(ev19);
			session.save(ev20);			
			
			session.getTransaction().commit();
			System.out.println("Db initialized");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	public Question createQuestion(Event event, String question, float betMinimum) throws  QuestionAlreadyExist {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		System.out.println(">> DataAccess: createQuestion=> event= "+event+" question= "+question+" betMinimum="+betMinimum);
		System.out.println(session+" "+event);
		
			session.beginTransaction(); 
			Event ev= getEvent(event);
				
			if (ev.DoesQuestionExists(question)) throw new QuestionAlreadyExist();
			
			Question q = ev.addQuestion(question, betMinimum);
			//db.persist(q);
			session.save(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
			session.save(q);		// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
			session.getTransaction().commit();
			return q;
		
	}
	
	public Event getEvent(Event e) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		org.hibernate.Query q = session.createQuery("from Event e where e.eventNumber=:ev");
        q.setParameter("ev", e.getEventNumber());
        List<Event> result=q.list();
        Event ev=result.get(0);
        return ev;
	}
	
	
	/**
	 * This method retrieves from the database the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public List<Event> getEvents(Date date) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		System.out.println(">> DataAccess: getEvents");
		List<Event> res = new ArrayList<Event>();	
		session.beginTransaction(); 
		org.hibernate.Query query = session.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=:e");   
		query.setParameter("e", date);	
		List<Event> events = query.list();
		session.getTransaction().commit();
	 	 for (Event ev:events){
	 	   System.out.println(ev.toString());		 
		   res.add(ev);
		  }
	 	return res;
	}
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	public List<Date> getEventsMonth(Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		List<Date> res = new ArrayList<Date>();	
		
		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);
				
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction(); 
		org.hibernate.Query query = session.createQuery("SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN :uno and :dos");   
		query.setParameter("uno", firstDayMonthDate);
		query.setParameter("dos", lastDayMonthDate);
		List<Date> dates = query.list();
		session.getTransaction().commit();
	 	 for (Date d:dates){
	 	   System.out.println(d.toString());		 
		   res.add(d);
		  }
	 	return res;
	}
	

public boolean existQuestion(Event event, String question) {
	System.out.println(">> DataAccess: existQuestion=> event= "+event+" question= "+question);
	Event ev= getEvent(event);
	return ev.DoesQuestionExists(question);
	
}

public Usuario getUser(String name) {
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction(); 
	org.hibernate.Query q = session.createQuery("from Usuario e where e.userName=:nn");
    q.setParameter("nn", name);
    List<Usuario> result=q.list();
    if (result.isEmpty()) return null;
    Usuario u=result.get(0);
    session.getTransaction().commit();
    return u;
}
	
public Usuario AddUser(String name,String pass,String card,String correo) {
	Usuario u2 = this.getUser(name);
	if(u2!=null) return null;
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction(); 
	Usuario u = new Usuario(name,pass,card,correo,false);
	session.save(u);
    session.getTransaction().commit();
    return u;
}

public Comentario createComentario(Comentario com) {
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	System.out.println(">> DataAccess: createQuestion=> event= "+com.getEvento()+" comentario= "+com);
	System.out.println(session+" "+com.getEvento());
	
		session.beginTransaction(); 
		Event ev= getEvent(com.getEvento());
		ev.addComentario(com);
			
		//if (ev.DoesQuestionExists(question)) throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));
		
		//Question q = ev.addQuestion(question, betMinimum);
		//db.persist(q);
		session.save(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
		session.save(com);		// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
		session.getTransaction().commit();
		return com;
	
}

public List<Comentario> getComentarios(Event evento){
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction(); 
	org.hibernate.Query q = session.createQuery("from Comentario c where c.evento=:ev");
    q.setParameter("ev", evento);
    List<Comentario> result=q.list();;
    return result;
}


public static void main(String[] args) {
	DataAccess ds= DataAccess.getInstance();
	//Question q=createQuestion(event,question,(float)3.2);
	
	ds.getUser("Hola").toString();
}


}

