
import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class HotelExample {

	static ArrayList<String[]> waitingQueue;

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String[][] hotel = new String[6][5];
		initialise(hotel);
		while (true) {
			System.out.println("WELCOME TO THE HOTEL");
			System.out.println("\n");
			System.out.println("-------- MENU --------");
			System.out.println("\n");
			System.out.println("A: Press A to add a customer to a room");
			System.out.println("V: Press V to view all rooms");
			System.out.println("E: Press e to display Empty rooms");
			System.out.println("D: Press D to delete customer from a room");
			System.out.println("F: Press F to find room from customer name");
			System.out.println("S: Press S to store program data to a file");
			System.out.println("L: Press L to load program data from a file");
			System.out.println("O: Press O to view guests in alphabetical order by name");
			System.out.println("\n");
			System.out.print("Please enter your choice: ");
			String choice = input.next();
			System.out.println("--------------------------------");
			switch (choice) {
			case "A":
				AddCustomerToRoom(hotel, input);
				break;
			case "V":
				ViewAllRooms(hotel);
				break;
			case "E":
				ViewEmptyRooms(hotel);
				break;
			case "D":
				DeleteCustomerFromRoom(hotel, input);
				break;
			case "F":
				FindRoomFromCustomerName(hotel, input);
				break;
			case "S":
				SaveToFile(hotel);
				break;
			case "L":
				hotel = LoadFromFile();
				break;
			case "O":
				OrderByName(hotel);
				break;
			default:
				System.out.println("Wrong choice !");
				input.close();
				return;
			}
			System.out.println("------------------------------------");
			System.out.println("\n");
		}
	}

//Alphabetical order method
	private static void OrderByName(String hotelRef[][]) {
		String[] temp;
		String[][] hotel = hotelRef;
		for (int i = 0; i < hotel.length; i++) {
			for (int j = i + 1; j < hotel.length; j++) {
				if (hotel[i] != null && hotel[j] != null)
					if (hotel[i][0].compareTo(hotel[j][0]) > 0) {
						temp = hotel[i];
						hotel[i] = hotel[j];
						hotel[j] = temp;
					}
			}
		}
		for (int x = 0; x < hotelRef.length; x++) {
			if (hotel[x] != null)
				System.out.println(hotel[x][0]);
		}

	}

//Saving data to a file method
	private static void SaveToFile(String hotelRef[][]) {
		try {
			File myObj = new File("data");
			myObj.createNewFile();
			ObjectOutputStream ois = new ObjectOutputStream(new FileOutputStream("data"));
			ois.writeObject(hotelRef);
			ois.close();
			System.out.println("File created: " + myObj.getName());
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

//Loading data from a file method
	private static String[][] LoadFromFile() {
		try {
			ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream("data"));
			String[][] hotelRef = (String[][]) objectIn.readObject();
			objectIn.close();
			System.out.println("The data has been read from the file");
			return hotelRef;
		} catch (Exception e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
			return null;
		}
	}

//Finding customer room by name method
	private static void FindRoomFromCustomerName(String hotelRef[][], Scanner input) {
		System.out.print("Enter Customer Name: ");
		String name = input.next();
		for (int i = 0; i < hotelRef.length; i++) {
			if (hotelRef[i] != null)
				if (hotelRef[i][0].equals(name)) {
					System.out.println("Room Number:" + i);
				}
		}
	}

//Deleting customer from a room method
	private static void DeleteCustomerFromRoom(String hotelRef[][], Scanner input) {
		System.out.print("Enter Room Number: ");
		int roomNum = input.nextInt();
		if (roomNum >= hotelRef.length)
			System.out.println("Room not found !");
		else
			hotelRef[roomNum] = null;
		if (!waitingQueue.isEmpty()) {
			String[] room = waitingQueue.remove(waitingQueue.size() - 1);
			hotelRef[roomNum] = room;
			System.out.println("Reserved for " + room[0] + " in the waiting list");
		}

	}


	private static void initialise(String hotelRef[][]) {
		waitingQueue = new ArrayList<String[]>();
		for (int x = 0; x < hotelRef.length; x++)
			hotelRef[x] = null;
		System.out.println("initilise ");
	}

//Viewing empty rooms method
	private static void ViewEmptyRooms(String hotelRef[][]) {
		for (int x = 0; x < hotelRef.length; x++) {
			if (hotelRef[x] == null)
				System.out.println("room " + x + " is empty");
		}
	}

//view all rooms method
	private static void ViewAllRooms(String hotelRef[][]) {
		for (int x = 0; x < hotelRef.length; x++) {
			if (hotelRef[x] == null)
				System.out.println("Room " + x + " is empty");
			else {
				System.out.println(" Room " + x + " occupied by :");
				System.out.println(" Name : " + hotelRef[x][0]);
				System.out.println(" Number Of guest : " + hotelRef[x][1]);
				System.out.println(" First Name : " + hotelRef[x][2]);
				System.out.println(" Second Name : " + hotelRef[x][3]);
				System.out.println(" CreditCard No : " + hotelRef[x][4]);
				System.out.println("\n");
			}
		}
	}
//Adding customer to room and the circular queue method
	private static void AddCustomerToRoom(String hotelRef[][], Scanner input) {
		String roomName;
		int roomNum = 0;
		while (roomNum < hotelRef.length) {
			Boolean isFull = true;
			for (int i = 0; i < hotelRef.length; i++) {
				if (hotelRef[i] == null)
					isFull = false;
			}
			if (!isFull) {
				System.out.println(
						"Please enter the room number (0-" + (hotelRef.length - 1) + ") or " + hotelRef.length + " to stop:");
				roomNum = input.nextInt();
				if (roomNum >= hotelRef.length)
					break;
			} else
				System.out.println("Sorry all of  the rooms have been occupied Your been added to the waiting queue.");
			System.out.println("The person who's doing the payment please enter the details below");
			System.out.println("--------------------------------------");
			System.out.println("\n");
			System.out.println("Please enter room name " + roomNum + " :");
			roomName = input.next();
			System.out.println("Please enter the no of guests : ");
			String guests = input.next();
			System.out.println("Please enter your first name : ");
			String fname = input.next();
			System.out.println("Please enter your second name : ");
			String sname = input.next();
			System.out.println("Please enter your credit card number : ");
			String creditCardNo = input.next();
			if (!isFull) {
				hotelRef[roomNum] = new String[5];
				hotelRef[roomNum][0] = roomName;
				hotelRef[roomNum][1] = guests;
				hotelRef[roomNum][2] = fname;
				hotelRef[roomNum][3] = sname;
				hotelRef[roomNum][4] = creditCardNo;
			} else {
				String[] room = { roomName, guests, fname, sname, creditCardNo };
				waitingQueue.add(room);
				return;
			}
		}
	}
}
