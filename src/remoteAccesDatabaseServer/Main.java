package remoteAccesDatabaseServer;

import java.io.IOException;

public class Main {	
	public static void main(String[] args) throws IOException{
		//ServerSide ss = new ServerSide();
		//ss.registerUser();
		
		//DatabaseAccess e=new DatabaseAccess("","","");
		//e.closeDatabase();
		
		NetworkServer ns=new NetworkServer(6066);
		System.out.println(ns.receiveMessage());
	}
}
