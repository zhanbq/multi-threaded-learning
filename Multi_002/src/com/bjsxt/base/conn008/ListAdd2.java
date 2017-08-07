package com.bjsxt.base.conn008;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
/**
 * wait notfiy 方法，wait释放锁，notfiy不释放锁 
 * 缺点
 * 不具有实时性
 * @author alienware
 *
 */
public class ListAdd2 {
	private volatile static List list = new ArrayList();	
	
	public void add(){
		list.add("bjsxt");
	}
	public int size(){
		return list.size();
	}
	
	public static void main(String[] args) {
		
		final ListAdd2 list2 = new ListAdd2();
		
		// 1 实例化出来一个 lock
		// 当使用wait 和 notify 的时候 ， 一定要配合着synchronized关键字去使用
		//final Object lock = new Object();
		
		final CountDownLatch countDownLatch = new CountDownLatch(1); //启动实时通知的作用 参数的作用:代表发几次通知 有两次countwait 就用二否则 无法实现
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					//synchronized (lock) {
						for(int i = 0; i <10; i++){
							list2.add();
							System.out.println("当前线程：" + Thread.currentThread().getName() + "添加了一个元素..");
							Thread.sleep(500);
							if(list2.size() == 5){
								System.out.println("已经发出通知..");
								countDownLatch.countDown(); //notify升级版
								//lock.notify();
							}
						}						
					//}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}, "t1");
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				//synchronized (lock) {
					if(list2.size() != 5){ 
						try {
							//System.out.println("t2进入...");
						  /*
						           如果size 不等于 5 就等待 程序会阻塞在当前行 
						          此用法不具有实时性,因为notify发起通知后,t1线程并没有释放所,必须等到t1线程执行完毕 才会释放锁
						    t2线程才真正收到通知,停止线程
						         这样做虽然实现了多线程间的通信,却不具备实时性
						   */
							//lock.wait(); 
							countDownLatch.await(); //wait 升级版
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println("当前线程：" + Thread.currentThread().getName() + "收到通知线程停止..");
					throw new RuntimeException();
				//}
			}
		}, "t2");	
		/*
		 * 为什么要t2 先启动:
		 * t2先启动 会首先拿到对象所(lock),进入wait等待
		 * t2线程会自动释放所
		 * 这样t1线程拿到所以后,才会执行
		 * 如果先启动t1线程,t1线程不会释放锁,也就是说t2线程不会执行wait(),从而进入线程等待,测试没有意义
		 */
		t2.start();
		t1.start();
		
	}
	
}
