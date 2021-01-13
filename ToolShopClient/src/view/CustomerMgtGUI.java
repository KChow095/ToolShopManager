package view;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ButtonGroup;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.ScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.LineBorder;

import model.Customer;

import java.awt.Color;
import javax.swing.JList;
/**
 * This class creates the customer managmenet GUI
 * @author Karlen Chow
 * @version 1.0
 * @since November 29, 2020
 *
 */
public class CustomerMgtGUI extends JFrame{

	private JTextField custSearchField;
	private JTextField custIdField;
	private JTextField custFNameField;
	private JTextField custLNameField;
	private JTextField custAddField;
	private JTextField custPostalField;
	private JTextField custNumField;
	
	private Container contentPane;
	private JPanel panel, leftPanel,leftTopPanel,leftBotPanel, rightPanel, listPanel;
	private JLabel searchTitle, actionPrompt, lblNewLabel, lblNewLabel_1, lblCustomerInformation,lblNewLabel_2,lblNewLabel_3,lblNewLabel_4,lblNewLabel_5,lblNewLabel_6,lblNewLabel_7, lblNewLabel_8;
	private JRadioButton selectId, selectFName, selectLName, selectType;
	private ButtonGroup radioButtons;
	private JButton btnCustSearch,btnCustClear, btnSave, btnDelete, btnAdd, btnClear;
	private JTextArea custResultsBox;
	private JComboBox custTypeSelector;
	private JList<Customer> searchList;
	/**
	 * Create the application.
	 */
	public CustomerMgtGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		contentPane = getContentPane();
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 5, 827, 491);
		panel.setLayout(new GridLayout(1,2));
		
		leftPanel = new JPanel();
		//leftPanel.setBackground(SystemColor.textHighlightText);
		leftPanel.setLayout(new GridLayout(2,1));
		
		leftTopPanel = new JPanel();
		leftTopPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		//leftPanel.setBackground(SystemColor.textHighlightText);
		leftTopPanel.setLayout(null);
		searchTitle = new JLabel("Search Customers");
		searchTitle.setBounds(133, 10, 147, 19);
		searchTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		leftTopPanel.add(searchTitle);
		actionPrompt = new JLabel("Select type of search to be performed:");
		actionPrompt.setBounds(10, 39, 393, 19);
		leftTopPanel.add(actionPrompt);
		
		leftBotPanel = new JPanel();
		leftBotPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		//leftPanel.setBackground(SystemColor.textHighlightText);
		leftBotPanel.setLayout(null);
		
		leftPanel.add(leftTopPanel);
		
		selectId = new JRadioButton("Customer ID");
		selectId.setBounds(20, 62, 176, 21);
		leftTopPanel.add(selectId);
		
		selectFName = new JRadioButton("First Name");
		selectFName.setBounds(20, 85, 176, 21);
		leftTopPanel.add(selectFName);
		
		selectLName = new JRadioButton("Last Name");
		selectLName.setBounds(20, 108, 176, 21);
		leftTopPanel.add(selectLName);
		
		selectType = new JRadioButton("Customer Type (R/C)");
		selectType.setBounds(20, 131, 176, 21);
		leftTopPanel.add(selectType);
		
		radioButtons = new ButtonGroup();
		radioButtons.add(selectId);
		radioButtons.add(selectFName);
		radioButtons.add(selectLName);
		radioButtons.add(selectType);
		
		lblNewLabel = new JLabel("Enter search parameter below:");
		lblNewLabel.setBounds(10, 158, 270, 13);
		leftTopPanel.add(lblNewLabel);
		
		custSearchField = new JTextField();
		custSearchField.setBounds(10, 175, 186, 19);
		leftTopPanel.add(custSearchField);
		custSearchField.setColumns(10);
		
		btnCustSearch = new JButton("Search");
		btnCustSearch.setBounds(204, 173, 89, 21);
		leftTopPanel.add(btnCustSearch);
		
		btnCustClear = new JButton("Clear");
		btnCustClear.setBounds(303, 173, 80, 21);
		leftTopPanel.add(btnCustClear);
		leftPanel.add(leftBotPanel);
		
		lblNewLabel_1 = new JLabel("Search Results:");
		lblNewLabel_1.setBounds(10, 10, 393, 13);
		leftBotPanel.add(lblNewLabel_1);
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setBounds(10, 200, 393, 35);
		custResultsBox = new JTextArea();
		scrollPane.add(custResultsBox);
		leftBotPanel.add(scrollPane);
		
		listPanel = new JPanel();
		listPanel.setBounds(10, 33, 393, 161);
		leftBotPanel.add(listPanel);
		listPanel.setLayout(new GridLayout(1,1));
		
		ScrollPane scrollPaneList = new ScrollPane();
		searchList = new JList();
		scrollPaneList.add(searchList);
		listPanel.add(scrollPaneList);
		
		rightPanel = new JPanel();
		rightPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		//rightPanel.setBackground(SystemColor.textHighlightText);
		rightPanel.setLayout(null);
		
		panel.add(leftPanel);
		panel.add(rightPanel);
		
		lblCustomerInformation = new JLabel("Customer Information");
		lblCustomerInformation.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCustomerInformation.setBounds(126, 10, 177, 19);
		rightPanel.add(lblCustomerInformation);
		
		lblNewLabel_2 = new JLabel("Customer ID:");
		lblNewLabel_2.setBounds(46, 42, 116, 13);
		rightPanel.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("First Name:");
		lblNewLabel_3.setBounds(46, 65, 116, 13);
		rightPanel.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Last Name:");
		lblNewLabel_4.setBounds(46, 88, 116, 13);
		rightPanel.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Address:");
		lblNewLabel_5.setBounds(46, 111, 116, 13);
		rightPanel.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("Postal Code:");
		lblNewLabel_6.setBounds(46, 136, 116, 13);
		rightPanel.add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("Phone Number:");
		lblNewLabel_7.setBounds(46, 159, 116, 13);
		rightPanel.add(lblNewLabel_7);
		
		custIdField = new JTextField();
		custIdField.setBounds(172, 39, 156, 19);
		rightPanel.add(custIdField);
		custIdField.setColumns(10);
		
		custFNameField = new JTextField();
		custFNameField.setBounds(172, 62, 156, 19);
		rightPanel.add(custFNameField);
		custFNameField.setColumns(10);
		
		custLNameField = new JTextField();
		custLNameField.setBounds(172, 85, 156, 19);
		rightPanel.add(custLNameField);
		custLNameField.setColumns(10);
		
		custAddField = new JTextField();
		custAddField.setBounds(172, 108, 156, 19);
		rightPanel.add(custAddField);
		custAddField.setColumns(10);
		
		custPostalField = new JTextField();
		custPostalField.setBounds(172, 133, 156, 19);
		rightPanel.add(custPostalField);
		custPostalField.setColumns(10);
		
		custNumField = new JTextField();
		custNumField.setBounds(172, 156, 156, 19);
		rightPanel.add(custNumField);
		custNumField.setColumns(10);
		
		lblNewLabel_8 = new JLabel("Customer Type:");
		lblNewLabel_8.setBounds(46, 182, 116, 13);
		rightPanel.add(lblNewLabel_8);
		
		custTypeSelector = new JComboBox();
		custTypeSelector.setModel(new DefaultComboBoxModel(new String[] {"","Commercial", "Residential"}));
		custTypeSelector.setEditable(false);
		custTypeSelector.setBounds(172, 182, 156, 21);
		rightPanel.add(custTypeSelector);
		
		btnSave = new JButton("Save");
		btnSave.setBounds(35, 218, 76, 21);
		rightPanel.add(btnSave);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(121, 218, 73, 21);
		rightPanel.add(btnDelete);
		
		btnAdd = new JButton("Add");
		btnAdd.setBounds(204, 218, 75, 21);
		rightPanel.add(btnAdd);
		
		btnClear = new JButton("Clear");
		btnClear.setBounds(289, 218, 76, 21);
		rightPanel.add(btnClear);
		
		contentPane.add(panel);
		setTitle("Customer Management App");
		setSize(841, 533);
		setVisible(true);
	}
	
	public String searchSelection() {
		if(selectId.isSelected()) {
			return "ID";
		}else if(selectFName.isSelected()) {
			return "FName";
		}else if(selectLName.isSelected()) {
			return "LName";
		}else if(selectType.isSelected()) {
			return "Type";
		}else{
			return null;
		}
	}
	public void displaySearch(Customer[] results) {
		searchList.setListData(results);
	}
	
	//Adding all action listeners
	public void addSearchListener(ActionListener listenForSearch) {
		btnCustSearch.addActionListener(listenForSearch);
	}
	public void addSearchEnterListener(ActionListener listenForSearchEnter) {
		custSearchField.addActionListener(listenForSearchEnter);
	}
	public void addClearCustListener(ActionListener listenForCustClear) {
		btnCustClear.addActionListener(listenForCustClear);
	}
	public void addListListener(MouseListener listenForClick) {
		searchList.addMouseListener(listenForClick);
	}
	public void addSaveListener(ActionListener listenToSave) {
		btnSave.addActionListener(listenToSave);
	}
	public void addDeleteListner(ActionListener listenToDelete) {
		btnDelete.addActionListener(listenToDelete);
	}
	public void addAddListener(ActionListener listenToAdd) {
		btnAdd.addActionListener(listenToAdd);
	}
	public void addClearListener(ActionListener listenToClear) {
		btnClear.addActionListener(listenToClear);
	}
	
	//All field/button getters
	public String getSearchedText() {
		return custSearchField.getText();
	}
	public ButtonGroup getButtonGroup() {
		return radioButtons;
	}
	public JTextField getParameterBox() {
		return custSearchField;
	}
	public JList<Customer> getSearchList() {
		return searchList;
	}
	public JTextArea getMsgBox() {
		return custResultsBox;
	}
	public JTextField getCustomerId() {
		return custIdField;
	}
	public JTextField getCustomerFName() {
		return custFNameField;
	}
	public JTextField getCustomerLName() {
		return custLNameField;
	}
	public JTextField getCustomerAdd() {
		return custAddField;
	}
	public JTextField getCustomerPostal() {
		return custPostalField;
	}
	public JTextField getCustomerPhone() {
		return custNumField;
	}
	public JComboBox getCustomerType() {
		return custTypeSelector;
	}
}
