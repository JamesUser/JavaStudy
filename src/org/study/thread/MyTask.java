package org.study.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyTask implements Runnable {
	
	private int i;
	
	public MyTask(int i) {
		this.i = i;
	}
	
	@Override
	public void run() {
		System.out.println(Thread.currentThread() + ":" + i);
	}
	
	
	public static void main(String[] args) {
//		ExecutorService threadPool = Executors.newFixedThreadPool(10);
//		for (int i = 0; i < 100; i++) {
//			MyTask task = new MyTask(i);
//			threadPool.execute(task);
//		}
//		threadPool.shutdown();
		String str1 = new StringBuilder("¼ÆËã»ú").append("Èí¼þ").toString();
        System.out.println(str1.intern() == str1);

        String str2 = new StringBuilder("java").toString();
        System.out.println(str2.intern() == str2);
	}
	
}
