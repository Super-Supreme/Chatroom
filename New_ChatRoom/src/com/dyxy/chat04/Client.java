package com.dyxy.chat04;
import java.io.BufferedReader;
import java.io.InputStreamReader;
/*
 * �ͻ���
 * ����������ʵ��Ⱥ��
 * 
 */
import java.net.Socket;

public class Client {
	public static void main(String[] args) {
		Socket client=null;
		try {
			System.out.println("�ͻ�������������������");
			System.out.println("�������û�����");
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
