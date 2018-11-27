package com.dyxy.chat03;
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
	private String name;
	public ServerConnect(Socket client) {
		this.client=client;
		try {
			din = new DataInputStream(client.getInputStream());
			dout = new DataOutputStream(client.getOutputStream());
			this.isRunning=true;
			name=receive();
			this.send("欢迎回来！");
			this.sendOthers(this.name+"来到了Super-Supreme的聊天室",true);
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
	//群聊
	private void sendOthers(String msg,boolean isSystem) {
		for(ServerConnect other : ServerChat.allClient) {
			if(other==this) {
				continue;
			}if(!isSystem) {
				other.send(this.name+"对所有人说：" + msg);
			}else {
				other.send(msg);
			}
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
					isRunning = false;
					sendOthers(this.name+"离开了聊天室。", true);
				} else {
					sendOthers(msg,false);
				}
			}else {
				send("服务器："+"接受客户端的消息发生错误！");
			}
		}
		close(din,dout,client);
	}

}
