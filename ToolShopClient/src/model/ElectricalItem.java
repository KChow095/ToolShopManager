package model;
/**
 * This is the class for an electrical item
 * @author Karlen Chow
 * @version 1.0
 * @since November 25, 2020
 *
 */
public class ElectricalItem extends Item{
	/**
	 * Power type
	 */
	private String powerType;
	/**
	 * Constructor takes the same arguments of item as well as powertype and calls super class
	 * @param itemId
	 * @param itemName
	 * @param quantity
	 * @param price
	 * @param supplierId
	 * @param powerType
	 */
	public ElectricalItem(int itemId, String itemName, int quantity, double price, int supplierId, String powerType){
		super(itemId, itemName, quantity, price, supplierId);
		this.setPowerType(powerType);
	}
	public String getPowerType() {
		return powerType;
	}
	public void setPowerType(String powerType) {
		this.powerType = powerType;
	}
	/**
	 * This method overrides the default toString method to display the information pertaining to this item.
	 * @return String with the item's information
	 */
	@Override
	public String toString() {
		return "ID: "+super.getItemId() + ", Name: " + super.getItemName() + ", Quantity: " + super.getQuantity() + ", $"+super.getPrice()+ ", Type: Electrical, "+powerType;
	}
}
