package com.zkai.chat.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import com.zkai.chat.util.ChatUtils;
import javax.swing.JTextPane;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenuBar;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.SystemColor;

public class ChatMainFrm extends JFrame {

	private JPanel contentPane;
	private JTextField txtMsg;
	public static JTextPane txtShow;
	private String uName;
	private Socket socket;

	public ChatMainFrm(String uName, Socket socket) {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				isClose(e);
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(ChatMainFrm.class.getResource("/com/zkai/chat/img/\u804A\u5929.png")));
		this.uName=uName;
		this.socket=socket;
		setResizable(false);
		setTitle("【  "+uName+"  】正在【Super-Supreme】的聊天室");
		setBounds(100, 100, 1027, 700);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		BorderLayout borderLayout =new BorderLayout();
		borderLayout.setHgap(10);
		contentPane.setLayout(borderLayout);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 50));
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1011, 37);
		panel.add(menuBar);
		
		JMenu mnzkai = new JMenu("\u5173\u4E8EZKai");
		menuBar.add(mnzkai);
		mnzkai.setFont(new Font("楷体", Font.PLAIN, 17));
		mnzkai.setIcon(new ImageIcon(ChatMainFrm.class.getResource("/com/zkai/chat/img/class.png")));
		
		JMenuItem menuItem = new JMenuItem("\u70B9\u6211\u4E00\u4E0B");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aboutMe();
			}
		});
		menuItem.setFont(new Font("楷体", Font.PLAIN, 17));
		menuItem.setIcon(new ImageIcon(ChatMainFrm.class.getResource("/com/zkai/chat/img/send.png")));
		mnzkai.add(menuItem);
		
		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(270, 10));
		contentPane.add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ChatMainFrm.class.getResource("/com/zkai/chat/img/QQ\u56FE\u724720181214091851.jpg")));
		label.setPreferredSize(new Dimension(50, 18));
		panel_1.add(label, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(10, 170));
		panel_2.add(panel_3, BorderLayout.SOUTH);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_4.setPreferredSize(new Dimension(10, 40));
		panel_3.add(panel_4, BorderLayout.NORTH);
		
		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5, BorderLayout.CENTER);
		
		txtMsg = new JTextField();
		txtMsg.setFont(new Font("楷体", Font.PLAIN, 22));
		txtMsg.setColumns(10);
		
		JButton btnSend = new JButton("\u53D1\u9001");
		btnSend.setIcon(new ImageIcon(ChatMainFrm.class.getResource("/com/zkai/chat/img/send (1).png")));
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendMsgEvt();
			}
		});
		btnSend.setFont(new Font("楷体", Font.PLAIN, 22));
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_5.createSequentialGroup()
					.addContainerGap(34, Short.MAX_VALUE)
					.addComponent(txtMsg, GroupLayout.PREFERRED_SIZE, 539, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSend, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
					.addGap(32))
		);
		gl_panel_5.setVerticalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_5.createSequentialGroup()
					.addContainerGap(44, Short.MAX_VALUE)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSend, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtMsg, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
					.addGap(34))
		);
		panel_5.setLayout(gl_panel_5);
		
		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane, BorderLayout.CENTER);
		
		txtShow = new JTextPane();
		txtShow.setEditable(false);
		txtShow.setFont(new Font("楷体", Font.PLAIN, 21));
		txtShow.setContentType("text/html");
		scrollPane.setViewportView(txtShow);
		//启动线程接收服务器发送的信息
		new Thread(new ChatMainThread(this.socket, uName)).start();
	}


	protected void isClose(WindowEvent e) {
		int result = JOptionPane.showConfirmDialog(null, "真的要残忍的离开这个大家庭吗~", "挽留中", 
				JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
		if(result==JOptionPane.YES_OPTION) {
			try {
//				System.out.println("点击了确认退出");
				PrintWriter pWriter = new PrintWriter(this.socket.getOutputStream(),true);
				//向服务器发送要退出的用户名		格式：7#用户名
				pWriter.println("7#"+this.uName);
				System.exit(0);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}else {
			JOptionPane.showMessageDialog(null, "我就知道你舍不得<(￣3￣)> ！");
			return;
		}
		
	}


	private void aboutMe() {
		new AboutMe().setVisible(true);
		
	}


	private void sendMsgEvt() {
		//得到输入框信息
		String str = this.txtMsg.getText();
		//封装信息		格式：3#发送者名@发送者IP@发送的消息@发送时间
		StringBuilder msg = new StringBuilder("3#");
		msg.append(this.uName)
		.append("@")
		.append(this.socket.getInetAddress().getHostAddress())
		.append("@")
		.append(str)
		.append("@")
		.append(new SimpleDateFormat("HH:mm:ss").format(new Date()));
		
		try {
			PrintWriter pWrite = new PrintWriter(this.socket.getOutputStream(),true);
			pWrite.println(msg.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//显示自己聊天窗体中
		String showMsg=ChatUtils.makeMsg(2, msg.toString().substring(2));
		ChatUtils.append(txtShow, showMsg);
		
		//清空消息框
		this.txtMsg.setText("");
		this.txtMsg.requestFocus();
	}
}
