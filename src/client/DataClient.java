package client;

import java.net.InetAddress;
import java.net.Socket;
import server.DataServer;
import java.util.List;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class DataClient {

	private List<Integer> data;
		
	public DataClient(List<Integer> data) {
		this.data = data;
	}
	
	public void getData() throws IOException{
		Socket clientSocket = new Socket(InetAddress.getLoopbackAddress(), DataServer.PORT);
		DataOutputStream dout = new DataOutputStream(clientSocket.getOutputStream());
		String out = "";
		for(int i = 0; i < data.size(); i++) {
			out = out + data.get(i);
			if(i != data.size() - 1)
				out = out + ", ";
		}		
		dout.writeUTF(out);
		System.out.println("Client is transfered data");
		dout.flush();
		DataInputStream din = new DataInputStream(clientSocket.getInputStream());
		System.out.println("This is responce from server: " + din.readUTF());
		clientSocket.close();
	}
	
	public static void main(String[] args) throws IOException{		
		List<Integer> forTransmition = List.of(10, 20 , 30 , 40);
		DataClient dataClient = new DataClient(forTransmition);
		dataClient.getData();		
	}
}
