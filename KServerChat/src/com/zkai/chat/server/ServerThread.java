package com.zkai.chat.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import com.zkai.chat.model.ClientModel;

public class ServerThread implements Runnable {
	private String loginName;
	private Socket socket;

	public ServerThread(String loginName, Socket socket) {
		super();
		this.loginName = loginName;
		this.socket = socket;
	}

	@Override
	public void run() {
		// ���տͻ��˵���Ϣ��
		try {
			BufferedReader buffRead = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			while (true) {
				// ���н�����Ϣ
				String row = buffRead.readLine();
				// 1.��֤��ǣ�
				String strFlag = row.substring(0, 1);
				if ("3".equals(strFlag)) {// ������Ϣ����ʽ��3#����������@������IP@��Ϣ����@����ʱ��
					// �����û��ļ���,����ȫ���ͻ���
					List<ClientModel> others = ServerMain.userList;
					for (int i = 0; i < others.size(); i++) {
						ClientModel other = others.get(i);
						Socket client = other.getClient();
						if(this.loginName.equals(other.getLoginName())) {
							continue;
						}
						PrintWriter out = new PrintWriter(client.getOutputStream(), true);
						out.println(row);// ԭ��Ϣ����
					}
				}else if("7".equals(strFlag)) {//�û��˳�������	��ʽ��7#�û���
					//�������ϣ��ҵ����û�
					List<ClientModel> others = ServerMain.userList;
					for(int i = 0;i<others.size();i++) {
						ClientModel other = others.get(i);
						String exitUserName = row.substring(2);
						//���Ҫ�˳����û����뼯���д洢���û�����ͬ�����Ƴ������е�����û�
						if(exitUserName.equals(other.getLoginName())) {
							ServerMain.userList.remove(i);
							System.out.println(exitUserName+"	#�˳���������#");
						}
					}
					
				}
			}

		} catch (Exception e) {
			
		}

	}
}
