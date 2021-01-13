package model;

import java.util.*;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;
/**
 * This class contains all the information pertaining to one item.  It also decreases the quantity for this item and checks if
 * the quantity is below 40, if so, it generates a new OrderLine object to add to the next order.  The amount order is 50-curent quantity
 * as specified in the assignment.
 * 
 * @author Karlen Chow
 * @version 2.0
 * @since November 25,2020
 *
 */
public class Item {
	/**
	 * This is the integer for the item id.
	 */
	private int itemId;
	/**
	 * This is the String for the item name.
	 */
	private String itemName;
	/**
	 * This is the integer for the quantity of items.
	 */
	private int quantity;
	/**
	 * This is the double for the price of the item.
	 */
	private double price;
	/**
	 * This is the integer for the supplier ID.
	 */
	private int supplierId;
	/**
	 * This is a random object that is used to generate a random number for the order id.
	 */
	private Random rnd;
	private int orderNumber;
	
	public Item(int itemId, String itemName, int quantity, double price, int supplierId) {
		setItemId(itemId);
		setItemName(itemName);
		setQuantity(quantity);
		setPrice(price);
		setSupplierId(supplierId);
		rnd = new Random();
	}
	/**
	 * This is the method to call to decrease the quantity for this item, it also checks if the quantity is below 40, in which case, it would
	 * create a new OrderLine object.
	 * @param decrease is the integer to decrease this item's quantity by
	 */
	public boolean decreaseQuantity(int decrease) {
		boolean successful = false;
		if(decrease>0 && decrease<this.quantity) {
			this.setQuantity(this.quantity-decrease);
			successful = true;
			//Generates new order line if quantity falls below 40
			if(quantity < 40) {
				generateNewOrderLine();
			}
		}
		return successful;
	}
	/**
	 * This is the method to generate a new OrderLine object.  It computes the quantity it needs to order and constructs the OrderLine object.
	 */
	private void generateNewOrderLine() {
		int orderAmount = 50 - this.getQuantity();
		String randNum = "";
		for(int i = 0; i<5; i++) {
			randNum+=rnd.nextInt(10);
		}
		orderNumber = Integer.parseInt(randNum);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();
		OrderLine order = new OrderLine(orderNumber, dtf.format(now), orderAmount, getSupplierId(), getItemName());
	}
	/**
	 * This is the method to call to retrieve the item id.
	 * @return integer for the item id
	 */
	public int getItemId() {
		return itemId;
	}
	/**
	 * This is the method to call to set the item id.
	 * @param itemId is an integer for the item id
	 */
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	/**
	 * This is the method to call to retrieve the item name.
	 * @return a string for the item name
	 */
	public String getItemName() {
		return itemName;
	}
	/**
	 * This is the method to call to set the item name.
	 * @param itemName is a string for the item name
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	/**
	 * This is the method to call to retrieve the item price.
	 * @return double for the item price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * This is the method to call to set the item price.
	 * @param price is a double for the item price
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	/**
	 * This is the method to call to retrieve the item quantity.
	 * @return integer for the item quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * This is the method to call to set the item quantity.
	 * @param quantity is an integer for the item quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	/**
	 * This is the method to call to retrieve the supplier id.
	 * @return an integer for the supplier id.
	 */
	public int getSupplierId() {
		return supplierId;
	}
	/**
	 * This is the method to call to set this item's supplier id.
	 * @param supplierId is an integer for the supplier id
	 */
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	/**
	 * To get this items orderId if a new order generated
	 * @return orderId
	 */
	public int getOrderNumber() {
		return orderNumber;
	}
}
