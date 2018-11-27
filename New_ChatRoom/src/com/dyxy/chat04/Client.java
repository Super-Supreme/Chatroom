package com.dyxy.chat04;
import java.io.BufferedReader;
import java.io.InputStreamReader;
/*
 * 客户端
 * 加入容器，实现群聊
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
