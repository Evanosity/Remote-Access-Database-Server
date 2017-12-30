package remoteAccesDatabaseServer;

import java.io.IOException;

public class Main {	
	
	private final static int portNet = 6066;	//Networking port
	private static NetworkServer ns;
	private static ServerSide ss;
	private static DatabaseAccess db;
	private final static String location = "localhost";
	private final static String username = "SWDB_API_USER";
	private final static String password = "dean22";
	private final static int portData = 1521;	//Database Access Port
	private static String table = "SWDB_System";
	
	public static void main(String[] args){
		ss = new ServerSide();
		
		try {
			 ns = new NetworkServer(portNet);
		} catch (IOException e) {
			e.printStackTrace();
		}
<<<<<<< HEAD
		// Doesn't contain correct variables 
		db = new DatabaseAccess("localhost", "SWDB_API_USER", "dean22", 1521, "SWDB_System");
				
		//DatabaseAccess e=new DatabaseAccess("","","");
		//e.closeDatabase();
=======
		db = new DatabaseAccess(location, username, password, portData, table);
		
		try {
			ns.sendMessage(/* This will send an array of information */ "Yes" );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
>>>>>>> GUI
	}
}