package remoteAccesDatabaseServer;

public class MainDataTesting {
	
	public static void main(String[] args) {
		DatabaseAccess db = new DatabaseAccess("localhost", "SWDB_API_USER", "dean22", 1521, "SWDB_SYSTEM");
		
		String[] infoTest = db.getColumnNames();
		
		for(int i = 0; i < infoTest.length; i++) {
			System.out.println(infoTest[i]);
		}
	}

}
