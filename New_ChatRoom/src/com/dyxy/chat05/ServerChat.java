package com.dyxy.chat05;

/*
 * 服务器端
 * 目标：实现私聊，约定私聊的数据格式： @xxx:msg
 */

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

public class ServerChat {
	//添加并发容器CopyOnWriteArrayList<>()，存储所有client的信息
	public static CopyOnWriteArrayList<ServerConnect> allClient=new CopyOnWriteArrayList<ServerConnect>();
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
				ServerConnect sc=new ServerConnect(client);//dou
				//将每个client对象丢到容器中，进行管理。
				allClient.add(sc);
				new Thread(sc).start();
			}
		} catch (Exception e) {
			System.out.println("服务器错误，请重新启动。。。");
			isRunning = false;
		}
	}
}
