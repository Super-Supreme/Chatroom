package com.dyxy.chat02;

/*
 * ��������
 * ����ͻ�������һ����������������ͬʱ�ͷ������Ի�
 * 
 */

import java.net.ServerSocket;
import java.net.Socket;

public class ServerChat {
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
				new Thread(new ServerConnect(client)).start();
			}
		} catch (Exception e) {
			System.out.println("��������������������������");
			isRunning = false;
		}
	}
}
