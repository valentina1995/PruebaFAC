package model;

public class Room {
	private int idRoom;
	private String nameRoom;
	
	
	public Room(int idRoom, String nameRoom) {
		super();
		this.idRoom = idRoom;
		this.nameRoom = nameRoom;
	}
	@Override
	public String toString() {
		return "Room [idRoom=" + idRoom + ", nameRoom=" + nameRoom + "]";
	}
	public int getIdRoom() {
		return idRoom;
	}
	public void setIdRoom(int idRoom) {
		this.idRoom = idRoom;
	}
	public String getNameRoom() {
		return nameRoom;
	}
	public void setNameRoom(String nameRoom) {
		this.nameRoom = nameRoom;
	}

}
