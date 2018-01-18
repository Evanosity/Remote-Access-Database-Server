package remoteAccesDatabaseServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
	private String columnNames[];
	
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
	
	public String[][] select() {
        int rows = count();
        int numOfColumns = columns();
        System.out.println(rows);
        System.out.println(numOfColumns);
        //int currentColumn = 1;
        String[][] info = new String[rows][numOfColumns];
        
        try{ 
            
            //Execute SQL query
            ResultSet rs=stmt.executeQuery("select * from " + useTable);
            
            //Process Result Set
            int count = 0;
            while(rs.next()) {
            	for(int f = 0; f < numOfColumns; f++) {
        			info[count][f] = rs.getString(f + 1);
        		}
            	count++;
            }
            
            
            //con.close();  
            }catch(Exception e){ System.out.println("Here is the error");}
         
        return info;
    }
	
	public void update(String[] returnInfo) {
		try {
			columnNames = getColumnNames();
			for(int i = 0; i < columnNames.length; i++) {
				if(returnInfo[i].equals("null")) {
					returnInfo[i] = null;
				}
				ResultSet rs = stmt.executeQuery("UPDATE " + useTable + " set " + columnNames[i] +" = '" + returnInfo[i] + "' where " + columnNames[0] + "=" + returnInfo[0]);
			}
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
			System.out.println(e);
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
			System.out.println(e);
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
			System.out.println(e);
		}
	}
	
	public int count() {
		int num = 0;
		
		try {
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM " + useTable);
			if(rs.next()) {
				num = Integer.parseInt(rs.getString(1));
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		
		return num;
	}
	
	public int columns() {
		int num = 0;
		
		try {
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM ALL_TAB_COLUMNS WHERE TABLE_NAME='" + useTable + "'");
			if(rs.next())
				num = Integer.parseInt(rs.getString(1));
		}catch(Exception e) {
			
		}
		
		return num;
	}
	
	public String[] getColumnNames() {
		String[] columnNames = new String[columns()];
		int count = 0;
		try {
			ResultSet rs = stmt.executeQuery("SELECT COLUMN_NAME FROM ALL_TAB_COLUMNS WHERE TABLE_NAME ='" + useTable +"' ORDER BY COLUMN_ID");
			while(rs.next()) {
				columnNames[count] = rs.getString(1);
				count++;
			}
		}catch(Exception e) {
			
		}
		return columnNames;
	}
	
	public String[][][] extendArray3D(String[][][] toExtend, int val1, int val2, int val3){
		String[][][] extended;
		
		extended=new String[val1][val2][val3];
		for(int i=0;i!=toExtend.length;){
			for(int f = 0; f != toExtend[0].length;) {
				for(int j = 0; j != toExtend[0][0].length;) {
					extended[i][f][j] = toExtend[i][f][j];
				}
			}
		}
		
		return extended;
	}
}
