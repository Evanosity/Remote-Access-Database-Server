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
	
	private String[] messageBuffer;
	
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
		
		//collectMessage();
	}
	
	/**
	 * private void collectMessage - this method constantly looks for new messages to be received, and then throws them into a big array that can be recalled at any time.
	 * @throws IOException
	 */
	private void collectMessage() throws IOException{
		messageBuffer=new String[1];
		int z=0;
		
		while(true){
			if(z>=messageBuffer.length){
				messageBuffer=extendArray(messageBuffer);
			}
			messageBuffer[z]=receive.readUTF();
			z++;
		}
	}
	
	/**
	 * public String receiveMessage - this method will read an input from the client, and then return it.
	 * @return the received message
	 * @throws IOException
	 */
	public String[] receiveMessage() throws IOException{
		//return receive.readUTF();
		return messageBuffer;
	}
	
	/**
	 * public String getMessafes - this method will return all of the stored messagess.
	 * @return the received messages
	 * @throws IOException
	 */
	public String[] getMessages() throws IOException{
		return messageBuffer;
	}
	
	/**
	 * public void sendMessage - this method sends a specified message to the client.
	 * @param toSend - the message to send.
	 */
	public void sendMessage(String toSend)throws IOException{
		send.writeUTF(toSend);
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
