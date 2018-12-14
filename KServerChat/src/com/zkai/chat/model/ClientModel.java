package com.zkai.chat.model;

import java.net.Socket;

public class ClientModel {
	private String loginName;
	private Socket client;
	
	public ClientModel() {
		super();
	}
	
	public ClientModel(String loginName, Socket client) {
		super();
		this.loginName = loginName;
		this.client = client;
	}

	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public Socket getClient() {
		return client;
	}
	public void setClient(Socket client) {
		this.client = client;
	}
	
}	
