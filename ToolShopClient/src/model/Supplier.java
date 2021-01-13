package model;

import java.util.*;
/**
 * This class contains all the information pertaining to one supplier.
 * 
 * @author Karlen Chow
 * @version 1.0
 * @since October 13, 2020
 *
 */
public class Supplier {
	/**
	 * This is the integer for the supplier id.
	 */
	private int supplierId;
	/**
	 * This is the string for the company name.
	 */
	private String companyName;
	/**
	 * This is the string for the supplier address.
	 */
	private String supplierAddress;
	/**
	 * This is the string for the sales contact.
	 */
	private String contactName;
	/**
	 * The constructor for this class retrieves all the necessary information pertaining to one supplier.
	 * @param supplierId is an integer for the supplier id
	 * @param companyName is a string for the company name
	 * @param supplierAddress is a string for the company address
	 * @param contactName is a string for the sales contact
	 */
	public Supplier(int supplierId,String companyName, String supplierAddress, String contactName) {
		this.setSupplierId(supplierId);
		this.setCompanyName(companyName);
		this.setSupplierAddress(supplierAddress);
		this.setContactName(contactName);
	}
	/**
	 * This is the method that returns the supplier id.
	 * @return integer for the supplier id
	 */
	public int getSupplierId() {
		return supplierId;
	}
	/**
	 * This is the method to set the supplier id.
	 * @param supplierId is an integer for the supplier id
	 */
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	/**
	 * This is the method to retrieve the company name
	 * @return a string with the company name
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * This is the method to set the company name.
	 * @param companyName is a string for the company name
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 * This is the method to retrieve the supplier address.
	 * @return string for the supplier address
	 */
	public String getSupplierAddress() {
		return supplierAddress;
	}
	/**
	 * This is the method to set the supplier address.
	 * @param supplierAddress is a string for the supplier address
	 */
	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}
	/**
	 * This method returns the sales contact name.
	 * @return string for the sales contact
	 */
	public String getContactName() {
		return contactName;
	}
	/**
	 * This method sets the sales contact.
	 * @param contactName is a string for the sales contact
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	/**
	 * This method overrides the default toString method to display all information pertaining to that supplier.
	 * @return a String with the supplier information
	 */
	@Override
	public String toString() {
		return "Supplier ID: "+supplierId + ", Company Name: " + companyName + ", Company Address: " + supplierAddress + ", Contact Name: "+contactName;
	}

}
