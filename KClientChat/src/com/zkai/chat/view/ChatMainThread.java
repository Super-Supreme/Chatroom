package com.zkai.chat.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import com.zkai.chat.util.ChatUtils;

public class ChatMainThread implements Runnable {
	private Socket socket;
	private String userName;

	public ChatMainThread(Socket socket, String userName) {
		super();
		this.socket = socket;
		this.userName = userName;
	}

	@Override
	public void run() {
		try {
			// 1.���շ���������Ϣ
			BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			while (true) {
				String row = in.readLine();
				String strFlag = row.substring(0, 1);
				if ("3".equals(strFlag)) {// ������Ϣ����ʽ��3#����������@������IP@��Ϣ����@����ʱ��
					String receiveMsg = row.substring(2);// msg = "����������@������IP@��Ϣ����@����ʱ��"
					// �ڴ˴�������ʾ��Ϣ
					String showMsg = ChatUtils.makeMsg(1, receiveMsg);
					ChatUtils.append(ChatMainFrm.txtShow, showMsg);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
