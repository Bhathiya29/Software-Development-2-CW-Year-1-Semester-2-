import java.io.Serializable;

public class Hotel implements Serializable {

	private static final long serialVersionUID = 1L;
	private Room[] rooms;

	public Room[] getRooms() {
		return rooms;
	}

	public void setRooms(Room[] rooms) {
		this.rooms = rooms;
	}

	public Hotel(int number) {
		rooms = new Room[number];
	}

	//printing whether occupied or empty
	public void printDetails() {
		for (int i = 0; i < rooms.length; i++) {
			if (rooms[i] == null)
				System.out.println(" Room " + i + " is empty");
			else {
				System.out.println(" Room " + i + " is Occupied by :");
				rooms[i].printDetails();
			}
		}
	}

}
