package model;

import model.Customer;

/**
 * This is the class for the commercial customer
 * 
 * @author Karlen Chow
 * @version 1.0
 * @since November 25, 2020
 *
 */
public class CommercialCustomer extends Customer{
	/**
	 * Constructor takes in the same parameters of regular customer object and calls superclass
	 * @param customerId
	 * @param firstName
	 * @param lastName
	 * @param address
	 * @param postalCode
	 * @param phone
	 */
	public CommercialCustomer(int customerId, String firstName, String lastName, String address, String postalCode, long phone) {
		super(customerId, firstName, lastName, address, postalCode, phone);
	}
	/**
	 * Overrides default to string method to display more informative string
	 */
	@Override
	public String toString() {
		String s = "ID:" + super.getCustomerId() + ", Name: "+super.getFirstName()+
				" "+super.getLastName()+", Type: C";
		return s;
	}
}