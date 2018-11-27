package com.dyxy.chat01;
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
	private boolean flag;
	public ServerConnect(Socket client) {
		this.client=client;
		try {
			din = new DataInputStream(client.getInputStream());
			dout = new DataOutputStream(client.getOutputStream());
			flag=true;
		} catch (IOException e) {
			System.out.println("-------client----------");
			close(din,dout,client);
		}
		
	}
	//接受消息
	private String receive() {
		String msg="";
		try {
			msg=din.readUTF();
		} catch (IOException e) {
			System.out.println("---------receive----------");
			close(din,dout,client);
			flag=false;
		}
		return msg;
	}
	
	//发送消息
	private void send(String msg) {
		try {
			dout.writeUTF(msg);
			dout.flush();
		} catch (IOException e) {
			System.out.println("-------send--------");
			close(din,dout,client);
			flag=false;
		}
	}
	//释放资源
	private void close(Closeable... target) {
		for(Closeable shut:target) {
			try {
				if(null!=target) {
					shut.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void run() {
		String msg="";
		while (flag) {			
			if ((msg=receive())!=null) {
				if ("bye".equals(msg)) {
					send("服务器回应：" + "再会，下次聊");
					flag = false;
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
