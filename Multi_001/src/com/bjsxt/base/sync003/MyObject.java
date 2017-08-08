package com.bjsxt.base.sync003;

/**
 * 对象锁的同步和异步问题
 * @author alienware
 * 同步:synchronized
 *  同步额概念就是共享,我们要牢牢记住"共享"这两个字,如果不是共享的资源,
 *  就没有必要进行同步
 *  
 * 异步:asynchronized
 *  异步的概念就是独立,相互之间不受到人和制约.就想想我们学习http的时候,在页面发起的Ajax请求,
 *  我们还可以继续浏览或操作页面的内容,需要满足两个特性:
 *              原子性(同步)
 *              可见性
 *  同步的目的是为了线程安全,其实对于线程安全来说,需要满足两个特性:
 *  @author zhanbq
 */
public class MyObject {

	public synchronized void method1(){
		try {
			System.out.println(Thread.currentThread().getName());
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/** synchronized */
	/*
	 * 当前只有一个实例对象,获得了mo对象的锁,第二个线程想去调佣m2 需要排队
	 */
	public synchronized void method2(){
			System.out.println(Thread.currentThread().getName());
	}
	
	public static void main(String[] args) {
		
		final MyObject mo = new MyObject();
		
		/**
		 * 分析：
		 * t1线程先持有object对象的Lock锁，t2线程可以以异步的方式调用对象中的非synchronized修饰的方法
		 * t1线程先持有object对象的Lock锁，t2线程如果在这个时候调用对象中的同步（synchronized）方法则需等待，也就是同步
		 */
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				mo.method1();
			}
		},"t1");
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				mo.method2();
			}
		},"t2");
		
		t1.start();
		t2.start();
		
	}
	
}
