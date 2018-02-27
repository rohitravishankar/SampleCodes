import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Receiver {
	int portNumber = 3000;

	public void performReceiving() throws Exception {
		
		// Creating a Datagram Socket for communication
		DatagramSocket socket = new DatagramSocket(portNumber);
		
		// To receive a packet from the sender and put the contents of the message into a buffer
		byte[] buffer = new byte[1024];
		DatagramPacket receivingPacket = new DatagramPacket(buffer, buffer.length);
		socket.receive(receivingPacket);
		
		// Received Message
		String receivedMessage = new String(receivingPacket.getData(), 0, receivingPacket.getLength());
		System.out.println(receivedMessage);
		
		// To respond to the client
		
		// Getting port information
		InetAddress IPAddress = receivingPacket.getAddress();
        int port = receivingPacket.getPort();
        
        //Building response
        String serverResponseMessage = "hello from server";
        buffer = serverResponseMessage.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, IPAddress, port);
        socket.send(sendPacket);
        
		socket.close();
	}

	public static void main(String[] args) throws Exception {
		Receiver receiver = new Receiver();
		receiver.performReceiving();
	}
}
