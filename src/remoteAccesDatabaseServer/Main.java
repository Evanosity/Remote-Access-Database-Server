package remoteAccesDatabaseServer;

import java.io.IOException;

import javax.swing.JFrame;

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
		db = new DatabaseAccess(location, username, password, portData, table);
	}
}
