package model;
/**
 * This is the class for a non electrical item
 * @author Karlen Chow
 * @version 1.0
 * @since November 25, 2020
 *
 */
public class NonElectricalItem extends Item{
	/**
	 * Constructor takes the same parameters as the item superclass and calls the super class constructor
	 * @param itemId
	 * @param itemName
	 * @param quantity
	 * @param price
	 * @param supplierId
	 */
	public NonElectricalItem(int itemId, String itemName, int quantity, double price, int supplierId){
		super(itemId, itemName, quantity, price, supplierId);
	}
	/**
	 * This method overrides the default toString method to display the information pertaining to this item.
	 * @return String with the item's information
	 */
	@Override
	public String toString() {
		return "ID: "+super.getItemId() + ", Name: " + super.getItemName() + ", Quantity: " + super.getQuantity() + ", $"+super.getPrice()+ ", Type: Non-Electrical";
	}
}
