package org.study.nio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIoDemo {
	
	public static void main(String[] args) throws IOException {
		File file = new File("E:/test.txt");
		FileOutputStream outputStream = new FileOutputStream(file);
		FileChannel fileChannel = outputStream.getChannel();
		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
		String str = "java nio";
		byteBuffer.put(str.getBytes());
		byteBuffer.flip();
		fileChannel.write(byteBuffer);
		fileChannel.close();
		outputStream.close();
	}
	
}
