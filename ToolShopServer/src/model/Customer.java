package model;
/**
 * This is the parent class for the customer objects
 * @author Karlen Chow
 * @version 1.0
 * @since November 25, 2020
 *
 */
public class Customer {
	/**
	 * The attributes for each customer
	 */
	private int customerId;
	private String firstName;
	private String lastName;
	private String address;
	private String postalCode;
	private long phone;
	/**
	 * Constructor takes the attributes for each customer and assigns them
	 * @param customerId
	 * @param firstName
	 * @param lastName
	 * @param address
	 * @param postalCode
	 * @param phone
	 */
	public Customer(int customerId, String firstName, String lastName, String address, String postalCode, long phone) {
		this.setCustomerId(customerId);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setAddress(address);
		this.setPostalCode(postalCode);
		this.setPhone(phone);
	}
	/**
	 * The various getters and setters for each customer attribute
	 */
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}

}
