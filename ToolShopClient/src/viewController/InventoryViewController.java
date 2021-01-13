package viewController;

import controller.ModelController;
import model.ElectricalItem;
import model.Item;
import view.InventoryMgtGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/**
 * This class contains the action listeners for the Inventory Management App
 * @author Karlen Chow
 * @version 1.0
 * @since November 29, 2020
 */
public class InventoryViewController {
	
	private ModelController control;
	private InventoryMgtGUI gui;
	
	public InventoryViewController(ModelController control) {
		this.setControl(control);
		gui = new InventoryMgtGUI();
		addAllActionListeners();
		gui.setVisible(true);
	}
	public void setControl(ModelController control) {
		this.control = control;
	}
	public void addAllActionListeners() {
		gui.addSearchItemListener(new SearchItemListener());
		gui.addSearchEnterListener(new SearchItemListener());
		gui.addQuantityListener(new DisplayQuantityListener());
		gui.addClearItemListener(new ItemClearListener());
		gui.addDisplayInventoryListener(new DisplayAllItemsListener());
		gui.addDeleteListner(new DeleteItemListener());
		gui.addAddListener(new AddItemListener());
		gui.addClearListener(new ClearInformationListener());
		gui.addListListener(new GetSelectedItem());
		gui.addDecreaseListener(new DecreaseQuantity());
		gui.addDecreaseEnterListener(new DecreaseQuantity());
	}
	public Item[] searchItem(String parameter, String option) {
		Item[] result= null;
		try {
			if(parameter.equals("")||option==null) {
				gui.getMsgBox().setText("Please fill out the search parameters");
			}else if(option.equals("ID")) {
				result = control.searchItemId((Integer.parseInt(parameter)));
			}else if(option.equals("Name")) {
				result = control.searchItemName(parameter);
			}
		}catch(NumberFormatException ex) {
			gui.getMsgBox().setText("Please enter a numerical id");
		}
		return result;
	}
	private void checkQuantity(int id, int quantity) {
		if(quantity<40)
			gui.getMsgBox().append("\nID: "+id+" has fallen below 40, order generated");
	}
	class SearchItemListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			gui.getMsgBox().setText("");
			String parameter = gui.getSearchedText();
			String option = gui.searchSelection();
			Item[] result = searchItem(parameter, option);
			if(result==null || result[0]==null) {
				gui.getMsgBox().setText("No item found");
			}else {
				gui.displaySearch(result);
			}
		}
	}
	class DisplayQuantityListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			gui.getMsgBox().setText("");
			String parameter = gui.getSearchedText();
			String option = gui.searchSelection();
			Item[] result = searchItem(parameter, option);
			if(result==null || result[0]==null) {
				gui.getMsgBox().setText("No item found, cannot display quantity");
				return;
			}else {
				int quantity = control.checkItemQuantity(result[0].getItemId());
				gui.getMsgBox().setText("The quantity of Id: "+result[0].getItemId() +" "+result[0].getItemName()+" is "+quantity);
			}
		}
		
	}
	class DisplayAllItemsListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			gui.getMsgBox().setText("");
			Item[] allItems = control.viewItemInventory();
			gui.displaySearch(allItems);
		}
	}
	class GetSelectedItem implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			gui.getItemId().setText(Integer.toString(gui.getSearchList().getSelectedValue().getItemId()));
			gui.getItemName().setText(gui.getSearchList().getSelectedValue().getItemName());
			gui.getItemQuantity().setText(Integer.toString(gui.getSearchList().getSelectedValue().getQuantity()));
			gui.getItemPrice().setText(Double.toString(gui.getSearchList().getSelectedValue().getPrice()));
			gui.getSupplierId().setText(Integer.toString(gui.getSearchList().getSelectedValue().getSupplierId()));
			gui.getItemType().setSelectedIndex(gui.getSearchList().getSelectedValue() instanceof ElectricalItem? 1:2);
			if(gui.getSearchList().getSelectedValue() instanceof ElectricalItem) {
				String print = gui.getSearchList().getSelectedValue().toString();
				String type = print.substring(print.length() - 2);
				gui.getPowerType().setText(type);
			}else {
				gui.getPowerType().setText("");
			}
		}
		//Actions overridden to do nothing
		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
	}
	class ItemClearListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			gui.getButtonGroup().clearSelection();
			gui.getParameterBox().setText("");
			gui.getMsgBox().setText("");
			Item[] empty = new Item[0];
			gui.getSearchList().setListData(empty);
		}
	}
	class DeleteItemListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int id = Integer.parseInt(gui.getItemId().getText());
			boolean success = control.deleteItem(id);
			String result = success?"Item ID: "+id+" removed":"\nID: "+id+" not found\n";
			gui.getMsgBox().setText(result);
		}
	}
	class AddItemListener implements ActionListener{
	
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				String itemId = gui.getItemId().getText();
				String name = gui.getItemName().getText();
				String itemQuantity = gui.getItemQuantity().getText();
				String itemPrice = gui.getItemPrice().getText();
				String itemSupplierId = gui.getSupplierId().getText();
				String powerType = gui.getPowerType().getText();
				int id = Integer.parseInt(itemId);
				int supplierId = Integer.parseInt(itemSupplierId);
				int quantity = Integer.parseInt(itemQuantity);
				double price = Double.parseDouble(itemPrice);
				int typeIndex = gui.getItemType().getSelectedIndex();
				if(itemId.equals("")||name.equals("")||itemQuantity.equals("")||itemPrice.equals("")||itemSupplierId.equals("")||typeIndex==0) {
					gui.getMsgBox().setText("Please ensure all required fields are filled when adding item");
					return;
				}
				if(typeIndex==1) {
					if(powerType.equals("")) {
						gui.getMsgBox().setText("Please ensure power type indicated for electrical item");
						return;
					}
					control.addElectricalItem(id, name, quantity, price, supplierId, powerType);
				}
				else if(typeIndex==2)
					control.addNonElectricalItem(id, name, quantity, price, supplierId);
				gui.getMsgBox().setText("ID: "+id+" "+name+" has been added");
			}catch(NumberFormatException ex) {
				gui.getMsgBox().setText("Please ensure ID's and phone are proper numbers");
			}
		}
	}
	class ClearInformationListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			gui.getItemId().setText("");
			gui.getItemName().setText("");
			gui.getItemQuantity().setText("");
			gui.getItemPrice().setText("");
			gui.getSupplierId().setText("");
			gui.getPowerType().setText("");
			gui.getItemDecrease().setText("");
			gui.getItemType().setSelectedIndex(0);
		}
	}
	class DecreaseQuantity implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				String itemDecrease = gui.getItemDecrease().getText();
				String itemId = gui.getItemId().getText();
				if(itemDecrease.equals("")) {
					gui.getMsgBox().setText("Please enter a decrease quantity");
				}else if(itemId.contentEquals("")) {
					gui.getMsgBox().setText("Please enter the item ID");
				}
				int decrease = Integer.parseInt(itemDecrease);
				int id = Integer.parseInt(itemId);
				boolean success = control.decreaseQuantity(id, decrease);
				int quantity = control.checkItemQuantity(id);
				if(success) {
					gui.getMsgBox().setText("New quantity for item ID "+id+": "+quantity);
					checkQuantity(id, quantity);
				}else {
					gui.getMsgBox().setText("Invalid decrease, current quantity for ID: "+id +" is " +quantity);
				}
			}catch(NumberFormatException ex) {
				gui.getMsgBox().setText("Please ensure ID and decrease are proper numbers");
			}
		}
		
	}
}
