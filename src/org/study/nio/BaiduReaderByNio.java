package org.study.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class BaiduReaderByNio {
	
	private Selector selector;
	
	public void initConnect(String address,int port) throws IOException{
		SocketChannel channel = SocketChannel.open();
		channel.configureBlocking(false);
		this.selector = Selector.open();
		channel.connect(new InetSocketAddress(address, port));
		channel.register(selector, SelectionKey.OP_CONNECT);
	}
	
	
	public void connect() throws IOException{
		while(true) {
			selector.select();
			
			Iterator<SelectionKey> it = selector.selectedKeys().iterator();
			while(it.hasNext()) {
				SelectionKey key = it.next();
				
				it.remove();
				
				if(key.isConnectable()) {
					SocketChannel channel = (SocketChannel)key.channel();
					
					if(channel.isConnectionPending()) {
						channel.finishConnect();
					}
					
					channel.write(ByteBuffer.wrap(new String("GET " + "/ HTTP/1.1" + "\r\n\r\n").getBytes("GBK")));
					
					channel.register(selector, SelectionKey.OP_READ);
				}else if(key.isReadable()) {
					read(key);
				}
			}
		}
	}
	
	public void read(SelectionKey key) throws IOException{
		SocketChannel channel = (SocketChannel)key.channel();
		
		channel.configureBlocking(false);
		
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		
		while(channel.read(buffer) != -1) {
			System.out.println(new String(buffer.array()));
			buffer.clear();
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		BaiduReaderByNio baiduReader = new BaiduReaderByNio();
		
		baiduReader.initConnect("www.baidu.com", 80);
		
		baiduReader.connect();
	}
}
