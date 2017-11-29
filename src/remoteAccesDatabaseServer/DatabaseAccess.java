package remoteAccesDatabaseServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DatabaseAccess class - this class contains the methods for retrieving information from the database, and initializing the database.
 * @author Evanosity
 * @date November 29 2017
 */
public class DatabaseAccess {
	//parameters for database access
	private String host;
	private String uname;
	private String pass;
	
	//the connection to the database in object form
	private Connection con;
	
	/**
	 * public DatabaseAccess - the main constructor for the database access.
	 * @param location - the "host" of the database.
	 * @param username - the username for the database
	 * @param password - the password for the database
	 */
	public DatabaseAccess(String location, String username, String password){
		host=location;
		uname=username;
		pass=password;
		initializeDatabase();
	}
	
	/**
	 * public String[][]getData - this is the main database access method; where it will fetch the data and then send it back in array form.
	 * @param query - The query for information, in string form.
	 * @return the generated section of data
	 */
	public String[][]getData(String[] query){
		//this is the one part of the project that is gonna bust our balls. Sending proper queries to the database..... good god.
		return null;
	}
	
	/**
	 * private void initializeDatabase - this is the method that initializes the connection to the database; data can then be fetched.
	 * FOR FUTURE - when starting the database side, user will have to enter the username, the password and the "host" of the database.
	 * We will keep it as something simple.
	 */
	private void initializeDatabase(){
		try{
			con=DriverManager.getConnection(host, uname, pass);
			//this *should* work.... However, we will need to set up an identical test environment to the one that will be on the permanent host computer.
			con.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * public void closeDatabase - simple method for closing the database.
	 */
	public void closeDatabase(){
		try{
			con.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
}
