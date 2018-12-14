package com.zkai.chat.util;

import java.io.IOException;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.html.HTMLDocument;

public class ChatUtils {
	public static String getText(String row1,String row2){
		return "<html><body>" + row1 + "<br/><br/>" + row2 + "<br/>&nbsp;</body></html>";
	}
	
	public static String makeMsg(int location,String msg){
		msg = msg.replaceAll("<*>", "<br/>");
		//msg格式：当前用户名@当前用户ip@msg@发送时间
		String[] msgArray = msg.split("@");
		String fromUser = msgArray[0];
		String fromIp = msgArray[1];
		String msgContent = msgArray[2];
		String time = msgArray[3];
		
		StringBuilder bld = new StringBuilder();
		String ct = location == 1 ? "left":"right";
		String color = location == 1 ? "red" : "blue";
		bld.append("<p align = '" + ct + "'><font color = 'blue' size = '21px'><b>【").append(fromUser).append("】   ")
		.append(time).append("</b></font><br>")
		.append("  <font color = '" + color + "' size = '21px'>").append(msgContent)
		.append("</font><br></p>");
		
		return bld.toString();
	}
	public static void append(JTextPane textPane,String msg){
		HTMLDocument dom = (HTMLDocument) textPane.getDocument();
		Element root = dom.getDefaultRootElement();
		Element body = root.getElement(0);
		Element el = body.getElement(body.getElementCount() - 1);
		try {
			dom.insertAfterEnd(el, msg);
			//设置滚动条滚动到末尾
			textPane.setCaretPosition(dom.getLength());
			
		} catch (BadLocationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
