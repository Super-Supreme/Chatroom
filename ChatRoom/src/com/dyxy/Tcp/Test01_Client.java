package com.dyxy.Tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Test01_Client {
	public static void main(String[] args) {
		
		Socket client = null;
		Send sender=null;
		Receive receiver=null;
		try {
			client = new Socket("localhost", 9999);
			System.out.println("已连接到服务器。。。");
			Scanner input = new Scanner(System.in);
			input.useDelimiter("\n");
			sender=new Send(client);
			receiver=new Receive(client);
			
			boolean isRunning=true;
			while(isRunning) {
				if(input.hasNext()) {
					String inStr=input.next().trim();
					sender.send_str(inStr);
					if(inStr.equals("bye")) {
						isRunning=false;
					}
					String isHas=null;
					if((isHas=receiver.receive())!=null) {
						System.out.println(isHas);
					}
				}
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			sender.close();
			receiver.close();
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	static class Receive{
		DataInputStream in = null;
		Socket client=null;
		public Receive(Socket client) {
			try {
				this.client=client;
				in = new DataInputStream(client.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		private String receive() {
			String receive_str="";
			try {
				receive_str=in.readUTF();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return receive_str;
		}
		private void close() {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	//发送
	static class Send{
		DataOutputStream out = null;
		Socket client=null;
		public Send(Socket client) {
			this.client=client;
			try {
				out = new DataOutputStream(client.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		private void send_str(String str) {
			try {
				out.writeUTF(str);
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		private void close() {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
