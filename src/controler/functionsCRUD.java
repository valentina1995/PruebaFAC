package controler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JComboBox;

import model.Evento;
import model.Room;

public class functionsCRUD implements CRUD {
	Room room;

	@Override
	public boolean crearEvento(Evento event) {
		boolean insert = false;
        Connection conn = null;
        Statement stmt = null;
		try {
            conn = connectioDB.connectioDB("sa", "1234");
            stmt = conn.createStatement();
            String sentencia = "insert into event (name_event, date_event, num_people, description, id_room ) values('" + event.getNombre()+ "', '" + event.getFecha()+ "', " + event.getCdtPersonas()+ ", '" + event.getDescripcion()+ "', " + event.getIdSalon()+ ");";
            stmt.execute(sentencia);
            insert = true;
        } catch (SQLException ex) {
            Logger.getLogger(functionsCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
		return insert;
	}

	@Override
	public boolean modificarEvento(Evento event, int id) {
		boolean insert = false;
        Connection conn = null;
        Statement stmt = null;
		try {
            conn = connectioDB.connectioDB("sa", "1234");
            stmt = conn.createStatement();
            String sentencia = "update event set  date_event = '" + event.getFecha()+ "' where id_event = "+id+";";
            stmt.execute(sentencia);
            insert = true;
        } catch (SQLException ex) {
            Logger.getLogger(functionsCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
		return insert;
	}

	@Override
	public ArrayList<Evento> listarEventos() {
			Connection conn = null;
	        Statement stmt = null;
	        ArrayList<Evento> event = new ArrayList<Evento>();
	        conn = connectioDB.connectioDB("sa", "1234");
	        try {
	            stmt = conn.createStatement();
	            String consulta = "select * from event order by date_event";
	            ResultSet resultado = stmt.executeQuery(consulta);
	            while(resultado.next()) {
	            	event.add(new Evento(resultado.getInt("id_event"),resultado.getString("name_event"),resultado.getString("date_event"), resultado.getInt("num_people"),resultado.getString("description"),resultado.getInt("id_room")));
	            }
	            

	        } catch (SQLException ex) {
	            Logger.getLogger(functionsCRUD.class.getName()).log(Level.SEVERE, null, ex);

	        }
			return event;
	}
	
	public ArrayList<Room> chargeRoom() {
        Connection conn = null;
        Statement stmt = null;
        ArrayList<Room> room = new ArrayList<Room>();
        conn = connectioDB.connectioDB("sa", "1234");
        try {
            stmt = conn.createStatement();
            String consulta = "select * from room order by id_room";
            ResultSet resultado = stmt.executeQuery(consulta);
            while(resultado.next()) {
            	room.add(new Room(resultado.getInt("id_room"),resultado.getString("name_room")));
            }
            

        } catch (SQLException ex) {
            Logger.getLogger(functionsCRUD.class.getName()).log(Level.SEVERE, null, ex);

        }
		return room;
	}
	
	public ArrayList<Date> fullDate(int idRoom){
		    Connection conn = null;
	        Statement stmt = null;
	        ArrayList<Date> dates = new ArrayList<Date>();
	        conn = connectioDB.connectioDB("sa", "1234");
	        try {
	            stmt = conn.createStatement();
	            String consulta = "select * from event where id_room = "+idRoom;
	            ResultSet resultado = stmt.executeQuery(consulta);
	            while(resultado.next()) {
	            	dates.add(resultado.getDate("date_event"));
	            }
	            

	        } catch (SQLException ex) {
	            Logger.getLogger(functionsCRUD.class.getName()).log(Level.SEVERE, null, ex);

	        }
		
		return dates;
		
	}
	public int getId(String name) {
		int id = 0;
		Connection conn = null;
        Statement stmt = null;
        conn = connectioDB.connectioDB("sa", "1234");
        try {
            stmt = conn.createStatement();
            String consulta = "select id_room from room where name_room = '"+name+"'";
            ResultSet resultado = stmt.executeQuery(consulta);
            while(resultado.next()) {
            	id = resultado.getInt("id_room");
            }
            

        } catch (SQLException ex) {
            Logger.getLogger(functionsCRUD.class.getName()).log(Level.SEVERE, null, ex);

        }
		
		return id;
		
	}
	
	public int getIdEvent(String name) {
		int id = 0;
		Connection conn = null;
        Statement stmt = null;
        conn = connectioDB.connectioDB("sa", "1234");
        try {
            stmt = conn.createStatement();
            String consulta = "select id_event from event where name_event = '"+name+"'";
            ResultSet resultado = stmt.executeQuery(consulta);
            while(resultado.next()) {
            	id = resultado.getInt("id_event");
            }
            

        } catch (SQLException ex) {
            Logger.getLogger(functionsCRUD.class.getName()).log(Level.SEVERE, null, ex);

        }
		
		return id;
		
	}
	
	public ArrayList<Evento> chargeEvents(){
		Connection conn = null;
        Statement stmt = null;
        ArrayList<Evento> event = new ArrayList<Evento>();
        conn = connectioDB.connectioDB("sa", "1234");
        try {
            stmt = conn.createStatement();
            String consulta = "select * from event order by date_event";
            ResultSet resultado = stmt.executeQuery(consulta);
            while(resultado.next()) {
            	event.add(new Evento(resultado.getString("name_event"),resultado.getString("date_event"), resultado.getInt("num_people"),resultado.getString("description"),resultado.getInt("id_room")));
            }
            

        } catch (SQLException ex) {
            Logger.getLogger(functionsCRUD.class.getName()).log(Level.SEVERE, null, ex);

        }
		return event;
	
		
	}
	
	public String getNameRoom(int id) {
		String name = null;
		Connection conn = null;
        Statement stmt = null;
        conn = connectioDB.connectioDB("sa", "1234");
        try {
            stmt = conn.createStatement();
            String consulta = "select name_room from room where id_room = "+id;
            ResultSet resultado = stmt.executeQuery(consulta);
            while(resultado.next()) {
            	name = resultado.getString("name_room");
            }
            

        } catch (SQLException ex) {
            Logger.getLogger(functionsCRUD.class.getName()).log(Level.SEVERE, null, ex);

        }
		
		return name;
		
	}

	@Override
	public boolean borrarEvento(int id) {
		boolean dlt = false;
		Connection conn = null;
        Statement stmt = null;
        conn = connectioDB.connectioDB("sa", "1234");
        try {
            stmt = conn.createStatement();
            String sentencia = "delete from event where id_event = "+id;
            stmt.execute(sentencia);
            dlt = true;
        } catch (SQLException ex) {
            Logger.getLogger(functionsCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
		return dlt;
	}

}
