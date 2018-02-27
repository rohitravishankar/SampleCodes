

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Sender {
	static String hostName = "127.0.0.1";

	public void performSending() throws Exception {

		// Creating a Datagram Socket for communication
		DatagramSocket client = new DatagramSocket();
		InetAddress inetAddress = InetAddress.getByName(hostName);

		// Sending information to the receiver
		String str = "hello world";
		DatagramPacket sendPacket = new DatagramPacket(str.getBytes(), str.getBytes().length, inetAddress, 3000);		
		client.send(sendPacket);

		// Receiving information from the sender
		byte[] receiveData = new byte[1024];
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		client.receive(receivePacket);		
		System.out.println("FROM SERVER:" + new String(receivePacket.getData()));
		

		client.close();
	}

	public static void main(String[] args) throws Exception {
		Sender sender = new Sender();
		sender.performSending();
	}
}
