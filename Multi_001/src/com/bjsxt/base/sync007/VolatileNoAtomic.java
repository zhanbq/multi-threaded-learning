package com.bjsxt.base.sync007;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile关键字不具备synchronized关键字的原子性（同步）
 * 可以算上是一个轻量级的synchronized,性能要比synchronized强很多,不会造成阻塞(在很多开源的架构里,
 * 比如netty的底层代码就大量的使用volatile,可见netty的性能是非常不错的.)这里需要注意:一般volatile用于
 * 只针对多个线程可见的变量操作,并不能代替synchronized的同步功能
 * 
 * @author alienware
 *
 */
public class VolatileNoAtomic extends Thread{
	//private static volatile int count;
	private static AtomicInteger count = new AtomicInteger(0); //volatile直觉倍多线程间的可见性,不具备原子特性
	                                                            //但是AtomicInteger具备多线程间的原子特性
	private static void addCount(){
		for (int i = 0; i < 1000; i++) {
			//count++ ;
			count.incrementAndGet();
		}
		System.out.println(count);
	}
	
	public void run(){
		addCount();
	}
	
	public static void main(String[] args) {
		
		VolatileNoAtomic[] arr = new VolatileNoAtomic[100];
		for (int i = 0; i < 10; i++) {
			arr[i] = new VolatileNoAtomic();
		}
		
		for (int i = 0; i < 10; i++) {
			arr[i].start();
		}
	}
	
	
	
	
}
