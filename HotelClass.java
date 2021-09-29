
import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

//class version
public class HotelClass {
	static ArrayList<Room> waitingQueue;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Hotel hotel = new Hotel(6);
		waitingQueue = new ArrayList<Room>();
		while (true) {
			System.out.println("WELCOME TO THE HOTEL");
			System.out.println("\n");
			System.out.println("-------- MENU --------");
			System.out.println("\n");
			System.out.println("A: Press A to add a customer to a room");
			System.out.println("V: Press V to view all rooms");
			System.out.println("E: Press E to display empty rooms");
			System.out.println("D: Press D to delete customer from a room");
			System.out.println("F: Press F to find room from customer name");
			System.out.println("S: Press S to store program data to a file");
			System.out.println("L: Press L to load program data from a file");
			System.out.println("O: Press O to view guests in alphabetical order by name");
			System.out.println("\n");
			System.out.print("Please enter your choice ");
			String choice = scanner.next();
			System.out.println("----------------------------");
			switch (choice) {
			case "A":
				AddCustomerToRoom(hotel, scanner);
				break;
			case "V":
				ViewAllRooms(hotel);
				break;
			case "E":
				ViewEmptyRooms(hotel);
				break;
			case "D":
				DeleteCustomerFromRoom(hotel, scanner);
				break;
			case "F":
				FindRoomFromCustomerName(hotel, scanner);
				break;
			case "S":
				SaveProgramDataToFile(hotel);
				break;
			case "L":
				hotel = LoadProgramDataFromFile();
				break;
			case "O":
				OrderByName(hotel);
				break;
			default:
				System.out.println("The choice you made is wrong!");
				scanner.close();
				return;
			}
			System.out.println("------------------------------------");
			System.out.println("\n");
		}
	}

//Alphabetical order method
	private static void OrderByName(Hotel hotelRef) {
		Room temp;
		Hotel hotel = hotelRef;
		for (int i = 0; i < hotel.getRooms().length; i++) {
			for (int j = i + 1; j < hotel.getRooms().length; j++) {
				if (hotel.getRooms()[i] != null && hotel.getRooms()[j] != null)
					if (hotel.getRooms()[i].getName().compareTo(hotel.getRooms()[j].getName()) > 0) {
						Room[] rooms = hotel.getRooms();
						temp = rooms[i];
						rooms[i] = rooms[j];
						rooms[j] = temp;
						hotel.setRooms(rooms);
					}
			}
		}
		for (int x = 0; x < hotelRef.getRooms().length; x++) {
			if (hotel.getRooms()[x] != null)
				System.out.println(hotel.getRooms()[x].getName());
		}

	}

//Saving data to a file method
	private static void SaveProgramDataToFile(Hotel hotelRef) {
		try {
			File myObj = new File("data");
			myObj.createNewFile();
			ObjectOutputStream ois = new ObjectOutputStream(new FileOutputStream("data"));
			ois.writeObject(hotelRef);
			ois.close();
			System.out.println("File has been created: " + myObj.getName());
		} catch (IOException e) {
			System.out.println("Error");
			e.printStackTrace();
		}
	}

//loading data from a file method
	private static Hotel LoadProgramDataFromFile() {
		try {
			ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream("data"));
			Hotel hotelRef = (Hotel) objectIn.readObject();
			objectIn.close();
			System.out.println("Note that the data has been read from the file!");
			return hotelRef;
		} catch (Exception e) {
			System.out.println("Error!!");
			e.printStackTrace();
			return null;
		}
	}

//Finding customers room by name method
	private static void FindRoomFromCustomerName(Hotel hotelRef, Scanner input) {
		System.out.print("Please enter customer name: ");
		String name = input.next();
		for (int i = 0; i < hotelRef.getRooms().length; i++) {
			if (hotelRef.getRooms()[i] != null)
				if (hotelRef.getRooms()[i].getName().equals(name)) {
					System.out.println("Room number:" + i);
				}
		}
	}

//Deleting customer from a room method
	private static void DeleteCustomerFromRoom(Hotel hotelRef, Scanner input) {
		System.out.print("Please enter room number: ");
		int roomNum = input.nextInt();
		if (roomNum >= hotelRef.getRooms().length)
			System.out.println("Room does not exsist !");
		else
			hotelRef.getRooms()[roomNum] = null;
		if (!waitingQueue.isEmpty()) {
			Room[] rooms = hotelRef.getRooms();
			Room room = waitingQueue.remove(waitingQueue.size() - 1);
			rooms[roomNum] = room;
			hotelRef.setRooms(rooms);
			System.out.println("Adding " + room.getName() + " in the waiting list");
		}
	}

//Viewing empty rooms method
	private static void ViewEmptyRooms(Hotel hotelRef) {
		for (int x = 0; x < hotelRef.getRooms().length; x++) {
			if (hotelRef.getRooms()[x] == null)
				System.out.println("room " + x + " is empty");
		}
	}

//Viewing all rooms
	private static void ViewAllRooms(Hotel hotelRef) {
		hotelRef.printDetails();
	}

//Adding customer to a room method and circular queue
	private static void AddCustomerToRoom(Hotel hotelRef, Scanner input) {
		int roomNum = 0;

		while (roomNum < hotelRef.getRooms().length) {
			Boolean isFull = true;
			for (int i = 0; i < hotelRef.getRooms().length; i++) {
				if (hotelRef.getRooms()[i] == null)
					isFull = false;
			}
			if (!isFull) {
				System.out.println("Please enter the room number (0-" + (hotelRef.getRooms().length - 1) + ") or "
						+ hotelRef.getRooms().length + " to stop ");
				roomNum = input.nextInt();
				if (roomNum >= hotelRef.getRooms().length)
					break;
			} else
				System.out.println("Sorry all of the rooms have been occupied Your been added to the waiting queue.");
			System.out.println("The person who's doing the payment please enter the details below");
			System.out.println("--------------------------------------");
			System.out.println("\n");
			System.out.println("Please enter room name : " + roomNum + " :");
			String roomName = input.next();
			System.out.println("Please enter the number of guests : ");
			String guests = input.next();
			System.out.println("Please enter your first name : ");
			String fname = input.next();
			System.out.println("Please enter your second name : ");
			String sname = input.next();
			System.out.println("Please enter your credit card number : ");
			String creditCardNo = input.next();
			Person person = new Person(fname, sname, creditCardNo);
			Room[] rooms = hotelRef.getRooms();
			Room room = new Room(roomName, guests, person);
			rooms[roomNum] = room;
			if (!isFull)
				hotelRef.setRooms(rooms);
			else {
				waitingQueue.add(room);
				return;
			}
		}
	}
}
