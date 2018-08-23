package org.study.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOClient {
	
	private Selector selector;
	
	public void init(String ip,int port) throws IOException{
		SocketChannel channel = SocketChannel.open();
		channel.configureBlocking(false);
		
		this.selector = Selector.open();
		
		channel.connect(new InetSocketAddress(ip, port));
		
		channel.register(selector, SelectionKey.OP_CONNECT);
	}
	
	public void connect() throws IOException{
		while(true) {
			selector.select();
			
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			while(iterator.hasNext()) {
				SelectionKey key = iterator.next();
				
				iterator.remove();
				
				if(key.isConnectable()) {
					SocketChannel channel = (SocketChannel)key.channel();
					
					if(channel.isConnectionPending()) {
						channel.finishConnect();
					}
					
					channel.configureBlocking(false);
					
					channel.write(ByteBuffer.wrap(new String("向服务端发送了一条消息").getBytes("GBK")));
					
					channel.register(selector, SelectionKey.OP_READ);
				}else if(key.isReadable()) {
					read(key);
				}
			}
		}
	}
	
	
	public void read(SelectionKey key) throws IOException{
		SocketChannel channel = (SocketChannel)key.channel();
		
		ByteBuffer buffer = ByteBuffer.allocate(512);
		
		channel.read(buffer);
		
		byte[] bytes = buffer.array();
		
		String message = new String(bytes).trim();
		
		System.out.println("客户端接收的消息:" + message);
		
		ByteBuffer outBuffer = ByteBuffer.wrap(message.getBytes("GBK"));
		
		channel.write(outBuffer);
	}
	
	public static void main(String[] args) throws IOException {
		NIOClient client = new NIOClient();
		client.init("localhost", 8000);
		client.connect();
	}
}
