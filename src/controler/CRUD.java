package controler;
import java.util.ArrayList;

import model.*;


public interface CRUD {
	public boolean crearEvento(Evento event);
	public boolean modificarEvento(Evento event, int id);
	public ArrayList<Evento> listarEventos();
	public boolean borrarEvento(int id);
	
	

}
