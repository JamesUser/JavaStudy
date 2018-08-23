package org.study.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOServer {
	
	private Selector selector;
	
	public void init(int port) throws IOException{
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(false);
		serverSocketChannel.bind(new InetSocketAddress(port));				//绑定端口
		this.selector = Selector.open();
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
	}
	
	
	public void listen() throws IOException{
		System.out.println("服务器启动成功");
		while(true) {
			selector.select();
			
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			while(iterator.hasNext()) {
				SelectionKey selectionKey = iterator.next();
				
				iterator.remove();
				
				if(selectionKey.isAcceptable()) {
					ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
					
					SocketChannel socketChannel = channel.accept();
					
					socketChannel.configureBlocking(false);
					
					socketChannel.write(ByteBuffer.wrap(new String("向客户端发送一条消息").getBytes("GBK")));
					
					socketChannel.register(this.selector,SelectionKey.OP_READ);
				}else if(selectionKey.isReadable()) {
					read(selectionKey);
				}
			}
		}
	}
	
	
	
	public void read(SelectionKey key) throws IOException{
		SocketChannel channel = (SocketChannel)key.channel();
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		channel.read(buffer);
		
		byte[] bytes = buffer.array();
		
		String message = new String(bytes).trim();
		
		System.out.println("读取客户端消息：" + message);
		
		ByteBuffer outBuffer = ByteBuffer.wrap(message.getBytes("GBK"));
		
		channel.write(outBuffer);
	}
	
	public static void main(String[] args) throws IOException {
		NIOServer server = new NIOServer();
		server.init(8000);
		server.listen();
	}
}
