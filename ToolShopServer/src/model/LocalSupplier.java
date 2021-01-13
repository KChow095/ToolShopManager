package model;
/**
 * This is the class for a local supplier
 * @author Karlen Chow
 * @version 1.0
 * @since November 25, 2020
 *
 */
public class LocalSupplier extends Supplier{
	/**
	 * Constructor takes the same parameters as parent class and calls the super class constructor.
	 * @param supplierId
	 * @param companyName
	 * @param supplierAddress
	 * @param contactName
	 */
	public LocalSupplier(int supplierId,String companyName, String supplierAddress, String contactName) {
		super(supplierId, companyName, supplierAddress, contactName);
	}
	/**
	 * Overrides default to string method to display more informative string
	 */
	@Override
	public String toString() {
		String s = "ID:" + super.getSupplierId() + ", Company: "+super.getCompanyName()+
				", Address: "+super.getSupplierAddress();
		return s;
	}
}
