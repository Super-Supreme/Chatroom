package com.dyxy.chat01;

/*
 * 客户端
 * 多个客户端连接一个服务器，并可以同时和服务器对话
 * 
 */
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	public static void main(String[] args) {
		Socket client=null;
		BufferedReader input=null;
		DataInputStream din=null;
		DataOutputStream dout=null;
		boolean isRunning=true;
		try {
			System.out.println("客户端已启动。。。。");
			client=new Socket("localhost", 7777);
			
			input=new BufferedReader(new InputStreamReader(System.in));
			din=new DataInputStream(client.getInputStream());
			dout=new DataOutputStream(client.getOutputStream());
			
			while(isRunning) {
				String msg=input.readLine();
				dout.writeUTF(msg);
				dout.flush();
				if("bye".equals(msg)) {
					isRunning=false;
				}
				String inStr=null;
				if((inStr=din.readUTF())!=null) {
					System.out.println(inStr);
				}
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(dout!=null) {
				try {
					dout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(din!=null) {
				try {
					din.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(client!=null) {
				try {
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
}
