package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.google.gson.*;

import model.*;
/**
 * This class takes the information from the database and initializes a toolshop model with the information.  It also sends string/Json string information
 * to and from the socket
 * @author Karlen Chow
 * @version 1.0
 * @since November 29, 2020
 *
 */
public class ModelController implements Runnable{
	
	private DBController dbController;
	
	private PrintWriter socketOut;
	private BufferedReader socketIn;
	
	private Gson rgson,igson,cgson;
	private GsonBuilder builder;
	private Shop shop;
	
	public ModelController(PrintWriter socketOut, BufferedReader socketIn, DBController dbController) {
		this.setDbController(dbController);
		this.socketOut = socketOut;
		this.socketIn = socketIn;
		setUpShop();
		rgson = new Gson();
		igson = itemGson();
		cgson = customerGson();
	}
	
	public Gson itemGson() {
		builder = new GsonBuilder();
		builder.registerTypeAdapter(Item.class, new SubclassAdapter());
		Gson gson = builder.create();
		return gson;
	}
	public Gson customerGson() {
		builder = new GsonBuilder();
		builder.registerTypeAdapter(Customer.class, new SubclassAdapter());
		Gson gson = builder.create();
		return gson;
	}
	
	private void setUpShop() {
		Inventory i = new Inventory(dbController.getAllItems());
		SupplierList s = new SupplierList(dbController.getAllSuppliers());
		CustomerList c = new CustomerList(dbController.getAllCustomers());
		shop = new Shop(s,i,c);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try {
				String command = socketIn.readLine();
				if(command.equals("VIEW ALL TOOLS")) {
					ArrayList<Item> results= shop.getInventory().getInventoryList();
					Item[] itemList= new Item[results.size()];
					for(int i = 0; i<results.size();i++) {
						itemList[i] =results.get(i);
					}
					String jresults = igson.toJson(itemList);
					socketOut.println(jresults);
				}
				else if(command.equals("SEARCH ITEM ID")) {
					int searchId = Integer.parseInt(socketIn.readLine());
					Item[] searchedItem = new Item[] {shop.searchItem(searchId)};
					String jitem = igson.toJson(searchedItem);
					socketOut.println(jitem);
				}
				else if(command.equals("SEARCH ITEM NAME")) {
					String searchName = socketIn.readLine();
					Item[] searchedItem = new Item[] {shop.searchItem(searchName)};
					String jitem = igson.toJson(searchedItem);
					socketOut.println(jitem);
				}
				else if(command.equals("ADD E ITEM")) {
					ElectricalItem newItem = rgson.fromJson(socketIn.readLine(),ElectricalItem.class);
					shop.addItem(newItem);
					dbController.insertItem(newItem.getItemId(),newItem.getItemName(),newItem.getQuantity(),newItem.getPrice(),newItem.getSupplierId(),newItem.getPowerType());
				}
				else if(command.contentEquals("ADD N ITEM")) {
					NonElectricalItem newItem = rgson.fromJson(socketIn.readLine(),NonElectricalItem.class);
					shop.addItem(newItem);
					dbController.insertItem(newItem.getItemId(),newItem.getItemName(),newItem.getQuantity(),newItem.getPrice(),newItem.getSupplierId());
				}
				else if(command.equals("DELETE ITEM")) {
					int delId = Integer.parseInt(socketIn.readLine());
					Item del = shop.deleteItem(delId);
					Boolean success = false;
					if(del!=null) {
						success = true;
						dbController.deleteItem(del.getItemId(),del.getItemName());
					}
					socketOut.println(success);
				}
				else if(command.equals("CHECK QUANTITY")) {
					int id = Integer.parseInt(socketIn.readLine());
					Item qItem = shop.searchItem(id);
					if(qItem ==null) {
						socketOut.println(Integer.toString(-1));
					}else {
						socketOut.println(Integer.toString(qItem.getQuantity()));
					}
				}
				else if(command.equals("DECREASE QUANTITY")) {
					int decId = Integer.parseInt(socketIn.readLine());
					int quantity = Integer.parseInt(socketIn.readLine());
					Item decItem = shop.searchItem(decId);
					boolean success = false;
					if(decItem!=null) {
						success = shop.decreaseItemQuantity(decItem, quantity);
						socketOut.println(success);
						if(success) {
							if(decItem.getQuantity()<40) {
								dbController.generateOrder(decItem, 50-decItem.getQuantity());
							}
							dbController.updateItemQuantity(decId, decItem.getItemName(), decItem.getQuantity());
						}
					}
				}
				else if(command.equals("VIEW ALL CUSTOMERS")) {
					ArrayList<Customer> results = shop.getCustomerList().getCustomerList();
					Customer[] custList= new Customer[results.size()];
					for(int i = 0; i<results.size();i++) {
						custList[i] =results.get(i);
					}
					String customerList = cgson.toJson(custList);
					socketOut.println(customerList);
				}
				else if(command.equals("ADD R CUSTOMER")) {
					ResidentialCustomer newCust = rgson.fromJson(socketIn.readLine(), ResidentialCustomer.class);
					shop.addCustomer(newCust);
					dbController.insertCustomer(newCust.getCustomerId(), newCust.getFirstName(),newCust.getLastName() , 'R', newCust.getPhone(),newCust.getPostalCode(),newCust.getAddress());
				}
				else if(command.equals("ADD C CUSTOMER")) {
					CommercialCustomer newCust = rgson.fromJson(socketIn.readLine(), CommercialCustomer.class);
					shop.addCustomer(newCust);
					dbController.insertCustomer(newCust.getCustomerId(), newCust.getFirstName(),newCust.getLastName() , 'C', newCust.getPhone(),newCust.getPostalCode(),newCust.getAddress());
				}
				else if(command.equals("MODIFY CUSTOMER")) {
					Customer modifiedCust = rgson.fromJson(socketIn.readLine(), Customer.class);
					char type = socketIn.readLine().charAt(0);
					boolean success = shop.updateCustomer(modifiedCust.getCustomerId(),modifiedCust.getFirstName(),modifiedCust.getLastName(),modifiedCust.getAddress(),modifiedCust.getPostalCode(),modifiedCust.getPhone(), type);
					if(success) {
						dbController.updateCustomer(modifiedCust.getCustomerId(),modifiedCust.getFirstName(),modifiedCust.getLastName(),type,modifiedCust.getPhone(),modifiedCust.getPostalCode(),modifiedCust.getAddress());
					}
					socketOut.println(success);
				}
				else if(command.equals("REMOVE CUSTOMER")) {
					int delCustId = Integer.parseInt(socketIn.readLine());
					Customer delCust = shop.removeCustomer(delCustId);
					boolean success = false;
					if(delCust != null) {
						dbController.deleteCustomer(delCust.getCustomerId(), delCust.getFirstName());
						success = true;
					}
					socketOut.println(success);
				}
				else if(command.equals("SEARCH CUSTOMER")) {
					ArrayList<Customer> results;
					String how = socketIn.readLine();
					if(how.equals("ID")) {
						int searchId = Integer.parseInt(socketIn.readLine());
						results = shop.searchCustomer(searchId);
					}else if(how.equals("FNAME")) {
						String searchName = socketIn.readLine();
						results = shop.searchCustomer('F', searchName);
					}else if(how.equals("LNAME")) {
						String searchName = socketIn.readLine();
						results = shop.searchCustomer('L', searchName);
					}else {
						char searchType = socketIn.readLine().charAt(0);
						results = shop.searchCustomer(searchType);
					}
					Customer[] custList= new Customer[results.size()];
					for(int i = 0; i<results.size();i++) {
						custList[i] =results.get(i);
					}
					String jresults = cgson.toJson(custList);
					socketOut.println(jresults);
				}
				else {
					socketOut.println("Invalid Option");
				}
			}catch(IOException e) {
				socketOut.println(e);
			}
		}
	}
	public void setDbController(DBController dbController) {
		this.dbController = dbController;
	}
}
