package remoteAccesDatabaseServer;

import java.io.IOException;

public class Main {	
	//todo-make it so the database can accept an array of parameters(cam?)
	//make it so database can return a 3d string array
	//test it I guess
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
		db = new DatabaseAccess("localhost", "SWDB_API_USER", "dean22", 1521, "SWDB_SYSTEM");
		
		String info[][] = null;
		if(db.select() != null) {
			info = db.select();
		}
		for(int i  =0; i< info.length; i++) {
			for(int f = 0; f < info[0].length; f++) {
				System.out.print(info[i][f] + " ");
			}
			System.out.println();
		}
		
		try {
			ns=new NetworkServer(portNet);
			ns.sendDoubleArray(info);
			String[][]toSend=ns.receiveDoubleArray();
			for(int i=0;i!=3;i++){
				db.update(toSend[i]);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		ss = new ServerSide();
	}
	
	public void Shutdown() {
		try {
			db.commit();
			db.closeDatabase();
			ns.shutdown();
		}catch(Exception e) {
			//cry I guess?
		}
	}
}