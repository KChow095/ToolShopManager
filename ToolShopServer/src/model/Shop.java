package model;

import java.util.ArrayList;

/**
 * This is the central back end class that contains all the functions that perform the actions specified in the front end.  
 * 
 * @author Karlen Chow
 * @version 1.0
 * @since October 13, 2020
 *
 */
public class Shop {
	
	/**
	 * This is the SupplyList item that contains all the suppliers.
	 */
	private SupplierList supplierList;
	/**
	 * This is the Inventory item that contains all the items in the inventory.
	 */
	private Inventory inventory;
	private CustomerList customerList;
	
	
	/**
	 * The constructor for this class takes the SupplyList and Inventory objects and sets it to this object's member variables.
	 * @param supplyList is the SupplyList object
	 * @param inventory is the Inventory object
	 */
	public Shop(SupplierList supplierList, Inventory inventory, CustomerList customerList) {
		this.setSupplyList(supplierList);
		this.setInventory(inventory);
		this.setCustomerList(customerList);
	}
	/**
	 * This is the search method that searches for an item based on the item name.
	 * @param itemName is a string for the item name
	 * @return the item object that corresponds to that item name, note if multiple items have the same name, it will return the first item found
	 */
	public Item searchItem(String itemName) {
		return inventory.search(itemName);
	}
	/**
	 * This is the search method that searches for an item based on the item id.
	 * @param id is an integer for the item id
	 * @return the item object corresponding to that item id, if multiple items have the same id, it will return the first item found 
	 */
	public Item searchItem(int id) {
		return inventory.search(id);
	}
	/**
	 * This is the method to call to retrieve the current quantity of an item.
	 * @param i is the item object
	 * @return the quantity for that item object
	 */
	public int getItemQuantity(Item i) {
		return i.getQuantity();
	}
	/**
	 * This is the method to call to decrease the item quantity
	 * @param i is the item object
	 * @param decrease is an integer for the decrease amount
	 */
	public boolean decreaseItemQuantity(Item i, int decrease) {
		boolean success = inventory.decreaseItemQuantity(i, decrease);
		return success;
	}
	/**
	 * This is the method to call to re-stock all items in the current order list.
	 */
	public void restockItems() {
		inventory.restockItems();
		System.out.println("All orders in have been restocked.");
	}
	public void addItem(int itemId, String itemName, int quantity, double price, int supplierId, String powType) {
		Item newItem = new ElectricalItem(itemId, itemName, quantity, price, supplierId, powType);
		inventory.addItem(newItem);
	}
	public void addItem(int itemId, String itemName, int quantity, double price, int supplierId) {
		Item newItem = new NonElectricalItem(itemId, itemName, quantity, price, supplierId);
		inventory.addItem(newItem);
	}
	public void addItem(Item newItem) {
		inventory.addItem(newItem);
	}
	public Item deleteItem(int itemId) {
		Item del = inventory.deleteItem(itemId);
		return del;
	}
	/**
	 * This is the method to call to set this shop's Inventory object variable.
	 * @param inventory is the Inventory object containing this shop object's inventory
	 */
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	public Inventory getInventory() {
		return inventory;
	}
	/**
	 * Displays all the objects in this shop's inventory.
	 * @return String with all of this shops inventory
	 */
	public String printInventory() {
		String s = "";
		for(Item i: inventory.getInventoryList()) {
			s+= i + "\n";
		}
		return s;
	}
	
	
	/**
	 * This is the method to call to retrieve the SupplyList object.
	 * @return SupplyList that contains all the suppliers
	 */
	public SupplierList getSupplyList() {
		return supplierList;
	}
	/**
	 * This is the method to call to set the SupplyLIst object.
	 * @param supplyList is a SupplyList object containing the suppliers
	 */
	public void setSupplyList(SupplierList supplierList) {
		this.supplierList = supplierList;
	}
	public SupplierList getSupplierList() {
		return supplierList;
	}
	public String printSupplierList() {
		String s = "";
		for(Supplier i: supplierList.getSupplierList()) {
			s+= i + "\n";
		}
		return s;
	}
	
	
	public void addCustomer(Customer c) {
		customerList.addCustomer(c);
	}
	public ArrayList<Customer> searchCustomer(int id){
		return customerList.search(id);
	}
	public ArrayList<Customer>searchCustomer(char type, String name){
		return customerList.search(type, name);
	}
	public ArrayList<Customer>searchCustomer(char type){
		return customerList.search(type);
	}
	public Customer removeCustomer(int id) {
		return customerList.removeCustomer(id);
	}
	public boolean updateCustomer(int custId, String fName, String lName, String address, String postalCode, Long phone,char type) {
		return customerList.update(custId, fName, lName, address, postalCode, phone, type);
	}
	public void setCustomerList(CustomerList customerList) {
		this.customerList = customerList;
	}
	public CustomerList getCustomerList() {
		return customerList;
	}
	public String printCustomerList() {
		String s = "";
		for(Customer i: customerList.getCustomerList()) {
			s+= i + "\n";
		}
		return s;
	}
}
