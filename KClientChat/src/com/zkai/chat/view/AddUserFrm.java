package com.zkai.chat.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.zkai.chat.dao.UserDao;
import com.zkai.chat.model.User;
import com.zkai.chat.util.DbUtil;
import com.zkai.chat.util.StringUtil;

public class AddUserFrm extends JFrame {
	
	private static final long serialVersionUID = -7397480873615684048L;
	private JTextField addUserTxt;
	private JPasswordField addPwdTxt;
	private JPasswordField againPwd;
	private UserDao userDao=new UserDao();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddUserFrm frame = new AddUserFrm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AddUserFrm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		setTitle("\u6CE8\u518C");
		setBounds(100, 100, 638, 538);
		
		JLabel label = new JLabel("\u8BF7\u8BBE\u7F6E\u4F60\u7684\u7528\u6237\u540D\uFF1A");
		label.setFont(new Font("楷体", Font.PLAIN, 21));
		
		JLabel lblNewLabel = new JLabel("\u7528\u6237\u6CE8\u518C");
		lblNewLabel.setFont(new Font("华文行楷", Font.BOLD, 27));
		lblNewLabel.setIcon(new ImageIcon(AddUserFrm.class.getResource("/com/zkai/chat/img/\u4F1A\u5458\u6CE8\u518C.png")));
		
		addUserTxt = new JTextField();
		addUserTxt.setFont(new Font("楷体", Font.PLAIN, 21));
		addUserTxt.setColumns(10);
		
		JLabel label_1 = new JLabel("\u8BF7\u8BBE\u7F6E\u4F60\u7684\u5BC6\u7801\uFF1A");
		label_1.setFont(new Font("楷体", Font.PLAIN, 21));
		
		addPwdTxt = new JPasswordField();
		addPwdTxt.setFont(new Font("Cambria", Font.PLAIN, 21));
		
		JLabel label_2 = new JLabel("\u8BF7\u786E\u8BA4\u4F60\u7684\u5BC6\u7801\uFF1A");
		label_2.setFont(new Font("楷体", Font.PLAIN, 21));
		
		againPwd = new JPasswordField();
		againPwd.setFont(new Font("Cambria", Font.PLAIN, 21));
		
		JButton okBtn = new JButton("\u8FD4\u56DE\u767B\u9646");
		okBtn.setIcon(new ImageIcon(AddUserFrm.class.getResource("/com/zkai/chat/img/ok-sign.png")));
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addUserEvt(e);
			}
		});
		okBtn.setFont(new Font("华文行楷", Font.PLAIN, 23));
		
		JButton resetBtn = new JButton("\u53D6\u6D88\u6CE8\u518C");
		resetBtn.setIcon(new ImageIcon(AddUserFrm.class.getResource("/com/zkai/chat/img/\u91CD\u7F6E.png")));
		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel(e);
			}
		});
		resetBtn.setFont(new Font("华文行楷", Font.PLAIN, 23));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(239, Short.MAX_VALUE)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
					.addGap(218))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(76)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label)
							.addGap(26)
							.addComponent(addUserTxt, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE))
							.addGap(26)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(againPwd, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)
								.addComponent(addPwdTxt, 246, 246, 246))))
					.addContainerGap(95, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(135, Short.MAX_VALUE)
					.addComponent(okBtn)
					.addGap(91)
					.addComponent(resetBtn, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
					.addGap(76))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(37)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
					.addGap(42)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addComponent(addUserTxt, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addComponent(addPwdTxt, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addComponent(againPwd, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
					.addGap(42)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(resetBtn, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
						.addComponent(okBtn, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE))
					.addGap(49))
		);
		getContentPane().setLayout(groupLayout);
		
		//设置界面居中显示
		this.setLocationRelativeTo(null);

	}

	//取消注册按钮事件
	private void cancel(ActionEvent e) {
		//销毁注册窗口，打开登录窗口
		dispose();
		new LoginFrm().setVisible(true);
	}
	
	//确定按钮事件
	private void addUserEvt(ActionEvent e) {
		String addUsername=addUserTxt.getText();
		String addPassword=new String(addPwdTxt.getPassword());
		String againP=new String(againPwd.getPassword());
		if(StringUtil.isEmpty(addUsername)) {
			JOptionPane.showMessageDialog(null, "用户名不能为空哦~");
			return;
		}if(StringUtil.isEmpty(addPassword)){
			JOptionPane.showMessageDialog(null, "要输入密码的");
			return;
		}if(StringUtil.isEmpty(againP)){
			JOptionPane.showMessageDialog(null, "需要确认一下密码哦~");
			return;
		}
		if(!addPassword.equals(againP)) {
			JOptionPane.showMessageDialog(null, "两次密码输入不一致，请重新输入~");
			return;
		}
		User user=new User(addUsername, addPassword);
		Connection conn=DbUtil.getConnect();
		boolean isAdd=userDao.addUser(conn, user);
		if(isAdd) {
			//销毁注册窗口，打开登录窗口
			dispose();
			new LoginFrm().setVisible(true);
		}else {
			JOptionPane.showMessageDialog(null, "很抱歉，内部错误，注册失败");
			return;
		}
		
		
		
	}
}
