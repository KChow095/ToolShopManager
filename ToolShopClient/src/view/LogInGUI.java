package view;
import java.awt.Container;

import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
/**
 * This class creates the log in GUI
 * @author Karlen Chow
 * @version 1.0
 * @since November 29, 2020
 */
public class LogInGUI extends JFrame{
	private JButton logInBtn,forgotUserPassBtn;
	private JLabel title, userNameLabel, passwordLabel;
	private Container contentPane;
	private JPanel panel;
	private JTextField userNameField;
	private JPasswordField passwordField;
	/**
	 * Create the application.
	 */
	public LogInGUI() {
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
		panel.setBounds(0, 5, 306, 247);
		panel.setLayout(null);
		
		contentPane.add(panel);
		
		title = new JLabel("Tool Shop Management App");
		title.setFont(new Font("Tahoma", Font.BOLD, 16));
		title.setBounds(27, 36, 279, 35);
		panel.add(title);
		
		userNameField = new JTextField();
		userNameField.setBounds(136, 87, 109, 19);
		panel.add(userNameField);
		userNameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(136, 116, 109, 19);
		panel.add(passwordField);
		passwordField.setColumns(10);
		
		userNameLabel = new JLabel("User Name:");
		userNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		userNameLabel.setBounds(46, 90, 96, 13);
		panel.add(userNameLabel);
		
		passwordLabel = new JLabel("Password:");
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		passwordLabel.setBounds(46, 119, 80, 13);
		panel.add(passwordLabel);
		
		logInBtn = new JButton("Log In");
		logInBtn.setBounds(75, 154, 141, 21);
		panel.add(logInBtn);
		
		forgotUserPassBtn = new JButton("Forgot User/Pass");
		forgotUserPassBtn.setBounds(75, 184, 141, 21);
		panel.add(forgotUserPassBtn);
		setTitle("Log In");
		setSize(320, 289);
		setVisible(true);
	}
	public void displayMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}
	public void addLogInListener(ActionListener listenForLogIn) {
		logInBtn.addActionListener(listenForLogIn);
	}
	public void addForgotListener(ActionListener listenToForgot) {
		forgotUserPassBtn.addActionListener(listenToForgot);
	}
	public void addLoginEnter(ActionListener listenForCustClear) {
		passwordField.addActionListener(listenForCustClear);
	}
	
	public JTextField getUserName() {
		return userNameField;
	}
	public JPasswordField getPassword() {
		return passwordField;
	}
}
