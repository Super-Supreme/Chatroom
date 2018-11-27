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
	private void sendOthers(String msg) {
		for(ServerConnect other : ServerChat.allClient) {
			if(other==this) {
				continue;
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
				} else {
					sendOthers(this.name+"对所有人说：" + msg);
				}
			}else {
				send("服务器："+"接受客户端的消息发生错误！");
			}
		}
		close(din,dout,client);
	}

}
