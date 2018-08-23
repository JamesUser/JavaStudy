package org.study.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
	
	public void initClient(String ip,int port) throws IOException{
		Socket socket = new Socket(ip, port);
		
		OutputStream outputStream = socket.getOutputStream();
		
		String message = "���ˢ�Ŀ��㶥�Ƽ�ash�ռ�ܿ�ͺ��յ������Ƽ��յ�������";
		
		outputStream.write(message.getBytes("GBK"));
		socket.shutdownOutput();
		
		InputStream inputStream = socket.getInputStream();
		
		byte[] buffer = new byte[125];
		
		StringBuilder sb = new StringBuilder();
		while(inputStream.read(buffer, 0, buffer.length) != -1) {
			sb.append(new String(buffer, "GBK"));
		}
		
		System.out.println("�ͻ��˽��յ���Ϣ��" + sb);
		
		inputStream.close();
		outputStream.close();
		socket.close();
	}
	
	public static void main(String[] args) throws IOException {
		Client client = new Client();
		client.initClient("localhost", 5000);
	}
}
