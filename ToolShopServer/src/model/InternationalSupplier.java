package model;
/**
 * Class for the international supplier
 * @author Karlen Chow
 * @version 1.0
 * @since November 25, 2020
 *
 */
public class InternationalSupplier extends Supplier{
	/**
	 * import tax for international customer
	 */
	private double importTax;
	/**
	 * The constructor takes same parameters as super class and calls the super class
	 * @param supplierId
	 * @param companyName
	 * @param supplierAddress
	 * @param contactName
	 */
	public InternationalSupplier(int supplierId,String companyName, String supplierAddress, String contactName) {
		super(supplierId, companyName, supplierAddress, contactName);
	}
	/**
	 * Method to calculate import tax not defined as per instructions
	 */
	public void calculateImportTax() {}

	public double getImportTax() {
		return importTax;
	}
	public void setImportTax(double importTax) {
		this.importTax = importTax;
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
