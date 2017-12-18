package remoteAccesDatabaseServer;

import java.io.IOException;
<<<<<<< HEAD

public class Main {	
	public static void main(String[] args) throws IOException{
		//ServerSide ss = new ServerSide();
		//ss.registerUser();
		
=======

import javax.swing.JFrame;

public class Main {	
	
	private final static int port = 6066;
	private static NetworkServer ns;
	private static ServerSide ss;
	private static DatabaseAccess db;
	
	public static void main(String[] args){
		ss = new ServerSide();
		
		try {
			 ns = new NetworkServer(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		db = new DatabaseAccess("SWDB_System");
				
>>>>>>> GUI
		//DatabaseAccess e=new DatabaseAccess("","","");
		//e.closeDatabase();
	}
}