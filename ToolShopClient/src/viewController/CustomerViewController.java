package viewController;

import controller.ModelController;
import model.Customer;
import model.ResidentialCustomer;
import view.CustomerMgtGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.WindowConstants;
/**
 * This class contains the action listeners for the Customer Management App
 * @author Karlen Chow
 * @version 1.0
 * @since November 29, 2020
 */
public class CustomerViewController {
	
	private ModelController control;
	private CustomerMgtGUI gui;
	
	public CustomerViewController(ModelController control) {
		this.setControl(control);
		gui = new CustomerMgtGUI();
		addAllActionListeners();
		gui.setVisible(true);
	}
	private void addAllActionListeners() {
		gui.addSearchListener(new searchCustListener());
		gui.addSearchEnterListener(new searchCustListener());
		gui.addClearCustListener(new clearCustListener());
		gui.addSaveListener(new saveCustomerListener());
		gui.addDeleteListner(new deleteCustomerListener());
		gui.addAddListener(new newCustomerListener());
		gui.addClearListener(new clearInformationListener());
		gui.addListListener(new getSelectedCustomer());
	}
	public void setControl(ModelController control) {
		this.control = control;
	}
	class searchCustListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			gui.getMsgBox().setText("");
			String parameter = gui.getSearchedText();
			String option = gui.searchSelection();
			Customer[] results= null;
			if(parameter.equals("")||option==null) {
				results=control.viewAllCustomers();
			}else if(option.equals("ID")) {
				results = control.searchCustomerId(Integer.parseInt(parameter));
			}else if(option.equals("FName")) {
				results = control.searchCustomerFName(parameter);
			}else if(option.equals("LName")) {
				results = control.searchCustomerLName(parameter);
			}else if(option.equals("Type")) {
				results = control.searchCustomerType(parameter.charAt(0));
			}
			if(results.length==0) {
				gui.getMsgBox().setText("No customer found");
			}
			gui.displaySearch(results);
		}
	}
	class getSelectedCustomer implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			gui.getCustomerId().setText(Integer.toString(gui.getSearchList().getSelectedValue().getCustomerId()));
			gui.getCustomerFName().setText(gui.getSearchList().getSelectedValue().getFirstName());
			gui.getCustomerLName().setText(gui.getSearchList().getSelectedValue().getLastName());
			gui.getCustomerAdd().setText(gui.getSearchList().getSelectedValue().getAddress());
			gui.getCustomerPostal().setText(gui.getSearchList().getSelectedValue().getPostalCode());
			gui.getCustomerPhone().setText(Long.toString(gui.getSearchList().getSelectedValue().getPhone()));
			gui.getCustomerType().setSelectedIndex(gui.getSearchList().getSelectedValue() instanceof ResidentialCustomer? 2:1);
		}
		//Methods that do not need to be used.
		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
	}
	class clearCustListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			gui.getButtonGroup().clearSelection();
			gui.getParameterBox().setText("");
			gui.getMsgBox().setText("");
			Customer[] empty = new Customer[0];
			gui.getSearchList().setListData(empty);
		}
	}
	class saveCustomerListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int id = Integer.parseInt(gui.getCustomerId().getText());
			String fName = gui.getCustomerFName().getText();
			String lName = gui.getCustomerLName().getText();
			String address = gui.getCustomerAdd().getText();
			String postal = gui.getCustomerPostal().getText();
			Long phone = Long.parseLong(gui.getCustomerPhone().getText().replace(String.valueOf('-'), ""));
			int typeIndex = gui.getCustomerType().getSelectedIndex();
			char type='N';
			if(typeIndex==1)
				type = 'C';
			else if(typeIndex==2)
				type = 'R';
			boolean success = control.modifyCustomer(id, fName, lName, address, postal, phone, type);
			String result = success?"Customer ID: "+id+" "+fName+" "+lName+" saved\n":"ID: "+id+" "+fName+" "+lName+" not found\n";
			gui.getMsgBox().setText(result);
		}
	}
	class deleteCustomerListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int id = Integer.parseInt(gui.getCustomerId().getText());
			boolean success = control.removeCustomer(id);
			String result = success?"Customer ID: "+id+" removed":"\nID: "+id+" not found\n";
			gui.getMsgBox().setText(result);
		}
	}
	class newCustomerListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				String custId = gui.getCustomerId().getText();
				String fName = gui.getCustomerFName().getText();
				String lName = gui.getCustomerLName().getText();
				String address = gui.getCustomerAdd().getText();
				String postal = gui.getCustomerPostal().getText();
				String number = gui.getCustomerPhone().getText();
				int id = Integer.parseInt(custId);
				Long phone = Long.parseLong(number.replace(String.valueOf('-'), ""));
				int typeIndex = gui.getCustomerType().getSelectedIndex();
				if(custId.equals("")||fName.equals("")||lName.equals("")||address.equals("")||postal.equals("")||number.equals("")||typeIndex==0) {
					gui.getMsgBox().setText("Please ensure all fields are filled when adding customer");
					return;
				}
				if(typeIndex==1)
					control.addCommercialCustomer(id, fName, lName, address, postal, phone);
				else if(typeIndex==2)
					control.addResidentialCustomer(id, fName, lName, address, postal, phone);
				gui.getMsgBox().setText("ID: "+id+" "+fName+ " "+lName+" has been added");
			}catch(NumberFormatException ex) {
				gui.getMsgBox().setText("Please ensure ID and phone are proper numbers");
			}
		}
	}
	class clearInformationListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			gui.getCustomerId().setText("");
			gui.getCustomerFName().setText("");
			gui.getCustomerLName().setText("");
			gui.getCustomerAdd().setText("");
			gui.getCustomerPostal().setText("");
			gui.getCustomerPhone().setText("");
			gui.getCustomerType().setSelectedIndex(0);
		}
	}
}
