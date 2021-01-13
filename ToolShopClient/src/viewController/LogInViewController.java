package viewController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.LogInGUI;
/**
 * This class contains the action listeners for the log in window
 * @author Karlen Chow
 * @version 1.0
 * @since November 29, 2020
 */
public class LogInViewController implements LogInCredentials {
	private LogInGUI gui;
	private boolean loggedIn;
	
	public LogInViewController() {
		gui = new LogInGUI();
		addAllActionListeners();
		gui.setVisible(true);
		loggedIn = false;
	}
	private void addAllActionListeners() {
		gui.addLogInListener(new LogInListener());
		gui.addForgotListener(new forgotCredentialsListener());
		gui.addLoginEnter(new LogInListener());
	}
	public boolean getLoggedIn() {
		return loggedIn;
	}
	class LogInListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String user = gui.getUserName().getText();
			String password = String.copyValueOf(gui.getPassword().getPassword());
			if(user.equals(USERNAME)&&password.equals(PASSWORD)) {
				loggedIn = true;
				gui.dispose();
			}else {
				gui.displayMessage("Incorrect Credentials");
			}
		}
	}
	class forgotCredentialsListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			gui.displayMessage("Username: "+USERNAME+"\nPassword: "+PASSWORD);
		}
	}
}
