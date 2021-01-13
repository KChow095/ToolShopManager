package model;

import java.util.*;
/**
 * This class contains the ArrayList containing the different orders that were queued up.
 * 
 * @author Karlen Chow
 * @version 1.0
 * @since October 12, 2020
 *
 */
public class Order {
	/**
	 * This is the static ArrayList that houses the new orderline items that are added to the list.
	 */
	static ArrayList<OrderLine> newOrders= new ArrayList<OrderLine>();
	/**
	 * The default constructor.
	 */
	public Order() {
	}
	/**
	 * This method takes an OrderLine object and adds it to the newOrders list.
	 * @param o is an OrderLine item with the new order
	 */
	public void addOrderLine(OrderLine o) {
		newOrders.add(o);
	}
	/**
	 * This method returns the current list of orders.
	 * @return current ArrayList of orders.
	 */
	public ArrayList<OrderLine> getNewOrders() {
		return newOrders;
	}
}
