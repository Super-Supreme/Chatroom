package com.dyxy.chat02;
/*
 * �ͻ���
 * ����ͻ�������һ����������������ͬʱ�ͷ������Ի�
 * 
 */
import java.net.Socket;

public class Client {
	public static void main(String[] args) {
		Socket client=null;
		try {
			System.out.println("�ͻ�����������������");
			client=new Socket("localhost", 7777);
			
			new Thread(new Client_Send(client)).start();
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
