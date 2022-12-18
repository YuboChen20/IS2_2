package modelo.bean;

import java.util.ArrayList;

import configuration.UtilDate;
import modelo.dominio.Event;
import modelo.dominio.Comentario;
import modelo.dominio.Usuario;

public class ChatBean {
	private String text;
	private ArrayList<Comentario> chat = new ArrayList<Comentario>();
	private String ult;
	private Event ev;
	
	public ChatBean() {}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
	public ArrayList<Comentario> getChat() {
		return chat;
	}

	public void setChat(ArrayList<Comentario> chat) {
		this.chat = chat;
	}
	

	public Event getEv() {
		return ev;
	}

	public void setEv(Event ev) {
		this.ev = ev;
	}

	public String getUlt() {
		return ult;
	}

	public void setUlt(String ult) {
		this.ult = ult;
	}

	/*public void sendComment() {
		System.out.println("fgd");
		System.out.println(ev);
		Usuario user = new Usuario("ChicoGuapo", "123", "123456789112", "pepe@pepa.pig", false);
		Event ev1=new Event(1, "Atlético-Athletic", UtilDate.newDate(2001,12,17));
		int num = chat.size();
		Comentario com = new Comentario(num, text,ev1,user,"Mañana");
		this.setText("");
		System.out.println(com);
		chat.add(com);
		if(ult==null) {
			ult=com.toString();
		}else {
			ult=ult+"\n"+com.toString();
		}
		
	}*/

	
}
