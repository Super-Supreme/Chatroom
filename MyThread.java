package �漣;


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
	private boolean bconnection = false;//���ӷ������Ƿ�ɹ�

	public MyThread(Socket s) {
		this.s = s;
		try {
			a = new DataInputStream(s.getInputStream());//��ȡ������
			b = new DataOutputStream(s.getOutputStream());//Dataʵ�������������
			bconnection = true;

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		public void run() {
			
			}
	}
