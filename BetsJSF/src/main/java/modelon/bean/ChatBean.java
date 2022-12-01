package modelon.bean;

import java.util.ArrayList;

import configuration.UtilDate;
import domain.Event;
import modelon.dominio.Comentario;
import modelon.dominio.Usuario;

public class ChatBean {
	private String text;
	private ArrayList<Comentario> chat = new ArrayList<Comentario>();
	private String ult;
	
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
	
	

	public String getUlt() {
		return ult;
	}

	public void setUlt(String ult) {
		this.ult = ult;
	}

	public void sendComment() {
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
		
	}

	
}
