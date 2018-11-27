package com.dyxy.chat02;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client_Send implements Runnable {
	private BufferedReader input = null;
	private DataOutputStream dout = null;
	private Socket client = null;
	private boolean isRunning;

	public Client_Send(Socket client) {
		this.client = client;
		try {
			this.isRunning = true;
			input = new BufferedReader(new InputStreamReader(System.in));
			dout = new DataOutputStream(client.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
			ServerConnect.close(input, dout, client);
		}
	}
	//得到控制台输入的数据
	private String getInputStr() {
		try {
			return input.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	//发送数据
	private void send(String msg) {
		try {
			dout.writeUTF(msg);
			dout.flush();
		} catch (IOException e) {
			System.out.println("======send========");
			ServerConnect.close(dout,client);
			isRunning=false;
		}
	}

	@Override
	public void run() {
		while (isRunning) {
			String msg = getInputStr();
			if(msg!=null) {
				send(msg);
			}if("bye".equals(msg)) {
				isRunning=false;
				ServerConnect.close(dout,client);
			}
		}
	}

}
