package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import model.*;
import viewController.CustomerViewController;
import viewController.InventoryViewController;
import viewController.LogInViewController;
/**
 * This class writes commands in strings/Gson strings to the socket to send to the server as well as receives/deserializes information from the server
 * @author Karlen Chow
 * @version 1.0
 * @since November 29, 2020
 *
 */
public class ModelController {
	private BufferedReader socketIn;
	private PrintWriter socketOut;
	private String command;
	private GsonBuilder builder;
	private Gson igson,cgson,rgson;
	
	public ModelController(BufferedReader socketIn, PrintWriter socketOut) {
		this.setSocketIn(socketIn);
		this.setSocketOut(socketOut);
		LogInViewController login = new LogInViewController();
		while(!login.getLoggedIn()) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		CustomerViewController customerApp = new CustomerViewController(this);
		InventoryViewController inventoryApp = new InventoryViewController(this);
		builder = new GsonBuilder();
		rgson = new Gson();
		igson = itemGson();
		cgson = customerGson();
	}
	
	public Gson itemGson() {
		builder = new GsonBuilder();
		builder.registerTypeAdapter(Item.class, new SubclassAdapter());
		Gson gson =  builder.create();
		return gson;
	}
	
	public Gson customerGson() {
		builder = new GsonBuilder();
		builder.registerTypeAdapter(Customer.class, new SubclassAdapter());
		Gson gson =  builder.create();
		return gson;
	}
	
	public Item[] viewItemInventory(){
		command = "VIEW ALL TOOLS";
		socketOut.println(command);
		try {
			String jresults = socketIn.readLine();
			Item[] allTools = igson.fromJson(jresults, Item[].class);
			return allTools;
		} catch (JsonSyntaxException e) {e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
		return null;
	}
	
	public Item[] searchItemId(int id) {
		command = "SEARCH ITEM ID";
		socketOut.println(command);
		socketOut.println(Integer.toString(id));
		Item[] item=null;
		try {
			String jresults = socketIn.readLine();
			item= igson.fromJson(jresults,Item[].class);
		} catch (JsonSyntaxException e) {e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
		return item;
	}
	
	public Item[] searchItemName(String name) {
		command = "SEARCH ITEM NAME";
		socketOut.println(command);
		socketOut.println(name);
		Item[] item=null;
		try {
			String jresults = socketIn.readLine();
			item= igson.fromJson(jresults,Item[].class);
		} catch (JsonSyntaxException e) {e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
		return item;
	}
	
	public void addElectricalItem(int itemId, String itemName, int quantity, double price, int supplierId, String powerType) {
		command = "ADD E ITEM";
		socketOut.println(command);
		ElectricalItem newItem = new ElectricalItem(itemId,itemName,quantity,price,supplierId,powerType);
		String jNewItem = rgson.toJson(newItem);
		socketOut.println(jNewItem);
	}
	
	public void addNonElectricalItem(int itemId, String itemName, int quantity, double price, int supplierId) {
		command = "ADD N ITEM";
		socketOut.println(command);
		NonElectricalItem newItem = new NonElectricalItem(itemId, itemName, quantity,price,supplierId);
		String jNewItem = rgson.toJson(newItem);
		socketOut.println(jNewItem);
	}
	
	public boolean deleteItem(int itemId) {
		command = "DELETE ITEM";
		socketOut.println(command);
		socketOut.println(Integer.toString(itemId));
		boolean success = false;
		try {
			success= Boolean.parseBoolean(socketIn.readLine());
		} catch (IOException e) {e.printStackTrace();}
		return success;
	}
	
	public int checkItemQuantity(int itemId) {
		command = "CHECK QUANTITY";
		socketOut.println(command);
		socketOut.println(Integer.toString(itemId));
		int quantity = -1;
		try {
			quantity = Integer.parseInt(socketIn.readLine());
		} catch (NumberFormatException e) {e.printStackTrace();} 
		catch (IOException e) {e.printStackTrace();}
		return quantity;
	}
	
	public boolean decreaseQuantity(int itemId, int quantity) {
		command = "DECREASE QUANTITY";
		socketOut.println(command);
		socketOut.println(Integer.toString(itemId));
		socketOut.println(Integer.toString(quantity));
		boolean success = false;
		try {
			success = Boolean.parseBoolean(socketIn.readLine());
		} catch (IOException e) {e.printStackTrace();}
		return success;
	}
	
	public Customer[] viewAllCustomers(){
		command = "VIEW ALL CUSTOMERS";
		socketOut.println(command);
		try {
			String jresults = socketIn.readLine();
			Customer[] results = cgson.fromJson(jresults, Customer[].class);
			return results;
		}  catch (JsonSyntaxException e) {e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
		return null;
	}
	
	public void addResidentialCustomer(int customerId, String firstName, String lastName, String address, String postalCode, long phone) {
		command = "ADD R CUSTOMER";
		socketOut.println(command);
		ResidentialCustomer newCust = new ResidentialCustomer(customerId, firstName, lastName, address, postalCode, phone);
		String jNewCust = rgson.toJson(newCust, ResidentialCustomer.class);
		socketOut.println(jNewCust);
	}
	
	public void addCommercialCustomer(int customerId, String firstName, String lastName, String address, String postalCode, long phone) {
		command = "ADD C CUSTOMER";
		socketOut.println(command);
		CommercialCustomer newCust = new CommercialCustomer(customerId, firstName, lastName, address, postalCode, phone);
		String jNewCust = rgson.toJson(newCust, CommercialCustomer.class);
		socketOut.println(jNewCust);
	}
	
	public boolean modifyCustomer(int customerId, String firstName, String lastName, String address, String postalCode, long phone, char type) {
		command = "MODIFY CUSTOMER";
		socketOut.println(command);
		Customer modifiedCust = new Customer(customerId, firstName, lastName, address, postalCode, phone);
		String jModifiedCust = rgson.toJson(modifiedCust, Customer.class);
		socketOut.println(jModifiedCust);
		socketOut.println(type);
		boolean success = false;
		try {
			success = Boolean.parseBoolean(socketIn.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return success;
	}
	
	public boolean removeCustomer(int id) {
		command = "REMOVE CUSTOMER";
		socketOut.println(command);
		socketOut.println(id);
		boolean success = false;
		try {
			success = Boolean.parseBoolean(socketIn.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return success;
	}
	
	public Customer[] searchCustomerId(int id){
		command = "SEARCH CUSTOMER";
		socketOut.println(command);
		socketOut.println("ID");
		socketOut.println(id);
		try {
			String jresults = socketIn.readLine();
			Customer[] results = cgson.fromJson(jresults, Customer[].class);
			return results;
		}  catch (JsonSyntaxException e) {e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
		return null;
	}
	
	public Customer[] searchCustomerFName(String fName){
		command = "SEARCH CUSTOMER";
		socketOut.println(command);
		socketOut.println("FNAME");
		socketOut.println(fName);
		try {
			String jresults = socketIn.readLine();
			Customer[] results = cgson.fromJson(jresults, Customer[].class);
			return results;
		}  catch (JsonSyntaxException e) {e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
		return null;
	}
	
	public Customer[] searchCustomerLName(String lName){
		command = "SEARCH CUSTOMER";
		socketOut.println(command);
		socketOut.println("LNAME");
		socketOut.println(lName);
		try {
			String jresults = socketIn.readLine();
			Customer[] results = cgson.fromJson(jresults, Customer[].class);
			return results;
		}  catch (JsonSyntaxException e) {e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
		return null;
	}
	
	public Customer[] searchCustomerType(char type){
		command = "SEARCH CUSTOMER";
		socketOut.println(command);
		socketOut.println("TYPE");
		socketOut.println(type);
		try {
			String jresults = socketIn.readLine();
			Customer[] results = cgson.fromJson(jresults, Customer[].class);
			return results;
		}  catch (JsonSyntaxException e) {e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
		return null;
	}
	
	public void setSocketIn(BufferedReader socketIn) {
		this.socketIn = socketIn;
	}
	
	public void setSocketOut(PrintWriter socketOut) {
		this.socketOut = socketOut;
	}
}
