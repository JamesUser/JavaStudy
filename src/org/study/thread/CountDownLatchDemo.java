package org.study.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo implements Runnable{
	
	static CountDownLatch latch = new CountDownLatch(1000);
	
	static List<Integer> list = new ArrayList<Integer>();
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService threadPool = Executors.newCachedThreadPool();
		for (int i = 0; i < 10000; i++) {
			threadPool.execute(new CountDownLatchDemo());
		}
		latch.await();
		System.out.println(list.size());
	}
	

	@Override
	public void run() {
		list.add(1);
		latch.countDown();
	}
	
}
