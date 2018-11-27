package com.dyxy.Tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Test01_MoreServer {

	public static void main(String[] args) {
		
		Socket client = null;
		ServerSocket server = null;
		try {
			System.out.println("服务器已启动，等待连接。。。。");
			server = new ServerSocket(9999);
			boolean isRunning=true;
			while(isRunning) {
				client = server.accept();
				System.out.println("客户端连接成功。。");
				new Thread(new ServerConnect(client)).start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	static class ServerConnect implements Runnable{

		DataOutputStream out = null;
		DataInputStream in = null;
		Socket client = null;
		public ServerConnect(Socket client) {
			this.client=client;
			try {
				in = new DataInputStream(client.getInputStream());
				out = new DataOutputStream(client.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
				try {
					client.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		private String receiver() {
			String receive_str="";
			try {
				receive_str=in.readUTF();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return receive_str;
		}
		
		private void sender(String send_str) {
			try {
				out.writeUTF(send_str);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		@Override
		public void run() {
			
			boolean flag = true;
			String str;
			while (flag) {
				if ((str = receiver()) != null) {
					if (str.equals("bye")) {
						sender("再会。下次聊。");
						flag = false;
					} else
						sender("服务器回应：" + str);
				}
			}	
			try {
				out.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			try {
				in.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
	}

}
