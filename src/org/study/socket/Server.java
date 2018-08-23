package org.study.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	public void initServer(int port) throws IOException{
		ServerSocket server = new ServerSocket();
		
		server.bind(new InetSocketAddress("localhost", port));
		
		Socket socket = server.accept();
		
		InputStream inputStream = socket.getInputStream();
		
		byte[] buffer = new byte[1024];
		
		StringBuffer stringBuffer = new StringBuffer();
		
		while(inputStream.read(buffer, 0, buffer.length) != -1) {
			stringBuffer.append(new String(buffer, "GBK"));
		}
		
		System.out.println("服务端接收到的消息:" + stringBuffer.toString());
		
		
		String message = "圣诞节啦时间到了卡死机离开记录立刻就对啦会计考试领导";
		OutputStream outputStream = socket.getOutputStream();
		outputStream.write(message.getBytes("GBK"));
		outputStream.close();
		
		inputStream.close();
		socket.close();
		server.close();
	}
	
	public static void main(String[] args) throws IOException {
		Server server = new Server();
		server.initServer(5000);
	}
}
