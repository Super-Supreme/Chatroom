package com.dyxy.chat02;
/*
 * 客户端
 * 多个客户端连接一个服务器，并可以同时和服务器对话
 * 
 */
import java.net.Socket;

public class Client {
	public static void main(String[] args) {
		Socket client=null;
		try {
			System.out.println("客户端已启动。。。。");
			client=new Socket("localhost", 7777);
			
			new Thread(new Client_Send(client)).start();
			new Thread(new Client_receive(client)).start();
		}catch(Exception ee) {
			try {
				client.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
