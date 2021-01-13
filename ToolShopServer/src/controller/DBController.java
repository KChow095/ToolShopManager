package controller;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

import model.CommercialCustomer;
import model.Customer;
import model.ElectricalItem;
import model.InternationalSupplier;
import model.Item;
import model.LocalSupplier;
import model.NonElectricalItem;
import model.ResidentialCustomer;
import model.Supplier;
/**
 * This class pulls information and updates the database
 * @author Karlen Chow
 * @version 1.0
 * @since November 29, 2020
 *
 */
public class DBController implements DatabaseCredentials{
	private Connection comm;
	private Statement stmt;
	private ResultSet result;
	
	public DBController() {
		initializeConnection();
	}
	
	public void initializeConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			comm = DriverManager.getConnection(CONNECTION_INTO, LOGIN, PASSWORD);
		}
		catch (ClassNotFoundException e) {e.printStackTrace();}
		catch(SQLException e) { e.printStackTrace(); }
	}
	
	public void close() {
		try {
			stmt.close();
			result.close();
			comm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//DB commands for customer management app
	public void insertCustomer(int id, String fName, String lName, char type, long phone, String postal, String address) {
		try {
			String query = "INSERT INTO CUSTOMER (CustomerId, FName, LName, CustomerType, PhoneNumber, PostalCode, Address) VALUES (?,?,?,?,?,?,?);";
			PreparedStatement pStat = comm.prepareStatement(query);
			pStat.setInt(1, id);
			pStat.setString(2, fName);
			pStat.setString(3, lName);
			pStat.setString(4, String.valueOf(type));
			pStat.setLong(5, phone);
			pStat.setString(6, postal);
			pStat.setString(7, address);
			pStat.executeUpdate();
			pStat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteCustomer(int id, String fName) {
		try {
			String query = "DELETE FROM CUSTOMER WHERE CustomerId = ? AND FName = ?;";
			PreparedStatement pStat = comm.prepareStatement(query);
			pStat.setInt(1, id);
			pStat.setString(2, fName);
			
			pStat.executeUpdate();
			//System.out.println("row Count = " + rowCount);
			pStat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateCustomer(int id, String fName, String lName, char type, long phone, String postal, String address) {
		try {
			String query = "UPDATE CUSTOMER SET FName = ?, LName = ?, CustomerType=?, PhoneNumber=?, PostalCode=?, Address=? WHERE CustomerId = ?;";
			PreparedStatement pStat = comm.prepareStatement(query);
			pStat.setString(1, fName);
			pStat.setString(2, lName);
			pStat.setString(3, String.valueOf(type));
			pStat.setLong(4, phone);
			pStat.setString(5, postal);
			pStat.setString(6, address);
			pStat.setInt(7, id);
			
			pStat.executeUpdate();
			//System.out.println("row Count = " + rowCount);
			pStat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Customer> getAllCustomers(){
		ArrayList<Customer> custList= new ArrayList<Customer>();
		String sql = "SELECT * FROM CUSTOMER;";
		ResultSet customerList;
		try {
			PreparedStatement pStat = comm.prepareStatement(sql);
			customerList=pStat.executeQuery();
			while(customerList.next()) {
				int custId = customerList.getInt("CustomerId");
				String fName = customerList.getString("FName");
				String lName = customerList.getString("LName");
				char type = customerList.getString("CustomerType").charAt(0);
				long phone = customerList.getLong("PhoneNumber");
				String postal = customerList.getString("PostalCode");
				String address = customerList.getString("Address");
				Customer c = type == 'R'?new ResidentialCustomer(custId,fName,lName,address,postal,phone):new CommercialCustomer(custId,fName,lName,address,postal,phone);
				custList.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return custList;
	}
	
	//Commands for inventory management app
	public void insertItem(int itemId, String itemName, int quantity, double price, int supplierId, String powType) {
		try {
			String query1 = "INSERT INTO ITEM (ItemId, ItemName, ItemType, QuantityInStock, Price, SupplierId) VALUES (?,?,?,?,?,?);";
			PreparedStatement pStat1 = comm.prepareStatement(query1);
			pStat1.setInt(1, itemId);
			pStat1.setString(2, itemName);
			pStat1.setString(3, "E");
			pStat1.setInt(4, quantity);
			pStat1.setBigDecimal(5, new BigDecimal(price));
			pStat1.setInt(6, supplierId);
			
			pStat1.executeUpdate();
			pStat1.close();
			
			String query2 = "INSERT INTO ELECTRICAL (ItemId, PowerType) VALUES (?,?);";
			PreparedStatement pStat2 = comm.prepareStatement(query2);
			pStat2.setInt(1, itemId);
			pStat2.setString(2, powType);
			
			pStat2.executeUpdate();
			pStat2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertItem(int itemId, String itemName, int quantity, double price, int supplierId) {
		try {
			String query = "INSERT INTO ITEM (ItemId, ItemName, ItemType, QuantityInStock, Price, SupplierId) VALUES (?,?,?,?,?,?);";
			PreparedStatement pStat = comm.prepareStatement(query);
			pStat.setInt(1, itemId);
			pStat.setString(2, itemName);
			pStat.setString(3, "N");
			pStat.setInt(4, quantity);
			pStat.setBigDecimal(5, new BigDecimal(price));
			pStat.setInt(6, supplierId);
			
			pStat.executeUpdate();
			pStat.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteItem(int id, String itemName) {
		try {
			String query = "DELETE FROM ITEM WHERE ItemId = ? AND ItemName = ?;";
			PreparedStatement pStat = comm.prepareStatement(query);
			pStat.setInt(1, id);
			pStat.setString(2, itemName);
			
			pStat.executeUpdate();
			pStat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateItemQuantity(int id, String itemName, int quantity) {
		try {
			String query = "UPDATE ITEM SET QuantityInStock = ? WHERE ItemId = ? AND ItemName =?;";
			PreparedStatement pStat = comm.prepareStatement(query);
			pStat.setInt(1, quantity);
			pStat.setInt(2, id);
			pStat.setString(3, itemName);
			
			pStat.executeUpdate();
			pStat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void generateOrder(Item item, int quantity) {
		try {
			String query1 = "INSERT INTO ORDERING VALUES(?,?);";
			PreparedStatement pStat1 = comm.prepareStatement(query1);
			pStat1.setInt(1,item.getOrderNumber());
			pStat1.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			pStat1.executeUpdate();
			
			String query2 = "INSERT INTO ORDERLINE VALUES(?,?,?,?);";
			PreparedStatement pStat2 = comm.prepareStatement(query2);
			pStat2.setInt(1, item.getOrderNumber());
			pStat2.setInt(2,  item.getItemId());
			pStat2.setInt(3, item.getSupplierId());
			pStat2.setInt(4, quantity);
			pStat2.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Item> getAllItems(){
		ArrayList<Item> itList= new ArrayList<Item>();
		String sql = "SELECT * FROM (ITEM AS I LEFT OUTER JOIN ELECTRICAL AS E ON I.ItemId = E.ItemId);";
		ResultSet itemList;
		try {
			PreparedStatement pStat = comm.prepareStatement(sql);
			itemList=pStat.executeQuery();
			while(itemList.next()) {
				int itemId = itemList.getInt("ItemId");
				String itemName = itemList.getString("ItemName");
				char type = itemList.getString("ItemType").charAt(0);
				int quantity = itemList.getInt("QuantityInStock");
				double price = itemList.getDouble("Price");
				int supplierId = itemList.getInt("SupplierId");
				String powType = itemList.getString("PowerType");
				Item i = type == 'E'?new ElectricalItem(itemId,itemName,quantity,price,supplierId,powType):new NonElectricalItem(itemId,itemName,quantity,price,supplierId);
				itList.add(i);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return itList;
	}
	
	public ArrayList<Supplier> getAllSuppliers(){
		ArrayList<Supplier> itList= new ArrayList<Supplier>();
		String sql = "SELECT * FROM (SUPPLIER AS S LEFT OUTER JOIN INTERNATIONAL AS I ON S.SupplierId = I.SupplierId);";
		ResultSet sList;
		try {
			PreparedStatement pStat = comm.prepareStatement(sql);
			sList=pStat.executeQuery();
			while(sList.next()) {
				int supplierId = sList.getInt("SupplierId");
				String compName = sList.getString("CompanyName");
				char type = sList.getString("SupplierType").charAt(0);
				String contact = sList.getString("SalesContact");
				Long number = sList.getLong("PhoneNumber");
				String address = sList.getString("Address");
				double importTax = sList.getDouble("ImportTax");
				if(type == 'I') {
					InternationalSupplier s=new InternationalSupplier(supplierId,compName,address,contact);
					s.setImportTax(importTax);
					itList.add(s);
				}else {
					LocalSupplier s = new LocalSupplier(supplierId,compName,address,contact);
					itList.add(s);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return itList;
	}
}
