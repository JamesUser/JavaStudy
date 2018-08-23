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
		
		System.out.println("����˽��յ�����Ϣ:" + stringBuffer.toString());
		
		
		String message = "ʥ������ʱ�䵽�˿������뿪��¼���̾Ͷ�����ƿ����쵼";
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
