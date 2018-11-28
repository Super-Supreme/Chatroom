package com.dyxy.chat05;
import java.io.BufferedReader;
import java.io.InputStreamReader;
/*
 * 客户端
 * 目标：实现私聊，约定私聊的数据格式： @xxx:msg
 * 
 */
import java.net.Socket;

public class Client {
	public static void main(String[] args) {
		Socket client=null;
		try {
			System.out.println("客户端正在启动。。。。");
			System.out.println("请输入用户名：");
			BufferedReader input_name=new BufferedReader(new InputStreamReader(System.in));
			String name=input_name.readLine();
			client=new Socket("localhost", 7777);
			//线程实现客户端之间可以发的时候收，收的时候发
			new Thread(new Client_Send(client,name)).start();
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
