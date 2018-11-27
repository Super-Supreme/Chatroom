package com.dyxy.chat01;
/*
 * 服务器端
 * 多个客户端连接一个服务器，并可以同时和服务器对话
 * 
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
	public static void main(String[] args) {
		ServerSocket server = null;
		Socket client = null;
		boolean isRunning = true;
		int count=0;
		try {
			server = new ServerSocket(7777);
			System.out.println("服务已启动，等待客户端连接。。。");

			//可以连接多个客户端（若无线程，必须等待第一个连接服务器的客户端结束，第二个连接服务器的客户端才可以和服务器对答。）
			while (isRunning) {
				client = server.accept();
				System.out.println((++count)+"个客户端已连接");
				//开启线程每个连接上的客户端可以同时和服务器对答
				new Thread(new ServerConnect(client)).start();
				
			}

		} catch (IOException e) {
			System.out.println("服务器错误，请重新启动。。。");
			isRunning = false;
		}
	}
}
