package remoteAccesDatabaseServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * public class NetworkServer - this is the framework for networking, and it just has the very basic receive/send methods. These will be used
 * by other classes to effectively send and receive data.
 * @author Evanosity
 * @date December 11 2017
 */
public class NetworkServer {
	private ServerSocket serverSocket;
	private Socket server;
	
	private DataInputStream receive;
	private DataOutputStream send;
	
	private int port;
	
	
	/**
	 * public Networking - constructor for this class; used to set up the class and prepares for data to be sent/received.
	 * @param portNumber - the port to be used.
	 * @throws IOException
	 */
	public NetworkServer(int portNumber) throws IOException{
		port=portNumber;
		serverSocket=new ServerSocket(port);
		serverSocket.setSoTimeout(0);
		
		try{
			server=serverSocket.accept();
		}
		catch(SocketTimeoutException t){
			System.out.println("Socket time out");
		}
		catch(IOException e){
			System.out.println("This is bad.");
		}
		receive=new DataInputStream(server.getInputStream());
		send=new DataOutputStream(server.getOutputStream());
		
	}
	
	/**
	 * public String receiveMessage - this method will read an input from the client, and then return it.
	 * @return the received message
	 * @throws IOException
	 */
	public String receiveMessage() throws IOException{
		return receive.readUTF();
	}
	
	public String[] receiveArray(int size) throws IOException{
		String[] toReturn=new String[size];
		for(int i=0; i!=size; i++){
			toReturn[i]=receive.readUTF();
		}
		return toReturn;
	}
	
	/**
	 * public String[][] receiveDoubleArray - this method will receive a 2d array over a socket. 
	 * @return
	 * @throws IOException
	 */
	public String[][] receiveDoubleArray()throws IOException{
		String size=receive.readUTF();
		int x=Integer.parseInt(size.substring(0, size.lastIndexOf(":")));
		int y=Integer.parseInt(size.substring(size.indexOf(":")+1, size.length()));
		
		String[][]toReturn=new String[x][y];
		
		for(int i=0;i!=x;i++){
			for(int f=0;f!=y;f++){
				toReturn[i][f]=receive.readUTF();
				System.out.println("Receiving: "+toReturn[i][f]);
			}
		}
		return toReturn;
	}
	
	/**
	 * public void sendMessage - this method sends a specified message to the client.
	 * @param toSend - the message to send.
	 */
	public void sendMessage(String toSend)throws IOException{
		send.writeUTF(toSend);
	}
	
	/**
	 * public void sendArray - this method sends a 1d string array to the client.
	 * @param toSend - string array to send
	 * @throws IOException
	 */
	public void sendArray(String[] toSend)throws IOException{
		send.writeUTF("messagelength:"+toSend.length);
		for(int i=0; i!=toSend.length; i++){
			send.writeUTF("arrayID:"+i+":Message:"+toSend[i]);
		}
	}
	
	/**
	 * public void sendDoubleArray - this method sends a 2d array over the socket.
	 * @param toSend - this is the array to send.
	 * @throws IOException
	 */
	public void sendDoubleArray(String[][]toSend)throws IOException{
		int x=toSend.length;
		int y=toSend[0].length;
		send.writeUTF(toSend.length+":"+toSend[0].length);
		
		for(int i=0;i!=x;i++){
			for(int f=0;f!=y;f++){
				send.writeUTF(i+":"+f+":"+toSend[i][f]);
				System.out.println("Sending: "+toSend[i][f]);
			}
		}
	}
	
	/**
	 * public void shutdown - this method safely closes the connection and server.
	 */
	public void shutdown()throws IOException{
		server.close();
	}
	
	/**
	 * public int getPort - this method returns the port used by this server.
	 * @return the specified port.
	 */
	public int getPort(){
		return port;
	}
	
	/**
	 * public String getConnectionAddress - returns the IP address of the connected client.
	 * @return the correct IP
	 */
	public String getConnectionAddress(){
		return null;
	}
	
	/**
	 * private String[] extendArray - this is a method for enlarging an array if it becomes full.
	 * @param toExtend
	 * @return
	 */
	private String[] extendArray(String[]toExtend){
		String[]extended;
		
		extended=new String[toExtend.length*2];
		for(int i=0;i!=toExtend.length;){
			extended[i]=toExtend[i];
		}
		
		return extended;
	}
}
