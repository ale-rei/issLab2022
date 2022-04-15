package it.unibo.comm2022.tcp;

import java.io.*;
import java.net.Socket;

import it.unibo.comm2022.interfaces.Interaction;

public class TcpConnection implements Interaction {

	private Socket socket;
	private BufferedReader inputChannel;
	private DataOutputStream outputChannel;
	
	public TcpConnection(Socket socket) throws IOException{
		this.socket=socket;
		OutputStream outStream 	       = socket.getOutputStream();
		InputStream inStream  	       = socket.getInputStream();
		inputChannel = new BufferedReader(new InputStreamReader(inStream));
		outputChannel = new DataOutputStream (outStream);
	}
	
	@Override
	public void forward(String msg) {
		try {
			outputChannel.writeBytes(msg+"\n");
			outputChannel.flush();
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public String request(String msg) {
		forward(msg);
		String answer = receiveMsg();
		return answer;
	}

	@Override
	public String receiveMsg() {
		try {
			String line= inputChannel.readLine();
			return line;
		}catch(Exception e) {
			System.out.println("TcpConnection");
			return null;
		}
	}

	@Override
	public void reply(String msg) {
		forward(msg);
	}

	@Override
	public void close(){
		try {
			socket.close();	
			System.out.println("TcpConnection | Closed");
		}catch(IOException e) {
			System.out.println("TcpConnectionClose | ERROR "+e.getMessage());
		}
	}
}
