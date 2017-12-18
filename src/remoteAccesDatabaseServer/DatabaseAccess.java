package remoteAccesDatabaseServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * DatabaseAccess class - this class contains the methods for retrieving information from the database, and initializing the database.
 * @author Evanosity
 * @date November 29 2017
 */
public class DatabaseAccess {
	//the connection to the database in object form
	private Connection con;
	private Statement stmt;
	private String useTable;
	private String pass;
	private String uname;
	private String host;
	private int port;
	
	/**
	 * public DatabaseAccess - the main constructor for the database access.
	 * @param location - the "host" of the database.
	 * @param username - the username for the database
	 * @param password - the password for the database
	 */
	public DatabaseAccess(String location, String username, String password, int portNum, String table){
		host=location;
		uname=username;
		pass=password;
		port = portNum;
		useTable = table;
		initializeDatabase();
	}

	private void initializeDatabase() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			//Class.forName("com.timetenjdbc.TimesTenDriver"); 
			
			//Get Connection
			con = DriverManager.getConnection(  
					"jdbc:oracle:thin:" + uname + "/" + pass + "@" + host + ":" + port + ":SWDB");
			//"jdbc:oracle:thin:SWDB_API_USER/dean22@localhost:1521:SWDB"
			
			//Create Statement
			stmt = con.createStatement();
			//System.out.println("Got Statement!");
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
	public void select() {
		
		try{ 
			
			//Execute SQL query
			ResultSet rs=stmt.executeQuery("select * from " + useTable);
			//System.out.println("Executed Query");
			
			//Process Result Set
			while(rs.next())  
			System.out.println(rs.getInt(1) + " : " + rs.getString(2) + " : " + rs.getString(3) + " : " + rs.getString(4) + " : " + rs.getString(5) + " : " + rs.getString(6) + " : " + rs.getInt(7) + " : " + rs.getString(8) + " : " + rs.getString(9) + " : " + rs.getInt(10) + " : " + rs.getString(11) + " : " + rs.getString(12));//Currentl for table SWDB_SYSTEM
			System.out.println("Done");
			con.close();  
			}catch(Exception e){ System.out.println(e);}  
			}
	
	public void update() {
		try {
			ResultSet rs = stmt.executeQuery("UPDATE swdb_invoice set invoice_no = 'INV-0003' where invoice_ID=9");
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void insert() {
		try {
			ResultSet rs = stmt.executeQuery("INSERT INTO swdb_invoice"
					+ "( invoice_id, company_id, client_id, invoice_no, invoice_date, invoice_transaction_amount, invoice_paid_flag, invoice_desc,created_system_id, created_user, created_date, updated_system_id, updated_user, updated_date ) "
			        + "VALUES ( null, 2, 3, 'INV-0004', TO_DATE('20171214','YYYYMMDD'), 1000, 'N', 'Test', 2, 'swdb_api_user', TO_DATE('20171214','YYYYMMDD'), 2, 'swdb_api_user', TO_DATE('20171214','YYYYMMDD') )");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void delete() {
		try {
			ResultSet rs = stmt.executeQuery("DELETE from swdb_invoice where invoice_ID=12");
		}catch(Exception e) {
			
		}
	
	}
	
	public void commit() {
		try {
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void exitDatabase() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
}