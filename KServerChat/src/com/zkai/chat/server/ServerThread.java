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
		// 接收客户端的信息：
		try {
			BufferedReader buffRead = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			while (true) {
				// 按行接收信息
				String row = buffRead.readLine();
				// 1.验证标记：
				String strFlag = row.substring(0, 1);
				if ("3".equals(strFlag)) {// 聊天信息，格式：3#发送者名称@发送者IP@消息内容@发送时间
					// 遍历用户的集合,发给全部客户端
					List<ClientModel> others = ServerMain.userList;
					for (int i = 0; i < others.size(); i++) {
						ClientModel other = others.get(i);
						Socket client = other.getClient();
						if(this.loginName.equals(other.getLoginName())) {
							continue;
						}
						PrintWriter out = new PrintWriter(client.getOutputStream(), true);
						out.println(row);// 原信息发送
					}
				}else if("7".equals(strFlag)) {//用户退出聊天室	格式：7#用户名
					//遍历集合，找到该用户
					List<ClientModel> others = ServerMain.userList;
					for(int i = 0;i<others.size();i++) {
						ClientModel other = others.get(i);
						String exitUserName = row.substring(2);
						//如果要退出的用户名与集合中存储的用户名相同，则移除集合中的这个用户
						if(exitUserName.equals(other.getLoginName())) {
							ServerMain.userList.remove(i);
							System.out.println(exitUserName+"	#退出了聊天室#");
						}
					}
					
				}
			}

		} catch (Exception e) {
			
		}

	}
}
