package com.dyxy.chat03;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Client_receive implements Runnable{
	private Socket client=null;
	private DataInputStream din=null;
	private boolean isRunning;
	public Client_receive(Socket client) {
		this.client=client;
		this.isRunning=true;
		try {
			din=new DataInputStream(client.getInputStream());
		} catch (Exception e) {
			ServerConnect.close(din,client);
			isRunning=false;
		}
	}
	private String receive() {
		String inStr=null;
		try {
			inStr=din.readUTF();
		} catch (IOException e) {
			System.out.println("============");
			ServerConnect.close(din,client);
			isRunning=false;
		}
		return inStr;
		
	}
	@Override
	public void run() {
		while(isRunning) {
			String msg=receive();
			if(msg!=null) {
				System.out.println(msg);
			}
		}
		
	}
	
}
