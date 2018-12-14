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
			// 1.接收服务器端信息
			BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			while (true) {
				String row = in.readLine();
				String strFlag = row.substring(0, 1);
				if ("3".equals(strFlag)) {// 聊天信息，格式：3#发送者名称@发送者IP@消息内容@发送时间
					String receiveMsg = row.substring(2);// msg = "发送者名称@发送者IP@消息内容@发送时间"
					// 在此窗体上显示信息
					String showMsg = ChatUtils.makeMsg(1, receiveMsg);
					ChatUtils.append(ChatMainFrm.txtShow, showMsg);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
