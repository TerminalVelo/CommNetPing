
/**
 * PingClient.java
 *
 * For use by CMPEN 461 Fall 2016 Penn State only
 */
package Default;
import java.net.*;
import java.io.*;

public class PingClient {
	
	DatagramSocket socket;
	
	static final int MAX_PING_LEN = 512;
	
    public void createSocket() {
    	try {
    		socket = new DatagramSocket(0);
    		
    	} catch (SocketException e)	{
    		System.out.println("Error creating socket: " + e);
    	}
    }
    
    public void createSocket(int port) {
    	try {
    		socket = new DatagramSocket(port);
    		
    	} catch (SocketException e)	{
    		System.out.println("Error creating socket: " + e);
    	}
    }
    
    public void sendPing(Message ping)	{
    	
    	InetAddress host = ping.getIP();
    	int port = ping.getPort();
    	String message = ping.getContents();
    	
    	try	{
    		DatagramPacket packet = new DatagramPacket(message.getBytes(), message.getBytes().length,host,port);
    				
    		socket.send(packet);
    		System.out.println("Sent message to " + host + ":" + port);
    	} catch (IOException e) {
    		System.out.println("Error sending packet: " + e);
    	}
    }
    
	public Message receivePing() throws SocketTimeoutException	{
	    	
			byte recvBuf[] = new byte[MAX_PING_LEN];
	    	DatagramPacket recvPacket = new DatagramPacket(recvBuf, MAX_PING_LEN);
	    	Message reply = null;
	    	
	    	/*Read message from socket. */
	    	try	{
	    		socket.receive(recvPacket);
	    		
	    		System.out.println("Received message from " + recvPacket.getAddress() + ":" + recvPacket.getPort());
	    		String recvMsg = new String(recvPacket.getData());		
	    		System.out.println(recvMsg.trim());
	    		reply = new Message(recvPacket.getAddress(), recvPacket.getPort(), recvMsg);
	    		
	    	} catch (SocketTimeoutException e) {
	    		throw e;
	    	} catch (IOException e)	{
	    		System.out.println("Error reading from socket: " + e);
	    	}
	    	return reply;
	    }
}