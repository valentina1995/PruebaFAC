package model;

import java.util.Date;

public class Evento {
	private int id;
	private String nombre;
	private String fecha;
	private int cdtPersonas;
	private String descripcion;
	private int idSalon;
	
	
	public Evento(String nombre, String fecha, int cdtPersonas, String descripcion, int idSalon) {
		super();
		this.nombre = nombre;
		this.fecha = fecha;
		this.cdtPersonas = cdtPersonas;
		this.descripcion = descripcion;
		this.idSalon = idSalon;
	}
	
	public Evento(int id, String nombre, String fecha, int cdtPersonas, String descripcion, int idSalon) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fecha = fecha;
		this.cdtPersonas = cdtPersonas;
		this.descripcion = descripcion;
		this.idSalon = idSalon;
	}

	public Evento() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public int getCdtPersonas() {
		return cdtPersonas;
	}
	public void setCdtPersonas(int cdtPersonas) {
		this.cdtPersonas = cdtPersonas;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getIdSalon() {
		return idSalon;
	}
	public void setIdSalon(int idSalon) {
		this.idSalon = idSalon;
	}
	@Override
	public String toString() {
		return "Evento [nombre=" + nombre + ", fecha=" + fecha + ", cdtPersonas=" + cdtPersonas + ", descripcion="
				+ descripcion + ", idSalon=" + idSalon + "]";
	}
	

}
