package model;

import java.util.*;
/**
 * This class contains all the item inventory as well as searches for the items, retrieves the item quantity, and restocks the items with the 
 * current Order list.
 * 
 * @author Karlen Chow
 * @version 2.0
 * @since November 25, 2020
 *
 */
public class Inventory {
	/**
	 * This is the arraylist of Items containing all the inventory.
	 */
	private ArrayList<Item> inventoryList;
	/**
	 * This is an Order object to retrieve the order list.
	 */
	private Order order;
	/**
	 * The constructor for this class retrieves the inventory list created by the File Manager class and sets it to this objects member variable.
	 * @param list is an ArrayList containing all the items
	 */
	public Inventory(ArrayList<Item> list) {
		this.setInventoryList(list);
		order = new Order();
	}
	/**
	 * This method searches for the item base on the item id.
	 * @param itemId is the id of the searched item
	 * @return the item object corresponding to that item
	 */
	public Item search(int itemId) {
		for(Item i : getInventoryList()) {
			if(i.getItemId()==itemId)
				return i;
		}
		return null;
	}
	/**
	 * This is the method to call to search for an item based on it's name.
	 * @param itemName is the name corresponding to that searched item
	 * @return the item object that is being searched for
	 */
	public Item search(String itemName) {
		for(Item i : getInventoryList()) {
			if(i.getItemName().toLowerCase().equals(itemName.toLowerCase()))
				return i;
		}
		return null;
	}
	/**
	 * This method takes in an item object and returns it's quantity.
	 * @param i is the item object
	 * @return an integer corresponding to that item's quantity
	 */
	public int getItemQuantity(Item i) {
		return i.getQuantity();
	}
	/**
	 * This method re-stocks the items in the current order list, it is not very efficient as it needs to cycle through all items to
	 * find the correct one.  An optimized solution would be to use hash tables with have constant retrieval time.  This method also clears
	 * the Orders list when items are re-stocked.
	 */
	public void restockItems() {
		if(order.getNewOrders().size() <1)
			return;
		for(OrderLine newOrder : order.getNewOrders()) {
			for(Item i: inventoryList) {
				if(i.getItemName().toLowerCase().equals(newOrder.getItemDescription().toLowerCase())) {
					i.setQuantity(i.getQuantity()+newOrder.getOrderAmount());
				}
			}
		}
		order.getNewOrders().clear();
	}
	/**
	 * Adds item to inventory list
	 * @param i is new item
	 */
	public void addItem(Item i) {
		inventoryList.add(i);
	}
	public Item deleteItem(int itemId) {
		Item del = null;
		for(Item i: inventoryList) {
			if(i.getItemId()==itemId) {
				del = i;
			}
		}
		inventoryList.remove(del);
		return del;
	}
	/**
	 * Decreases the number of items by a specified amount
	 * @param i is the item
	 * @param decrease is the amount decreased
	 * @return true if decrease successful, false if not
	 */
	public boolean decreaseItemQuantity(Item i, int decrease) {
		boolean success = i.decreaseQuantity(decrease);
		return success;
	}
	/**
	 * This is the method to call to retrieve the inventory list.
	 * @return an ArrayList containing all inventory
	 */
	public ArrayList<Item> getInventoryList() {
		return inventoryList;
	}
	/**
	 * This is the method to call to set the inventory list.
	 * @param list is an ArrayList containing this objects inventory
	 */
	public void setInventoryList(ArrayList<Item> list) {
		inventoryList = list;
	}
}
