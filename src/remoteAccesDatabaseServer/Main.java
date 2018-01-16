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
		ss = new ServerSide();
		
		try {
			 ns = new NetworkServer(portNet);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Doesn't contain correct variables 
		db = new DatabaseAccess("localhost", "SWDB_API_USER", "dean22", 1521, "SWDB_System");
				
		//DatabaseAccess e=new DatabaseAccess("","","");
		//e.closeDatabase();
	   //	db = new DatabaseAccess(location, username, password, portData, table);
		
		try {
			doTheThing(db.select(null));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * public void doTheThing - this is the method that runs the entire database. It waits for a message, then sends back the appropriate info. It then waits to receive the returned array, saves it properly and then loops back to the start.
	 * @throws IOException
	 */
	public static void doTheThing(String send[][][])throws IOException{
		String received;
		String[][] parameters;
		String[][][]toSend = send;
		
		//the client sends a message containing first what the info is, and then any additional shit. For example, for sending info to store, it will have that it's sending info to store, and then the number of incoming packages. I don't think packet loss will be a thing, so we should be good.
		
		while(true){
			received=ns.receiveMessage();

			if(true){
				parameters=decodeParameters(received);
				//send those parameters to the database
				ns.sendSuperArray(toSend);
			}
			if(true){
				//TODO - actually add this lul
				//ns.receiveSuperArray(x, y, z);
			}
		}
	}
	
	/**
	 * public void decodeParameters - this method turns a single string into a much easier to read 2d string array containing all the parameters to search for in the database.
	 * The first value is the actual parameter. The second is the value held by the parameter. The third is equals/greater than/smaller than for that parameter. The 4th value is a logical comparison between 2/3 and 5/6 - or, and, etc. The 5th and 6th are a copy of 2 and 3, so that you can have one parameter have multiple values. IE; is greater than 5 AND lower than 10. Is greater than 10 OR lower than 5
	 * @param toDecode
	 * @return
	 */
	public static String[][] decodeParameters(String toDecode){
		//TODO - make this method.
		String[][]toSend=null;
		return toSend;
	}
}