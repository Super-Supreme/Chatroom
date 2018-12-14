package com.zkai.chat.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.zkai.chat.dao.UserDao;
import com.zkai.chat.model.User;
import com.zkai.chat.util.DbUtil;
import com.zkai.chat.util.StringUtil;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.SystemColor;

public class LoginFrm extends JFrame {

	private static final long serialVersionUID = -5856503985201891215L;
	private JPanel contentPane;
	private JTextField userNameTxt;
	private JTextField ipTxt;
	private String uName=null;
	private String ip=null;
	private JPasswordField passWordTxt;
	private UserDao userDao =new UserDao();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrm frame = new LoginFrm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginFrm() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginFrm.class.getResource("/com/zkai/chat/img/\u8DA3\u6EE1\u6EE1\u534A\u661F (1).png")));
		setTitle("ZK_Chat");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 885, 704);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u7528\u6237\u767B\u5F55");
		label.setFont(new Font("华文行楷", Font.BOLD, 37));
		label.setIcon(new ImageIcon(LoginFrm.class.getResource("/com/zkai/chat/img/\u8D26\u53F7\u767B\u5F55.png")));
		label.setBounds(316, 37, 265, 129);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u7528\u6237\u540D\uFF1A");
		label_1.setFont(new Font("楷体", Font.PLAIN, 21));
		label_1.setBounds(173, 188, 116, 59);
		contentPane.add(label_1);
		
		userNameTxt = new JTextField();
		userNameTxt.setFont(new Font("宋体", Font.PLAIN, 21));
		userNameTxt.setBounds(293, 188, 342, 58);
		contentPane.add(userNameTxt);
		userNameTxt.setColumns(10);
		
		JLabel lblIp = new JLabel("IP\u5730\u5740\uFF1A");
		lblIp.setFont(new Font("楷体", Font.PLAIN, 21));
		lblIp.setBounds(173, 407, 116, 59);
		contentPane.add(lblIp);
		
		ipTxt = new JTextField();
		ipTxt.setText("127.0.0.1");
		ipTxt.setFont(new Font("宋体", Font.PLAIN, 21));
		ipTxt.setColumns(10);
		ipTxt.setBounds(293, 408, 342, 58);
		contentPane.add(ipTxt);
		
		JLabel label_2 = new JLabel("\u9ED8\u8BA4\u7AEF\u53E3\uFF1A7737");
		label_2.setBounds(14, 13, 156, 58);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("\u5BC6  \u7801\uFF1A");
		label_3.setFont(new Font("楷体", Font.PLAIN, 21));
		label_3.setBounds(173, 298, 116, 59);
		contentPane.add(label_3);
		
		passWordTxt = new JPasswordField();
		passWordTxt.setFont(new Font("宋体", Font.PLAIN, 21));
		passWordTxt.setBounds(293, 299, 342, 58);
		contentPane.add(passWordTxt);
		
		JButton loginBtn = new JButton("\u767B\u5F55");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginEvt(e);
			}
		});
		loginBtn.setIcon(new ImageIcon(LoginFrm.class.getResource("/com/zkai/chat/img/\u7528\u6237.png")));
		loginBtn.setFont(new Font("楷体", Font.PLAIN, 21));
		loginBtn.setBounds(248, 529, 123, 59);
		contentPane.add(loginBtn);
		
		JButton resetBtn = new JButton("\u91CD\u7F6E");
		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset(e);
			}
		});
		resetBtn.setIcon(new ImageIcon(LoginFrm.class.getResource("/com/zkai/chat/img/\u91CD\u7F6E (1).png")));
		resetBtn.setFont(new Font("楷体", Font.PLAIN, 21));
		resetBtn.setBounds(496, 529, 123, 59);
		contentPane.add(resetBtn);
		
		JButton newBtn = new JButton("\u6CE8\u518C\u65B0\u7528\u6237");
		newBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addUserEvt(e);
			}
		});
		newBtn.setIcon(new ImageIcon(LoginFrm.class.getResource("/com/zkai/chat/img/\u6CE8\u518C\u767B\u5F55.png")));
		newBtn.setBounds(714, 609, 139, 35);
		contentPane.add(newBtn);
		
		JLabel lblLongTimeOn = new JLabel("Long time on see !");
		lblLongTimeOn.setForeground(Color.BLUE);
		lblLongTimeOn.setFont(new Font("Axure Handwriting", Font.ITALIC, 21));
		lblLongTimeOn.setBounds(618, 13, 220, 75);
		contentPane.add(lblLongTimeOn);
		
		JLabel lblzkai = new JLabel("\u2014\u2014ZKai");
		lblzkai.setFont(new Font("Axure Handwriting", Font.PLAIN, 17));
		lblzkai.setForeground(Color.BLUE);
		lblzkai.setBounds(769, 71, 98, 31);
		contentPane.add(lblzkai);
		this.setLocationRelativeTo(null);
	}
	
	//登录按钮事件
	private void loginEvt(ActionEvent e) {
		uName=userNameTxt.getText();
		String pwd=new String(passWordTxt.getPassword());
		ip=ipTxt.getText();
		if(StringUtil.isEmpty(uName)) {
			JOptionPane.showMessageDialog(null, "用户名不能为空哦~");
			return;
		}
		if(StringUtil.isEmpty(pwd)) {
			JOptionPane.showMessageDialog(null, "不输密码你怎么进入啊~");
			return;
		}
		if(StringUtil.isEmpty(ip)) {
			JOptionPane.showMessageDialog(null, "不输地址你和谁聊啊~");
			return;
		}
		User user=new User(uName, pwd);
		Connection conn=DbUtil.getConnect();
		User newUser = userDao.login(conn, user);
		if(newUser!=null) {
			
			connServer();
			
		}else {
			JOptionPane.showMessageDialog(null, "很遗憾，用户名和密码不正确");
			return;
		}
	}

	//登陆验证成功后连接服务器
	private void connServer() {
		try {
			Socket client=new Socket(ip, 7737);
			//向服务器发送信息
			PrintWriter pWrite=new PrintWriter(client.getOutputStream(),true);
			//发送要登录的客户名 	格式：0#登录名
			pWrite.println("0#"+uName);
			//接受服务器端发来的信息
			BufferedReader buffRead=new BufferedReader(new InputStreamReader(client.getInputStream()));
			//接受登陆结果信息	格式：4#结果信息
			String row=buffRead.readLine();
			String result=row.substring(2);
			if(!"OK".equalsIgnoreCase(result)) {
				JOptionPane.showMessageDialog(this, result);
				return;
			}
			
			//销毁登陆界面，转换到用户列表界面
			dispose();
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						//转到聊天主页面 	 并将本次登录的用户名，Socket传过去
						new ChatMainFrm(uName,client).setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "服务器端还没开呢，快去让ZKai打开。。。");
		}
	}

	//重置按钮事件
	private void reset(ActionEvent evt) {
		userNameTxt.setText("");
		passWordTxt.setText("");
		ipTxt.setText("");
	}

	//注册新用户按钮事件
	private void addUserEvt(ActionEvent e) {
		this.setVisible(false);
		new AddUserFrm().setVisible(true);;
		
	}
}
