package com.dyxy.chat05;

/*
 * ��������
 * Ŀ�꣺ʵ��˽�ģ�Լ��˽�ĵ����ݸ�ʽ�� @xxx:msg
 */

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

public class ServerChat {
	//��Ӳ�������CopyOnWriteArrayList<>()���洢����client����Ϣ
	public static CopyOnWriteArrayList<ServerConnect> allClient=new CopyOnWriteArrayList<ServerConnect>();
	public static void main(String[] args) {
		ServerSocket server = null;
		Socket client = null;
		boolean isRunning = true;
		int count=0;
		try {
			server = new ServerSocket(7777);
			System.out.println("�������������ȴ��ͻ������ӡ�����");
			while (isRunning) {
				client = server.accept();
				System.out.println((++count)+"���ͻ���������");
				ServerConnect sc=new ServerConnect(client);//dou
				//��ÿ��client���󶪵������У����й���
				allClient.add(sc);
				new Thread(sc).start();
			}
		} catch (Exception e) {
			System.out.println("��������������������������");
			isRunning = false;
		}
	}
}
