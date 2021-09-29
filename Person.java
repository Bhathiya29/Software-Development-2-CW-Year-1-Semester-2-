import java.io.Serializable;

//person class
public class Person implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String secondName;
	private String creditCardNumber;

	public String getFirstName() {
		return firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public Person(String fName, String sName, String creditCardNo) {
		this.firstName = fName;
		this.secondName = sName;
		this.creditCardNumber = creditCardNo;
	}

//printing out the details
	public void printDetails() {
		System.out.println(" First Name : " + firstName +"\n Second Name : " + secondName + "\n CreditCard Number : " + creditCardNumber);
		System.out.println("\n");
	}
}
