package remoteAccesDatabaseServer;

import java.io.IOException;

public class Main {	
<<<<<<< HEAD
	public static void main(String[] args) throws IOException{
		//ServerSide ss = new ServerSide();
		//ss.registerUser();
		
		//DatabaseAccess e=new DatabaseAccess("","","");
		//e.closeDatabase();
=======
	public static void main(String[] args){
		ServerSide ss = new ServerSide();
		//ss.registerUser();
>>>>>>> 646d7fecf9bae4b588966a2a05338b690d748ac8
		
		NetworkServer ns=new NetworkServer(6066);
		System.out.println(ns.receiveMessage());
	}
}
