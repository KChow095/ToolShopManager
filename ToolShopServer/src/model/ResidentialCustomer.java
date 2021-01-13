package model;

import model.Customer;

/**
 * This is the class for the residential customer
 * @author Karlen Chow
 * @version 1.0
 * @since November 25, 2020
 *
 */
public class ResidentialCustomer extends Customer{
	/**
	 * Constructor takes the same parameters as customer superclass and instantiates the super class
	 * @param customerId
	 * @param firstName
	 * @param lastName
	 * @param address
	 * @param postalCode
	 * @param phone
	 */
	public ResidentialCustomer(int customerId, String firstName, String lastName, String address, String postalCode, long phone) {
		super(customerId, firstName, lastName, address, postalCode, phone);
	}
	/**
	 * Overrides default tostring method
	 */
	@Override
	public String toString() {
		String s = "ID:" + super.getCustomerId() + ", Name: "+super.getFirstName()+
				" "+super.getLastName()+", Type: R";
		return s;
	}
}
