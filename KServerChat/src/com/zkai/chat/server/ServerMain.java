package com.zkai.chat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.zkai.chat.model.ClientModel;

public class ServerMain {
	public static List<ClientModel> userList = new ArrayList<>();
	public static void main(String[] args) {
		ServerSocket server = null;
		try {
			server = new ServerSocket(7737);
			System.out.println("���������������ȴ����ӡ�����");
			goOn:
			while (true) {
				Socket client = server.accept();
				//���ܿͻ��˷�������Ϣ
				BufferedReader buffRead = new BufferedReader(
						new InputStreamReader(client.getInputStream()));
				// ���ܵ�½�û��� ��ʽ��0#��¼��
				String row = buffRead.readLine();
				String loginName = row.substring(2);
				
				//��ͻ��˷�����Ϣ
				PrintWriter pWrite = new PrintWriter(client.getOutputStream(),true);
				//��½��֤ 	
				//�ж��Ƿ���ͬһip����ͬ�û�����¼
				for(ClientModel other:userList) {
					String otherName=other.getLoginName();
					if(loginName.equals(otherName)) {
						//��ͻ��˷�����֤��Ϣ���	��ʽ��4#��֤�����Ϣ
						pWrite.println("4#���ǳƵ��û��ѵ�¼���뻻���ǳ����µ�½����û���˻�������ע��~");
						continue goOn;
					}
				}
				
				//������������������½�ɹ�
				pWrite.println("4#OK");
				System.out.println(loginName+"	�����˷�����������");
				//����½�ɹ����û�������ӵ�ClientModel�У�Ȼ���װ��������
				ClientModel loginUser = new ClientModel(loginName, client);
				userList.add(loginUser);
				
				new Thread(new ServerThread(loginName, client)).start();
				
			}
		} catch (Exception e) {
			System.out.println("�������쳣�رա�����");
			e.printStackTrace();
			if(server!=null) {
				try {
					server.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
		}
	}
}
