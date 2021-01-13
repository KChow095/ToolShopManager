package controller;
/**
 * This interface contains the credentials to log in and connect to the MySQL database
 * @author Karlen Chow
 * @version 1.0
 * @since November 29, 2020
 *
 */
public interface DatabaseCredentials {
	static final String CONNECTION_INTO = "jdbc:mysql://localhost:3306/ToolShop";
	
	static final String LOGIN = "root";
	
	static final String PASSWORD = "password";
}
