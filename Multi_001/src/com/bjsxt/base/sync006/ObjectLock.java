package com.bjsxt.base.sync006;

/**
 * 使用synchronized代码块加锁,比较灵活
 * @author alienware
 * 使用synchronized生命的方法在某些情况下是有弊端的,
 * 比如A线程调同步的方法执行一个很长时间的任务,那么B线程就必须
 * 等待比较长的时间才能执行,这样的情况下可以使用synchronized代码块去优化代码执行时间,
 * 也就是通常所说的减小锁的粒度.
 */
public class ObjectLock {

	public void method1(){
		synchronized (this) {	//对象锁
			try {
				System.out.println("do method1..");
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void method2(){		//类锁
		synchronized (ObjectLock.class) {
			try {
				System.out.println("do method2..");
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private Object lock = new Object();
	public void method3(){		//任何对象锁
		synchronized (lock) {
			try {
				System.out.println("do method3..");
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static void main(String[] args) {
		
		final ObjectLock objLock = new ObjectLock();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				objLock.method1();
			}
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				objLock.method2();
			}
		});
		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				objLock.method3();
			}
		});
		
		t1.start();
		t2.start();
		t3.start();
		
		
	}
	
}
