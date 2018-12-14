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
			System.out.println("服务器已启动，等待连接。。。");
			goOn:
			while (true) {
				Socket client = server.accept();
				//接受客户端发来的信息
				BufferedReader buffRead = new BufferedReader(
						new InputStreamReader(client.getInputStream()));
				// 接受登陆用户名 格式：0#登录名
				String row = buffRead.readLine();
				String loginName = row.substring(2);
				
				//向客户端发送信息
				PrintWriter pWrite = new PrintWriter(client.getOutputStream(),true);
				//登陆验证 	
				//判断是否是同一ip且相同用户名登录
				for(ClientModel other:userList) {
					String otherName=other.getLoginName();
					if(loginName.equals(otherName)) {
						//向客户端发送验证信息结果	格式：4#验证结果信息
						pWrite.println("4#此昵称的用户已登录，请换个昵称重新登陆，若没有账户，请先注册~");
						continue goOn;
					}
				}
				
				//如果程序到了这里表明登陆成功
				pWrite.println("4#OK");
				System.out.println(loginName+"	连接了服务器。。。");
				//将登陆成功的用户数据添加到ClientModel中，然后封装到集合中
				ClientModel loginUser = new ClientModel(loginName, client);
				userList.add(loginUser);
				
				new Thread(new ServerThread(loginName, client)).start();
				
			}
		} catch (Exception e) {
			System.out.println("服务器异常关闭。。。");
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
