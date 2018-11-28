package com.dyxy.chat05;

/*
 * ����˱�����ͻ������ӿ������߳�
 * Ŀ�꣺ʵ��˽�ģ�Լ��˽�ĵ����ݸ�ʽ�� @xxx:msg
 */
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerConnect implements Runnable {
	private DataInputStream din = null;
	private DataOutputStream dout = null;
	private Socket client = null;
	private boolean isRunning;
	private String name;

	public ServerConnect(Socket client) {
		this.client = client;
		try {
			din = new DataInputStream(client.getInputStream());
			dout = new DataOutputStream(client.getOutputStream());
			this.isRunning = true;
			name = receive();
			this.send("��ӭ������");
			this.sendOthers(this.name + "������Super-Supreme��������", true);
		} catch (Exception e) {
			System.out.println("-------client----------");
			close(din, dout, client);
			isRunning = false;
		}
	}

	// ������Ϣ
	private String receive() {
		String msg = null;
		try {
			msg = din.readUTF();
		} catch (IOException e) {
			close(din, dout, client);
			isRunning = false;
			System.out.println("---------receive----------");
		}
		return msg;
	}

	// ������Ϣ
	private void send(String msg) {
		try {
			dout.writeUTF(msg);
			dout.flush();
		} catch (IOException e) {
			close(din, dout, client);
			isRunning = false;
			System.out.println("-------send--------");
		}
	}

	/*
	 * Ⱥ�ģ���ȡҪ������Ϣ���͸������ͻ��� 
	 * ˽�ģ�Լ�����ݸ�ʽ�� @xxx:msg
	 * 
	 */
	private void sendOthers(String msg, boolean isSystem) {

		if (msg.startsWith("@") && msg.contains(":")) {// ˽��
			String sendName = msg.substring(1, msg.indexOf(":"));// ��ȡ˽������
			msg = msg.substring(msg.indexOf(":") + 1);// ��ȡ��Ϣ
			for (ServerConnect other : ServerChat.allClient) {
				if (other.name.equalsIgnoreCase(sendName)) {
					other.send(this.name + "͵͵�ض���˵��" + msg);
					break;
				}
			}

		} else {// Ⱥ��
			for (ServerConnect other : ServerChat.allClient) {
				if (other == this) {
					continue;
				}
				if (!isSystem) {
					other.send(this.name + "��������˵��" + msg);
				} else {
					other.send(msg);
				}
			}
		}
	}

	// �ͷ���Դ
	public static void close(Closeable... target) {
		for (Closeable shut : target) {
			try {
				if (null != target) {
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
			String msg = receive();
			if (msg != null) {
				if ("bye".equals(msg)) {
					isRunning = false;
					ServerChat.allClient.remove(this);
					sendOthers(this.name + "�뿪�������ҡ�", true);
				} else {
					sendOthers(msg, false);
				}
			} else {
				send("��������" + "���ܿͻ��˵���Ϣ��������");
			}
		}
		close(din, dout, client);
	}

}
