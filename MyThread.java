package 奇迹;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public  class MyThread extends Thread {
	private Socket s;
	private InputStream a = null;
	private OutputStream b = null;
	private boolean bconnection = false;//连接服务器是否成功

	public MyThread(Socket s) {
		this.s = s;
		try {
			a = new DataInputStream(s.getInputStream());//读取数据流
			b = new DataOutputStream(s.getOutputStream());//Data实例化输入输出流
			bconnection = true;

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		public void run() {
			
			}
	}
