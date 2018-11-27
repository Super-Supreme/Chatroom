package com.dyxy.chat02;
/*
 * ����˱�����ͻ������ӿ������߳�
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
	//������Ϣ
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
	
	//������Ϣ
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
	//�ͷ���Դ
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
					send("��������Ӧ���ٻᣬ�´���");
					isRunning = false;
				} else {
					send("��������Ӧ��" + msg);
				}
			}else {
				send("��������"+"���ܿͻ��˵���Ϣ��������");
			}
		}
		close(din,dout,client);
	}

}
