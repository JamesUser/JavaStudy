package org.study.thread;

public class VolatileDemo {
	
	public static void main(String[] args) throws InterruptedException {
		Count count = new Count();
		count.start();
		Thread.sleep(1000);
		
		
		count.flag = false;
		count.join();
		System.out.println(count.getName() + "¼ÆÊý:" + count.i);
	}
	
	static class Count extends Thread{
		
		 volatile boolean flag = true;
		
		 int i = 0;
		
		@Override
		public void run() {
			while(flag) {
				i++;	
			}
		}
		
	}
}
