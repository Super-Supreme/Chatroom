package com.dyxy.chat02;
/*
 * 服务端被多个客户端连接开启的线程
 * 
 */
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerConnect implements Runnable{
	private DataInputStream din = null;
	private DataOutputStream dout = null;
	private Socket client=null;
	private boolean isRunning;
	public ServerConnect(Socket client) {
		this.client=client;
		try {
			din = new DataInputStream(client.getInputStream());
			dout = new DataOutputStream(client.getOutputStream());
			this.isRunning=true;
		} catch (Exception e) {
			System.out.println("-------client----------");
			close(din,dout,client);
			isRunning=false;
		}
	}
	//接受消息
	private String receive() {
		String msg=null;
		try {
			msg=din.readUTF();
		} catch (IOException e) {
			close(din,dout,client);
			isRunning=false;
			System.out.println("---------receive----------");
		}
		return msg;
	}
	
	//发送消息
	private void send(String msg) {
		try {
			dout.writeUTF(msg);
			dout.flush();
		} catch (IOException e) {
			close(din,dout,client);
			isRunning=false;
			System.out.println("-------send--------");
		}
	}
	//释放资源
	public static void close(Closeable... target) {
		for(Closeable shut:target) {
			try {
				if(null!=target) {
					shut.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Don't close!!");
			}
		}
	}
	@Override
	public void run() {
		while (isRunning) {	
			String msg=receive();
			if (msg!=null) {
				if ("bye".equals(msg)) {
					send("服务器回应：再会，下次聊");
					isRunning = false;
				} else {
					send("服务器回应：" + msg);
				}
			}else {
				send("服务器："+"接受客户端的消息发生错误！");
			}
		}
		close(din,dout,client);
	}

}
