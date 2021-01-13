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

import model.Item;

import java.awt.Color;
import javax.swing.JList;
import javax.swing.UIManager;
/**
 * This class creates the inventory management GUI
 * @author Karlen Chow
 * @version 1.0
 * @since November 29, 2020
 *
 */
public class InventoryMgtGUI extends JFrame{

	private JTextField itemSearchField;
	private JTextField idField;
	private JTextField nameField;
	private JTextField quantityField;
	private JTextField priceField;
	private JTextField supplierIdField;
	private JTextField powerField;
	private JTextField decreaseField;
	
	private Container contentPane;
	private JPanel panel, leftPanel,leftTopPanel,leftBotPanel, rightPanel, listPanel, decreasePanel;
	private JLabel searchTitle, actionPrompt, lblNewLabel, lblNewLabel_1, lblCustomerInformation,lblNewLabel_2,lblNewLabel_3,lblNewLabel_4,lblNewLabel_5,lblNewLabel_6,lblNewLabel_7, lblNewLabel_8;
	private JRadioButton selectId, selectName;
	private ButtonGroup radioButtons;
	private JButton btnItemSearch,btnItemClear, btnDelete, btnAdd, btnClear, btnQuantity, decreaseBtn,btnDisplayAllItems;
	private JTextArea resultsBox;
	private JComboBox typeSelector;
	private JList<Item> searchList;
	private JLabel lblNewLabel_9;
	private JLabel lblNewLabel_10;
	/**
	 * Create the application.
	 */
	public InventoryMgtGUI() {
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
		searchTitle = new JLabel("Search Items");
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
		
		selectId = new JRadioButton("Item ID");
		selectId.setBounds(20, 62, 176, 21);
		leftTopPanel.add(selectId);
		
		selectName = new JRadioButton("Item Name");
		selectName.setBounds(20, 85, 176, 21);
		leftTopPanel.add(selectName);
		
		radioButtons = new ButtonGroup();
		radioButtons.add(selectId);
		radioButtons.add(selectName);
		
		lblNewLabel = new JLabel("Enter search parameter below:");
		lblNewLabel.setBounds(10, 112, 270, 13);
		leftTopPanel.add(lblNewLabel);
		
		itemSearchField = new JTextField();
		itemSearchField.setBounds(10, 129, 278, 19);
		leftTopPanel.add(itemSearchField);
		itemSearchField.setColumns(10);
		
		btnItemSearch = new JButton("Search");
		btnItemSearch.setBounds(10, 158, 89, 21);
		leftTopPanel.add(btnItemSearch);
		
		btnItemClear = new JButton("Clear");
		btnItemClear.setBounds(208, 158, 80, 21);
		leftTopPanel.add(btnItemClear);
		
		btnQuantity = new JButton("Quantity");
		btnQuantity.setBounds(109, 158, 89, 21);
		leftTopPanel.add(btnQuantity);
		
		btnDisplayAllItems = new JButton("Display all Items");
		btnDisplayAllItems.setBounds(10, 189, 278, 21);
		leftTopPanel.add(btnDisplayAllItems);
		leftPanel.add(leftBotPanel);
		
		lblNewLabel_1 = new JLabel("Results:");
		lblNewLabel_1.setBounds(10, 10, 393, 13);
		leftBotPanel.add(lblNewLabel_1);
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setBounds(10, 200, 393, 35);
		resultsBox = new JTextArea();
		scrollPane.add(resultsBox);
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
		
		lblCustomerInformation = new JLabel("Modify Inventory");
		lblCustomerInformation.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCustomerInformation.setBounds(126, 10, 177, 19);
		rightPanel.add(lblCustomerInformation);
		
		lblNewLabel_2 = new JLabel("Item ID:");
		lblNewLabel_2.setBounds(46, 42, 116, 13);
		rightPanel.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Item Name:");
		lblNewLabel_3.setBounds(46, 65, 116, 13);
		rightPanel.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Quantity:");
		lblNewLabel_4.setBounds(46, 88, 116, 13);
		rightPanel.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Price ($):");
		lblNewLabel_5.setBounds(46, 111, 116, 13);
		rightPanel.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("Supplier ID:");
		lblNewLabel_6.setBounds(46, 134, 116, 13);
		rightPanel.add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("Power Type (AC/DC):");
		lblNewLabel_7.setBounds(46, 157, 142, 13);
		rightPanel.add(lblNewLabel_7);
		
		idField = new JTextField();
		idField.setBounds(198, 39, 156, 19);
		rightPanel.add(idField);
		idField.setColumns(10);
		
		nameField = new JTextField();
		nameField.setBounds(198, 62, 156, 19);
		rightPanel.add(nameField);
		nameField.setColumns(10);
		
		quantityField = new JTextField();
		quantityField.setBounds(198, 85, 156, 19);
		rightPanel.add(quantityField);
		quantityField.setColumns(10);
		
		priceField = new JTextField();
		priceField.setBounds(198, 108, 156, 19);
		rightPanel.add(priceField);
		priceField.setColumns(10);
		
		supplierIdField = new JTextField();
		supplierIdField.setBounds(198, 131, 156, 19);
		rightPanel.add(supplierIdField);
		supplierIdField.setColumns(10);
		
		powerField = new JTextField();
		powerField.setBounds(198, 154, 156, 19);
		rightPanel.add(powerField);
		powerField.setColumns(10);
		
		lblNewLabel_8 = new JLabel("Item Type:");
		lblNewLabel_8.setBounds(46, 184, 116, 13);
		rightPanel.add(lblNewLabel_8);
		
		typeSelector = new JComboBox();
		typeSelector.setModel(new DefaultComboBoxModel(new String[] {"","Electrical", "Non-Eletrical"}));
		typeSelector.setEditable(false);
		typeSelector.setBounds(198, 180, 156, 21);
		rightPanel.add(typeSelector);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(74, 213, 73, 21);
		rightPanel.add(btnDelete);
		
		btnAdd = new JButton("Add");
		btnAdd.setBounds(157, 213, 75, 21);
		rightPanel.add(btnAdd);
		
		btnClear = new JButton("Clear");
		btnClear.setBounds(242, 213, 76, 21);
		rightPanel.add(btnClear);
		
		decreasePanel = new JPanel();
		decreasePanel.setBackground(UIManager.getColor("CheckBox.shadow"));
		decreasePanel.setBounds(34, 294, 338, 125);
		rightPanel.add(decreasePanel);
		decreasePanel.setLayout(null);
		
		lblNewLabel_9 = new JLabel("Decrease Inventory");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_9.setBounds(92, 10, 222, 20);
		decreasePanel.add(lblNewLabel_9);
		
		lblNewLabel_10 = new JLabel("Decrease Amount:");
		lblNewLabel_10.setBounds(24, 43, 119, 13);
		decreasePanel.add(lblNewLabel_10);
		
		decreaseField = new JTextField();
		decreaseField.setBounds(153, 40, 108, 19);
		decreasePanel.add(decreaseField);
		decreaseField.setColumns(10);
		
		decreaseBtn = new JButton("Decrease");
		decreaseBtn.setBounds(153, 69, 108, 21);
		decreasePanel.add(decreaseBtn);
		
		contentPane.add(panel);
		setTitle("Inventory Management App");
		setSize(841, 533);
		setVisible(true);
	}
	public String searchSelection() {
		if(selectId.isSelected()) {
			return "ID";
		}else if(selectName.isSelected()) {
			return "Name";
		}else{
			return null;
		}
	}

	public void displaySearch(Item[] results) {
		searchList.setListData(results);
	}
	public void addSearchItemListener(ActionListener listenForItemSearch) {
		btnItemSearch.addActionListener(listenForItemSearch);
	}
	public void addSearchEnterListener(ActionListener listenForItemSearch) {
		itemSearchField.addActionListener(listenForItemSearch);
	}
	public void addQuantityListener(ActionListener listenForQuantity) {
		btnQuantity.addActionListener(listenForQuantity);
	}
	public void addClearItemListener(ActionListener listenForItemClear) {
		btnItemClear.addActionListener(listenForItemClear);
	}
	public void addDisplayInventoryListener(ActionListener listenToDisplayAll) {
		btnDisplayAllItems.addActionListener(listenToDisplayAll);
	}
	public void addListListener(MouseListener listenForClick) {
		searchList.addMouseListener(listenForClick);
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
	public void addDecreaseListener(ActionListener listenToDecrease) {
		decreaseBtn.addActionListener(listenToDecrease);
	}
	public void addDecreaseEnterListener(ActionListener listenToDecrease) {
		decreaseField.addActionListener(listenToDecrease);
	}
	
	//All field/button getters
	public String getSearchedText() {
		return itemSearchField.getText();
	}
	public ButtonGroup getButtonGroup() {
		return radioButtons;
	}
	public JTextField getParameterBox() {
		return itemSearchField;
	}
	public JList<Item> getSearchList() {
		return searchList;
	}
	public JTextArea getMsgBox() {
		return resultsBox;
	}
	public JTextField getItemId() {
		return idField;
	}
	public JTextField getItemName() {
		return nameField;
	}
	public JTextField getItemQuantity() {
		return quantityField;
	}
	public JTextField getItemPrice() {
		return priceField;
	}
	public JTextField getSupplierId() {
		return supplierIdField;
	}
	public JTextField getPowerType() {
		return powerField;
	}
	public JTextField getItemDecrease() {
		return decreaseField;
	}
	public JComboBox getItemType() {
		return typeSelector;
	}
}
