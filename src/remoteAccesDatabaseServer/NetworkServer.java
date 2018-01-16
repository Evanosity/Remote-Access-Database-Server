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
	 * public String[][][] receiveSuperArray - this method will receive a 3d array from the client 
	 * @param x - the size of the first array
	 * @param y - the size of the second array
	 * @param z - this size of the third array
	 * @return toReturn - the organized 3d array
	 * @throws IOException
	 */
	public String[][][] receiveSuperArray()throws IOException{
		int x=0;
		int y=0;
		int z=0;
		String size="";

		size=receive.readUTF();
		
		x=Integer.parseInt(size.substring(size.lastIndexOf(":")+1, size.length()));
		size=size.substring(0, size.lastIndexOf(":"));
		
		y=Integer.parseInt(size.substring(size.lastIndexOf(":")+1, size.length()));
		size=size.substring(0, size.lastIndexOf(":"));
		
		z=Integer.parseInt(size.substring(size.lastIndexOf(":")+1, size.length()));
		size=size.substring(0, size.lastIndexOf(":"));
		
		String[][][]toReturn=new String[x][y][z];
		String[]unorganized=new String[x*y*z+1];
		int a=0;
		
		
		
		//this loop will simply collect all the information and then immediately store it.
		for(int i=0; i!=toReturn.length; i++){
			for(int k=0; k!=toReturn[i].length;k++){
				for(int l=0; l!=toReturn[i][k].length;l++){
					a++;
					unorganized[a]=receive.readUTF();
					System.out.println(unorganized[a]);
				}
			}
		}
		
		//this loop will evaluate all of the information we received and organize it appropriately.
		for(int i=0; i!=unorganized.length; i++){
			int xe;
			int ye;
			int ze;
			
			xe=Integer.parseInt(unorganized[i].substring(0, unorganized[i].indexOf(":")));
			unorganized[i]=unorganized[i].substring(unorganized[i].indexOf(":")+1,unorganized[i].length());
			
			ye=Integer.parseInt(unorganized[i].substring(0, unorganized[i].indexOf(":")));
			unorganized[i]=unorganized[i].substring(unorganized[i].indexOf(":")+1,unorganized[i].length());
			
			ze=Integer.parseInt(unorganized[i].substring(0, unorganized[i].indexOf(":")));
			unorganized[i]=unorganized[i].substring(unorganized[i].indexOf(":")+1,unorganized[i].length());
			
			toReturn[xe][ye][ze]=unorganized[i];
		}
		
		for(int i=0; i!=toReturn.length; i++){
			for(int k=0; k!=toReturn[i].length;k++){
				for(int l=0; l!=toReturn[i][k].length;l++){
					System.out.println(toReturn[i][k][l]);
				}
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
	 * public void sendSuperArray - this method will send a 3d array over the network. It's a bit convoluted but it works.
	 * @param toSend - this is the 3d to send.
	 * @throws IOException
	 */
	public void sendSuperArray(String[][][]toSend)throws IOException{
		int fullLength=0;
		int xr=0;
		int yr=0;
		int zr=0;
		//this loop will read the size of the array. This is important, as the client needs to prepare for the volume of information we are sending.
		for(int i=0; i!=toSend.length; i++){
			for(int k=0; k!=toSend[i].length;k++){
				for(int l=0; l!=toSend[i][k].length;l++){
					fullLength++;
					xr=i;
					yr=k;
					zr=l;
				}
			}
		}
		send.writeUTF("messagelength:"+xr+":"+yr+":"+zr);
		
		//This loop will actually send each piece of information. However, it also adds an array ID string to each one of the messages.
		//This is so that if the packets for some reason don't arrive in the proper order, the receiver can piece it back together.
		for(int i=0; i!=toSend.length; i++){
			for(int k=0; k!=toSend[i].length;k++){
				for(int l=0; l!=toSend[i][k].length;l++){
					send.writeUTF(i+":"+k+":"+l+":"+toSend[i][k][l]);
					System.out.println(toSend[i][k][l]);
				}
			}
		}
		send.writeUTF("weeeeeeeeeeee");
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
