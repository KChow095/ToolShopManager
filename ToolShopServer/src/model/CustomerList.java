package model;

import java.util.ArrayList;
/**
 * This is the list object that performs operations on the customer objects and holds the list of customers
 * @author Karlen Chow
 * @version 1.0
 * @since November 25, 2020
 *
 */
public class CustomerList {
	/**
	 * Arraylist that houses all customer objects
	 */
	private ArrayList<Customer> customerList;
	/**
	 * Constructor takes the arraylist from db
	 * @param customerList
	 */
	public CustomerList(ArrayList<Customer> customerList) {
		this.setCustomerList(customerList);
	}
	public ArrayList<Customer> getCustomerList() {
		return customerList;
	}
	public void setCustomerList(ArrayList<Customer> customerList) {
		this.customerList = customerList;
	}
	/**
	 * Default class to add a new customer to the list.  Type determines if customer is residential or commercial
	 * @param custId
	 * @param fName
	 * @param lName
	 * @param address
	 * @param postalCode
	 * @param phone
	 * @param type
	 */
	public void addCustomer(int custId, String fName, String lName, String address, String postalCode, Long phone,char type){
		if(type=='R') {
			ResidentialCustomer newCustomer = new ResidentialCustomer(custId, fName, lName, address, postalCode, phone);
			customerList.add(newCustomer);
		}else {
			CommercialCustomer newCustomer = new CommercialCustomer(custId, fName, lName, address, postalCode, phone);
			customerList.add(newCustomer);
		}
	}
	/**
	 * Adds customer item if already created
	 * @param newCustomer
	 */
	public void addCustomer(Customer newCustomer) {
		customerList.add(newCustomer);
	}
	/**
	 * Removes a customer from list based on customer ID
	 * @param customerId 
	 * @return the removed customer
	 */
	public Customer removeCustomer(int customerId) {
		Customer removed = null;
		for(Customer c: customerList) {
			if(c.getCustomerId()==customerId) {
				removed = c;
			}
		}
		customerList.remove(removed);
		return removed;
	}
	/**
	 * Searches the list based on customer id
	 * @param id
	 * @return an arraylist of customer objects
	 */
	public ArrayList<Customer> search(int id){
		ArrayList<Customer> results = new ArrayList<Customer>();
		for(Customer c: customerList) {
			if (c.getCustomerId() == id)
				results.add(c);
		}
		return results;
	}
	/**
	 * Seaches the list based on first name or last name
	 * @param nameType for first or last name
	 * @param name
	 * @return an arraylist of customer objects
	 */
	public ArrayList<Customer>search(char nameType, String name){
		ArrayList<Customer> results = new ArrayList<Customer>();
		if(nameType=='F') {
			for(Customer c: customerList) {
				if(c.getFirstName().equals(name)) {
					results.add(c);
				}
			}
		}else {
			for(Customer c: customerList) {
				if(c.getLastName().equals(name)) {
					results.add(c);
				}
			}
		}
		return results;
	}
	/**
	 * Searches list based off of customer type
	 * @param custType
	 * @return an arraylist of customer object
	 */
	public ArrayList<Customer>search(char custType){
		ArrayList<Customer> results = new ArrayList<Customer>();
		if(custType=='C') {
			for(Customer c: customerList) {
				if(c instanceof CommercialCustomer) {
					results.add(c);
				}
			}
		}else {
			for(Customer c: customerList) {
				if(c instanceof ResidentialCustomer) {
					results.add(c);
				}
			}
		}
		return results;
	}
	/**
	 * Updates an existing customer id with new values
	 * @param custId
	 * @param fName
	 * @param lName
	 * @param address
	 * @param postalCode
	 * @param phone
	 * @param type
	 * @return true if update successful, false if not
	 */
	public boolean update(int custId, String fName, String lName, String address, String postalCode, Long phone,char type) {
		boolean success = false;
		for(Customer c: customerList) {
			if (c.getCustomerId()==custId) {
				removeCustomer(c.getCustomerId());
				addCustomer(custId, fName, lName, address, postalCode, phone, type);
				success = true;
			}
		}
		return success;
	}
}
