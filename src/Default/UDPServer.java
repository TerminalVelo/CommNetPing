
/**
 * UDPServer.java
 *
 * For use by CMPEN 461 Fall 2016 Penn State only
 */
package Default;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {

    /**
     * Main function. Read command line argument and start the server thread.
     */
    public static void main(String[] args) throws Exception {
	
    	int port = 0;

    	 /** Parse port number from command line **/
    	 try {
    	 port = Integer.parseInt(args[0]);
    	 } catch (ArrayIndexOutOfBoundsException e) {
    	 System.out.println("Need one argument: port number.");
    	 System.exit(-1);
    	 } catch (NumberFormatException e) {
    	 System.out.println("Please give port number as integer.");
    	 System.exit(-1);
    	 }
    	 
    	 /** Create a new datagram socket at the port **/
    	 DatagramSocket serverSocket = new DatagramSocket(port);
    	 byte[] receiveData = new byte[512];
    	 byte[] sendData = new byte[512];
    	 /** Let the user know the server is running **/
    	 System.out.println("The UDP server is listening on port " + port);
    	 
    	 while (true) {


    		 DatagramPacket receivePacket = new DatagramPacket(receiveData, 512);
    		 serverSocket.receive(receivePacket);

    		 /* Print the message received **/
    		 String sentence = new String(receivePacket.getData());
    		 if (!sentence.equals("")) {
    		 System.out.println(sentence.trim());
    		 }

    		 /* Get the IP Address of the Sender **/
    		 InetAddress IPAddress = receivePacket.getAddress();

    		 /* Get the port of the Sender **/
    		 int senderPort = receivePacket.getPort();

    		 /* Prepare the data to send back **/
    		 sendData = receiveData;
    		 DatagramPacket sendPacket = new DatagramPacket(sendData,
    		 sendData.length, IPAddress, senderPort);

    		 /* Send the packet **/
    		 serverSocket.send(sendPacket);

    		 }
    }
}
