package remoteAccesDatabaseServer;

import javax.swing.JFrame;

public class Main {	
	public static void main(String[] args){
		ServerSide ss = new ServerSide();
		ss.registerUser();
				
		//DatabaseAccess e=new DatabaseAccess("","","");
		//e.closeDatabase();
	}
}
