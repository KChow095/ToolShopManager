package model;

import java.util.*;
/**
 * This class houses the list of suppliers that supply the items to the shop.
 * 
 * @author Karlen Chow
 * @version 1.0
 * @since October 13, 2020
 *
 */
public class SupplierList {
	/**
	 * This is the ArrayList of suppliers.
	 */
	private ArrayList<Supplier> supplierList;
	/**
	 * The constructor for this class takes in an ArrayList of suppliers given by the FileManager class.
	 * @param supplyList is a list of all suppliers
	 */
	public SupplierList(ArrayList<Supplier> supplierList) {
		this.setSupplierList(supplierList);
	}
	/**
	 * This method returns the list of suppliers.
	 * @return a list of the current suppliers
	 */
	public ArrayList<Supplier> getSupplierList() {
		return supplierList;
	}
	/**
	 * This method sets the list of suppliers
	 * @param supplyList is an array list of suppliers
	 */
	public void setSupplierList(ArrayList<Supplier> supplyList) {
		this.supplierList = supplyList;
	}

}
