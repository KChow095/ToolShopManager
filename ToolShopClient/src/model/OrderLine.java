package model;
/**
 * The OrderLine class's main function is to generate an object with the order information and add it to the current Orders list.  The various getter and setter
 * functions are also included to retrieve the object's variables.
 * 
 * @author Karlen Chow
 * @version 1.0
 * @since October 12, 2020
 *
 */
public class OrderLine {
	/**
	 * This is the integer for the orderID
	 */
	private int orderId;
	/**
	 * This is the string for the order date
	 */
	private String orderDate;
	/**
	 * This is the string for the item description (name)
	 */
	private String itemDescription;
	/**
	 * This is the integer for the ordered amount
	 */
	private int orderAmount;
	/**
	 * This is the string for the supplier name that supplies the item
	 */
	private int supplierId;
	/**
	 * The constructor for the OrderLine class takes in all the information necessary to generate the order, and calls the addToOrder method to add this object
	 * to the current list of orders.
	 * 
	 * @param orderId is and integer for the order ID for this order
	 * @param orderDate is a String for the date which this order was placed
	 * @param orderAmount is an integer for the amount needed to be ordered
	 * @param supplierName is a string for the supplier who supplies this item
	 * @param name is the string name of the item
	 */
	public OrderLine(int orderId, String orderDate, int orderAmount, int supplierId, String name) {
		this.setOrderId(orderId);
		this.setOrderDate(orderDate);
		this.setItemDescription(name);
		this.setOrderAmount(orderAmount);
		this.setSupplierId(supplierId);
		addToOrder();
	}
	/**
	 * This method adds this OrderLine object to the list of orders
	 */
	private void addToOrder() {
		Order order = new Order();
		order.addOrderLine(this);
	}
	/**
	 * This method returns the order ID
	 * @return integer for the order ID
	 */
	public int getOrderId() {
		return orderId;
	}
	/**
	 * This method sets the order ID
	 * @param orderId is an integer for the order ID
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	/**
	 * This method returns the order date
	 * @return string for the order date
	 */
	public String getOrderDate() {
		return orderDate;
	}
	/**
	 * This method sets the order date
	 * @param orderDate is a string for the order date
	 */
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	/**
	 * This method retrieves the ordered amount
	 * @return integer for the ordered amount
	 */
	public int getOrderAmount() {
		return orderAmount;
	}
	/**
	 * This method sets the order amount
	 * @param orderAmount integer for the ordered amount
	 */
	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}
	/**
	 * This method returns the item description (name)
	 * @return String for the item description
	 */
	public String getItemDescription() {
		return itemDescription;
	}
	/**
	 * This method sets the item description (name)
	 * @param itemDescription is a string for the item description
	 */
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
}
