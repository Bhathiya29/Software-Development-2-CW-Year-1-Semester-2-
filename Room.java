import java.io.Serializable;

// room class
public class Room implements Serializable {

	private static final long serialVersionUID = 1L;
	private String noOfGuests;
	private Person person;
	private String name;

	public String getNuOfGuests() {
		return noOfGuests;
	}

	public void setNuOfGuests(String nuOfGuests) {
		this.noOfGuests = nuOfGuests;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Room(String name,String noOfGuest, Person person) {
		this.name = name;
		this.noOfGuests = noOfGuest;
		this.person = person;
	}
//printing details out room name and number of guests
	public void printDetails() {
		System.out.println(" Name of room : " + name);
		System.out.println(" Number of guests : " + noOfGuests);
		person.printDetails();
	}
}
