package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class DataServer {
	
	public static final Integer PORT = 8888;
	
	public static Socket start() throws IOException {
		ServerSocket serverSocket = new ServerSocket(PORT);		
		return serverSocket.accept();
	}
	
	public static void main(String[] args) throws IOException {
		Socket clientSocket = DataServer.start();
		DataInputStream din = new DataInputStream(clientSocket.getInputStream());
		System.out.println("Server is reading data: " + din.readUTF());
		DataOutputStream dout = new DataOutputStream(clientSocket.getOutputStream());
		dout.writeUTF("Server has been read your message");
	}
}
