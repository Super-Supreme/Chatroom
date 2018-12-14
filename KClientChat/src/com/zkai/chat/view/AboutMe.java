package com.zkai.chat.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.Window.Type;

public class AboutMe extends JFrame {
	private static final long serialVersionUID = -2908632906663349586L;


	public AboutMe() {
		setType(Type.POPUP);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		getContentPane().setBackground(SystemColor.info);
		setBackground(SystemColor.info);
		setTitle("\u5173\u4E8E\u4F5C\u8005");
		setBounds(100, 100, 659, 497);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u5F20\u51EF");
		label.setFont(new Font("·½ÕýÊæÌå", Font.PLAIN, 57));
		label.setBounds(253, 110, 190, 204);
		getContentPane().add(label);
		
		JLabel lblNewLabel = new JLabel("Try your best");
		lblNewLabel.setIcon(new ImageIcon(AboutMe.class.getResource("/com/zkai/chat/img/\u8DA3\u6EE1\u6EE1\u534A\u661F (1).png")));
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Rage Italic", Font.PLAIN, 27));
		lblNewLabel.setBounds(409, 373, 220, 75);
		getContentPane().add(lblNewLabel);

	}
}
