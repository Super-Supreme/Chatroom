package com.dyxy.chat02;

/*
 * 服务器端
 * 多个客户端连接一个服务器，并可以同时和服务器对话
 * 
 */

import java.net.ServerSocket;
import java.net.Socket;

public class ServerChat {
	public static void main(String[] args) {
		ServerSocket server = null;
		Socket client = null;
		boolean isRunning = true;
		int count=0;
		try {
			server = new ServerSocket(7777);
			System.out.println("服务已启动，等待客户端连接。。。");

			while (isRunning) {
				client = server.accept();
				System.out.println((++count)+"个客户端已连接");
				new Thread(new ServerConnect(client)).start();
			}
		} catch (Exception e) {
			System.out.println("服务器错误，请重新启动。。。");
			isRunning = false;
		}
	}
}
