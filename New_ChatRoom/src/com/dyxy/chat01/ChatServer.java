package com.dyxy.chat01;
/*
 * ��������
 * ����ͻ�������һ����������������ͬʱ�ͷ������Ի�
 * 
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
	public static void main(String[] args) {
		ServerSocket server = null;
		Socket client = null;
		boolean isRunning = true;
		int count=0;
		try {
			server = new ServerSocket(7777);
			System.out.println("�������������ȴ��ͻ������ӡ�����");

			//�������Ӷ���ͻ��ˣ������̣߳�����ȴ���һ�����ӷ������Ŀͻ��˽������ڶ������ӷ������Ŀͻ��˲ſ��Ժͷ������Դ𡣣�
			while (isRunning) {
				client = server.accept();
				System.out.println((++count)+"���ͻ���������");
				//�����߳�ÿ�������ϵĿͻ��˿���ͬʱ�ͷ������Դ�
				new Thread(new ServerConnect(client)).start();
				
			}

		} catch (IOException e) {
			System.out.println("��������������������������");
			isRunning = false;
		}
	}
}
